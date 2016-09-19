package br.com.itwv.mappings.clinical.tables;

import br.com.itwv.cdatasy.base.html.templates.collections.ProblemCollection;
import br.com.itwv.cdatasy.base.html.templates.objects.Problem;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.ccd.ProblemAct;
import org.openhealthtools.mdht.uml.cda.ccd.ProblemObservation;
import org.openhealthtools.mdht.uml.cda.ccd.ProblemSection;
import org.openhealthtools.mdht.uml.cda.ccd.ProblemStatusObservation;
import org.openhealthtools.mdht.uml.hl7.datatypes.ANY;
import org.openhealthtools.mdht.uml.hl7.datatypes.CD;

public class ProblemSectionTable {
    private static ProblemSectionTable instance = null;

    protected ProblemSectionTable() {
    }

    public static ProblemSectionTable getInstance() {
        if (instance == null) {
            instance = new ProblemSectionTable();
        }
        return instance;
    }

    public String getTable(ProblemSection problemSection) {

        ProblemCollection problemCollection = new ProblemCollection();

        for (ProblemAct problemAct : problemSection.getProblemActs()) {

            switch (IClinicalMapping.x_DocEntryStatusCode.valueOf(problemAct.getStatusCode().getCode().toUpperCase())) {
                case ACTIVE:
                case NEW:
                case COMPLETED: {

                    Problem problem = new Problem();

                    for (Observation observation : problemAct.getObservations()) {
                        if (observation instanceof ProblemObservation) {
                            problem.setProblem(observation.getCode().getDisplayName());
                        }
                        for (Observation problemStatusObservation : observation.getObservations()) {
                            if (problemStatusObservation instanceof ProblemStatusObservation) {
                                for (ANY value : problemStatusObservation.getValues()) {
                                    problem.setStatus(((CD) value).getDisplayName());
                                }
                            }
                        }
                    }
                    problemCollection.add(problem);

                    break;
                }
                default:
                    break;
            }
        }
        if (problemCollection.size() == 0)
            return "<content ID=\"actproblem\">Não existem problemas activos.</content>";
        return problemCollection.toString();
    }
}
