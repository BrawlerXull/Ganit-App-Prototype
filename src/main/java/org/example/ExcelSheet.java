package org.example;

import org.apache.poi.ss.usermodel.*;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class ExcelSheet {
    public static Sheet createNewSheet(Workbook workbook) {
        return workbook.createSheet("Combined Data and Images");
    }

    public static void generateDataAndCharts(Sheet combinedSheet) {
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

            JFreeChart barChart = BarGraph.createBarGraph(chartTitle, xAxisLabel, yAxisLabel, categories, values);

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
        int[] valueBound = {10, 100, 1000, 10000};
        int x = random.nextInt(valueBound.length);
        for (int i = 0; i < size; i++) {
            values[i] = random.nextInt(valueBound[x]);
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

    private static int calculateDifference() {
        Random random = new Random();
        int most = random.nextInt(100);
        int least = random.nextInt(100);
        return Math.abs(most - least);
    }
}
