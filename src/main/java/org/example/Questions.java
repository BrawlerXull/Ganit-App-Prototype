package org.example;


public class Questions {
    public static String[] getRandomQuestions(int count) {
        String[] allQuestions = getQuestions();
        int[] indices = new int[count];

        for (int i = 0; i < count; i++) {
            indices[i] = i;
        }

        for (int i = count - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            int temp = indices[i];
            indices[i] = indices[j];
            indices[j] = temp;
        }

        String[] randomQuestions = new String[count];
        for (int i = 0; i < count; i++) {
            randomQuestions[i] = allQuestions[indices[i]];
        }

        return randomQuestions;
    }

    public static String[] getQuestions() {
        return new String[]{
                "This bar graph is for ...",
                "Scale used in this graph is ...",
                "How much is the total of travellers for top $3$ values?",
                "Which vehicle is used least for travelling?",
                "Which vehicle is used most for travelling?",
                "How many are the travellers travelling by second minimum used vehicle?",
                "How many are the travellers travelling by third maximum used vehicle?",
                "How many different vehicles are used by travellers?",
                "Which are the different vehicles are used by travellers?",
                "How much is the difference in the number of travellers between the vehicle used most and least?",
                "How many are the total travellers travelling by the vehicles used most and least?",
                "How many are the total travellers?",
                "How many are the total of travellers travelling by the second and third most used vehicle?",

        };
    }
}
