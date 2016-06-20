package br.com.itwv.mappings.patient.facade;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IPatientMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IPatientMappingFacade;
import br.com.itwv.mappings.patient.PatientMapping;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

public class PatientMappingFacade implements IPatientMappingFacade {

    private static PatientMappingFacade instance = null;

    protected PatientMappingFacade() {
    }

    public static PatientMappingFacade getInstance() {
        if (instance == null) {
            instance = new PatientMappingFacade();
        }
        return instance;
    }

    @Override
    public Object mapPatientSections(ClinicalDocument doc, Dto dto) throws Exception {

        PatientMapping.getInstance().mapPatientSections(doc, IPatientMapping.x_DocPatientSectionType.RECORD_TARGET, dto);

        PatientMapping.getInstance().mapPatientSections(doc, IPatientMapping.x_DocPatientSectionType.PATIENTROLE, dto);

        PatientMapping.getInstance().mapPatientSections(doc, IPatientMapping.x_DocPatientSectionType.PATIENT, dto);

        return doc;
    }
}
