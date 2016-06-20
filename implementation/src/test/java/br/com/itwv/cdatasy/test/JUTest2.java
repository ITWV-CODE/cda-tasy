package br.com.itwv.cdatasy.test;

import br.com.itwv.br.com.itwv.dto.Patient;
import br.com.itwv.builders.PatientBuilder;
import br.com.itwv.cdatasy.common.business.resources.Resources;
import junit.framework.TestCase;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * Created by itwv_2 on 13/06/2016.
 */
public class JUTest2 extends TestCase {

    private InputStream file;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        file = Resources.class.getResourceAsStream("/Dados TASY.xlsx");
    }

    @Test
    public void test() throws Exception {

        XSSFWorkbook workbook = new XSSFWorkbook(file);
        final List<Patient> patients = new PatientBuilder().build(workbook).getResult();
        workbook.close();
        file.close();

        assertNotNull(patients);
        assertFalse(patients.isEmpty());
        assertEquals(patients.size(), 2);

        for (int i = 0; i < patients.size(); i++) {

            switch (i) {
                case 0: {
                    assertEquals(patients.get(i).getId(), "19283920183");
                    assertNotNull(patients.get(i).getAllergies());
                    assertFalse(patients.get(i).getAllergies().isEmpty());
                    assertEquals(patients.get(i).getAllergies().size(), 2);
                    break;
                }
                case 1: {
                    assertEquals(patients.get(i).getId(), "43444343433");
                    assertNotNull(patients.get(i).getAllergies());
                    assertFalse(patients.get(i).getAllergies().isEmpty());
                    assertEquals(patients.get(i).getAllergies().size(), 2);
                    break;
                }
            }
        }
    }
}
