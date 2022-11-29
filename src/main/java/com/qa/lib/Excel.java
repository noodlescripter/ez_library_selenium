package com.qa.lib;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

    XSSFWorkbook book;
    XSSFSheet sheet;

    // @Please create object in order to read file, pass @file path and @param
    // (sheet name) EX: sheet1
    public Excel(String bfilePath, String bsheetName) {
        String rPath = System.getProperty("user.dir");
        try {
            book = new XSSFWorkbook(rPath + "/" + bfilePath);
            sheet = book.getSheet(bsheetName);
        } catch (Exception e) {
            ThrowError.error();
        }
    }

    /*
     * public static void main(String[] args) throws IOException { XSSFWorkbook book
     * = new
     * XSSFWorkbook("C:\\E-Workspace\\qa_jaggar_trunk_script\\data\\products.xlsx");
     * XSSFSheet sheet = book.getSheet("Product");
     * System.out.println(sheet.getPhysicalNumberOfRows());
     * System.out.println(sheet.getRow(1).getCell(4)); }
     */

    public String getCellData(int row, int cell) {
        return sheet.getRow(row).getCell(cell).toString();
    }

}
