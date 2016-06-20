package br.com.itwv.cdatasy.common.business.interop.mappings.interfaces;

import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.util.List;

public interface IClinicalMappingFacade {

    public Object mapClinicalSections(List<ClinicalDocument> docList, Object relatedDocumentObject,
                                      IClinicalMapping.x_DocClinicalSectionType x_DocClinicalSectionType) throws Exception;
}
