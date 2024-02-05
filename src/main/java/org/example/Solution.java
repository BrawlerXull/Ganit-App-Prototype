package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public static String getSolution(String question, int[] values, List<String> vehicleList) {
        String answer = Answers.getAnswer(question, values, vehicleList);

        return switch (question) {
            case "Which vehicle is used most for travelling?", "Which vehicle is used most?" ->
                    String.format("From the given graph we can see that<br>" +
                                    "No. of travellers using the vehicle most is $= %s$ is the answer.<br>" +
                                    "#दिलेल्या स्तंभालेखानुसार <br>" +
                                    "सगळ्यात जास्त वापरल्या जाणाऱ्या वाहनासाठी प्रवाशांची संख्या $= %s$ आहे, हे उत्तर.<br>",
                            answer, getSingleVehicleTranslation(answer));
            case "Which vehicle is used least for travelling?", "Which vehicle is used least?" ->
                    String.format("From the given graph we can see that<br>" +
                                    "No. of travellers using the least used vehicle is $= %s$ is the answer.<br>" +
                                    "#दिलेल्या स्तंभालेखानुसार <br>" +
                                    "सगळ्यात कमी वापरलेल्या वाहनासाठी प्रवाशांची संख्या $= %s$ आहे, हे उत्तर.<br>",
                            answer, getSingleVehicleTranslation(answer));
            case "How many are the total of travellers travelling by the second and third most used vehicle?" -> {
//                System.out.println("ok");
                int[] sortedValues = Arrays.copyOf(values, values.length);
                Arrays.sort(sortedValues);

                yield String.format("From the given graph we can see that<br>" +
                                "No. of travellers using the second most used vehicle is $= %d$ <br>" +
                                "No. of travellers using the third most used vehicle is $= %d$ <br>" +
                                "By taking the addition of these two numbers we get  <br>" +
                                "$%d + %d = %s$ is the answer.<br>" +
                                "#दिलेल्या स्तंभालेखानुसार <br>" +
                                "दोन क्रमांकाचे सर्वात जास्त वापरल्या जाणाऱ्या वाहनासाठी प्रवाशांची संख्या $= %s$<br>" +
                                "तीन क्रमांकाचे सर्वात जास्त वापरल्या जाणाऱ्या वाहनासाठी प्रवाशांची संख्या $= %s$<br>" +
                                "या दोन्ही प्रवाशांच्या संख्यांची बेरीज  घेऊन आपल्याला <br>" +
                                "$%s + %s = %s$  हे उत्तर मिळते.<br>",
                        sortedValues[1],sortedValues[2],sortedValues[1],sortedValues[2],sortedValues[1]+sortedValues[2],sortedValues[1],sortedValues[2],sortedValues[1],sortedValues[2],sortedValues[1]+sortedValues[2]
                       );
            }
            case "How many are the total travellers?" -> {
                yield String.format("From the given graph we can see that<br>" +
                                "No. of travellers using the %s $= %d$<br>" +
                                "No. of travellers using the %s  $= %d$<br>" +
                                "No. of travellers using the %s  $= %d$<br>" +
                                "No. of travellers using the %s  $= %d$<br>" +
                                "By taking the total of these numbers we get  <br>" +
                                "$%d + %d + %d + %d = %d$ is the answer.<br>"+
                                "#दिलेल्या स्तंभालेखानुसार <br>" +
                                "%s या पहिल्या वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "%s या दुसऱ्या वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "%s या तिसऱ्या वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "%s या . . . . . . वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "या सर्व  प्रवासी संख्येची बेरीज  घेऊन आपल्याला <br>" +
                                "$%d + %d + %d + %d = %d$  हे उत्तर मिळते.<br>",
                        vehicleList.get(0), values[0], vehicleList.get(1), values[1],
                        vehicleList.get(2), values[2], vehicleList.get(3), values[3],
                        values[0], values[1], values[2], values[3], Arrays.stream(values).sum(),
                        vehicleList.get(0), values[0], vehicleList.get(1), values[1],
                        vehicleList.get(2), values[2], vehicleList.get(3), values[3],
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

                yield String.format("Ans: $%d$ <br>" +
                                "From the given graph we can see that<br>" +
                                "No. of travellers using the vehicle most $= %d$<br>" +
                                "No. of travellers using the vehicle least $= %d$<br>" +
                                "By taking the difference of these two numbers we get  <br>" +
                                "$%d - %d = %d$  is the answer.<br>" +
                                "#उत्तर : $%d$<br>" +
                                "दिलेल्या स्तंभालेखानुसार <br>" +
                                "सगळ्यात जास्त वापरल्या जाणाऱ्या वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "सगळ्यात कमी वापरलेल्या जाणाऱ्या वाहनासाठी प्रवाशांची संख्या $= %d$<br>" +
                                "या दोन्ही प्रवाशांच्या संख्यांची वेगवेगळी फरक  घेऊन आपल्याला <br>" +
                                "$%d - %d = %d$  हे उत्तर मिळते.<br>",
                        difference, maxValue, minValue, maxValue, minValue, difference, difference,
                        maxValue, minValue, maxValue, minValue, difference);
            }


            case "This bar graph is for?" -> {
                yield "As mentioned in the problem statement, the bar graph shows the number of travellers, travelling by different vehicles.<br>#प्रश्नात दिल्यानुसार वेगवेगळ्या वाहनातून प्रवास करणाऱ्या प्रवाशांची संख्या दाखविण्यासाठी हे स्तंभालेख आहे.<br>";
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
