package br.com.itwv.cdatasy.common.business.interop.mappings.interfaces;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

public interface IPatientMapping {

    public enum x_DocPatientSectionType {
        UNDEFINED, PATIENT, PATIENTROLE, RECORD_TARGET
    }

    public ClinicalDocument mapPatientSections(ClinicalDocument doc, IPatientMapping.x_DocPatientSectionType x_DocPatientSectionType, Dto patientDto) throws Exception;
}
