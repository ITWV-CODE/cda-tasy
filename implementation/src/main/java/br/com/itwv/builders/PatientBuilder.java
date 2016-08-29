package br.com.itwv.builders;

import br.com.itwv.br.com.itwv.dto.AllergyDTO;
import br.com.itwv.br.com.itwv.dto.CodedValue;
import br.com.itwv.br.com.itwv.dto.PatientDto;
import br.com.itwv.br.com.itwv.dto.ProblemDTO;
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

        final PatientDto patient = new PatientDto();
        this.patients.add(patient);

        final String sequence = ExcelUtils.getCellValue(row.getCell(0));
        patient.setId(ExcelUtils.getCellValue(row.getCell(1)));
        patient.setInstitution(new CodedValue(ExcelUtils.getCellValue(row.getCell(2)), ExcelUtils.getCellValue(row.getCell(3))));
        patient.setGivenName(ExcelUtils.getCellValue(row.getCell(4)));
        patient.setFamilyName(ExcelUtils.getCellValue(row.getCell(5)));
        patient.setGender(ExcelUtils.getCellValue(row.getCell(6)));
        patient.setBirthDate(ExcelUtils.getCellValue(row.getCell(7)));

        final XSSFSheet allergiesSheet = workbook.getSheet("Alergias");
        final XSSFSheet problemsSheet = workbook.getSheet("Problemas");

        this.buildAllergies(patient, allergiesSheet, sequence);
        this.buildProblems(patient, problemsSheet, sequence);
    }

    private void buildAllergies(final PatientDto patient, final XSSFSheet allergiesSheet, final String sequence) {

        final Iterator<Row> rowIterator = allergiesSheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            if (sequence.equals(ExcelUtils.getCellValue(row.getCell(0)))) {
                final AllergyDTO allergy = new AllergyDTO();
                patient.getAllergies().add(allergy);
                allergy.setId(ExcelUtils.getCellValue(row.getCell(1)));
                allergy.setType(new CodedValue(ExcelUtils.getCellValue(row.getCell(2)), ExcelUtils.getCellValue(row.getCell(3))));
                allergy.setSubstance(new CodedValue(ExcelUtils.getCellValue(row.getCell(4)), ExcelUtils.getCellValue(row.getCell(5))));
                allergy.setReaction(new CodedValue(ExcelUtils.getCellValue(row.getCell(6)), ExcelUtils.getCellValue(row.getCell(7))));
                allergy.setStatus(new CodedValue(ExcelUtils.getCellValue(row.getCell(8)), ExcelUtils.getCellValue(row.getCell(9))));
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
                final ProblemDTO problem = new ProblemDTO();
                patient.getProblems().add(problem);
                problem.setId(ExcelUtils.getCellValue(row.getCell(1)));
                problem.setProblem(new CodedValue(ExcelUtils.getCellValue(row.getCell(2)), ExcelUtils.getCellValue(row.getCell(3))));
                problem.setStatus(new CodedValue(ExcelUtils.getCellValue(row.getCell(4)), ExcelUtils.getCellValue(row.getCell(5))));
                problem.setDate(ExcelUtils.getCellValue(row.getCell(6)));
            }
        }
    }
}