package br.com.itwv.mappings.patient;

import br.com.itwv.br.com.itwv.dto.PatientDto;
import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IPatientMapping;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

public class PatientMapping extends PatientMappingBase implements IPatientMapping {

    private static PatientMapping instance = null;

    protected PatientMapping() {
        super();
    }

    public static PatientMapping getInstance() {
        if (instance == null) {
            instance = new PatientMapping();
        }
        return instance;
    }

    @Override
    public ClinicalDocument mapPatientSections(ClinicalDocument doc, IPatientMapping.x_DocPatientSectionType x_DocPatientSectionType,
                                               Dto patientDto) throws Exception {

        switch (x_DocPatientSectionType) {
            case RECORD_TARGET:
                super.mapRecordTarget(doc);
            case PATIENTROLE:
                super.mapPatientRole((PatientDto) patientDto, doc);
            case PATIENT:
                super.mapPatient((PatientDto) patientDto, doc);
            default:
                return null;
        }
    }
}