package br.com.itwv.br.com.itwv.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;

/**
 * Created by itwv_2 on 20/06/2016.
 */
public final class ExcelUtils {

    private ExcelUtils() {
    }

    public static final String getCellValue(final Cell cell) {

        cell.setCellType(Cell.CELL_TYPE_STRING);
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
                return Double.valueOf(cell.getNumericCellValue()).toString();
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return StringUtils.EMPTY;
        }
    }
}
