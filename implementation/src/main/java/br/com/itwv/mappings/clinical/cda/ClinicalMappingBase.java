package br.com.itwv.mappings.clinical.cda;

import br.com.itwv.br.com.itwv.dto.AllergyDTO;
import br.com.itwv.br.com.itwv.dto.ProblemDTO;
import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.ContinuityOfCareDocumentFactory;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.IClinicalDocumentFactory;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.helpers.ClinicalMappingEntryRelationships;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.helpers.EObjectElementsFactory;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import br.com.itwv.mappings.clinical.tables.AlertSectionTable;
import br.com.itwv.mappings.clinical.tables.ProblemSectionTable;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.cda.ccd.*;
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

    protected static List<ClinicalDocument> insertMedicationEntry(List<ClinicalDocument> docList) throws Exception {

        return docList;
    }

    protected static List<ClinicalDocument> insertAlertEntry(List<Dto> allergies, List<ClinicalDocument> docList) throws Exception {

        final AlertsSection alertsSection = (AlertsSection) EObjectElementsFactory.getLogicalDocumentSection(docList.get(0), IClinicalDocumentFactory.x_EObjectTypes.ALLERGIES, "", "");

        for (final Dto dto : allergies) {
            final AllergyDTO allergy = (AllergyDTO) dto;
            ProblemAct problemAct = ContinuityOfCareDocumentFactory.createAlertsSectionEntry(null, false);
            alertsSection.addAct(problemAct);
            problemAct.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(allergy.getId(), null, null));
            problemAct.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(allergy.getDate(), null));
            problemAct.getObservations().get(0).setCode(CDADataTypesFactory.getInstance().createBaseCodeCD(allergy.getSubstance().getCode(), null, "SUBS", allergy.getSubstance().getDescription(), null));
            problemAct.getObservations().get(0).getValues().add(CDADataTypesFactory.getInstance().createBaseCodeCD(allergy.getType().getCode(), null, "TIPOS", null, CDADataTypesFactory.getInstance().createBaseED(null, allergy.getType().getDescription())));

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
            CDADataTypesFactory.getInstance().createBaseReactionObservation(problemAct.getObservations().get(0), allergy.getReaction().getCode(), null, null, allergy.getReaction().getDescription());
            CDADataTypesFactory.getInstance().createBaseAlertStatusObservation(problemAct.getObservations().get(0), allergy.getStatus().getCode(), null, null, allergy.getStatus().getDescription(), null);
            ClinicalMappingEntryRelationships.getInstance().defineProblemActEntryRelationships(docList.get(0), problemAct);
        }
        alertsSection.createStrucDocText(AlertSectionTable.getInstance().getTable(alertsSection));
        return docList;
    }


    protected static List<ClinicalDocument> insertImmunizationEntry(List<ClinicalDocument> docList)
            throws Exception {

        return docList;
    }

    protected static List<ClinicalDocument> mapMedicationsFactory(List<ClinicalDocument> docList) throws Exception {
        return ClinicalMappingBase.insertMedicationEntry(docList);
    }

    protected static List<ClinicalDocument> mapImmunizationsFactory(List<ClinicalDocument> docList) throws Exception {

        return ClinicalMappingBase.insertImmunizationEntry(docList);
    }

    protected static List<ClinicalDocument> mapAlertsFactory(final List<Dto> allergies, List<ClinicalDocument> docList) throws Exception {

        return ClinicalMappingBase.insertAlertEntry(allergies, docList);
    }

    protected List<ClinicalDocument> mapProblemsFactory(final List<Dto> problems, List<ClinicalDocument> docList) throws Exception {

        return ClinicalMappingBase.insertProblemEntry(problems, docList);
    }

    protected List<ClinicalDocument> mapProceduresFactory(List<ClinicalDocument> docList) throws Exception {

        return ClinicalMappingBase.insertProcedureEntry(docList);
    }

    protected static List<ClinicalDocument> insertProcedureEntry(List<ClinicalDocument> docList)
            throws Exception {

        return docList;

    }

    protected static List<ClinicalDocument> insertProblemEntry(List<Dto> problems, List<ClinicalDocument> docList)
            throws Exception {

        final ProblemSection problemSection = (ProblemSection) EObjectElementsFactory.getLogicalDocumentSection(docList.get(0), IClinicalDocumentFactory.x_EObjectTypes.PROBLEMS, "", "");

        for (final Dto dto : problems) {
            final ProblemDTO problem = (ProblemDTO) dto;
            ProblemAct problemAct = ContinuityOfCareDocumentFactory.createProblemsSectionEntry(null, false);
            problemSection.addAct(problemAct);
            problemAct.getIds().add(CDADataTypesFactory.getInstance().createBaseRootII(problem.getId(), null, null));
            problemAct.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(problem.getDate(), null));
            problemAct.getObservations().get(0).getValues().add(CDADataTypesFactory.getInstance().createProblemCodeCD(problem.getProblem().getCode(), "I9C", problem.getProblem().getDescription()));
            ProblemStatusObservation problemStatusObservation = CCDFactory.eINSTANCE.createProblemStatusObservation().init();
            problemAct.getObservations().get(0).addObservation(problemStatusObservation);
            problemStatusObservation.setEffectiveTime(CDADataTypesFactory.getInstance().createBaseEffectiveTimeIVL_TS(problem.getDate(), problem.getDate()));
            problemStatusObservation.getValues().add(CDADataTypesFactory.getInstance().createBaseCodeCE(problem.getStatus().getCode(), null, null, problem.getStatus().getDescription(), null));
            ClinicalMappingEntryRelationships.getInstance().defineProblemActEntryRelationships(docList.get(0), problemAct);
        }
        problemSection.createStrucDocText(ProblemSectionTable.getInstance().getTable(problemSection));
        return docList;
    }
}