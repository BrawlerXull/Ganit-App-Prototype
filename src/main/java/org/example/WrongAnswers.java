package org.example;

import java.util.Arrays;
import java.util.Random;

public class WrongAnswers {
    public static Object[] generateWrongAnswers(String input) {
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
        }
        else if (Arrays.asList("Car", "Bus", "Truck", "Motorcycle", "Bicycle", "Scooter").contains(input)) {
            return getOtherVehicles(input, 3);
        } else {
            return new Object[0];
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

    private static Integer[] generateRandomNumbersNear(int number, int count) {
        Random random = new Random();
        Integer[] result = new Integer[count];
        for (int i = 0; i < count; i++) {
            int randomOffset = random.nextInt(5) - 2;
            int generatedNumber = number + randomOffset;

            // Check if the generated number already exists in the array
            while (Arrays.asList(result).contains(generatedNumber)) {
                randomOffset = random.nextInt(5) - 2;
                generatedNumber = number + randomOffset;
            }

            result[i] = generatedNumber;
        }
        return result;
    }

    private static String[] getOtherVehicles(String vehicle, int count) {
        String[] allVehicles = {"Car", "Bus", "Truck", "Motorcycle", "Bicycle", "Scooter"};
        return Arrays.stream(allVehicles)
                .filter(v -> !v.equals(vehicle))
                .limit(count)
                .toArray(String[]::new);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(generateWrongAnswers("This bar graph represents the number of travelers by different means.")));
        System.out.println(Arrays.toString(generateWrongAnswers("10")));
        System.out.println(Arrays.toString(generateWrongAnswers("Car")));


    }
}
