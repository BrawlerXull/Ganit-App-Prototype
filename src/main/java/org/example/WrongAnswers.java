package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class WrongAnswers {
    public static String[] generateWrongAnswers(String input) {
        if (isInteger(input)) {
            int number = Integer.parseInt(input);
            return generateRandomNumbersNear(number, 3);
        } else if (input.equals("This bar graph represents the number of travelers by different means.")) {
            return new String[]{
                    "This bar graph represents the number of students by different means.",
                    "This bar graph represents the number of teachers by different means.",
                    "This bar graph represents the number of principal by different means."
            };
        } else if (input.contains("1 cm on the y-axis represents")) {
            return new String[]{
                    "1 cm on the y-axis represents 9 travelers",
                    "1 cm on the y-axis represents 10 travelers",
                    "1 cm on the y-axis represents 5 travelers"
            };
        } else if (input.equals("Bus")) {
            return new String[]{
                    "Car",
                    "Motorcycle",
                    "Bicycle"
            };
        } else if (input.equals("Truck")) {
            return new String[]{
                    "Car",
                    "Motorcycle",
                    "Bicycle"
            };
        } else if (input.equals("Motorcycle")) {
            return new String[]{
                    "Car",
                    "Bus",
                    "Scooter"
            };
        } else if (input.equals("Bicycle")) {
            return new String[]{
                    "Car",
                    "Bus",
                    "Scooter"
            };
        } else if (input.equals("Scooter")) {
            return new String[]{
                    "Car",
                    "Bus",
                    "Truck"
            };
        }
        else {
            return new String[]{
                    "Car, Bus, Truck, Motorcycle",
                    "Car, Bus, Truck, Scooter",
                    "Bicycle, Bus, Truck, Motorcycle"
            };
        }
    }

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String[] generateRandomNumbersNear(int number, int count) {
        Random random = new Random();
        String[] result = new String[count];

        for (int i = 0; i < count; i++) {
            int randomOffset = random.nextInt(5) - 2;
            int generatedNumber = number + randomOffset;

            while (contains(result, String.valueOf(generatedNumber))) {
                randomOffset = random.nextInt(5) - 2;
                generatedNumber = number + randomOffset;
            }

            result[i] = String.valueOf(generatedNumber);
        }

        return result;
    }

    private static boolean contains(String[] array, String value) {
        for (String element : array) {
            if (element != null && element.equals(value)) {
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {
        System.out.println(Arrays.toString(generateWrongAnswers("This bar graph represents the number of travelers by different means.")));
        System.out.println(Arrays.toString(generateWrongAnswers("10")));
        System.out.println(Arrays.toString(generateWrongAnswers("Car")));


    }
}
