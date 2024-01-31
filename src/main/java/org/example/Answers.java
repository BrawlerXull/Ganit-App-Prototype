package org.example;

import java.util.Arrays;
import java.util.List;

import static org.example.BarGraph.findMax;
import static org.example.BarGraph.getBound;

public class Answers {
    public static String getAnswer(String question, int[] values, List<String> vehicleList) {
        return switch (question) {
            case "This bar graph is for?" -> "This bar graph represents the number of travelers by different means.";
            case "Scale used in this graph is?" -> {
                int maxElement = findMax(values);
                int bound = getBound(maxElement);

                if (bound > 0) {
                    yield String.format("$1$ unit $= %d$ vehicles.<br>#$1$ एकक $= %d$ वाहने <br>", bound / 10, bound);
                } else {
                    yield "Unable to determine the scale for the given data.";
                }
            }


            case "How much is the total of travellers for top 3 values?" -> {
                int totalTop3Values = calculateTotalTop3Values(values);
                yield String.valueOf(totalTop3Values);
            }
            case "Which vehicle is used least for travelling?", "Which vehicle is used least?" -> {
                yield findLeastUsedVehicle(values, vehicleList);
            }
            case "Which vehicle is used most for travelling?", "Which vehicle is used most?" -> {
                yield findMostUsedVehicle(values, vehicleList);
            }
            case "How many different vehicles do travellers use?" -> {
                int numberOfDifferentVehicles = countDifferentVehicles(values);
                yield String.valueOf(numberOfDifferentVehicles);
            }
            case "Which are the different vehicles used by travellers?" -> {
                yield listDifferentVehicles(values, vehicleList);
            }
            case "How much is the difference in the number of travellers between the vehicle used most and least?" -> {
                int difference = calculateDifferenceMostLeast(values, vehicleList);
                yield String.valueOf(difference);
            }
            case "How many are the total travellers travelling by the vehicles used most and least?" -> {
                int totalMostLeast = calculateTotalMostLeast(values, vehicleList);
                yield String.valueOf(totalMostLeast);
            }
            case "How many are the total travellers?" -> {
                int totalTravelers = calculateTotalTravelers(values);
                yield String.valueOf(totalTravelers);
            }
            case "How many are the total of travellers travelling by the second and third most used vehicle?" -> {
                int totalSecondThirdMostUsed = calculateTotalSecondThirdMostUsed(values, vehicleList);
                yield String.valueOf(totalSecondThirdMostUsed);
            }
            default -> "Invalid question";
        };
    }


    private static int calculateTotalTop3Values(int[] values) {
        int total = 0;

        Arrays.sort(values);
        int l = values.length;
        total = values[l - 1] + values[l - 2] + values[l - 3];
        return total;
    }

    private static String findLeastUsedVehicle(int[] values, List<String> vehicleList) {
        int minIndex = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] < values[minIndex]) {
                minIndex = i;
            }
        }
        return vehicleList.get(minIndex);
    }

    private static String findMostUsedVehicle(int[] values, List<String> vehicleList) {
        int maxIndex = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return vehicleList.get(maxIndex);
    }

    private static int countDifferentVehicles(int[] values) {
        int count = 0;
        for (int value : values) {
            if (value > 0) {
                count++;
            }
        }
        return count;
    }

    private static String listDifferentVehicles(int[] values, List<String> vehicleList) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            if (values[i] > 0) {
                result.append(vehicleList.get(i)).append(", ");
            }
        }
        return result.toString().replaceAll(", $", "");
    }

    private static int calculateDifferenceMostLeast(int[] values, List<String> vehicleList) {
        int mostIndex = findMostUsedIndex(values);
        int leastIndex = findLeastUsedIndex(values);
        return values[mostIndex] - values[leastIndex];
    }

    private static int calculateTotalMostLeast(int[] values, List<String> vehicleList) {
        int mostIndex = findMostUsedIndex(values);
        int leastIndex = findLeastUsedIndex(values);
        return values[mostIndex] + values[leastIndex];
    }

    private static int calculateTotalTravelers(int[] values) {
        int total = 0;
        for (int value : values) {
            total += value;
        }
        return total;
    }

    private static int calculateTotalSecondThirdMostUsed(int[] values, List<String> vehicleList) {
        Arrays.sort(values);

        return values[2] + values[1];
    }

    private static int findMostUsedIndex(int[] values) {
        int maxIndex = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] > values[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static int findLeastUsedIndex(int[] values) {
        int minIndex = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[i] < values[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

}
