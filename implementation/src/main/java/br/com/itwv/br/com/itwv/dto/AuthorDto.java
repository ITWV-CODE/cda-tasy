package br.com.itwv.br.com.itwv.dto;

import java.io.Serializable;

/**
 * Created by itwv_2 on 20/06/2016.
 */
public class AuthorDto extends PersonDto implements Serializable {

    private CodedValueDto institution;

    public AuthorDto(String id) {
        super(id);
    }

    public AuthorDto(String id, String prefix, String givenName, String familyName) {
        super(id);
        super.prefix = prefix;
        super.givenName = givenName;
        super.familyName = familyName;
    }

    public CodedValueDto getInstitution() {
        return institution;
    }

    public void setInstitution(CodedValueDto institution) {
        this.institution = institution;
    }
}
