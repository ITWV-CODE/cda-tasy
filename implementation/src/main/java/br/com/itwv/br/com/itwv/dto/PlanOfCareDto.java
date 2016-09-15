package br.com.itwv.br.com.itwv.dto;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;

import java.io.Serializable;

/**
 * Created by itwv_2 on 29/08/2016.
 */
public class PlanOfCareDto extends Dto implements Serializable {

    private TermCodedValueDto procedure;
    private String date;

    public PlanOfCareDto(String id) {
        super(id);
    }

    public TermCodedValueDto getProcedure() {
        return procedure;
    }

    public void setProcedure(TermCodedValueDto procedure) {
        this.procedure = procedure;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}