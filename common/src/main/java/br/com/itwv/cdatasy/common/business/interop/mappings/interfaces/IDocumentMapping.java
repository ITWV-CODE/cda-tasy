package br.com.itwv.cdatasy.common.business.interop.mappings.interfaces;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.util.List;
import java.util.Map;

public interface IDocumentMapping {

    enum x_DocDocumentSectionType {
        UNDEFINED, PROPERTIES, AUTHOR, DOCUMENTOF, CUSTODIAN, PARTICIPANT
    }

    ClinicalDocument mapDocumentSections(ClinicalDocument doc, IDocumentMapping.x_DocDocumentSectionType x_DocDocumentSectionType, final Map<String, List<Dto>> segments)
            throws Exception;
}
