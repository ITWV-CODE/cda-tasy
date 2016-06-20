package br.com.itwv.mappings.clinical.tables;

import br.com.itwv.cdatasy.base.html.templates.collections.MedicationCollection;
import br.com.itwv.cdatasy.base.html.templates.objects.Medication;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import org.openhealthtools.mdht.uml.cda.Act;
import org.openhealthtools.mdht.uml.cda.ccd.Comment;
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
			switch (IClinicalMapping.x_DocEntryStatusCode.valueOf(activity.getStatusCode().getOriginalText().getText())) {
			case ACTIVE:
			case NEW:
			case COMPLETED: {

				medication.setActiveSubstance(activity.getConsumable().getManufacturedProduct().getManufacturedMaterial().getCode().getTranslations()
						.get(1).getDisplayName());

				medication.setCodeATC(activity.getConsumable().getManufacturedProduct().getManufacturedMaterial().getCode().getCode());

				for (Act act : activity.getActs()) {
					if (act instanceof Comment)
						medication.setPharmaceuticalForm(((Comment) act).getText().getText());
				}

				if (activity.getDoseQuantity().getLow().getUnit() != null)
					medication.setDosage(activity.getDoseQuantity().getLow().getUnit());

				medication.setInitialDate(CDADataTypesFactory.getInstance().getPrettyPrintDate(
						((IVL_TS) activity.getEffectiveTimes().get(0)).getLow(), "yyyyMMdd", "yyyy/MM/dd"));

				medication.setFinalDate(CDADataTypesFactory.getInstance().getPrettyPrintDate(
						((IVL_TS) activity.getEffectiveTimes().get(0)).getHigh(), "yyyyMMdd", "yyyy/MM/dd"));

				if (activity.getPatientInstructions().size() > 0)
					medication.setApplicationRate(((MedicationActivity) activity).getPatientInstructions().get(0).getText().getText());

				medication.setDescription(medication.getActiveSubstance() + "," + medication.getDosage() + "," + medication.getPharmaceuticalForm()
						+ "," + activity.getConsumable().getManufacturedProduct().getManufacturedMaterial().getCode().getDisplayName());

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
