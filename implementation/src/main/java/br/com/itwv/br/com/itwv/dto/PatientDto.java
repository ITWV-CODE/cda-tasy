package br.com.itwv.br.com.itwv.dto;

import br.com.itwv.cdatasy.common.business.interop.entities.Dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by itwv_2 on 20/06/2016.
 */
public class PatientDto extends Dto {

    private CodedValue institution;
    private String givenName;
    private String familyName;
    private String gender;
    private String birthDate;
    private List<AllergyDTO> allergies;
    private List<ProblemDTO> problems;

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

    public List<AllergyDTO> getAllergies() {
        if (allergies == null) {
            allergies = new ArrayList<AllergyDTO>();
        }
        return allergies;
    }

    public void setAllergies(List<AllergyDTO> allergies) {
        this.allergies = allergies;
    }

    public List<ProblemDTO> getProblems() {
        if (problems == null) {
            problems = new ArrayList<ProblemDTO>();
        }
        return problems;
    }

    public void setProblems(List<ProblemDTO> problems) {
        this.problems = problems;
    }

}
