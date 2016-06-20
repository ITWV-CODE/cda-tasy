package br.com.itwv.mappings.clinical.tables;

import br.com.itwv.cdatasy.base.html.templates.collections.ImmunizationCollection;
import br.com.itwv.cdatasy.base.html.templates.objects.Immunization;
import br.com.itwv.cdatasy.common.business.interop.mappings.interfaces.IClinicalMapping;
import br.com.itwv.cdatasy.common.business.interop.mappings.types.CDADataTypesFactory;
import org.openhealthtools.mdht.uml.cda.ccd.ImmunizationsSection;
import org.openhealthtools.mdht.uml.cda.ccd.MedicationActivity;

public class ImmunizationSectionTable {
	private static ImmunizationSectionTable instance = null;

	protected ImmunizationSectionTable() {
	}

	public static ImmunizationSectionTable getInstance() {
		if (instance == null) {
			instance = new ImmunizationSectionTable();
		}
		return instance;
	}

	public String getTable(ImmunizationsSection immunizationsSection) {

		ImmunizationCollection immunizationCollection = new ImmunizationCollection();
		for (MedicationActivity activity : immunizationsSection.getMedicationActivities()) {

			Immunization immunization = new Immunization();
			switch (IClinicalMapping.x_DocEntryStatusCode.valueOf(activity.getStatusCode().getOriginalText().getText())) {
			case ACTIVE:
			case NEW:
			case COMPLETED: {

				immunization.setInitialDate(CDADataTypesFactory.getInstance().getPrettyPrintDate(activity.getEffectiveTimes().get(0), "yyyyMMdd",
						"yyyy/MM/dd"));
				immunization.setCode(activity.getConsumable().getManufacturedProduct().getManufacturedMaterial().getCode().getCode());
				immunization.setName(activity.getConsumable().getManufacturedProduct().getManufacturedMaterial().getCode().getDisplayName());
				immunization.setComercialCode(activity.getConsumable().getManufacturedProduct().getManufacturedMaterial().getCode().getOriginalText()
						.getText());
				immunization.setDosage(activity.getDoseQuantity().getLow().getValue().toString());
				immunization.setLot(activity.getConsumable().getManufacturedProduct().getManufacturedMaterial().getLotNumberText().getText());
				immunizationCollection.add(immunization);
			}
				break;
			default:
				break;
			}
		}
		if (immunizationCollection.size() == 0)
			return "<content ID=\"immunizationsdescription\">Não existem vacinas.</content>";
		return immunizationCollection.toString();
	}
}
