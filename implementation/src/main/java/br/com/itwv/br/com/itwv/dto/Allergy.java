package br.com.itwv.br.com.itwv.dto;

import java.io.Serializable;

/**
 * Created by itwv_2 on 20/06/2016.
 */
public class Allergy implements Serializable {

    private String id;
    private CodedValue type;
    private CodedValue substance;
    private CodedValue reaction;
    private CodedValue status;
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CodedValue getType() {
        return type;
    }

    public void setType(CodedValue type) {
        this.type = type;
    }

    public CodedValue getSubstance() {
        return substance;
    }

    public void setSubstance(CodedValue substance) {
        this.substance = substance;
    }

    public CodedValue getReaction() {
        return reaction;
    }

    public void setReaction(CodedValue reaction) {
        this.reaction = reaction;
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