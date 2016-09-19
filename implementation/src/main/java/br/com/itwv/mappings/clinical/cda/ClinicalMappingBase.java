package br.com.itwv.mappings.clinical.cda;

import br.com.itwv.br.com.itwv.dto.*;
import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.ContinuityOfCareDocumentFactory;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.IClinicalDocumentFactory;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.helpers.ClinicalMappingEntryRelationships;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.helpers.EObjectElementsFactory;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import br.com.itwv.mappings.clinical.tables.*;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.cda.ccd.*;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.EN;
import org.openhealthtools.mdht.uml.hl7.datatypes.PN;
import org.openhealthtools.mdht.uml.hl7.vocab.EntityClassRoot;
import org.openhealthtools.mdht.uml.hl7.vocab.ParticipationType;
import org.openhealthtools.mdht.uml.hl7.vocab.RoleClassRoot;

import java.util.List;

public abstract class ClinicalMappingBase {

    public ClinicalMappingBase() {
    }

    protected static List<ClinicalDocument> mapContent(List<ClinicalDocument> docList) throws Exception {

        if (docList.get(0).getComponent() == null) {
            Component2 component = CDAFactory.eINSTANCE.createComponent2();
            docList.get(0).setComponent(component);
        }
        return docList;
    }

    protected static List<ClinicalDocument> mapStructuredBody(List<ClinicalDocument> docList) throws Exception {

        if (docList.get(0).getComponent().getStructuredBody() == null) {
            StructuredBody structuredBody = CDAFactory.eINSTANCE.createStructuredBody();
            docList.get(0).setStructuredBody(structuredBody);
        }
        return docList;
    }

    protected static List<ClinicalDocument> insertMedicationEntry(List<Dto> medications, List<ClinicalDocument> docList) throws Exception {


        final MedicationsSection medicationSection = (MedicationsSection) EObjectElementsFactory.getLogicalDocumentSection(docList.get(0), IClinicalDocumentFactory.x_EObjectTypes.MEDICATIONS, "", "");

        for (final Dto dto : medications) {
            final MedicationDto medication = (MedicationDto) dto;
            MedicationActivity medicationActivity = ContinuityOfCareDocumentFactory.createMedicationsSectionEntry(null, false);
            medicationSection.addSubstanceAdministration(medicationActivity);
            medicationActivity.getIds().add(
                    CDADataTypesFactory.getInstance().createBaseRootII(medication.getId(), null, null));

            medicationActivity.getEffectiveTimes().add(
                    CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(medication.getDate(), medication.getDate()));

            medicationActivity.setRouteCode(CDADataTypesFactory.getInstance().createBaseCodeCE(medication.getRoute().getCode(), medication.getRoute().getTerminolgy(), null,
                    medication.getRoute().getDescription(), null));

            medicationActivity.setDoseQuantity(CDADataTypesFactory.getInstance().createBaseIVL_PQ());

            medicationActivity
                    .getConsumable()
                    .getManufacturedProduct()
                    .getManufacturedMaterial().setCode(
                    CDADataTypesFactory.getInstance().createBaseCodeCE(medication.getMedicine().getCode(), "2.16.840.1.113883.6.96", medication.getMedicine().getTerminolgy(), medication.getMedicine().getDescription(), null));

            CDADataTypesFactory.getInstance().createBasePatientInstruction(medicationActivity, medication.getDosage());

        }
        medicationSection.createStrucDocText(MedicationSectionTable.getInstance().getTable(medicationSection));

        return docList;
    }

