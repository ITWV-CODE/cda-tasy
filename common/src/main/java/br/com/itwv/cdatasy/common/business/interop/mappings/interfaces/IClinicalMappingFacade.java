package br.com.itwv.cdatasy.common.business.interop.mappings.interfaces;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.util.List;

public interface IClinicalMappingFacade {

    public <T extends Dto>Object mapClinicalSections(List<ClinicalDocument> docList, Object relatedDocumentObject,
                                                     IClinicalMapping.x_DocClinicalSectionType x_DocClinicalSectionType, List<T> segments) throws Exception;
}
