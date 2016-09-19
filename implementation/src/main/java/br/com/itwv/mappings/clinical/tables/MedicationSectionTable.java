package br.com.itwv.mappings.clinical.tables;

import br.com.itwv.cdatasy.base.html.templates.collections.MedicationCollection;
import br.com.itwv.cdatasy.base.html.templates.objects.Medication;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import org.openhealthtools.mdht.uml.cda.ccd.MedicationActivity;
import org.openhealthtools.mdht.uml.cda.ccd.MedicationsSection;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_TS;

public class MedicationSectionTable {
    private static MedicationSectionTable instance = null;

    protected MedicationSectionTable() {
    }

    public static MedicationSectionTable getInstance() {
        if (instance == null) {
            instance = new MedicationSectionTable();
        }
        return instance;
    }

    public String getTable(MedicationsSection medicationsSection) {

        MedicationCollection medicationCollection = new MedicationCollection();
        for (MedicationActivity activity : medicationsSection.getMedicationActivities()) {

            Medication medication = new Medication();
            switch (IClinicalMapping.x_DocEntryStatusCode.valueOf(activity.getStatusCode().getCode().toUpperCase())) {
                case ACTIVE:
                case NEW:
                case COMPLETED: {

                    medication.setSubstance(activity.getConsumable().getManufacturedProduct().getManufacturedMaterial().getCode().getDisplayName());

                    medication.setDate(CDADataTypesFactory.getInstance().getPrettyPrintDate(
                            ((IVL_TS) activity.getEffectiveTimes().get(0)).getHigh(), "yyyyMMdd", "yyyy/MM/dd"));

                    if (activity.getPatientInstructions().size() > 0)
                        medication.setDosage(activity.getPatientInstructions().get(0).getText().getText());

                    medicationCollection.add(medication);
                }
                break;
                default:
                    break;
            }
        }
        if (medicationCollection.size() == 0)
            return "<content ID=\"medicationdescription\">Não existem medicamentos.</content>";
        return medicationCollection.toString();
    }
}
