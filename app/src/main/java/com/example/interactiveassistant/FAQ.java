package com.example.interactiveassistant;

import java.util.List; // Example of an import statement
import java.util.ArrayList;

public class FAQ {
    private final String question;
    private final String answer;

    public FAQ(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
