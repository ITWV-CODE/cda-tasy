package br.com.itwv.br.com.itwv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by itwv_2 on 20/06/2016.
 */
public class Patient implements Serializable {

    private String id;
    private CodedValue institution;
    private String givenName;
    private String familyName;
    private String gender;
    private String birthDate;

    private List<Allergy> allergies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CodedValue getInstitution() {
        return institution;
    }

    public void setInstitution(CodedValue institution) {
        this.institution = institution;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public List<Allergy> getAllergies() {
        if (allergies == null) {
            allergies = new ArrayList<Allergy>();
        }
        return allergies;
    }

    public void setAllergies(List<Allergy> allergies) {
        this.allergies = allergies;
    }
}
