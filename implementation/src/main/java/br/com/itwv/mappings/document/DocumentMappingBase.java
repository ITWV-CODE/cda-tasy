package br.com.itwv.mappings.document;

import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.RelatedDocumentObject;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

public abstract class DocumentMappingBase {

    public DocumentMappingBase() {
    }

    protected static ClinicalDocument mapDocumentProperties(ClinicalDocument doc, RelatedDocumentObject relatedDocumentObject)
            throws Exception {

        return doc;
    }

    protected static ClinicalDocument mapDocumentParticipant(ClinicalDocument doc) throws Exception {
        return doc;

    }

    protected static ClinicalDocument mapDocumentAuthor(ClinicalDocument doc) throws Exception {

        return doc;
    }

    protected static ClinicalDocument mapDocumentCustodian(ClinicalDocument doc, RelatedDocumentObject relatedDocumentObject)
            throws Exception {
        return doc;
    }

    protected static ClinicalDocument mapDocumentDocumentOf(ClinicalDocument doc) throws Exception {

        return doc;
    }
}
