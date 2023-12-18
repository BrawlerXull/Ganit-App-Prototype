package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter the text :");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        Workbook workbook = new XSSFWorkbook();
        Sheet sh = workbook.createSheet("data");
        String[] colHead = {"Question", "Option"};
        Row headerRow = sh.createRow(0);
        for (int i = 0; i < colHead.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(colHead[i]);
        }

        ArrayList<Item> a = createData(input);

        int rownum = 1;
        for (Item i : a) {
            Row row = sh.createRow(rownum++);
            row.createCell(0).setCellValue(i.getQuestion());
            row.createCell(1).setCellValue(i.getO1());
        }

        for (int i = 0; i < colHead.length; i++) {
            sh.autoSizeColumn(i);
        }

        try (FileOutputStream fileOut = new FileOutputStream("/Users/chinmay/Documents/ganit/data.xlsx")) {
            workbook.write(fileOut);
        }
    }

    private static ArrayList<Item> createData(String input) {
        ArrayList<Item> list = new ArrayList<>();

        for (int x = 0; x < 200; x++) {
            Random random = new Random();
            String[] subStrings = input.split(" ");

            StringBuilder ans = new StringBuilder();
            for (String subString : subStrings) {
                if (subString.equals("_")) {
                    int value = random.nextInt(100);
                    ans.append(" ").append(value);
                } else {
                    ans.append(" ").append(subString);
                }
            }

            Item i = new Item(ans.toString(), 1);
            list.add(i);
            System.out.println(ans);
        }

        return list;
    }
}







// 200 questions will be generated in the same structure as the user has given as input just the values will be changed

// Here random values will be inserted in the place of the symbol _ in the input

// The project is made with Java and data will be exported in xlsx format as given in the criteria

// This is just a prototype so I haven't worked on the correct option logic I have given a default value to it as 1

// Ganit App
