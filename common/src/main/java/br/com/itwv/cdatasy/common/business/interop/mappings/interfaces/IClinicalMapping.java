package br.com.itwv.cdatasy.common.business.interop.mappings.interfaces;

import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.util.List;

public interface IClinicalMapping {

    public enum x_DocClinicalSectionType {
        UNDEFINED, MEDICATIONS, ALERTS, IMMUNIZATIONS, MEDICATIONSQUERY, ALLERGIESQUERY, PROBLEMS, FACILITYTRANSFERS, DOCUMENTSQUERY
    }

    public enum x_DocObservationTypes {
        UNDEFINED, ALERTOBSERVATIONIMPL, REACTIONOBSERVATIONIMPL, ALERTSTATUSOBSERVATIONIMPL
    }

    public enum x_DocEntryStatusCode {
        UNDEFINED, ABORTED, NULLFIED, OBSOLETE, COMPLETED, ACTIVE, CANCELLED, SUSPENDED, HELD, NEW
    }

    public enum x_DocClinicalMedicationTypeCode {
        EMB, DCIPT, ATC, CNPEM
    }

    public enum x_DocEntryMoodCode {
        EVN, INT
    }

    public enum x_DocClinicalFullfillInstructionsTypeCode {
        PRESCRIPTIONTYPE, PESCRIBEDAMOUNT, PACKINGSIZE
    }

    public Object mapClinicalSections(List<ClinicalDocument> doc, Object relatedDocumentObject,
                                      IClinicalMapping.x_DocClinicalSectionType x_DocClinicalSectionType) throws Exception;
}
