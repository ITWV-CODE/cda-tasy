package br.com.itwv.mappings.clinical.tables;

import br.com.itwv.cdatasy.base.html.templates.collections.AlertCollection;
import br.com.itwv.cdatasy.base.html.templates.objects.Alert;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.Participant2;
import org.openhealthtools.mdht.uml.cda.ccd.AlertObservation;
import org.openhealthtools.mdht.uml.cda.ccd.AlertsSection;
import org.openhealthtools.mdht.uml.cda.ccd.ProblemAct;
import org.openhealthtools.mdht.uml.cda.ccd.ReactionObservation;
import org.openhealthtools.mdht.uml.hl7.datatypes.ANY;
import org.openhealthtools.mdht.uml.hl7.datatypes.CD;

public class AlertSectionTable {
    private static AlertSectionTable instance = null;

    protected AlertSectionTable() {
    }

    public static AlertSectionTable getInstance() {
        if (instance == null) {
            instance = new AlertSectionTable();
        }
        return instance;
    }

    public String getTable(AlertsSection alertsSection) {

        AlertCollection alertCollection = new AlertCollection();
        for (ProblemAct problemAct : alertsSection.getProblemActs()) {

            switch (IClinicalMapping.x_DocEntryStatusCode.valueOf(problemAct.getStatusCode().getCode().toUpperCase())) {
                case ACTIVE:
                case NEW:
                case COMPLETED: {

                    Alert alert = new Alert();

                    for (Observation observation : problemAct.getObservations()) {

                        if (observation instanceof AlertObservation) {

                            if (((AlertObservation) observation).getAlertStatusObservation() != null) {
                                for (ANY value : ((AlertObservation) observation).getAlertStatusObservation().getValues()) {
                                    alert.setStatus(((CD) value).getDisplayName());
                                }
                            }
                            for (Participant2 participant2 : ((AlertObservation) observation).getParticipants()) {
                                if (participant2.getParticipantRole().getPlayingEntity().getCode() != null) {
                                    alert.setSubstance(participant2.getParticipantRole().getPlayingEntity().getCode().getDisplayName());
                                }
                            }
                            for (ReactionObservation reactionObservation : ((AlertObservation) observation).getReactionObservations()) {
                                for (ANY value : reactionObservation.getValues()) {
                                    alert.setReaction(((CD) value).getDisplayName());
                                }
                            }
                        }
                    }
                    alertCollection.add(alert);

                    break;
                }
                default:
                    break;
            }
        }
        if (alertCollection.size() == 0)
            return "<content ID=\"allergy\">Não existem reacções adversas.</content>";
        return alertCollection.toString();
    }
}
