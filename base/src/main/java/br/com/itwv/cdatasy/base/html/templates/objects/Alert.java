package br.com.itwv.cdatasy.base.html.templates.objects;

public class Alert {
    private String substance;
    private String reaction;
    private String status;

    public Alert() {
    }

    public Alert(String substance, String reaction, String status) {
        this.substance = substance;
        this.reaction = reaction;
        this.status = status;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getSubstance() {
        return (substance == null) ? "--" : substance;
    }

    public String getReaction() {
        return (reaction == null) ? "--" : reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public String getStatus() {
        return (status == null) ? "--" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}