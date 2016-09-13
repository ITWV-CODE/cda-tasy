package br.com.itwv.br.com.itwv.dto;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;

import java.io.Serializable;

/**
 * Created by itwv_2 on 29/08/2016.
 */
public class EncounterDto extends Dto implements Serializable {

    private TermCodedValueDto type;
    private String date;
    private CodedValueDto instituition;
    private AuthorDto author;

    public EncounterDto(String id) {
        super(id);
    }

    public TermCodedValueDto getType() {
        return type;
    }

    public void setType(TermCodedValueDto type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CodedValueDto getInstituition() {
        return instituition;
    }

    public void setInstituition(CodedValueDto instituition) {
        this.instituition = instituition;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }
}
