package br.com.itwv.mappings.document.facade;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IDocumentMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IDocumentMappingFacade;
import br.com.itwv.mappings.clinical.facade.ClinicalMappingFacade;
import br.com.itwv.mappings.document.DocumentMapping;
import org.eclipse.emf.ecore.util.Switch;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentMappingFacade implements IDocumentMappingFacade {

    private static DocumentMappingFacade instance = null;

    protected DocumentMappingFacade() {
    }

    public static DocumentMappingFacade getInstance() {
        if (instance == null) {
            instance = new DocumentMappingFacade();
        }
        return instance;
    }

    @Override
    public <T extends Dto> Object mapDocumentSections(ClinicalDocument doc, IDocumentMapping.x_DocDocumentSectionType x_DocDocumentSectionType, List<T> segments) throws Exception {

        Map<String, List<Dto>> segmentsHash = null;
        if (segments != null) {
            segmentsHash = DocumentMappingFacade.instanceSegments(segments);
        }
        switch (x_DocDocumentSectionType) {
            case PROPERTIES:
            case PARTICIPANT:
                return DocumentMapping.getInstance().mapDocumentSections(doc, x_DocDocumentSectionType, null);
            case CUSTODIAN:
            case AUTHOR:
                return DocumentMapping.getInstance().mapDocumentSections(doc, x_DocDocumentSectionType, segmentsHash);
            case DOCUMENTOF:
                return DocumentMapping.getInstance().mapDocumentSections(doc, x_DocDocumentSectionType, null);
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
