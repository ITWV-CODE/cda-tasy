package br.com.itwv.cdatasy.base.html.templates.objects;

public class Alert {
	private String allergenCode;
	private String allergenDescription;
	private String diagnosisDate;
	private String reactions;
	private String status;
	private String severity;
	private String category;
	private String source;

	public Alert() {
		this(null, null, null, null, null, null, null, null);
	};

	public Alert(String allergenCode, String allergenDescription, String diagnosisDate, String reactions, String status, String severity,
			String category, String source) {

		this.setAllergenCode(allergenCode);
		this.setAllergenDescription(allergenDescription);
		this.setDiagnosisDate(diagnosisDate);
		this.setReactions(reactions);
		this.setStatus(status);
		this.setSeverity(severity);
		this.setCategory(category);
		this.setSource(source);
	}

	public String getAllergenCode() {
		return (allergenCode == null) ? "--" : allergenCode;
	}

	public void setAllergenCode(String allergenCode) {
		this.allergenCode = allergenCode;
	}

	public String getAllergenDescription() {
		return (allergenDescription == null) ? "--" : allergenDescription;
	}

	public void setAllergenDescription(String allergenDescription) {
		this.allergenDescription = allergenDescription;
	}

	public String getDiagnosisDate() {
		return (diagnosisDate == null) ? "--" : diagnosisDate;
	}

	public void setDiagnosisDate(String diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}

	public String getReactions() {
		return (reactions == null) ? "--" : reactions;
	}

	public void setReactions(String reactions) {
		this.reactions = reactions;
	}

	public String getStatus() {
		return (status == null) ? "--" : status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSeverity() {
		return (severity == null) ? "--" : severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getCategory() {
		return (category == null) ? "--" : category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSource() {
		return (source == null) ? "--" : source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}