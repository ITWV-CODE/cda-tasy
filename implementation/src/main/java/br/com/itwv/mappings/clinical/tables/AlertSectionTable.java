package br.com.itwv.mappings.clinical.tables;

import br.com.itwv.cdatasy.base.html.templates.collections.AlertCollection;
import br.com.itwv.cdatasy.base.html.templates.objects.Alert;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import org.openhealthtools.mdht.uml.cda.Author;
import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.Participant2;
import org.openhealthtools.mdht.uml.cda.Supply;
import org.openhealthtools.mdht.uml.cda.ccd.*;
import org.openhealthtools.mdht.uml.hl7.datatypes.ANY;
import org.openhealthtools.mdht.uml.hl7.datatypes.CD;
import org.openhealthtools.mdht.uml.hl7.datatypes.ON;

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

            switch (IClinicalMapping.x_DocEntryStatusCode.valueOf(problemAct.getStatusCode().getOriginalText().getText())) {
                case ACTIVE:
                case NEW:
                case COMPLETED: {

                    Alert alert = new Alert();
                    alert.setDiagnosisDate(CDADataTypesFactory.getInstance().getPrettyPrintDate(problemAct.getEffectiveTime().getLow(), "yyyyMMdd",
                            "yyyy/MM/dd"));

                    for (Observation observation : problemAct.getObservations()) {

                        if (observation instanceof AlertObservation) {

                            if (((AlertObservation) observation).getAlertStatusObservation() != null) {
                                for (ANY value : ((AlertObservation) observation).getAlertStatusObservation().getValues()) {
                                    alert.setStatus(((CD) value).getDisplayName());
                                }
                            }
                            for (Participant2 participant2 : ((AlertObservation) observation).getParticipants()) {
                                if (participant2.getParticipantRole().getPlayingEntity().getCode() != null) {
                                    alert.setAllergenCode(participant2.getParticipantRole().getPlayingEntity().getCode().getCode());
                                    alert.setAllergenDescription(participant2.getParticipantRole().getPlayingEntity().getCode().getDisplayName());
                                }
                            }
                            for (ReactionObservation reactionObservation : ((AlertObservation) observation).getReactionObservations()) {
                                for (ANY value : reactionObservation.getValues()) {
                                    alert.setReactions(((CD) value).getDisplayName());
                                }

                            }

                            if (observation.getCode() != null) {
                                alert.setCategory(((AlertObservation) observation).getCode().getDisplayName());
                            }

                            for (Observation alertObservationObservation : ((AlertObservation) observation).getObservations())
                                if (alertObservationObservation instanceof SeverityObservation)
                                    for (ANY value : alertObservationObservation.getValues())
                                        alert.setSeverity(((CD) value).getDisplayName());

                            for (Supply supply : ((AlertObservation) observation).getSupplies()) {
                                for (Author author : supply.getAuthors()) {

                                    for (ON name : author.getAssignedAuthor().getRepresentedOrganization().getNames())
                                        alert.setSource(name.getText());
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
