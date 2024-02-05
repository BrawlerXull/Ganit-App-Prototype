package org.example;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ExcelSheet {
    public static Sheet createNewSheet(Workbook workbook) {
        return workbook.createSheet("Combined Data and Images");
    }

    private static void addImageToSheet(Workbook workbook, Sheet sheet, String imagePath, int row, int col) {
        try (InputStream inputStream = new FileInputStream(imagePath)) {
            byte[] inputImageBytes = IOUtils.toByteArray(inputStream);
            int inputImagePictureID = workbook.addPicture(inputImageBytes, Workbook.PICTURE_TYPE_PNG);
            Drawing<?> drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = createClientAnchor(row + 1, col);
            drawing.createPicture(anchor, inputImagePictureID);

            Row currentRow = sheet.getRow(row + 1);
            if (currentRow == null) {
                currentRow = sheet.createRow(row + 1);
            }
            currentRow.setHeightInPoints(100);
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
        headingRow.createCell(14).setCellValue("Question (Image)");
        headingRow.createCell(15).setCellValue("Question (Audio / Video / Image)");
        headingRow.createCell(16).setCellValue("Contributor's Registered mailId");
        headingRow.createCell(17).setCellValue("Solution (Text Only)");
        headingRow.createCell(18).setCellValue("Solution (Image/ Audio/ Video)");
        headingRow.createCell(19).setCellValue("Variation Number");
        headingRow.createCell(20).setCellValue("");



        for (int i = 0; i < 10; i++) {
            String chartTitle = "Chart " + (i + 1);
            String xAxisLabel = "Category";
            String yAxisLabel = "Value";

            String[] categories = generateRandomCategories();
            int[] values = generateRandomValues(categories.length);

            JFreeChart barChart = BarGraph.createBarGraph(chartTitle, xAxisLabel, yAxisLabel, categories, values);

            String projectPath = System.getProperty("user.dir");

            String imagePath = projectPath + File.separator + "img" + File.separator + "chart_" + (i + 1) + ".png";

            saveChartAsImage(barChart, imagePath);

            String[] questions = Arrays.copyOfRange(Questions.getRandomQuestions(13), 0, 5);

            System.out.println(Arrays.toString(questions));

            String[] answers = new String[5];

            for (int j = 0; j < 5; j++) {
                Row dataRow = combinedSheet.createRow((i * 5) + j + 1);
                dataRow.createCell(0).setCellValue((i * 5) + j + 1);
                dataRow.createCell(1).setCellValue("Image");
                dataRow.createCell(2).setCellValue(1);
                dataRow.createCell(3).setCellValue("09030201");


                answers[j] = Answers.getAnswer(questions[j], values, Arrays.stream(categories).toList());

                String solution = Solution.getSolution(questions[j],values,Arrays.stream(categories).toList());
                dataRow.createCell(17).setCellValue(solution);


                String marathiQuestion = MarathiQuestion.translateToMarathi(questions[j]);
//                System.out.println(questions[0]);
//                System.out.println(marathiQuestion);
                dataRow.createCell(4).setCellValue(marathiQuestion);

                String marathiAnswer = MarathiAnswers.getMarathiAnswers(answers[j]);
                dataRow.createCell(5).setCellValue(marathiAnswer);

                dataRow.createCell(6).setCellValue("");
                dataRow.createCell(7).setCellValue("");
                dataRow.createCell(8).setCellValue("");

                String wrongAns1 = "";
                String wrongAns2 = "";
                String wrongAns3 = "";

                String[] listOfWrongAnswers = WrongAnswers.generateWrongAnswers(answers[j]);
                wrongAns1 = MarathiWrongAnswers.getMarathiWrongAnswers(listOfWrongAnswers[0]);
                wrongAns2 = MarathiWrongAnswers.getMarathiWrongAnswers(listOfWrongAnswers[1]);
                wrongAns3 = MarathiWrongAnswers.getMarathiWrongAnswers(listOfWrongAnswers[2]);

                dataRow.createCell(9).setCellValue(wrongAns1);
                dataRow.createCell(10).setCellValue(wrongAns2);
                dataRow.createCell(11).setCellValue(wrongAns3);

                dataRow.createCell(12).setCellValue("60");
                dataRow.createCell(13).setCellValue(4);
                if(j == 0){
                    dataRow.createCell(15).setCellValue(imagePath);
                }
                if (j == 0) {
                    addImageToSheet(combinedSheet.getWorkbook(), combinedSheet, imagePath, (i * 5) + j , 14);
                }




                dataRow.createCell(16).setCellValue("2022.chinmay.chaudhari@ves.ac.in");

                dataRow.createCell(19).setCellValue(110);

                if( j % 5 == 0){
                    dataRow.createCell(20).setCellValue(1);
                }else{
                    dataRow.createCell(20).setCellValue(2);
                }
            }

        }
        for (int i = 0; i < 16; i++) {
            if (i != 14) {
                combinedSheet.autoSizeColumn(i);
            }
        }

        combinedSheet.setColumnWidth(14, 10000);

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
