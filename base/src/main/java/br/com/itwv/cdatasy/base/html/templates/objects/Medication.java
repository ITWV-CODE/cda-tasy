package br.com.itwv.cdatasy.base.html.templates.objects;

public class Medication {
    private String substance;
    private String dosage;
    private String date;

    public Medication() {
    }

    public Medication(String substance, String dosage, String date) {

        this.substance = substance;
        this.dosage = dosage;
        this.date = date;
    }

    public String getSubstance() {
        return (substance == null) ? "--" : substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getDosage() {
        return (dosage == null) ? "--" : dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDate() {
        return (date == null) ? "--" : date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
