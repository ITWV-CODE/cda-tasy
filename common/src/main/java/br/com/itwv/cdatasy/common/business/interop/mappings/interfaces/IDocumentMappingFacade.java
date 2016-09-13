package br.com.itwv.cdatasy.common.business.interop.mappings.interfaces;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.util.List;

public interface IDocumentMappingFacade {

    <T extends Dto> Object mapDocumentSections(ClinicalDocument doc, IDocumentMapping.x_DocDocumentSectionType x_DocDocumentSectionType, List<T> segments) throws Exception;
}
