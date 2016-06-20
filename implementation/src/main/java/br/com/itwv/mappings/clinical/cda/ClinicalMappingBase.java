package br.com.itwv.mappings.clinical.cda;

import br.com.itwv.br.com.itwv.dto.AllergyDTO;
import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.ContinuityOfCareDocumentFactory;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.IClinicalDocumentFactory;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.helpers.ClinicalMappingEntryRelationships;
import br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.helpers.EObjectElementsFactory;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import br.com.itwv.mappings.clinical.tables.AlertSectionTable;
import org.eclipse.emf.ecore.EObject;
import org.openhealthtools.mdht.uml.cda.*;
import org.openhealthtools.mdht.uml.cda.ccd.AlertsSection;
import org.openhealthtools.mdht.uml.cda.ccd.ProblemAct;
import org.openhealthtools.mdht.uml.hl7.vocab.EntityClassRoot;
import org.openhealthtools.mdht.uml.hl7.vocab.ParticipationType;
import org.openhealthtools.mdht.uml.hl7.vocab.RoleClassRoot;

import java.util.List;

import static br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping.x_DocEntryStatusCode;

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

    protected static List<ClinicalDocument> insertMedicationEntry(List<ClinicalDocument> docList, EObject... eObjects) throws Exception {

        return docList;
    }

    protected static List<ClinicalDocument> insertAlertEntry(List<Dto> allergies, List<ClinicalDocument> docList, EObject... eObjects) throws Exception {

        final AlertsSection alertsSection = (AlertsSection) EObjectElementsFactory.getLogicalDocumentSection(docList.get(0), IClinicalDocumentFactory.x_EObjectTypes.ALLERGIES, "", "");
        ProblemAct problemAct = null;

        for (final Dto dto : allergies) {
            final AllergyDTO allergy = (AllergyDTO) dto;
            problemAct = ContinuityOfCareDocumentFactory.createAlertsSectionEntry(problemAct, false);
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

            //CDADataTypesFactory.getInstance().createBaseSeverityObservation(problemAct.getObservations().get(0), iam.getIam4_AllergySeverityCode().getCe1_Identifier().getValue(), null, iam.getIam4_AllergySeverityCode().getCe3_NameOfCodingSystem().getValue(), iam.getIam4_AllergySeverityCode().getCe2_Text().getValue());

            CDADataTypesFactory.getInstance().createBaseAlertStatusObservation(problemAct.getObservations().get(0), allergy.getStatus().getCode(), null, null, allergy.getStatus().getDescription(), null);


            problemAct = ClinicalMappingIndex.getInstance().indexAlerts(docList.get(0), problemAct);
            ClinicalMappingEntryRelationships.getInstance().defineProblemActEntryRelationships(docList.get(0), problemAct);
        }
        if (eObjects.length == 0)
            alertsSection.addAct(problemAct);
        alertsSection.createStrucDocText(AlertSectionTable.getInstance().getTable(alertsSection));

        return docList;
    }

    protected static List<ClinicalDocument> updateProblemEntry(List<ClinicalDocument> docList, x_DocEntryStatusCode x_DocEntryStatusCode) throws Exception {

        return docList;

    }

    protected static List<ClinicalDocument> updateProcedureEntry(List<ClinicalDocument> docList, x_DocEntryStatusCode x_DocEntryStatusCode) throws Exception {

        return docList;

    }

    protected static List<ClinicalDocument> updateAlertEntry(List<ClinicalDocument> docList, x_DocEntryStatusCode x_DocEntryStatusCode) throws Exception {

        return docList;
    }

    protected static List<ClinicalDocument> updateImmunizationEntry(List<ClinicalDocument> docList, x_DocEntryStatusCode x_DocEntryStatusCode) throws Exception {
        return docList;
    }

    protected static List<ClinicalDocument> updateMedicationEntry(List<ClinicalDocument> docList, x_DocEntryStatusCode x_DocEntryStatusCode) throws Exception {
        return docList;
    }

    protected static List<ClinicalDocument> insertImmunizationEntry(List<ClinicalDocument> docList, EObject... eObjects)
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

    protected List<ClinicalDocument> mapProblemsFactory(List<ClinicalDocument> docList) throws Exception {

        return ClinicalMappingBase.insertProblemEntry(docList);
    }

    protected List<ClinicalDocument> mapProceduresFactory(List<ClinicalDocument> docList) throws Exception {

        return ClinicalMappingBase.insertProcedureEntry(docList);
    }

    protected static List<ClinicalDocument> insertProcedureEntry(List<ClinicalDocument> docList, EObject... eObjects)
            throws Exception {

        return docList;

    }

    protected static List<ClinicalDocument> insertProblemEntry(List<ClinicalDocument> docList, EObject... eObjects)
            throws Exception {

        return docList;
    }
}