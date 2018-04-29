package com.example.phundal2091.basicapplication;

import java.util.List;

/**
 * Created by phundal2091 on 4/28/18.
 */

public interface IQuestionBank {
    List<QuestionModel> getListofQuestions();

    QuestionModel getRandomQuestion();

    List<String> getRandomAnswers(QuestionModel questionModel);

    QuestionModel getQuestion();
}
