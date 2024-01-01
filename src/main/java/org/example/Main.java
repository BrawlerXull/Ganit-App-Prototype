package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;


public class Main {
    public static void main(String[] args) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet combinedSheet = ExcelSheet.createNewSheet(workbook);
            ExcelSheet.generateDataAndCharts(combinedSheet);

            try (FileOutputStream fileOut = new FileOutputStream("output.xlsx")) {
                workbook.write(fileOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}










// Prototype of generating Bar Graphs with Java and it's solution

// After running the script we can generate 200 different bar graphs and a question with it which will be exported to an Excel Sheet

// This is just the prototype version other features can be added in the further version with GUI (if required)

// 'Output.xlsx will be generated at the root dire





