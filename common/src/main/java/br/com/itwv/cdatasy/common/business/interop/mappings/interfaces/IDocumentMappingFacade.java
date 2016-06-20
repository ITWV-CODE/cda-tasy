package br.com.itwv.cdatasy.common.business.interop.mappings.interfaces;

import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

public interface IDocumentMappingFacade {

    Object mapDocumentSections(ClinicalDocument doc, IDocumentMapping.x_DocDocumentSectionType x_DocDocumentSectionType,
                               IClinicalMapping.x_DocClinicalSectionType x_DocClinicalSectionType, Object relatedDocumentObject) throws Exception;
}