    protected static List<ClinicalDocument> insertAlertEntry
            (List<Dto> allergies, List<ClinicalDocument> docList) throws Exception {

        final AlertsSection alertsSection = (AlertsSection) EObjectElementsFactory.getLogicalDocumentSection(docList.get(0), IClinicalDocumentFactory.x_EObjectTypes.ALLERGIES, "", "");

        for (final Dto dto : allergies) {
            final AllergyDto allergy = (AllergyDto) dto;
            ProblemAct problemAct = ContinuityOfCareDocumentFactory.createAlertsSectionEntry(null, false);
            alertsSection.addAct(problemAct);
            problemAct.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(allergy.getId(), null, null));
            problemAct.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(allergy.getDate(), allergy.getDate()));
            problemAct.getObservations().get(0).getValues().add(CDADataTypesFactory.getInstance().createBaseCodeCD(allergy.getType().getCode(), "2.16.840.1.113883.6.96", allergy.getType().getTerminolgy(), allergy.getType().getDescription(), null));
            problemAct.getObservations().get(0).setCode(CDADataTypesFactory.getInstance().createBaseCodeCD("ASSERTION", "2.16.840.1.113883.5.4", null, null, null));

            //CDADataTypesFactory.getInstance().createBaseComment(problemAct.getObservations().get(0), null, nte.getNte3_Comment(0).getValue());
            Participant2 participant = CDAFactory.eINSTANCE.createParticipant2();
            problemAct.getObservations().get(0).getParticipants().add(participant);
            participant.setTypeCode(ParticipationType.CSM);

            ParticipantRole participantRole = CDAFactory.eINSTANCE.createParticipantRole();
            participant.setParticipantRole(participantRole);
            participantRole.setClassCode(RoleClassRoot.MANU);

            PlayingEntity playingEntity = CDAFactory.eINSTANCE.createPlayingEntity();
            participantRole.setPlayingEntity(playingEntity);
            playingEntity.setClassCode(EntityClassRoot.MMAT);
            playingEntity.setCode(CDADataTypesFactory.getInstance().createBaseCodeCE(allergy.getSubstance().getCode(), null, allergy.getSubstance().getTerminolgy(), allergy.getSubstance().getDescription(), null));

