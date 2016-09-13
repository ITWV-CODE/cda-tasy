package br.com.itwv.br.com.itwv.dto;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;

import java.io.Serializable;

/**
 * Created by itwv_2 on 29/08/2016.
 */
public class FamilyHistoryDto extends Dto implements Serializable {

    private TermCodedValueDto problem;
    private TermCodedValueDto relation;
    private String date;

    public FamilyHistoryDto(String id) {
        super(id);
    }

    public TermCodedValueDto getProblem() {
        return problem;
    }

    public void setProblem(TermCodedValueDto problem) {
        this.problem = problem;
    }

    public TermCodedValueDto getRelation() {
        return relation;
    }

    public void setRelation(TermCodedValueDto relation) {
        this.relation = relation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}