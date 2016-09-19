package br.com.itwv.mappings.clinical.cda;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;

import java.util.List;
import java.util.Map;

public class ClinicalMapping extends ClinicalMappingBase implements IClinicalMapping {

    private static ClinicalMapping instance = null;

    protected ClinicalMapping() {
        super();
    }

    public static ClinicalMapping getInstance() {
        if (instance == null) {
            instance = new ClinicalMapping();
        }
        return instance;
    }

    @Override
    public Object mapClinicalSections(final List<ClinicalDocument> docList, final x_DocClinicalSectionType x_DocClinicalSectionType, final Map<String, List<Dto>> segments) throws Exception {

        super.mapContent(docList);
        super.mapStructuredBody(docList);

        switch (x_DocClinicalSectionType) {
            case MEDICATIONS:
                return super.mapMedicationsFactory(segments.get("MedicationDto"),docList);
            case ALERTS:
                return super.mapAlertsFactory(segments.get("AllergyDto"), docList);
            case IMMUNIZATIONS:
                return super.mapImmunizationsFactory(docList);
            case PROBLEMS:
                return super.mapProblemsFactory(segments.get("ProblemDto"), docList);
            case PLAN_OF_CARE:
                return super.mapPlanOfCareFactory(segments.get("PlanOfCareDto"), docList);
            case ENCOUNTERS:
                return super.mapEncountersFactory(segments.get("EncounterDto"), docList);
            case FAMILY_HISTORY:
                return super.mapFamilyHistoryFactory(segments.get("FamilyHistoryDto"), docList);
            default:
                return null;
        }
    }
}
