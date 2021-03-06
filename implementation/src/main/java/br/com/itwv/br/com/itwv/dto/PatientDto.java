package br.com.itwv.br.com.itwv.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by itwv_2 on 20/06/2016.
 */
public class PatientDto extends PersonDto implements Serializable {

    private String sequence;
    private CodedValueDto institution;
    private List<AllergyDto> allergies;
    private List<ProblemDto> problems;
    private AuthorDto author;
    private List<EncounterDto> encounters;
    private List<MedicationDto> medications;
    private List<PlanOfCareDto> planOfCares;
    private List<FamilyHistoryDto> familyHistory;
    private List<LaboratoryResultDto> laboratoryResults;

    public PatientDto(String id) {
        super(id);
    }

    public CodedValueDto getInstitution() {
        return institution;
    }

    public void setInstitution(CodedValueDto institution) {
        this.institution = institution;
    }

    public List<AllergyDto> getAllergies() {
        if (allergies == null) {
            allergies = new ArrayList<AllergyDto>();
        }
        return allergies;
    }

    public void setAllergies(List<AllergyDto> allergies) {
        this.allergies = allergies;
    }

    public List<ProblemDto> getProblems() {
        if (problems == null) {
            problems = new ArrayList<ProblemDto>();
        }
        return problems;
    }

    public void setProblems(List<ProblemDto> problems) {
        this.problems = problems;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public List<MedicationDto> getMedications() {
        if (medications == null) {
            medications = new ArrayList<MedicationDto>();
        }
        return medications;
    }

    public void setMedications(List<MedicationDto> medications) {
        this.medications = medications;
    }

    public List<PlanOfCareDto> getPlanOfCares() {
        if (planOfCares == null) {
            planOfCares = new ArrayList<PlanOfCareDto>();
        }
        return planOfCares;
    }

    public void getPlanOfCares(List<PlanOfCareDto> planOfCares) {
        this.planOfCares = planOfCares;
    }

    public List<FamilyHistoryDto> getFamilyHistory() {
        if (familyHistory == null) {
            familyHistory = new ArrayList<FamilyHistoryDto>();
        }
        return familyHistory;
    }

    public void setFamilyHistory(List<FamilyHistoryDto> familyHistory) {
        this.familyHistory = familyHistory;
    }

    public List<EncounterDto> getEncounters() {
        if (encounters == null) {
            encounters = new ArrayList<EncounterDto>();
        }
        return encounters;
    }

    public void setEncounters(List<EncounterDto> encounters) {
        this.encounters = encounters;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public List<LaboratoryResultDto> getLaboratoryResults() {
        if (laboratoryResults == null) {
            laboratoryResults = new ArrayList<LaboratoryResultDto>();
        }
        return laboratoryResults;
    }

    public void setLaboratoryResults(List<LaboratoryResultDto> laboratoryResults) {
        this.laboratoryResults = laboratoryResults;
    }
}
