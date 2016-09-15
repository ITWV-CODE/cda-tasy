package br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2;

public interface IClinicalDocumentFactory {

    public enum x_FactoryLoadTypes {
        INPUTSTREAM, FILE, DEFAULT
    }

    public enum x_EObjectTypes {
        ALLERGIES, MEDICATIONS, IMMUNIZATIONS, PROBLEMS, PROCEDURES, MEDICALEQUIPMENT, ENCOUNTERS, FAMILY_HISTORY, PLAN_OF_CARE
    }

    public void createClinicalDocumentFactory(x_FactoryLoadTypes factoryLoadType, Object obj, boolean validateClinicalDocument,
                                              boolean instanceClinicalSections);
}
