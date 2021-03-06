package br.com.itwv.br.com.itwv.dto;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;

import java.io.Serializable;

/**
 * Created by itwv_2 on 29/08/2016.
 */
public class ProblemDto extends Dto implements Serializable {

    private TermCodedValueDto problem;
    private TermCodedValueDto status;
    private String date;

    public ProblemDto(String id) {
        super(id);
    }

    public TermCodedValueDto getProblem() {
        return problem;
    }

    public void setProblem(TermCodedValueDto problem) {
        this.problem = problem;
    }

    public TermCodedValueDto getStatus() {
        return status;
    }

    public void setStatus(TermCodedValueDto status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
