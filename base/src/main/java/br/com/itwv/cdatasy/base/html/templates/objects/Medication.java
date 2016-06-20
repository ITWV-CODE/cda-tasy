package br.com.itwv.cdatasy.base.html.templates.objects;

public class Medication {
	private String activeSubstance;
	private String codeATC;
	private String pharmaceuticalForm;
	private String dosage;
	private String initialDate;
	private String finalDate;
	private String applicationRate;
	private String description;

	public Medication() {
		this(null, null, null, null, null, null, null, null);
	}

	public Medication(String activeSubstance, String codeATC, String pharmaceuticalForm, String dosage, String initialDate, String finalDate,
			String applicationRate, String description) {

		this.setActiveSubstance(activeSubstance);
		this.setCodeATC(codeATC);
		this.setPharmaceuticalForm(pharmaceuticalForm);
		this.setDosage(dosage);
		this.setInitialDate(initialDate);
		this.setFinalDate(finalDate);
		this.setApplicationRate(applicationRate);
		this.setDescription(description);
	}

	public String getActiveSubstance() {
		return (activeSubstance == null) ? "--" : activeSubstance;
	}

	public void setActiveSubstance(String activeSubstance) {
		this.activeSubstance = activeSubstance;
	}

	public String getCodeATC() {
		return (codeATC == null) ? "--" : codeATC;
	}

	public void setCodeATC(String codeATC) {
		this.codeATC = codeATC;
	}

	public String getPharmaceuticalForm() {
		return (pharmaceuticalForm == null) ? "--" : pharmaceuticalForm;
	}

	public void setPharmaceuticalForm(String pharmaceuticalForm) {
		this.pharmaceuticalForm = pharmaceuticalForm;
	}

	public String getDosage() {
		return (dosage == null) ? "--" : dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getInitialDate() {
		return (initialDate == null) ? "--" : initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}

	public String getFinalDate() {
		return (finalDate == null) ? "--" : finalDate;
	}

	public void setFinalDate(String finalDate) {
		this.finalDate = finalDate;
	}

	public String getApplicationRate() {
		return (applicationRate == null) ? "--" : applicationRate;
	}

	public void setApplicationRate(String applicationRate) {
		this.applicationRate = applicationRate;
	}

	public String getDescription() {
		return (description == null) ? "--" : description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
