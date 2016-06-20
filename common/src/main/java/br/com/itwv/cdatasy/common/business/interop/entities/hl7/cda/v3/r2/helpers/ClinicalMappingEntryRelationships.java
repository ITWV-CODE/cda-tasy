package br.com.itwv.cdatasy.common.business.interop.entities.hl7.cda.v3.r2.helpers;

import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.EntryRelationship;
import org.openhealthtools.mdht.uml.cda.Supply;
import org.openhealthtools.mdht.uml.cda.ccd.*;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActRelationshipEntryRelationship;

public class ClinicalMappingEntryRelationships {

    private static ClinicalMappingEntryRelationships instance = null;

    protected ClinicalMappingEntryRelationships() {
    }

    public static ClinicalMappingEntryRelationships getInstance() {
        if (instance == null) {
            instance = new ClinicalMappingEntryRelationships();
        }
        return instance;
    }

    public MedicationActivity defineMedicationActivityEntryRelationships(ClinicalDocument doc, MedicationActivity medicationActivity)
            throws Exception {

        for (EntryRelationship e : medicationActivity.getEntryRelationships()) {
            if (e.getAct() instanceof FulfillmentInstruction || e.getAct() instanceof Comment || e.getAct() instanceof PatientInstruction)
                CDADataTypesFactory.getInstance().defineEntryRelationship(e, x_ActRelationshipEntryRelationship.SUBJ, true);

            if (e.getSupply() instanceof Supply) {
                CDADataTypesFactory.getInstance().defineEntryRelationship(e, x_ActRelationshipEntryRelationship.REFR, false);

                for (EntryRelationship eSupply : e.getSupply().getEntryRelationships()) {
                    if (eSupply.getAct() instanceof FulfillmentInstruction)
                        CDADataTypesFactory.getInstance().defineEntryRelationship(eSupply, x_ActRelationshipEntryRelationship.SUBJ, true);
                    for (EntryRelationship eAct : eSupply.getAct().getEntryRelationships()) {
                        if (eAct.getAct() instanceof Comment)
                            CDADataTypesFactory.getInstance().defineEntryRelationship(eAct, x_ActRelationshipEntryRelationship.SUBJ, true);
                    }
                }
            }
        }
        return medicationActivity;
    }

    public ProblemAct defineProblemActEntryRelationships(ClinicalDocument doc, ProblemAct problemAct) throws Exception {

        for (EntryRelationship e : problemAct.getEntryRelationships()) {

            if (e.getObservation() instanceof AlertObservation || e.getObservation() instanceof ProblemObservation) {
                CDADataTypesFactory.getInstance().defineEntryRelationship(e, x_ActRelationshipEntryRelationship.SUBJ, false);

                for (EntryRelationship eObservation : e.getObservation().getEntryRelationships()) {

                    if (eObservation.getAct() instanceof FulfillmentInstruction || eObservation.getAct() instanceof Comment
                            || eObservation.getAct() instanceof PatientInstruction)

                        CDADataTypesFactory.getInstance().defineEntryRelationship(eObservation, x_ActRelationshipEntryRelationship.SUBJ, true);

                    if (eObservation.getObservation() instanceof AlertStatusObservation
                            || eObservation.getObservation() instanceof ProblemStatusObservation || eObservation.getSupply() instanceof Supply)
                        CDADataTypesFactory.getInstance().defineEntryRelationship(eObservation, x_ActRelationshipEntryRelationship.REFR, false);

                    if (eObservation.getObservation() instanceof SeverityObservation)
                        CDADataTypesFactory.getInstance().defineEntryRelationship(eObservation, x_ActRelationshipEntryRelationship.SAS, true);

                    if (eObservation.getObservation() instanceof ReactionObservation)
                        CDADataTypesFactory.getInstance().defineEntryRelationship(eObservation, x_ActRelationshipEntryRelationship.MFST, true);
                }
            }
        }
        return problemAct;
    }

    public ProcedureActivityProcedure defineProcedureActivityProcedureEntryRelationships(ClinicalDocument doc,
                                                                                         ProcedureActivityProcedure procedureActivityProcedure) throws Exception {

        for (EntryRelationship e : procedureActivityProcedure.getEntryRelationships()) {

            if (e.getObservation() instanceof ProcedureActivityObservation) {
                CDADataTypesFactory.getInstance().defineEntryRelationship(e, x_ActRelationshipEntryRelationship.SUBJ, true);

                for (EntryRelationship eObservation : e.getObservation().getEntryRelationships()) {
                    if (eObservation.getAct() instanceof FulfillmentInstruction || eObservation.getAct() instanceof Comment
                            || eObservation.getAct() instanceof PatientInstruction)
                        CDADataTypesFactory.getInstance().defineEntryRelationship(eObservation, x_ActRelationshipEntryRelationship.SUBJ, true);

                    if (eObservation.getSupply() instanceof Supply)
                        CDADataTypesFactory.getInstance().defineEntryRelationship(eObservation, x_ActRelationshipEntryRelationship.REFR, false);
                }
            }
        }
        return procedureActivityProcedure;
    }
}