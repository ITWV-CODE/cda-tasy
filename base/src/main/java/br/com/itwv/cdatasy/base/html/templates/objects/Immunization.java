package br.com.itwv.cdatasy.base.html.templates.objects;

public class Immunization {
	private String code;
	private String name;
	private String comercialCode;
	private String initialDate;
	private String lot;
	private String dosage;

	public Immunization() {
		this(null, null, null, null, null, null);
	}

	public Immunization(String code, String name, String comercialCode, String initialDate, String lot, String dosage) {

		this.setCode(code);
		this.setName(name);
		this.setComercialCode(comercialCode);
		this.setInitialDate(initialDate);
		this.setLot(lot);
		this.setDosage(dosage);
	}

	public String getCode() {
		return (code == null) ? "--" : code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return (name == null) ? "--" : name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComercialCode() {
		return (comercialCode == null) ? "--" : comercialCode;
	}

	public void setComercialCode(String comercialCode) {
		this.comercialCode = comercialCode;
	}

	public String getInitialDate() {
		return (initialDate == null) ? "--" : initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}

	public String getLot() {
		return (lot == null) ? "--" : lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getDosage() {
		return (dosage == null) ? "--" : dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
}
