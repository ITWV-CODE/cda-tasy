package br.com.itwv.mappings.document.facade;

import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IDocumentMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IDocumentMappingFacade;
import br.com.itwv.mappings.document.DocumentMapping;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

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
    public Object mapDocumentSections(ClinicalDocument doc, IDocumentMapping.x_DocDocumentSectionType x_DocDocumentSectionType,
                                      IClinicalMapping.x_DocClinicalSectionType x_DocClinicalSectionType, Object relatedDocumentObject) throws Exception {

        DocumentMapping.getInstance().mapDocumentSections(doc, IDocumentMapping.x_DocDocumentSectionType.PROPERTIES, x_DocClinicalSectionType,
                relatedDocumentObject);

        DocumentMapping.getInstance().mapDocumentSections(doc, IDocumentMapping.x_DocDocumentSectionType.AUTHOR, x_DocClinicalSectionType,
                relatedDocumentObject);

        DocumentMapping.getInstance().mapDocumentSections(doc, IDocumentMapping.x_DocDocumentSectionType.PARTICIPANT, x_DocClinicalSectionType,
                relatedDocumentObject);

        DocumentMapping.getInstance().mapDocumentSections(doc, IDocumentMapping.x_DocDocumentSectionType.CUSTODIAN, x_DocClinicalSectionType,
                relatedDocumentObject);

        return doc;
    }
}
