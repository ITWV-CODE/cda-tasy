package br.com.itwv.mappings.patient;

import br.com.itwv.br.com.itwv.dto.PatientDto;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.TS;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;

public abstract class PatientMappingBase {

    public PatientMappingBase() {
    }

    protected static ClinicalDocument mapPatient(PatientDto patientDto, ClinicalDocument doc) throws Exception {

        Patient patient = CDAFactory.eINSTANCE.createPatient();
        doc.getRecordTargets().get(0).getPatientRole().setPatient(patient);
        patient.getNames().add(
                CDADataTypesFactory.getInstance().createBasePN(patientDto.getFamilyName(), patientDto.getGivenName(), null, null));

        patient.setAdministrativeGenderCode(CDADataTypesFactory.getInstance().createBaseAdministrativeGenderCode(
                patientDto.getGender()));

        TS birthTime = DatatypesFactory.eINSTANCE.createTS(patientDto.getBirthDate());
        if (birthTime.getValue() == null)
            birthTime.setNullFlavor(NullFlavor.NI);
        patient.setBirthTime(birthTime);
        return doc;
    }

    protected static ClinicalDocument mapPatientRole(final PatientDto patientDto, ClinicalDocument doc) throws Exception {
        PatientRole patientRole = CDAFactory.eINSTANCE.createPatientRole();
        doc.getRecordTargets().get(0).setPatientRole(patientRole);
        patientRole.setProviderOrganization(CDADataTypesFactory.getInstance().createBaseProviderOrganization(patientDto.getInstitution().getCode(), patientDto.getInstitution().getDescription()));
        II id = CDADataTypesFactory.getInstance().createBaseRootII(patientDto.getId(), "2.16.17.710.820", null);
        patientRole.getIds().add(id);
        return doc;
    }

    protected static ClinicalDocument mapRecordTarget(ClinicalDocument doc) throws Exception {
        doc.getRecordTargets().clear();
        RecordTarget recordTarget = CDAFactory.eINSTANCE.createRecordTarget();
        doc.getRecordTargets().add(recordTarget);
        return doc;
    }
}
