package br.com.itwv.cdatasy.base.html.templates.objects;

public class FamilyHistory {
    private String relationship;
    private String problem;

    public FamilyHistory() {
    }

    public FamilyHistory(String relationship, String problem) {
        super();
        this.relationship = relationship;
        this.problem = problem;
    }

    public String getRelationship() {
        return (relationship == null) ? "--" : relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getProblem() {
        return (problem == null) ? "--" : problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }
}