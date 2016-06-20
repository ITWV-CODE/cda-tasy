package br.com.itwv.mappings.clinical.comparable;

import org.openhealthtools.mdht.uml.cda.SubstanceAdministration;
import org.openhealthtools.mdht.uml.cda.ccd.MedicationActivity;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_TS;

public class MedicationActivityComparable implements Comparable<MedicationActivity> {
	private SubstanceAdministration medicationActivity;

	public MedicationActivityComparable(SubstanceAdministration activity) {
		this.medicationActivity = activity;
	}

	public int compareTo(MedicationActivity medicationActivity) {

		try {
			if (this.medicationActivity.getConsumable().getManufacturedProduct().getManufacturedMaterial().getCode().getCode()
					.equals(medicationActivity.getConsumable().getManufacturedProduct().getManufacturedMaterial().getCode().getCode())
					&& this.medicationActivity
							.getConsumable()
							.getManufacturedProduct()
							.getManufacturedMaterial()
							.getCode()
							.getTranslations()
							.get(0)
							.getCode()
							.equals(medicationActivity.getConsumable().getManufacturedProduct().getManufacturedMaterial().getCode().getTranslations()
									.get(0).getCode())
					&& this.medicationActivity
							.getConsumable()
							.getManufacturedProduct()
							.getManufacturedMaterial()
							.getCode()
							.getTranslations()
							.get(1)
							.getCode()
							.equals(medicationActivity.getConsumable().getManufacturedProduct().getManufacturedMaterial().getCode().getTranslations()
									.get(1).getCode())
					&& this.medicationActivity.getDoseQuantity().getUnit().equals(medicationActivity.getDoseQuantity().getUnit())
					&& ((IVL_TS) this.medicationActivity.getEffectiveTimes().get(0)).getLow().getValue()
							.equals(((IVL_TS) medicationActivity.getEffectiveTimes().get(0)).getLow().getValue()))
				return 1;
		} catch (Exception e) {
			return 0;
		}
		return 0;
	}

	@Override
	public boolean equals(Object e) {
		return this.compareTo((MedicationActivity) e) == 1 ? true : false;
	}
}
