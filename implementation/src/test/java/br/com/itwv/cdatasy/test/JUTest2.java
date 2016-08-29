package br.com.itwv.cdatasy.test;

import br.com.itwv.br.com.itwv.dto.PatientDto;
import br.com.itwv.builders.PatientBuilder;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.ContinuityOfCareDocumentFactory;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.IClinicalDocumentFactory;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IDocumentMapping;
import br.com.itwv.cdatasy.common.business.resources.Resources;
import br.com.itwv.mappings.factory.MappingsFactory;
import junit.framework.TestCase;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by itwv_2 on 13/06/2016.
 */
public class JUTest2 extends TestCase {

    private InputStream file;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        file = Resources.class.getResourceAsStream("/Dados TASY.xlsx");
    }

    @Test
    public void test() throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        final List<PatientDto> patients = new PatientBuilder().build(workbook).getResult();
        workbook.close();
        file.close();

        assertNotNull(patients);
        assertFalse(patients.isEmpty());
        assertEquals(patients.size(), 2);

        for (int i = 0; i < patients.size(); i++) {

            switch (i) {
                case 0: {
                    assertEquals(patients.get(i).getId(), "19283920183");
                    assertNotNull(patients.get(i).getAllergies());
                    assertFalse(patients.get(i).getAllergies().isEmpty());
                    assertEquals(patients.get(i).getAllergies().size(), 2);

                    assertNotNull(patients.get(i).getProblems());
                    assertFalse(patients.get(i).getProblems().isEmpty());
                    assertEquals(patients.get(i).getProblems().size(), 1);
                    break;
                }
                case 1: {
                    assertEquals(patients.get(i).getId(), "43444343433");
                    assertNotNull(patients.get(i).getAllergies());
                    assertFalse(patients.get(i).getAllergies().isEmpty());
                    assertEquals(patients.get(i).getAllergies().size(), 1);
                    break;
                }
            }
        }

        for (final PatientDto patientDto : patients) {
            ContinuityOfCareDocumentFactory.getInstance().createClinicalDocumentFactory(IClinicalDocumentFactory.x_FactoryLoadTypes.DEFAULT, null);
            List<ClinicalDocument> docList = new ArrayList<ClinicalDocument>();
            docList.add(ContinuityOfCareDocumentFactory.getClinicalDocumentInstance());

            MappingsFactory.getInstance().createEntityMappingsFactory();
            MappingsFactory.documentMappingFacade.mapDocumentSections(docList.get(0), IDocumentMapping.x_DocDocumentSectionType.PROPERTIES);
            MappingsFactory.documentMappingFacade.mapDocumentSections(docList.get(0), IDocumentMapping.x_DocDocumentSectionType.AUTHOR);
            MappingsFactory.documentMappingFacade.mapDocumentSections(docList.get(0), IDocumentMapping.x_DocDocumentSectionType.CUSTODIAN);
            MappingsFactory.patientMappingFacade.mapPatientSections(docList.get(0), patientDto);
            if (!patientDto.getAllergies().isEmpty()) {
                MappingsFactory.clinicalMappingFacade.mapClinicalSections(docList, null, IClinicalMapping.x_DocClinicalSectionType.ALERTS, patientDto.getAllergies());
            }
            if (!patientDto.getProblems().isEmpty()) {
                MappingsFactory.clinicalMappingFacade.mapClinicalSections(docList, null, IClinicalMapping.x_DocClinicalSectionType.PROBLEMS, patientDto.getProblems());
            }
            ContinuityOfCareDocumentFactory.toFile(patientDto.getId() + ".xml");
        }
    }
}
