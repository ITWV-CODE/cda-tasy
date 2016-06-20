package br.com.itwv.builders;

import br.com.itwv.br.com.itwv.dto.Allergy;
import br.com.itwv.br.com.itwv.dto.CodedValue;
import br.com.itwv.br.com.itwv.dto.Patient;
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

    private List<Patient> patients;

    public PatientBuilder() {
        this.patients = new ArrayList<Patient>();
    }

    public List<Patient> getResult() {
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

        final Patient patient = new Patient();
        this.patients.add(patient);

        final String sequence = ExcelUtils.getCellValue(row.getCell(0));
        patient.setId(ExcelUtils.getCellValue(row.getCell(1)));
        patient.setInstitution(new CodedValue(ExcelUtils.getCellValue(row.getCell(2)), ExcelUtils.getCellValue(row.getCell(3))));
        patient.setGivenName(ExcelUtils.getCellValue(row.getCell(4)));
        patient.setFamilyName(ExcelUtils.getCellValue(row.getCell(5)));
        patient.setGender(ExcelUtils.getCellValue(row.getCell(6)));
        patient.setBirthDate(ExcelUtils.getCellValue(row.getCell(7)));

        final XSSFSheet allergiesSheet = workbook.getSheet("Alergias");
        this.buildAllergies(patient, allergiesSheet, sequence);
    }

    private void buildAllergies(final Patient patient, final XSSFSheet allergiesSheet, final String sequence) {

        final Iterator<Row> rowIterator = allergiesSheet.iterator();
        rowIterator.next();

        while (rowIterator.hasNext()) {
            final Row row = rowIterator.next();
            if (sequence.equals(ExcelUtils.getCellValue(row.getCell(0)))) {
                final Allergy allergy = new Allergy();
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
}