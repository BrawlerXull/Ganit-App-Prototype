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
        headingRow.createCell(1).setCellValue("Question Type");
        headingRow.createCell(2).setCellValue("Answer Type");
        headingRow.createCell(3).setCellValue("Topic Number");
        headingRow.createCell(4).setCellValue("Question (Text Only)");
        headingRow.createCell(5).setCellValue("Correct Answer 1");
        headingRow.createCell(6).setCellValue("Correct Answer 2");
        headingRow.createCell(7).setCellValue("Correct Answer 3");
        headingRow.createCell(8).setCellValue("Correct Answer 4");
        headingRow.createCell(9).setCellValue("Wrong Answer 1");
        headingRow.createCell(10).setCellValue("Wrong Answer 2");
        headingRow.createCell(11).setCellValue("Wrong Answer 3");
        headingRow.createCell(12).setCellValue("Time in seconds");
        headingRow.createCell(13).setCellValue("Difficulty Level");
        headingRow.createCell(14).setCellValue("Question (Audio / Video / Image)");
        headingRow.createCell(15).setCellValue("Contributor's Registered mailId");
        headingRow.createCell(16).setCellValue("Solution (Text Only)");
        headingRow.createCell(17).setCellValue("Solution (Image/ Audio/ Video)");
        headingRow.createCell(18).setCellValue("Variation Number");
        headingRow.createCell(19).setCellValue("");



        for (int i = 0; i < 200; i++) {
            String chartTitle = "Chart " + (i + 1);
            String xAxisLabel = "Category";
            String yAxisLabel = "Value";

            String[] categories = generateRandomCategories();
            int[] values = generateRandomValues(categories.length);

            JFreeChart barChart = BarGraph.createBarGraph(chartTitle, xAxisLabel, yAxisLabel, categories, values);

            String imagePath = "/Users/chinmay/Documents/ganit/ganitt/img/chart_" + (i + 1) + ".png";

            saveChartAsImage(barChart, imagePath);

            for (int j = 0; j < 5; j++) {
                Row dataRow = combinedSheet.createRow((i * 5) + j + 1);
                dataRow.createCell(0).setCellValue((i * 5) + j + 1);
                dataRow.createCell(1).setCellValue("Image");
                dataRow.createCell(2).setCellValue(1);
                dataRow.createCell(3).setCellValue("09030201");

                String[] questions = Arrays.copyOfRange(Questions.getRandomQuestions(13), 0, 1); // Get only 1 question
                String[] answers = new String[5];

                answers[0] = Answers.getAnswer(questions[0], values, Arrays.stream(categories).toList());
                dataRow.createCell(4).setCellValue(questions[0]);
                dataRow.createCell(5).setCellValue(answers[0]);

                dataRow.createCell(6).setCellValue("");
                dataRow.createCell(7).setCellValue("");
                dataRow.createCell(8).setCellValue("");

                String wrongAns1 = "";
                String wrongAns2 = "";
                String wrongAns3 = "";

                String[] listOfWrongAnswers = WrongAnswers.generateWrongAnswers(answers[0]);
                wrongAns1 = listOfWrongAnswers[0];
                wrongAns2 = listOfWrongAnswers[1];
                wrongAns3 = listOfWrongAnswers[2];

                dataRow.createCell(9).setCellValue(wrongAns1);
                dataRow.createCell(10).setCellValue(wrongAns2);
                dataRow.createCell(11).setCellValue(wrongAns3);

                dataRow.createCell(12).setCellValue("60");
                dataRow.createCell(13).setCellValue(4);
                if(j == 0){
                    dataRow.createCell(14).setCellValue(imagePath);
                }


                dataRow.createCell(15).setCellValue("2022.chinmay.chaudhari@ves.ac.in");

                dataRow.createCell(18).setCellValue(110);

                if( j % 5 == 0){
                    dataRow.createCell(19).setCellValue(1);
                }else{
                    dataRow.createCell(19).setCellValue(2);
                }
            }

        }
        for (int i = 0; i < 16; i++) {
            combinedSheet.autoSizeColumn(i);
        }
        Row lastRow = combinedSheet.createRow(1001);
        lastRow.createCell(0).setCellValue("****");
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
