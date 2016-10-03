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
import java.util.Arrays;
import java.util.List;

/**
 * Created by itwv_2 on 13/06/2016.
 */
public class JUTest1 extends TestCase {

    private InputStream file;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        file = Resources.class.getResourceAsStream("/Dados TASY.xlsx");
        assertNotNull(file);
    }

    @Test
    public void test() throws Exception {

        System.out.println("----------> Loading excel file...");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        assertNotNull(workbook);
        System.out.println("----------> Starting building the dto object instances...");
        final List<PatientDto> patients = new PatientBuilder().build(workbook).getResult();
        workbook.close();
        file.close();

        assertNotNull(patients);
        assertFalse(patients.isEmpty());

        System.out.println("\t----------> Starting generating the clinical documents...");

        for (final PatientDto patientDto : patients) {

            if (patientDto.getLaboratoryResults().size() > 0 || patientDto.getPlanOfCares().size() > 0 || patientDto.getProblems().size() > 0 || patientDto.getMedications().size() > 0 || patientDto.getAllergies().size() > 0 || patientDto.getEncounters().size() > 0 || patientDto.getFamilyHistory().size() > 0) {

                ContinuityOfCareDocumentFactory.getInstance().createClinicalDocumentFactory(IClinicalDocumentFactory.x_FactoryLoadTypes.DEFAULT, null);
                List<ClinicalDocument> docList = new ArrayList<ClinicalDocument>();
                docList.add(ContinuityOfCareDocumentFactory.getClinicalDocumentInstance());

                MappingsFactory.getInstance().createEntityMappingsFactory();
                MappingsFactory.documentMappingFacade.mapDocumentSections(docList.get(0), IDocumentMapping.x_DocDocumentSectionType.PROPERTIES, null);
                MappingsFactory.documentMappingFacade.mapDocumentSections(docList.get(0), IDocumentMapping.x_DocDocumentSectionType.AUTHOR, Arrays.asList(patientDto.getAuthor()));
                MappingsFactory.documentMappingFacade.mapDocumentSections(docList.get(0), IDocumentMapping.x_DocDocumentSectionType.CUSTODIAN, Arrays.asList(patientDto.getAuthor()));
                MappingsFactory.patientMappingFacade.mapPatientSections(docList.get(0), patientDto);

                if (!patientDto.getMedications().isEmpty()) {
                    MappingsFactory.clinicalMappingFacade.mapClinicalSections(docList, null, IClinicalMapping.x_DocClinicalSectionType.MEDICATIONS, patientDto.getMedications());
                }
                if (!patientDto.getAllergies().isEmpty()) {
                    MappingsFactory.clinicalMappingFacade.mapClinicalSections(docList, null, IClinicalMapping.x_DocClinicalSectionType.ALERTS, patientDto.getAllergies());
                }
                if (!patientDto.getProblems().isEmpty()) {
                    MappingsFactory.clinicalMappingFacade.mapClinicalSections(docList, null, IClinicalMapping.x_DocClinicalSectionType.PROBLEMS, patientDto.getProblems());
                }
                if (!patientDto.getPlanOfCares().isEmpty()) {
                    MappingsFactory.clinicalMappingFacade.mapClinicalSections(docList, null, IClinicalMapping.x_DocClinicalSectionType.PLAN_OF_CARE, patientDto.getPlanOfCares());
                }
                if (!patientDto.getEncounters().isEmpty()) {
                    MappingsFactory.clinicalMappingFacade.mapClinicalSections(docList, null, IClinicalMapping.x_DocClinicalSectionType.ENCOUNTERS, patientDto.getEncounters());
                }
                if (!patientDto.getFamilyHistory().isEmpty()) {
                    MappingsFactory.clinicalMappingFacade.mapClinicalSections(docList, null, IClinicalMapping.x_DocClinicalSectionType.FAMILY_HISTORY, patientDto.getFamilyHistory());
                }
                ContinuityOfCareDocumentFactory.toFile("./out/" + patientDto.getId() + ".xml");
                System.out.println("\t\t----------> Clinical document generated for patient with sequence " + patientDto.getSequence() + "...");
            } else {
                System.out.println("\t\t----------> Skipping clinical document generation for patient with sequence " + patientDto.getSequence() + "...");
            }
        }
    }
}
