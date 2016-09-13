package br.com.itwv.builders;

import br.com.itwv.br.com.itwv.dto.*;
import br.com.itwv.br.com.itwv.utils.ExcelUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by itwv_2 on 20/06/2016.
 */
public class PatientBuilder {

    private List<PatientDto> patients;

    public PatientBuilder() {
        this.patients = new ArrayList<PatientDto>();
    }

    public List<PatientDto> getResult() {
        return this.patients;
    }

    public PatientBuilder build(final XSSFWorkbook workbook) {

        final XSSFSheet patientsSheet = workbook.getSheet("Pacientes");
        final Iterator<Row> rowIterator = patientsSheet.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            this.buildPatient(workbook, rowIterator.next());
        }
        return this;
    }

    private void buildPatient(final XSSFWorkbook workbook, final Row row) {

        final PatientDto patient = new PatientDto(ExcelUtils.getCellValue(row.getCell(1)));
        this.patients.add(patient);

        final String sequence = ExcelUtils.getCellValue(row.getCell(0));
        patient.setInstitution(new CodedValueDto(ExcelUtils.getCellValue(row.getCell(2)), ExcelUtils.getCellValue(row.getCell(3))));
        patient.setGivenName(ExcelUtils.getCellValue(row.getCell(4)));
        patient.setFamilyName(ExcelUtils.getCellValue(row.getCell(5)));
        patient.setGender(ExcelUtils.getCellValue(row.getCell(6)));
        patient.setBirthDate(ExcelUtils.getCellValue(row.getCell(7)));

        final XSSFSheet authorsSheet = workbook.getSheet("Autores");
        final XSSFSheet encountersSheet = workbook.getSheet("Atendimentos");
        final XSSFSheet allergiesSheet = workbook.getSheet("Alergias");
        final XSSFSheet problemsSheet = workbook.getSheet("Problemas");
        final XSSFSheet medicationsSheet = workbook.getSheet("Medicamentos");
        final XSSFSheet proceduresSheet = workbook.getSheet("Procedimentos");
        final XSSFSheet familyHistorySheet = workbook.getSheet("Antecedentes Familiares");

        this.buildAllergies(patient, allergiesSheet, sequence);
        this.buildAuthor(patient, authorsSheet, sequence);
        this.buildEncounters(patient, encountersSheet, sequence);
        this.buildProblems(patient, problemsSheet, sequence);
        this.buildMedications(patient, medicationsSheet, sequence);
        this.buildProcedures(patient, proceduresSheet, sequence);
        this.buildFamilyHistory(patient, familyHistorySheet, sequence);
    }

    private void buildAuthor(final PatientDto patient, final XSSFSheet authorsSheet, final String sequence) {

        final Iterator<Row> rowIterator = authorsSheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            if (sequence.equals(ExcelUtils.getCellValue(row.getCell(0)))) {
                final AuthorDto author = new AuthorDto(ExcelUtils.getCellValue(row.getCell(1)));
                patient.setAuthor(author);
                author.setInstitution(new CodedValueDto(ExcelUtils.getCellValue(row.getCell(2)), ExcelUtils.getCellValue(row.getCell(3))));
                author.setGivenName(ExcelUtils.getCellValue(row.getCell(4)));
                author.setFamilyName(ExcelUtils.getCellValue(row.getCell(5)));
                break;
            }
        }
    }

    private void buildEncounters(final PatientDto patient, final XSSFSheet encountersSheet, final String sequence) {

        final Iterator<Row> rowIterator = encountersSheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            if (sequence.equals(ExcelUtils.getCellValue(row.getCell(0)))) {
                final EncounterDto encounter = new EncounterDto(ExcelUtils.getCellValue(row.getCell(1)));
                patient.getEncounters().add(encounter);
                encounter.setType(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(2)), ExcelUtils.getCellValue(row.getCell(3)), "TASY"));
                encounter.setDate(ExcelUtils.getCellValue(row.getCell(4)));
                encounter.setInstituition(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(5)), ExcelUtils.getCellValue(row.getCell(6)), "TASY"));
                encounter.setAuthor(new AuthorDto(ExcelUtils.getCellValue(row.getCell(7)), ExcelUtils.getCellValue(row.getCell(8)), ExcelUtils.getCellValue(row.getCell(9)), ExcelUtils.getCellValue(row.getCell(10))));
            }
        }
    }

    private void buildAllergies(final PatientDto patient, final XSSFSheet allergiesSheet, final String sequence) {

        final Iterator<Row> rowIterator = allergiesSheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            if (sequence.equals(ExcelUtils.getCellValue(row.getCell(0)))) {
                final AllergyDto allergy = new AllergyDto(ExcelUtils.getCellValue(row.getCell(1)));
                patient.getAllergies().add(allergy);
                allergy.setType(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(2)), ExcelUtils.getCellValue(row.getCell(3)), "TASY"));
                allergy.setSubstance(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(4)), ExcelUtils.getCellValue(row.getCell(5)), "TASY"));
                allergy.setReaction(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(6)), ExcelUtils.getCellValue(row.getCell(7)), "TASY"));
                allergy.setStatus(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(8)), ExcelUtils.getCellValue(row.getCell(9)), "TASY"));
                allergy.setDate(ExcelUtils.getCellValue(row.getCell(10)));
            }
        }
    }

    private void buildProblems(final PatientDto patient, final XSSFSheet problemsSheet, final String sequence) {

        final Iterator<Row> rowIterator = problemsSheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            if (sequence.equals(ExcelUtils.getCellValue(row.getCell(0)))) {
                final ProblemDto problem = new ProblemDto(ExcelUtils.getCellValue(row.getCell(1)));
                patient.getProblems().add(problem);
                problem.setProblem(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(2)), ExcelUtils.getCellValue(row.getCell(3)), "CID"));
                problem.setStatus(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(4)), ExcelUtils.getCellValue(row.getCell(5)), "TASY"));
                problem.setDate(ExcelUtils.getCellValue(row.getCell(6)));
            }
        }
    }

    private void buildMedications(final PatientDto patient, final XSSFSheet medicationsSheet, final String sequence) {

        final Iterator<Row> rowIterator = medicationsSheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            if (sequence.equals(ExcelUtils.getCellValue(row.getCell(0)))) {
                final MedicationDto medication = new MedicationDto(ExcelUtils.getCellValue(row.getCell(1)));
                patient.getMedications().add(medication);
                medication.setDate(ExcelUtils.getCellValue(row.getCell(2)));
                medication.setDosage(ExcelUtils.getCellValue(row.getCell(3)));
                medication.setRoute(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(4)), ExcelUtils.getCellValue(row.getCell(5)), "TASY"));
                medication.setDose(ExcelUtils.getCellValue(row.getCell(6)));
                medication.setUnit(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(7)), ExcelUtils.getCellValue(row.getCell(8)), "TASY"));
                medication.setMedicine(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(9)), ExcelUtils.getCellValue(row.getCell(10)), "TASY"));
            }
        }
    }

    private void buildProcedures(final PatientDto patient, final XSSFSheet proceduresSheet, final String sequence) {

        final Iterator<Row> rowIterator = proceduresSheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            if (sequence.equals(ExcelUtils.getCellValue(row.getCell(0)))) {
                final ProcedureDto procedure = new ProcedureDto(ExcelUtils.getCellValue(row.getCell(1)));
                patient.getProcedures().add(procedure);
                procedure.setDate(ExcelUtils.getCellValue(row.getCell(2)));
                procedure.setProcedure(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(3)), ExcelUtils.getCellValue(row.getCell(4)), "TASY"));
            }
        }
    }

    private void buildFamilyHistory(final PatientDto patient, final XSSFSheet familyHistorySheet, final String sequence) {

        final Iterator<Row> rowIterator = familyHistorySheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            if (sequence.equals(ExcelUtils.getCellValue(row.getCell(0)))) {
                final FamilyHistoryDto familyHistory = new FamilyHistoryDto(ExcelUtils.getCellValue(row.getCell(1)));
                patient.getFamilyHistory().add(familyHistory);
                familyHistory.setDate(ExcelUtils.getCellValue(row.getCell(2)));
                familyHistory.setProblem(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(3)), ExcelUtils.getCellValue(row.getCell(4)), "CID"));
                familyHistory.setRelation(new TermCodedValueDto(ExcelUtils.getCellValue(row.getCell(5)), ExcelUtils.getCellValue(row.getCell(6)), "TASY"));
            }
        }
    }
}