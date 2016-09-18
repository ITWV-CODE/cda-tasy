package br.com.itwv.mappings.clinical.tables;

import br.com.itwv.cdatasy.base.html.templates.collections.PlanOfCareCollection;
import br.com.itwv.cdatasy.base.html.templates.objects.PlanOfCare;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import org.openhealthtools.mdht.uml.cda.ccd.PlanOfCareSection;

public class PlanOfCareSectionTable {
    private static PlanOfCareSectionTable instance = null;

    protected PlanOfCareSectionTable() {
    }

    public static PlanOfCareSectionTable getInstance() {
        if (instance == null) {
            instance = new PlanOfCareSectionTable();
        }
        return instance;
    }

    public String getTable(PlanOfCareSection planOfCareSection) {

        PlanOfCareCollection planOfCareCollection = new PlanOfCareCollection();

        for (org.openhealthtools.mdht.uml.cda.Procedure planOfCareActivityProcedure : planOfCareSection.getProcedures()) {

            switch (IClinicalMapping.x_DocEntryStatusCode.valueOf(planOfCareActivityProcedure.getStatusCode().getCode().toUpperCase())) {
                case ACTIVE:
                case NEW:
                case COMPLETED: {

                    PlanOfCare planOfCare = new PlanOfCare();
                    planOfCare.setDate(CDADataTypesFactory.getInstance().getPrettyPrintDate(planOfCareActivityProcedure.getEffectiveTime().getLow(),
                            "yyyyMMdd", "yyyy/MM/dd"));
                    planOfCare.setDescription(planOfCareActivityProcedure.getCode().getDisplayName());
                    planOfCareCollection.add(planOfCare);
                    break;
                }
                default:
                    break;
            }
        }
        if (planOfCareCollection.size() == 0)
            return "<content ID=\"surgeries\">Sem plano de saúde conhecido.</content>";
        return planOfCareCollection.toString();
    }
}
