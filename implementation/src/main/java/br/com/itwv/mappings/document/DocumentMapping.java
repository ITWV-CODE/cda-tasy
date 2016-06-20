package br.com.itwv.mappings.document;

import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IDocumentMapping;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.util.HashMap;

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
    public ClinicalDocument mapDocumentSections(ClinicalDocument doc, IDocumentMapping.x_DocDocumentSectionType x_DocDocumentSectionType,
                                                IClinicalMapping.x_DocClinicalSectionType x_DocClinicalSectionType, Object relatedDocumentObject) throws Exception {
        return null;
    }
}
