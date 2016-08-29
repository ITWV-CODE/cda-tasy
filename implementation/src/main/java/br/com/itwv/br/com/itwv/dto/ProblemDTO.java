package br.com.itwv.br.com.itwv.dto;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;

/**
 * Created by itwv_2 on 29/08/2016.
 */
public class ProblemDTO extends Dto {

    private CodedValue problem;
    private CodedValue status;
    private String date;

    public CodedValue getProblem() {
        return problem;
    }

    public void setProblem(CodedValue problem) {
        this.problem = problem;
    }

    public CodedValue getStatus() {
        return status;
    }

    public void setStatus(CodedValue status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
