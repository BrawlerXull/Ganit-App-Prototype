package org.example;

import org.apache.poi.ss.usermodel.*;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ExcelSheet {
    public static Sheet createNewSheet(Workbook workbook) {
        return workbook.createSheet("Combined Data and Images");
    }

    public static void generateDataAndCharts(Sheet combinedSheet) {
        Row headingRow = combinedSheet.createRow(0);
        headingRow.createCell(0).setCellValue("Sr. No");
        headingRow.createCell(1).setCellValue("Image Path");

        for (int j = 0; j < 5; j++) {
            headingRow.createCell(2 + j * 2).setCellValue("Question " + (j + 1));
            headingRow.createCell(3 + j * 2).setCellValue("Answer " + (j + 1));
        }

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

            String[] questions = Questions.getRandomQuestions(13);
            String[] answers = new String[5];
            for (int j = 0; j < 5; j++) {
                answers[j] = Answers.getAnswer(questions[j], values);
                dataRow.createCell(2 + j * 2).setCellValue(questions[j]);
                dataRow.createCell(3 + j * 2).setCellValue(answers[j]);
            }
        }

        for (int i = 0; i < 14; i++) {
            combinedSheet.autoSizeColumn(i);
        }
    }

    private static String[] generateRandomCategories() {
        String[] vehicles = {"Car", "Bus", "Truck", "Motorcycle", "Bicycle", "Scooter"};
        List<String> vehicleList = Arrays.asList(vehicles);
        Collections.shuffle(vehicleList);
        return vehicleList.subList(0, 4).toArray(new String[0]);
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
}
