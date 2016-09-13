package br.com.itwv.br.com.itwv.dto;

/**
 * Created by itwv_2 on 20/06/2016.
 */
public class TermCodedValueDto extends CodedValueDto {

    private String terminolgy;

    public TermCodedValueDto(String code, String description, String terminolgy) {
        super(code, description);
        this.terminolgy = terminolgy;
    }

    public String getTerminolgy() {
        return terminolgy;
    }

    public void setTerminolgy(String terminolgy) {
        this.terminolgy = terminolgy;
    }
}