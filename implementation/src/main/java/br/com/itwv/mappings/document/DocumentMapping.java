package br.com.itwv.mappings.document;

import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IDocumentMapping;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

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
    public ClinicalDocument mapDocumentSections(ClinicalDocument doc, x_DocDocumentSectionType x_DocDocumentSectionType) throws Exception {

        switch (x_DocDocumentSectionType) {
            case PROPERTIES:
                return super.mapDocumentProperties(doc);
            case AUTHOR:
                return super.mapDocumentAuthor(doc);

            case CUSTODIAN:
                return super.mapDocumentCustodian(doc);
              /*
            case PARTICIPANT:
                return super.mapDocumentParticipant((PD1) segments.get("PD1"), (ORC) segments.get("ORC"), doc);
            case DOCUMENTOF:
                switch (x_DocClinicalSectionType) {
                    case MEDICATIONS:
                    case IMMUNIZATIONS:
                        return super.mapDocumentDocumentOf((ORC) segments.get("ORC"), doc);
                    case ALERTS:
                        return super.mapDocumentDocumentOf((MSH) segments.get("MSH"), (IAM) segments.get("IAM"), doc);
                    case PROBLEMS:
                        return super.mapDocumentDocumentOf((MSH) segments.get("MSH"), (PRB) segments.get("PRB"), (ROL) segments.get("ROL"), doc);
                    default:
                        return null;
                }
                */

            default:
                return null;
        }
    }
}
