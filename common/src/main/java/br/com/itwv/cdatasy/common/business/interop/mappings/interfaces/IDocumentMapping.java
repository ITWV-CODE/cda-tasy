package br.com.itwv.cdatasy.common.business.interop.mappings.interfaces;

import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

public interface IDocumentMapping {

    public enum x_DocDocumentSectionType {
        UNDEFINED, PROPERTIES, AUTHOR, DOCUMENTOF, CUSTODIAN, PARTICIPANT
    }

    public ClinicalDocument mapDocumentSections(ClinicalDocument doc, IDocumentMapping.x_DocDocumentSectionType x_DocDocumentSectionType,
                                                IClinicalMapping.x_DocClinicalSectionType x_DocClinicalSectionType, Object relatedDocumentObject)
            throws Exception;
}
