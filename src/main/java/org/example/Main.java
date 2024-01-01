package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import java.io.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook();

        Sheet combinedSheet = workbook.createSheet("Combined Data and Images");

        Row headingRow = combinedSheet.createRow(0);
        headingRow.createCell(0).setCellValue("Sr. No");
        headingRow.createCell(1).setCellValue("Image Path");
        headingRow.createCell(2).setCellValue("Question");
        headingRow.createCell(3).setCellValue("Answer");

        for (int i = 0; i < 200; i++) {
            String chartTitle = "Chart " + (i + 1);
            String xAxisLabel = "Category";
            String yAxisLabel = "Value";

            String[] categories = generateRandomCategories();
            int[] values = generateRandomValues(categories.length);

            JFreeChart barChart = BarGraphExample.createBarGraph(chartTitle, xAxisLabel, yAxisLabel, categories, values);

            String imagePath = "/Users/chinmay/Documents/ganit/ganitt/img/chart_" + (i + 1) + ".png";

            saveChartAsImage(barChart, imagePath);

            Row dataRow = combinedSheet.createRow(i + 1);
            dataRow.createCell(0).setCellValue(i + 1);
            dataRow.createCell(1).setCellValue(imagePath);
            dataRow.createCell(2).setCellValue("How much is the difference in the number of travelers between the vehicle used most and least?");
            int difference = calculateDifference();
            dataRow.createCell(3).setCellValue("Difference: " + difference);
        }

        for (int i = 0; i < 4; i++) {
            combinedSheet.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream("output.xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String[] generateRandomCategories() {
        String[] categories = new String[5];
        for (int i = 0; i < categories.length; i++) {
            categories[i] = "Category " + (i + 1);
        }
        return categories;
    }

    private static int[] generateRandomValues(int size) {
        int[] values = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            values[i] = random.nextInt(100);
        }
        return values;
    }

    private static void saveChartAsImage(JFreeChart chart, String imagePath) {
        try {
            ChartUtils.saveChartAsJPEG(new File(imagePath), chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addImageToSheet(Workbook workbook, Sheet sheet, String imagePath, int row, int col) {
        try (InputStream inputStream = new FileInputStream(imagePath)) {
            byte[] inputImageBytes = IOUtils.toByteArray(inputStream);
            int inputImagePictureID = workbook.addPicture(inputImageBytes, Workbook.PICTURE_TYPE_PNG);
            Drawing<?> drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = createClientAnchor(row + 1, col);
            drawing.createPicture(anchor, inputImagePictureID);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ClientAnchor createClientAnchor(int row, int col) {
        ClientAnchor clientAnchor = new XSSFClientAnchor();
        clientAnchor.setCol1(col);
        clientAnchor.setCol2(col + 1);
        clientAnchor.setRow1(row);
        clientAnchor.setRow2(row + 1);
        return clientAnchor;
    }

    private static int calculateDifference() {
        Random random = new Random();
        int most = random.nextInt(100);
        int least = random.nextInt(100);
        return Math.abs(most - least);
    }
}













// Prototype of generating Bar Graphs with Java and it's solution

// After running the script we can generate 200 different bar graphs and a question with it which will be exported to an Excel Sheet

// This is just the prototype version other features can be added in the further version with GUI (if required)

// 'Output.xlsx will be generated at the root dire





