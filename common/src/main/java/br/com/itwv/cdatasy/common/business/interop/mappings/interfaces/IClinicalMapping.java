package br.com.itwv.cdatasy.common.business.interop.mappings.interfaces;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.util.List;
import java.util.Map;

public interface IClinicalMapping {

    public enum x_DocClinicalSectionType {
        UNDEFINED, MEDICATIONS, ALERTS, IMMUNIZATIONS, PROCEDURES, MEDICATIONSQUERY, ALLERGIESQUERY, PROBLEMS, ENCOUNTERS, FACILITYTRANSFERS, DOCUMENTSQUERY
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

    Object mapClinicalSections(List<ClinicalDocument> docList, x_DocClinicalSectionType x_DocClinicalSectionType, Map<String, List<Dto>> segments) throws Exception;
}
