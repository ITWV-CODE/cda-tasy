package br.com.itwv.mappings.document;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IDocumentMapping;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.util.List;
import java.util.Map;

public class DocumentMapping extends DocumentMappingBase implements IDocumentMapping {
    private static DocumentMapping instance = null;

    protected DocumentMapping() {
        super();
    }

    public static DocumentMapping getInstance() {
        if (instance == null) {
            instance = new DocumentMapping();
        }
        return instance;
    }

    @Override
    public ClinicalDocument mapDocumentSections(final ClinicalDocument doc, final x_DocDocumentSectionType x_DocDocumentSectionType, final Map<String, List<Dto>> segments) throws Exception {

        switch (x_DocDocumentSectionType) {
            case PROPERTIES:
                return super.mapDocumentProperties(doc);
            case AUTHOR:
                return super.mapDocumentAuthor(doc, segments.get("AuthorDto"));

            case CUSTODIAN:
                return super.mapDocumentCustodian(doc, segments.get("AuthorDto"));
            default:
                return null;
        }
    }
}
