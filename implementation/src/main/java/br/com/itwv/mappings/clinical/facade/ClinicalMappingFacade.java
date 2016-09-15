package br.com.itwv.mappings.clinical.facade;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMappingFacade;
import br.com.itwv.mappings.clinical.cda.ClinicalMapping;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicalMappingFacade implements IClinicalMappingFacade {

    private static ClinicalMappingFacade instance = null;

    protected ClinicalMappingFacade() {
    }

    public static ClinicalMappingFacade getInstance() {
        if (instance == null) {
            instance = new ClinicalMappingFacade();
        }
        return instance;
    }

    @Override
    public <T extends Dto> Object mapClinicalSections(List<ClinicalDocument> docList, Object relatedDocumentObject,
                                                      IClinicalMapping.x_DocClinicalSectionType x_DocClinicalSectionType, List<T> segments) throws Exception {

        Map<String, List<Dto>> segmentsHash = ClinicalMappingFacade.instanceSegments(segments);

        switch (x_DocClinicalSectionType) {
            case MEDICATIONS:
            case ALERTS:
            case IMMUNIZATIONS:
            case PROBLEMS:
            case PROCEDURES:
            case ENCOUNTERS:
            case FAMILY_HISTORY:
            case PLAN_OF_CARE:
                return ClinicalMapping.getInstance().mapClinicalSections(docList, x_DocClinicalSectionType, segmentsHash);
            default:
                return null;
        }
    }

    private static <T extends Dto> Map<String, List<Dto>> instanceSegments(List<T> segments) {

        Map<String, List<Dto>> segmentsHash = new HashMap<String, List<Dto>>();
        segmentsHash.put(segments.get(0).getClass().getSimpleName(), (List<Dto>) segments);
        return segmentsHash;
    }
}
