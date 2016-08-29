package br.com.itwv.cdatasy.test;

import br.com.itwv.cdatasy.base.encoding.streams.FastByteArrayOutputStream;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.ContinuityOfCareDocumentFactory;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import junit.framework.TestCase;
import org.junit.Test;
import org.openhealthtools.mdht.uml.cda.CDAFactory;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.ccd.CCDFactory;
import org.openhealthtools.mdht.uml.cda.ccd.MedicationsSection;
import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.vocab.ActMood;

/**
 * Created by itwv_2 on 13/06/2016.
 */
public class JUTest1 extends TestCase {

    private ClinicalDocument clinicalDocumentInstance = CDAFactory.eINSTANCE.createClinicalDocument();

    @Override
    public void setUp() throws Exception {
        super.setUp();

        MedicationsSection medicationsSection = CCDFactory.eINSTANCE.createMedicationsSection().init();
        medicationsSection.setTitle(DatatypesFactory.eINSTANCE.createST("Medicamentos Usados"));
        medicationsSection.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "1.3.6.1.4.1.12559.11.10.1.3.1.2.3", null));
        medicationsSection.setMoodCode(ActMood.EVN);
        medicationsSection.createStrucDocText("<content ID=\"nomedicationdescription\">Não existem medicamentos.</content>");
        medicationsSection.addSubstanceAdministration(ContinuityOfCareDocumentFactory.createMedicationsSectionEntry(null, true));
        clinicalDocumentInstance.addSection(medicationsSection);
    }

    @Test
    public void test() throws Exception {

        FastByteArrayOutputStream writer = new FastByteArrayOutputStream();
        CDAUtil.save(this.clinicalDocumentInstance, writer);
        String docString = writer.toString();
        writer.close();
        System.out.println(docString);
    }
}
