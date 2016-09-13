package br.com.itwv.br.com.itwv.dto;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;

import java.io.Serializable;

/**
 * Created by itwv_2 on 20/06/2016.
 */
public class AllergyDto extends Dto implements Serializable {

    private TermCodedValueDto type;
    private TermCodedValueDto substance;
    private TermCodedValueDto reaction;
    private TermCodedValueDto status;
    private String date;

    public AllergyDto(String id) {
        super(id);
    }

    public TermCodedValueDto getType() {
        return type;
    }

    public void setType(TermCodedValueDto type) {
        this.type = type;
    }

    public TermCodedValueDto getSubstance() {
        return substance;
    }

    public void setSubstance(TermCodedValueDto substance) {
        this.substance = substance;
    }

    public TermCodedValueDto getReaction() {
        return reaction;
    }

    public void setReaction(TermCodedValueDto reaction) {
        this.reaction = reaction;
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
