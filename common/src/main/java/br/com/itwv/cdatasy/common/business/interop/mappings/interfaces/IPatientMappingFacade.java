package br.com.itwv.cdatasy.common.business.interop.mappings.interfaces;

import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

public interface IPatientMappingFacade {

    public Object mapPatientSections(ClinicalDocument doc, IPatientMapping.x_DocPatientSectionType x_DocPatientSectionType,
                                     IClinicalMapping.x_DocClinicalSectionType x_DocClinicalSectionType) throws Exception;
}
