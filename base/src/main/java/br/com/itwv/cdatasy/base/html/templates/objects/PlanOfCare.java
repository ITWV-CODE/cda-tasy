package br.com.itwv.cdatasy.base.html.templates.objects;

public class PlanOfCare {
    private String description;
    private String date;

    public PlanOfCare() {
    }

    public PlanOfCare(String description, String date) {
        super();
        this.description = description;
        this.date = date;
    }

    public String getDescription() {
        return (description == null) ? "--" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return (date == null) ? "--" : date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}