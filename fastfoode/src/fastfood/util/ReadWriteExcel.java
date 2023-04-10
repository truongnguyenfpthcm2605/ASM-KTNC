package fastfood.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadWriteExcel {
private String filename;
    
    public ReadWriteExcel(String filename) {
        this.filename = filename;
    }
    
    public void readExcel(String sheetname) throws IOException {
        FileInputStream file = new FileInputStream(filename);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetname);
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i = 0; i < rowCount + 1; i++) {
            Row row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                System.out.print(row.getCell(j).getStringCellValue() + " \t ");
            }
            System.out.println();
        }
    }
    
    public void writeExcel(String sheetname, Object[][] data) throws IOException {
        FileInputStream file = new FileInputStream(filename);
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet(sheetname);
        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i = 0; i < data.length; i++) {
            Row row = sheet.createRow(rowCount + i + 1);
            for (int j = 0; j < data[i].length; j++) {
                Cell cell = row.createCell(j);
                if (data[i][j] instanceof String) {
                    cell.setCellValue((String)data[i][j]);
                } else if (data[i][j] instanceof Integer) {
                    cell.setCellValue((Integer)data[i][j]);
                } else if (data[i][j] instanceof Double) {
                    cell.setCellValue((Double)data[i][j]);
                }
            }
        }
        file.close();
        FileOutputStream output_file = new FileOutputStream(filename);
        workbook.write(output_file);
        output_file.close();
    }
}
