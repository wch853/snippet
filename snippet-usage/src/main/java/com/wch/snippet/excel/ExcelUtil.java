package com.wch.snippet.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 读Excel工具
 */
public class ExcelUtil {

    /**
     * 打开工作簿
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    private static Workbook getWorkBook(String filePath) throws IOException {
        InputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = null;
        if (filePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (filePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        }
        return workbook;
    }

    /**
     * 读取数值和文本值
     *
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell) {
        String value = "";
        if (cell != null) {
            switch (cell.getCellTypeEnum()) {
                case NUMERIC:
                    value = new DecimalFormat("#").format(cell.getNumericCellValue());
                    break;
                case STRING:
                    value = cell.getStringCellValue().trim();
                    break;
            }
        }
        return value;
    }

    /**
     * 读取Excel
     *
     * @param filePath
     * @return 行号-列名-值
     * @throws IOException
     */
    public static Map<Integer, Map<String, String>> readFromExcel(String filePath) throws IOException {
        Map<Integer, Map<String, String>> data = new HashMap<>();
        Map<Integer, String> columnMap = new HashMap<>();
        Workbook workBook = getWorkBook(filePath);
        for (Sheet sheet : workBook) {
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            for (Cell cell : firstRow) {
                columnMap.put(cell.getColumnIndex(), cell.getStringCellValue().trim());
            }

            for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
                Row row = sheet.getRow(rowNum);
                Map<String, String> rowData = new HashMap<>();
                for (Map.Entry<Integer, String> entry : columnMap.entrySet()) {
                    rowData.put(entry.getValue(), getCellValue(row.getCell(entry.getKey())));
                }
                data.put(rowNum, rowData);
            }
        }
        return data;
    }
}
