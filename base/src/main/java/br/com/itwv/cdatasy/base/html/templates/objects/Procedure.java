package br.com.itwv.cdatasy.base.html.templates.objects;

public class Procedure {
	private String classificationCode;
	private String code;
	private String description;
	private String initialDate;
	private String finalDate;

	public Procedure() {
		this(null, null, null, null, null);
	}

	public Procedure(String classificationCode, String code, String description, String initialDate, String finalDate) {
		super();
		this.setClassificationCode(classificationCode);
		this.setCode(code);
		this.setDescription(description);
		this.setInitialDate(initialDate);
		this.setFinalDate(finalDate);
	}

	public String getClassificationCode() {
		return (classificationCode == null) ? "--" : classificationCode;
	}

	public void setClassificationCode(String classificationCode) {
		this.classificationCode = classificationCode;
	}

	public String getCode() {
		return (code == null) ? "--" : code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return (description == null) ? "--" : description;
	}

	public void setDescription(String description) {
		this.description = description;
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
}