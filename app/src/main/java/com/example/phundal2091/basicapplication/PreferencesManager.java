package com.example.phundal2091.basicapplication;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by phundal2091 on 4/29/18.
 */

public class PreferencesManager {
    private SharedPreferences sharedPreferences;
    private Context context;
    public static final String PREF_NAME = "my_prefs";
    private static final String QUESTION_KEY = "question";
    private static final String ANSWER_KEY = "answers";


    public PreferencesManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_NAME, 0);
        this.context = context;
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }

    public void saveQuestion(QuestionModel questionModel) {
        getEditor()
                .putString(QUESTION_KEY, questionModel.getQuestion())
                .apply();
    }

    public String getSavedQuestion() {
        return sharedPreferences.getString(QUESTION_KEY, null);
    }

    public void saveAnswers(QuestionModel questionModel) {
        Set<String> answers = new HashSet<>();
        for (String answer : questionModel.getAnswers()) {
            answers.add(answer);
        }
        getEditor()
                .putStringSet(ANSWER_KEY, answers)
                .apply();
    }

    public List<String> getAnswers() {
        List<String> answerList = new ArrayList<>();
        Set<String> stringSet = sharedPreferences.getStringSet(ANSWER_KEY, new HashSet<String>());
        for (String answer : stringSet) {
            answerList.add(answer);
        }
        return answerList;
    }

    public void removeQuestion() {
        getEditor().putString(QUESTION_KEY, null).apply();
    }

}