            CDADataTypesFactory.getInstance().createBaseReactionObservation(problemAct.getObservations().get(0), allergy.getReaction().getCode(), null, allergy.getReaction().getTerminolgy(), allergy.getReaction().getDescription());
            CDADataTypesFactory.getInstance().createBaseAlertStatusObservation(problemAct.getObservations().get(0), allergy.getStatus().getCode(), null, allergy.getStatus().getTerminolgy(), allergy.getStatus().getDescription(), null);
            ClinicalMappingEntryRelationships.getInstance().defineProblemActEntryRelationships(docList.get(0), problemAct);
        }
        alertsSection.createStrucDocText(AlertSectionTable.getInstance().getTable(alertsSection));
        return docList;
    }

    protected static List<ClinicalDocument> insertEncounterEntry
            (List<Dto> encounters, List<ClinicalDocument> docList) throws Exception {

        final EncountersSection encountersSection = (EncountersSection) EObjectElementsFactory.getLogicalDocumentSection(docList.get(0), IClinicalDocumentFactory.x_EObjectTypes.ENCOUNTERS, "", "");

        for (final Dto dto : encounters) {
            final EncounterDto encounter = (EncounterDto) dto;
            EncountersActivity encountersActivity = ContinuityOfCareDocumentFactory.createEncountersSectionEntry(null, false);
            encountersSection.addEncounter(encountersActivity);
            encountersActivity.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(encounter.getId(), null, null));
            encountersActivity.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(encounter.getDate(), null));
            encountersActivity.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD(encounter.getType().getCode(), null, encounter.getType().getTerminolgy(), encounter.getType().getDescription(), null));

            Participant2 participant = CDAFactory.eINSTANCE.createParticipant2();
            encountersActivity.getParticipants().add(participant);
            participant.setTypeCode(ParticipationType.LOC);
            participant.getTemplateIds().add(CDADataTypesFactory.getInstance().createBaseRootII(null, "2.16.840.1.113883.10.20.1.45", null));
            ParticipantRole participantRole = CDAFactory.eINSTANCE.createParticipantRole();
            participant.setParticipantRole(participantRole);
            participantRole.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(encounter.getInstituition().getCode(), "2.16.840.1.113883.1", null));
            PlayingEntity playingEntity = CDAFactory.eINSTANCE.createPlayingEntity();
            participantRole.setPlayingEntity(playingEntity);
            playingEntity.setClassCode(EntityClassRoot.PLC);
            EN en = DatatypesFactory.eINSTANCE.createPN();
            en.addText(encounter.getInstituition().getDescription());
            playingEntity.getNames().add((PN) en);

            Performer2 performer = CDAFactory.eINSTANCE.createPerformer2();
            encountersActivity.getPerformers().add(performer);
            encountersActivity.getPerformers().get(0).setTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(encounter.getDate(), null));
            encountersActivity.getPerformers().get(0).setAssignedEntity(CDADataTypesFactory.getInstance().createBaseAssignedEntity(encounter.getAuthor().getId(), "2.16.840.1.113883.1", null, encounter.getAuthor().getFamilyName(), encounter.getAuthor().getGivenName(), null, encounter.getAuthor().getPrefix(), encounter.getInstituition().getDescription(), encounter.getInstituition().getCode(), null, null, null, null, null, null, true));
        }
        encountersSection.createStrucDocText(EncounterSectionTable.getInstance().getTable(encountersSection));

        return docList;
    }

    protected static List<ClinicalDocument> insertFamilyHistoryEntry
            (List<Dto> familyHistoryList, List<ClinicalDocument> docList) throws Exception {

        final FamilyHistorySection familyHistorySection = (FamilyHistorySection) EObjectElementsFactory.getLogicalDocumentSection(docList.get(0), IClinicalDocumentFactory.x_EObjectTypes.FAMILY_HISTORY, "", "");

        for (final Dto dto : familyHistoryList) {
            final FamilyHistoryDto familyHistory = (FamilyHistoryDto) dto;
            FamilyHistoryObservation familyHistoryObservation = ContinuityOfCareDocumentFactory.createFamilyHistorySectionEntry(null, false);
            familyHistorySection.addObservation(familyHistoryObservation);
            familyHistoryObservation.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(familyHistory.getId(), null, null));
            familyHistoryObservation.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(familyHistory.getDate(), familyHistory.getDate()));
            familyHistoryObservation.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD(familyHistory.getProblem().getCode(), null, familyHistory.getProblem().getTerminolgy(), familyHistory.getProblem().getDescription(), null));
            familyHistoryObservation.setSubject(CDADataTypesFactory.getInstance().createBaseSubject(familyHistory.getRelation().getCode(), familyHistory.getRelation().getTerminolgy(), familyHistory.getRelation().getDescription()));

        }
        familyHistorySection.createStrucDocText(FamilyHistorySectionTable.getInstance().getTable(familyHistorySection));

        return docList;
    }


    protected static List<ClinicalDocument> insertImmunizationEntry(List<ClinicalDocument> docList)
            throws Exception {

        return docList;
    }

    protected static List<ClinicalDocument> mapMedicationsFactory(final List<Dto> medications, List<ClinicalDocument> docList) throws
            Exception {
        return ClinicalMappingBase.insertMedicationEntry(medications, docList);
    }

    protected static List<ClinicalDocument> mapImmunizationsFactory(List<ClinicalDocument> docList) throws
            Exception {

        return ClinicalMappingBase.insertImmunizationEntry(docList);
    }

    protected static List<ClinicalDocument> mapAlertsFactory(final List<Dto> allergies, List<
            ClinicalDocument> docList) throws Exception {

        return ClinicalMappingBase.insertAlertEntry(allergies, docList);
    }

    protected List<ClinicalDocument> mapProblemsFactory(final List<Dto> problems, List<
            ClinicalDocument> docList) throws Exception {

        return ClinicalMappingBase.insertProblemEntry(problems, docList);
    }

    protected List<ClinicalDocument> mapPlanOfCareFactory(final List<Dto> planOfCareList, List<
            ClinicalDocument> docList) throws Exception {

        return ClinicalMappingBase.insertPlanOfCareEntry(planOfCareList, docList);
    }

    protected List<ClinicalDocument> mapEncountersFactory(final List<Dto> encounters, List<
            ClinicalDocument> docList) throws Exception {

        return ClinicalMappingBase.insertEncounterEntry(encounters, docList);
    }

    protected List<ClinicalDocument> mapFamilyHistoryFactory(final List<Dto> familyHistoryList, List<
            ClinicalDocument> docList) throws Exception {

        return ClinicalMappingBase.insertFamilyHistoryEntry(familyHistoryList, docList);
    }


    protected List<ClinicalDocument> mapProceduresFactory(final List<Dto> procedures, List<
            ClinicalDocument> docList) throws Exception {

        return ClinicalMappingBase.insertProcedureEntry(procedures, docList);
    }

    protected static List<ClinicalDocument> insertProcedureEntry
            (List<Dto> procedures, List<ClinicalDocument> docList) throws Exception {

        return docList;
    }

    protected static List<ClinicalDocument> insertPlanOfCareEntry
            (List<Dto> planOfCareList, List<ClinicalDocument> docList) throws Exception {

        final PlanOfCareSection planOfCareSection = (PlanOfCareSection) EObjectElementsFactory.getLogicalDocumentSection(docList.get(0), IClinicalDocumentFactory.x_EObjectTypes.PLAN_OF_CARE, "", "");

        for (final Dto dto : planOfCareList) {
            final PlanOfCareDto planOfCare = (PlanOfCareDto) dto;
            PlanOfCareActivityProcedure planOfCareActivityProcedure = ContinuityOfCareDocumentFactory.createPlanOfCareSectionEntry(null, false);
            planOfCareSection.addProcedure(planOfCareActivityProcedure);
            planOfCareActivityProcedure.getIds().add(
                    CDADataTypesFactory.getInstance().createBaseRootII(planOfCare.getId(), null, null));
            planOfCareActivityProcedure.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(
                    planOfCare.getDate(), null));
            planOfCareActivityProcedure.setCode(CDADataTypesFactory.getInstance().createBaseCodeCD(planOfCare.getProcedure().getCode(), null,
                    planOfCare.getProcedure().getTerminolgy(), planOfCare.getProcedure().getDescription(), null));
        }
        planOfCareSection.createStrucDocText(PlanOfCareSectionTable.getInstance().getTable(planOfCareSection));

        return docList;
    }

    protected static List<ClinicalDocument> insertProblemEntry
            (List<Dto> problems, List<ClinicalDocument> docList) throws Exception {

        final ProblemSection problemSection = (ProblemSection) EObjectElementsFactory.getLogicalDocumentSection(docList.get(0), IClinicalDocumentFactory.x_EObjectTypes.PROBLEMS, "", "");

        for (final Dto dto : problems) {
            final ProblemDto problem = (ProblemDto) dto;
            ProblemAct problemAct = ContinuityOfCareDocumentFactory.createProblemsSectionEntry(null, false);
            problemSection.addAct(problemAct);
            problemAct.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(problem.getId(), null, null));
            problemAct.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(problem.getDate(), null));
            problemAct.getObservations().get(0).getValues().add(CDADataTypesFactory.getInstance().createProblemCodeCD(problem.getProblem().getCode(), "I9C", problem.getProblem().getDescription()));
            ProblemStatusObservation problemStatusObservation = CCDFactory.eINSTANCE.createProblemStatusObservation().init();
            problemAct.getObservations().get(0).addObservation(problemStatusObservation);
            problemStatusObservation.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(problem.getDate(), problem.getDate()));
            problemStatusObservation.getValues().add(CDADataTypesFactory.getInstance().createBaseCodeCE(problem.getStatus().getCode(), null, problem.getStatus().getTerminolgy(), problem.getStatus().getDescription(), null));
            ClinicalMappingEntryRelationships.getInstance().defineProblemActEntryRelationships(docList.get(0), problemAct);
        }
        problemSection.createStrucDocText(ProblemSectionTable.getInstance().getTable(problemSection));
        return docList;
    }
}