package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static String getSolution(String question, int[] values, List<String> vehicleList) {
        String answer = Answers.getAnswer(question, values, vehicleList);

        return switch (question) {
            case "Which vehicle is used most for travelling?", "Which vehicle is used most?" -> {
                int[] sortedValues = Arrays.copyOf(values, values.length);
                Arrays.sort(sortedValues);
                int maxValue = sortedValues[sortedValues.length - 1];
                yield String.format("Ans: %s<br>From the given graph we can see that<br>" +
                                "No. of travellers using the vehicle most is $= %d$ $\\therefore$ $= %s$ is the answer.<br>" +
                                "#उत्तर : $%s$<br>ददिलेल्या स्तंभालेखानुसार<br>" +
                                "सगळ्यात जास्त वापरल्या जाणाऱ्या वाहनासाठी प्रवाशांची संख्या $= %d$ $\\therefore$ $= %s$ आहे, हे उत्तर.<br>",
                        answer,maxValue, answer, getSingleVehicleTranslation(answer),maxValue, getSingleVehicleTranslation(answer));
            }
            case "Which vehicle is used least for travelling?", "Which vehicle is used least?" ->
                    String.format("Ans: %s<br>From the given graph we can see that<br>" +
                                    "No. of travellers using the least used vehicle is $= %s$ $\\therefore$  is the answer.<br>" +
                                    "#उत्तर : %s<br>दिलेल्या स्तंभालेखानुसार<br>" +
                                    "सगळ्यात कमी वापरलेल्या वाहनासाठी प्रवाशांची संख्या $= %s$ आहे,$\\therefore$ हे उत्तर.<br>",
                            answer,answer,getSingleVehicleTranslation(answer) , getSingleVehicleTranslation(answer));
            case "How many are the total of travellers travelling by the second and third most used vehicle?" -> {
                int[] sortedValues = Arrays.copyOf(values, values.length);
                Arrays.sort(sortedValues);

                yield String.format("Ans: $%d$<br>From the given graph we can see that<br>" +
                                "No. of travellers using the second most used vehicle is $= %d$<br>" +
                                "No. of travellers using the third most used vehicle is $= %d$<br>" +
                                "By taking the addition of these two numbers we get<br>" +
                                "$%d + %d = %s$ is the answer.<br>" +
                                "#उत्तर : $%d$<br>दिलेल्या स्तंभालेखानुसार<br>" +
                                "दोन क्रमांकाचे सर्वात जास्त वापरल्या जाणाऱ्या वाहनासाठी प्रवाशांची संख्या $= %s$<br>" +
                                "तीन क्रमांकाचे सर्वात जास्त वापरल्या जाणाऱ्या वाहनासाठी प्रवाशांची संख्या $= %s$<br>" +
                                "या दोन्ही प्रवाशांच्या संख्यांची बेरीज घेऊन आपल्याला<br>" +
                                "$%s + %s = %s$ हे उत्तर मिळते.<br>",
                        sortedValues[1] + sortedValues[2],sortedValues[1], sortedValues[2], sortedValues[1], sortedValues[2], sortedValues[1] + sortedValues[2],sortedValues[1] + sortedValues[2], sortedValues[1], sortedValues[2], sortedValues[1], sortedValues[2], sortedValues[1] + sortedValues[2]);
            }
            case "How many are the total travellers?" -> {
                yield String.format("Ans: $%d$<br>From the given graph we can see that<br>" +
                                "No. of travellers using the %s $= %d$<br>" +
                                "No. of travellers using the %s $= %d$<br>" +
                                "No. of travellers using the %s $= %d$<br>" +
                                "No. of travellers using the %s $= %d$<br>" +
                                "By taking the total of these numbers we get<br>" +
                                "$%d + %d + %d + %d = %d$ is the answer.<br>" +
                                "#उत्तर : $%d$<br>दिलेल्या स्तंभालेखानुसार<br>" +
                                "%s या वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "%s या वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "%s या वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "%s या वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "या सर्व प्रवासी संख्येची बेरीज घेऊन आपल्याला<br>" +
                                "$%d + %d + %d + %d = %d$ हे उत्तर मिळते.<br>",
                        Arrays.stream(values).sum(),vehicleList.get(0), values[0], vehicleList.get(1), values[1],
                        vehicleList.get(2), values[2], vehicleList.get(3), values[3],
                        values[0], values[1], values[2], values[3], Arrays.stream(values).sum(),
                        Arrays.stream(values).sum(),getSingleVehicleTranslation(vehicleList.get(0)), values[0], getSingleVehicleTranslation(vehicleList.get(1)), values[1],
                        getSingleVehicleTranslation(vehicleList.get(2)), values[2], getSingleVehicleTranslation(vehicleList.get(3)), values[3],
                        values[0], values[1], values[2], values[3], Arrays.stream(values).sum());
            }
            case "Which are the different vehicles used by travellers?" -> {
                String answer1 = String.join(", ", vehicleList);
                String vehicleListString = vehicleList.stream()
                        .map(vehicle -> "$" + vehicle + "$")
                        .collect(Collectors.joining(", "));

                yield String.format("Ans: %s<br>" +
                                "From the given graph we can see that, %s these are the different vehicles used is the answer.<br>" +
                                "#उत्तर : %s<br>" +
                                "दिलेल्या स्तंभालेखानुसार %s अशी वाहने वापरली जातात हे उत्तर.<br>",
                        answer1, vehicleListString, answer1, vehicleListString);
            }
            case "How much is the difference in the number of travellers between the vehicle used most and least?" -> {
                int maxValue = Arrays.stream(values).max().orElse(0);
                int minValue = Arrays.stream(values).min().orElse(0);
                int difference = maxValue - minValue;

                yield String.format("Ans: $%d$<br>" +
                                "From the given graph we can see that<br>" +
                                "No. of travellers using the vehicle most $= %d$<br>" +
                                "No. of travellers using the vehicle least $= %d$<br>" +
                                "By taking the difference of these two numbers we get<br>" +
                                "$%d - %d = %d$ is the answer.<br>" +
                                "#उत्तर : $%d$<br>" +
                                "दिलेल्या स्तंभालेखानुसार<br>" +
                                "सगळ्यात जास्त वापरल्या जाणाऱ्या वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "सगळ्यात कमी वापरलेल्या वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "या दोन्ही प्रवाशांच्या संख्यांचा फरक घेऊन आपल्याला<br>" +
                                "$%d - %d = %d$ हे उत्तर मिळते.<br>",
                        difference, maxValue, minValue, maxValue, minValue, difference, difference,
                        maxValue, minValue, maxValue, minValue, difference);
            }
            case "This bar graph is for ..." -> {
                yield "Ans : $Number of travellers travelling by different vehicles$<br>" +
                        "As mentioned in the problem statement, the bar graph shows the number of travellers, travelling by different vehicles.<br>" +
                        "#उत्तर : वेगवेगळ्या वाहनांतून प्रवास करणाऱ्या प्रवाशांची संख्या दाखविण्यासाठी<br>" +
                        "प्रश्नात दिल्यानुसार वेगवेगळ्या वाहनांतून प्रवास करणाऱ्या प्रवाशांची संख्या दाखविण्यासाठी हा स्तंभालेख आहे.<br>";
            }
            case "How much is the total of travellers for top $3$ values?" -> {
                int[] sortedValues = Arrays.copyOf(values, values.length);
                Arrays.sort(sortedValues);

                int totalTop3Values = sortedValues[sortedValues.length - 1] +
                        sortedValues[sortedValues.length - 2] +
                        sortedValues[sortedValues.length - 3];
                int l = sortedValues.length;

                int sum = sortedValues[l - 1] + sortedValues[l - 2] + sortedValues[l - 3];
                yield String.format("Ans: $%d$<br>From the given graph we can see that<br>" +
                                "No. of travellers travelling by the most used vehicle is $= %d$<br>" +
                                "No. of travellers travelling by the second most used vehicle is $= %d$<br>" +
                                "No. of travellers travelling by the third most used vehicle is $= %d$<br>" +
                                "By taking the addition of these three numbers we get<br>" +
                                "$%d + %d + %d = %s$ is the answer.<br>" +
                                "#उत्तर : $%d$<br>#दिलेल्या स्तंभालेखानुसार<br>" +
                                "सर्वात जास्त वापरल्या जाणाऱ्या वाहनासाठी प्रवाशांची संख्या $= %s$<br>" +
                                "दुसऱ्या क्रमांकाचे सर्वात जास्त वापरल्या जाणाऱ्या वाहनासाठी प्रवाशांची संख्या $= %s$<br>" +
                                "तिसऱ्या क्रमांकाचे सर्वात जास्त वापरल्या जाणाऱ्या वाहनासाठी प्रवाशांची संख्या $= %s$<br>" +
                                "या तिन्ही प्रवाशांच्या संख्यांची बेरीज घेऊन आपल्याला<br>" +
                                "$%s + %s + %s = %s$ हे उत्तर मिळते.<br>",
                        sum,sortedValues[l - 1], sortedValues[l - 2], sortedValues[l - 3], sortedValues[l - 1], sortedValues[l - 2], sortedValues[l - 3], sum,
                        sum,sortedValues[l - 1], sortedValues[l - 2], sortedValues[l - 3], sortedValues[l - 1], sortedValues[l - 2], sortedValues[l - 3], sum);
            }
            case "How many are the total travellers travelling by the vehicles used most and least?" -> {
                int maxValue = Arrays.stream(values).max().orElse(0);
                int minValue = Arrays.stream(values).min().orElse(0);
                int sum = maxValue + minValue;

                yield String.format("Ans: $%d$<br>" +
                                "From the given graph we can see that<br>" +
                                "No. of travellers using the vehicle most $= %d$<br>" +
                                "No. of travellers using the vehicle least $= %d$<br>" +
                                "By taking the total of these two numbers we get<br>" +
                                "$%d + %d = %d$ is the answer.<br>" +
                                "#उत्तर : $%d$<br>" +
                                "दिलेल्या स्तंभालेखानुसार<br>" +
                                "सगळ्यात जास्त वापरल्या जाणाऱ्या वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "सगळ्यात कमी वापरलेल्या वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "या दोन्ही प्रवाशांच्या संख्यांची बेरीज घेऊन आपल्याला<br>" +
                                "$%d + %d = %d$ हे उत्तर मिळते.<br>",
                        sum, maxValue, minValue, maxValue, minValue, sum, sum,
                        maxValue, minValue, maxValue, minValue, sum);
            }
            case "Scale used in this graph is ..." -> {
                yield "Ans : $1$ unit = $. . .$ vehicles<br>" +
                        "As shown in the bar graph, the scale used is, $1$ unit = $. . .$ vehicles.<br>" +
                        "#उत्तर : $1$ एकक = $. . .$ वाहने<br>" +
                        "स्तंभलेखात दाखविल्यानुसार वापरलेले प्रमाण $1$ एकक = $. . .$ वाहने असे आहे.<br>";
            }
            default ->
                    "";
        };
    }

    private static String getSingleVehicleTranslation(String vehicle) {
        return switch (vehicle.trim().toLowerCase()) {
            case "scooter" -> "दुचाकी";
            case "car" -> "कार";
            case "motorcycle" -> "मोटरसायकल";
            case "bicycle" -> "सायकल";
            case "bus" -> "बस";
            case "truck" -> "ट्रक";
            default -> vehicle;
        };
    }
}
