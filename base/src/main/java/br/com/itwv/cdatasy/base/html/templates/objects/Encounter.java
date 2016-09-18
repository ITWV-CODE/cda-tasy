package br.com.itwv.cdatasy.base.html.templates.objects;

public class Encounter {
    private String description;
    private String date;
    private String location;

    public Encounter() {
    }

    public Encounter(String description, String date, String location) {
        super();
        this.description = description;
        this.date = date;
        this.location = location;
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

    public String getLocation() {
        return (location == null) ? "--" : location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}