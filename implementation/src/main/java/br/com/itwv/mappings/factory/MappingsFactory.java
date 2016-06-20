package br.com.itwv.mappings.factory;

import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IMappingsFactory;
import br.com.itwv.mappings.clinical.facade.ClinicalMappingFacade;
import br.com.itwv.mappings.document.facade.DocumentMappingFacade;
import br.com.itwv.mappings.patient.facade.PatientMappingFacade;

public class MappingsFactory implements IMappingsFactory {

    private static MappingsFactory instance = null;
    public static PatientMappingFacade patientMappingFacade;
    public static DocumentMappingFacade documentMappingFacade;
    public static ClinicalMappingFacade clinicalMappingFacade;

    protected MappingsFactory() {
    }

    public static MappingsFactory getInstance() {
        if (instance == null) {
            instance = new MappingsFactory();
        }
        return instance;
    }

    public void createEntityMappingsFactory() {
        MappingsFactory.patientMappingFacade = PatientMappingFacade.getInstance();
        MappingsFactory.documentMappingFacade = DocumentMappingFacade.getInstance();
        MappingsFactory.clinicalMappingFacade = ClinicalMappingFacade.getInstance();
    }

    public void dispose() {
        MappingsFactory.patientMappingFacade = null;
        MappingsFactory.documentMappingFacade = null;
        MappingsFactory.clinicalMappingFacade = null;
    }
}
