package com.example.phundal2091.basicapplication.ui;

import com.example.phundal2091.basicapplication.QuestionModel;
import com.example.phundal2091.basicapplication.framework.IPresenter;

/**
 * Created by phundal2091 on 1/18/18.
 */

public interface IContentViewPresenter extends IPresenter<ContentView, Object>{
    QuestionModel getSelectedQuestion();

    void updateTimer(long secondsLeft);

    void bindSelectedQuestion(QuestionModel questionModel);
}
