package br.com.itwv.cdatasy.base.html.templates.objects;

public class Problem {
    private String problem;
    private String status;

    public Problem() {
    }

    public Problem(String problem, String status) {
        this.problem = problem;
        this.status = status;
    }

    public String getProblem() {
        return (problem == null) ? "--" : problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getStatus() {
        return (status == null) ? "--" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}