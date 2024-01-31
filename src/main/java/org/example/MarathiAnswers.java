package org.example;

public class MarathiAnswers {
    public static String getMarathiAnswers(String answer) {
        String marathiAnswer;

        if (answer.toLowerCase().startsWith("1 cm on the y-axis represents") &&
                answer.toLowerCase().endsWith("travelers.")) {
            String variedNumber = answer
                    .replace("1 cm on the y-axis represents", "")
                    .replace("travelers.", "")
                    .trim();

            marathiAnswer = "1 cm on the y-axis represents " + variedNumber +
                    " travelers.<br>#1 सी.एम. य-अक्षावर " + variedNumber + " प्रवाशींची दर्शवणार.<br>";
        } else {
            marathiAnswer = getSingleVehicleTranslation(answer);
        }

        System.out.println(marathiAnswer);
        return marathiAnswer;
    }

    private static String getSingleVehicleTranslation(String vehicle) {
        return switch (vehicle.trim().toLowerCase()) {
            case "scooter" -> "Scooter<br>#दुचाकी <br>";
            case "car" -> "Car<br>#कार<br>";
            case "motorcycle" -> "Motorcycle<br>#मोटरसायकल<br>";
            case "bicycle" -> "Bicycle<br>#सायकल<br>";
            case "bus" -> "Bus<br>#बस<br>";
            case "truck" -> "Truck<br>#ट्रक<br>";
            default -> vehicle;
        };
    }

}
