package br.com.itwv.cdatasy.common.business.interop.mappings.interfaces;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

public interface IPatientMappingFacade {

    public Object mapPatientSections(ClinicalDocument doc, Dto dto) throws Exception;
}
