package com.example.phundal2091.basicapplication;

import java.util.List;

/**
 * Created by phundal2091 on 4/28/18.
 */

public class QuestionModel {
    private String question;
    private List<String> answers;

    public String getQuestion() {
        return question;
    }

    public QuestionModel() {
    }

    public QuestionModel(String question, List<String> answers) {
        this.question = question;
        this.answers = answers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }



}
