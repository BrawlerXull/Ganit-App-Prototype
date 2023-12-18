package org.example;

public class Item {
    String question;
    int o1;

    public Item(String question, int o1) {
        this.question = question;
        this.o1 = o1;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getO1() {
        return o1;
    }

    public void setO1(int o1) {
        this.o1 = o1;
    }

}
