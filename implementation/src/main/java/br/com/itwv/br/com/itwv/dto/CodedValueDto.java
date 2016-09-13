package br.com.itwv.br.com.itwv.dto;

import java.io.Serializable;

/**
 * Created by itwv_2 on 20/06/2016.
 */
public class CodedValueDto implements Serializable {

    private String code;
    private String description;

    public CodedValueDto(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public CodedValueDto() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
