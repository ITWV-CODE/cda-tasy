package br.com.itwv.mappings.clinical.tables;

import br.com.itwv.cdatasy.base.html.templates.collections.FamilyHistoryCollection;
import br.com.itwv.cdatasy.base.html.templates.objects.FamilyHistory;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import org.openhealthtools.mdht.uml.cda.Observation;
import org.openhealthtools.mdht.uml.cda.ccd.FamilyHistorySection;

public class FamilyHistorySectionTable {
    private static FamilyHistorySectionTable instance = null;

    protected FamilyHistorySectionTable() {
    }

    public static FamilyHistorySectionTable getInstance() {
        if (instance == null) {
            instance = new FamilyHistorySectionTable();
        }
        return instance;
    }

    public String getTable(FamilyHistorySection familyHistorySection) {

        FamilyHistoryCollection familyHistoryCollection = new FamilyHistoryCollection();
        for (Observation observation : familyHistorySection.getObservations()) {

            switch (IClinicalMapping.x_DocEntryStatusCode.valueOf(observation.getStatusCode().getCode().toUpperCase())) {
                case ACTIVE:
                case NEW:
                case COMPLETED: {

                    FamilyHistory familyHistory = new FamilyHistory();
                    familyHistory.setProblem(observation.getCode().getDisplayName());
                    familyHistory.setRelationship(observation.getSubject().getRelatedSubject().getCode().getDisplayName());
                    familyHistoryCollection.add(familyHistory);
                    break;
                }
                default:
                    break;
            }
        }
        if (familyHistoryCollection.size() == 0)
            return "<content ID=\"allergy\">Não existem antecedentes familiares.</content>";
        return familyHistoryCollection.toString();
    }
}
