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
        headingRow.createCell(1).setCellValue("Question Type (Audio / Video / Image)");
        headingRow.createCell(2).setCellValue("Answer Type");
        headingRow.createCell(3).setCellValue("Topic Number");
        headingRow.createCell(4).setCellValue("Questions");
        headingRow.createCell(5).setCellValue("Correct Answer 1");
        headingRow.createCell(6).setCellValue("Correct 2");
        headingRow.createCell(7).setCellValue("Correct 3");
        headingRow.createCell(8).setCellValue("Correct 4");
        headingRow.createCell(9).setCellValue("Wrong Answer 1");
        headingRow.createCell(10).setCellValue("Wrong Answer 2");
        headingRow.createCell(11).setCellValue("Wrong Answer 3");
        headingRow.createCell(12).setCellValue("Time in Seconds");
        headingRow.createCell(13).setCellValue("Difficulty Level");
        headingRow.createCell(14).setCellValue("Question (Audio / Video / Image)");
        headingRow.createCell(15).setCellValue("Contributor's Registered mailId");
        headingRow.createCell(16).setCellValue("Variation Number");

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
            dataRow.createCell(1).setCellValue("Image");
            dataRow.createCell(2).setCellValue(1);
            dataRow.createCell(3).setCellValue("09030201");

            String[] questions = Arrays.copyOfRange(Questions.getRandomQuestions(13), 0, 5);
            String[] answers = new String[5];
            for (int j = 0; j < 5; j++) {
                answers[j] = Answers.getAnswer(questions[j], values , Arrays.stream(categories).toList());
            }
            dataRow.createCell(4).setCellValue(String.join("\n", questions));
            dataRow.createCell(5).setCellValue(String.join("\n", answers));

            dataRow.createCell(6).setCellValue("");
            dataRow.createCell(7).setCellValue("");
            dataRow.createCell(8).setCellValue("");


            String wrongAns1 = "";
            String wrongAns2 = "";
            String wrongAns3 = "";
            for (int j = 0; j < 5; j++) {
                String[] listOfWrongAnswers =  WrongAnswers.generateWrongAnswers(answers[j]);
                wrongAns1 = wrongAns1 + listOfWrongAnswers[0] + "\n";
                wrongAns2 = wrongAns2 + listOfWrongAnswers[1] + "\n";
                wrongAns3 = wrongAns3 + listOfWrongAnswers[2] + "\n";
            }
            dataRow.createCell(9).setCellValue(wrongAns1);
            dataRow.createCell(10).setCellValue(wrongAns2);
            dataRow.createCell(11).setCellValue(wrongAns3);


            dataRow.createCell(12).setCellValue("60s");
            dataRow.createCell(13).setCellValue("Easy");

            dataRow.createCell(14).setCellValue(imagePath);

            dataRow.createCell(15).setCellValue("2022.chinmay.chaudhari@ves.ac.in");

            dataRow.createCell(16).setCellValue(110);
        }

        for (int i = 0; i < 27; i++) {
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

    private static String getTopicNumber(int index) {
        String[] topicNumbers = {"09", "03", "02", "01"};
        return topicNumbers[index % topicNumbers.length];
    }
}
