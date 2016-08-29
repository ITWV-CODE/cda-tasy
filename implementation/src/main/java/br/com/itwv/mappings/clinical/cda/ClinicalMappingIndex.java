package br.com.itwv.mappings.clinical.cda;

import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.helpers.EObjectElementsFactory;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import br.com.itwv.mappings.clinical.comparable.*;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.Procedure;
import org.openhealthtools.mdht.uml.cda.SubstanceAdministration;
import org.openhealthtools.mdht.uml.cda.ccd.*;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_TS;

import static br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.IClinicalDocumentFactory.x_EObjectTypes;

public class ClinicalMappingIndex {

    private static ClinicalMappingIndex instance = null;

    protected ClinicalMappingIndex() {
    }

    public static ClinicalMappingIndex getInstance() {
        if (instance == null) {
            instance = new ClinicalMappingIndex();
        }
        return instance;
    }

    public MedicationActivity indexMedications(ClinicalDocument doc, MedicationActivity medicationActivity) throws Exception {

        for (SubstanceAdministration activity : ((MedicationsSection) EObjectElementsFactory.getDocumentSection(doc, x_EObjectTypes.MEDICATIONS))
                .getSubstanceAdministrations()) {
            if (new MedicationActivityComparable(activity).equals((medicationActivity))
                    && IClinicalMapping.x_DocEntryStatusCode.valueOf(medicationActivity.getStatusCode().getCode().toUpperCase()) != IClinicalMapping.x_DocEntryStatusCode.OBSOLETE)
                medicationActivity.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                        IClinicalMapping.x_DocEntryStatusCode.HELD.name().toLowerCase()));
        }

        if (IClinicalMapping.x_DocEntryStatusCode.valueOf(medicationActivity.getStatusCode().getCode().toUpperCase()).equals(
                IClinicalMapping.x_DocEntryStatusCode.NEW)) {
            if ((((IVL_TS) medicationActivity.getEffectiveTimes().get(0)).getHigh().getValue() != null))
                medicationActivity.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            else
                medicationActivity.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        }
        return medicationActivity;
    }

    public ProblemAct indexAlerts(ClinicalDocument doc, ProblemAct problemAct) throws Exception {

        for (ProblemAct act : ((AlertsSection) EObjectElementsFactory.getDocumentSection(doc, x_EObjectTypes.ALLERGIES)).getProblemActs()) {
            if (new AlertsProblemActComparable(act).equals((problemAct))
                    && IClinicalMapping.x_DocEntryStatusCode.valueOf(problemAct.getStatusCode().getCode().toUpperCase()) != IClinicalMapping.x_DocEntryStatusCode.OBSOLETE)
                problemAct
                        .setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(IClinicalMapping.x_DocEntryStatusCode.HELD.name().toLowerCase()));
        }
        if (IClinicalMapping.x_DocEntryStatusCode.valueOf(problemAct.getStatusCode().getCode().toUpperCase()).equals(
                IClinicalMapping.x_DocEntryStatusCode.NEW)) {
            if ((((IVL_TS) problemAct.getEffectiveTime()).getHigh().getValue() != null))
                problemAct.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            else
                problemAct.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        }
        return problemAct;
    }

    public ProcedureActivityProcedure indexProcedures(ClinicalDocument doc, ProcedureActivityProcedure procedureActivityProcedure) throws Exception {

        for (Procedure proc : ((ProceduresSection) EObjectElementsFactory.getDocumentSection(doc, x_EObjectTypes.PROCEDURES)).getProcedures()) {
            if (new ProcedureActivityComparable((ProcedureActivityProcedure) proc).equals((procedureActivityProcedure))
                    && IClinicalMapping.x_DocEntryStatusCode.valueOf(procedureActivityProcedure.getStatusCode().getCode().toUpperCase()) != IClinicalMapping.x_DocEntryStatusCode.OBSOLETE)
                procedureActivityProcedure.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                        IClinicalMapping.x_DocEntryStatusCode.HELD.name().toLowerCase()));
        }
        if (IClinicalMapping.x_DocEntryStatusCode.valueOf(procedureActivityProcedure.getStatusCode().getCode().toUpperCase()).equals(
                IClinicalMapping.x_DocEntryStatusCode.NEW)) {
            if ((((IVL_TS) procedureActivityProcedure.getEffectiveTime()).getHigh().getValue() != null))
                procedureActivityProcedure.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            else
                procedureActivityProcedure.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        }
        return procedureActivityProcedure;
    }

    public MedicationActivity indexImmunizations(ClinicalDocument doc, MedicationActivity medicationActivity) throws Exception {

        for (SubstanceAdministration activity : ((ImmunizationsSection) EObjectElementsFactory.getDocumentSection(doc, x_EObjectTypes.IMMUNIZATIONS))
                .getSubstanceAdministrations()) {
            if (new ImmunizationActivityComparable(activity).equals((medicationActivity))
                    && IClinicalMapping.x_DocEntryStatusCode.valueOf(medicationActivity.getStatusCode().getCode().toUpperCase()) != IClinicalMapping.x_DocEntryStatusCode.OBSOLETE)
                medicationActivity.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                        IClinicalMapping.x_DocEntryStatusCode.HELD.name().toLowerCase()));
        }
        if (IClinicalMapping.x_DocEntryStatusCode.valueOf(medicationActivity.getStatusCode().getCode().toUpperCase()).equals(
                IClinicalMapping.x_DocEntryStatusCode.NEW)) {
            if (medicationActivity.getEffectiveTimes().get(0).getValue() != null)
                medicationActivity.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            else
                medicationActivity.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        }
        return medicationActivity;
    }

    public ProblemAct indexProblems(ClinicalDocument doc, ProblemAct problemAct) throws Exception {

        for (ProblemAct act : ((ProblemSection) EObjectElementsFactory.getDocumentSection(doc, x_EObjectTypes.PROBLEMS)).getProblemActs()) {
            if (new ProblemsProblemActComparable(act).equals((problemAct))
                    && IClinicalMapping.x_DocEntryStatusCode.valueOf(problemAct.getStatusCode().getCode().toUpperCase()) != IClinicalMapping.x_DocEntryStatusCode.OBSOLETE)
                problemAct
                        .setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(IClinicalMapping.x_DocEntryStatusCode.HELD.name().toLowerCase()));
        }
        if (IClinicalMapping.x_DocEntryStatusCode.valueOf(problemAct.getStatusCode().getCode().toUpperCase()).equals(
                IClinicalMapping.x_DocEntryStatusCode.NEW)) {
            if ((((IVL_TS) problemAct.getEffectiveTime()).getHigh().getValue() != null))
                problemAct.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
            else
                problemAct.setStatusCode(CDADataTypesFactory.getInstance().createBaseStatusCodeCS(
                        IClinicalMapping.x_DocEntryStatusCode.COMPLETED.name().toLowerCase()));
        }
        return problemAct;
    }
}
