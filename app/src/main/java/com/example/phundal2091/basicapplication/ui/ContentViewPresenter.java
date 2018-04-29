package com.example.phundal2091.basicapplication.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.phundal2091.basicapplication.BroadcastService;
import com.example.phundal2091.basicapplication.IQuestionBank;
import com.example.phundal2091.basicapplication.QuestionModel;
import com.example.phundal2091.basicapplication.R;
import com.example.phundal2091.basicapplication.framework.Presenter;
import com.example.phundal2091.basicapplication.wrapper.IImageWrapper;
import com.example.services.IApiService;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by phundal2091 on 1/18/18.
 */

public class ContentViewPresenter extends Presenter<ContentView, Object> implements IContentViewPresenter {

    private IApiService apiService;
    private ProgressDialog progressDialog;
    private IQuestionBank questionBank;
    private IImageWrapper imageWrapper;

    public ContentViewPresenter(Context context, ContentView view, IApiService apiService,
                                IQuestionBank questionBank, IImageWrapper iImageWrapper) {
        super(context, view, false);
        this.apiService = apiService;
        this.questionBank = questionBank;
        this.imageWrapper = iImageWrapper;
    }

    @Override
    public void bindControls() {
        showQuestion();
    }

    private void hideQuestion() {
        view.questionsLayout.setVisibility(View.GONE);
    }

    private void restartTimer() {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.stopService(new Intent(mainActivity, BroadcastService.class));
        mainActivity.startService(new Intent(mainActivity, BroadcastService.class));
    }

    private void stopService() {
        MainActivity mainActivity = (MainActivity) context;
        mainActivity.stopService(new Intent(mainActivity, BroadcastService.class));
    }

    private void showQuestion() {
        view.questionsLayout.setVisibility(View.VISIBLE);
        Observable.just(questionBank.getQuestion())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn(new Function<Throwable, QuestionModel>() {
                    @Override
                    public QuestionModel apply(Throwable throwable) throws Exception {
                        return null;
                    }
                })
                .subscribe(new Consumer<QuestionModel>() {
                    @Override
                    public void accept(QuestionModel questionModel) throws Exception {
                        view.question.setText(questionModel.getQuestion());
                        imageWrapper.displayImage(questionModel.getAnswers().get(0), view.image_one);
                        imageWrapper.displayImage(questionModel.getAnswers().get(1), view.image_two);
                        imageWrapper.displayImage(questionModel.getAnswers().get(2), view.image_three);
                        imageWrapper.displayImage(questionModel.getAnswers().get(3), view.image_four);
                        handleQuizAnswerSelection(view.image_one, 0, questionModel);
                        handleQuizAnswerSelection(view.image_two, 1, questionModel);
                        handleQuizAnswerSelection(view.image_three, 2, questionModel);
                        handleQuizAnswerSelection(view.image_four, 3, questionModel);
                    }
                });
    }


    private void handleQuizAnswerSelection(View image, final int pos, final QuestionModel questionModel) {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isAnswerCorrect(pos, questionModel)) {
                    Toast.makeText(context, context.getString(R.string.correct), Toast.LENGTH_LONG).show();
                    stopService();
                    view.time_count.setText(context.getString(R.string.times_up));
                } else {
                    Toast.makeText(context, context.getString(R.string.incorrect), Toast.LENGTH_LONG).show();
                    stopService();
                    bindRetakeQuiz();
                }
            }
        });
    }

    private boolean isAnswerCorrect(int position, QuestionModel questionModel) {
        List<QuestionModel> listofQuestions = questionBank.getListofQuestions();
        for (QuestionModel question : listofQuestions) {
            if (questionModel.getQuestion().equals(question.getQuestion())) {
                if (question.getAnswers().get(0).equals(questionModel.getAnswers().get(position))) {
                    return true;
                }
            }
        }
        return false;
    }

    private void bindRetakeQuiz() {
        view.retake.setVisibility(View.VISIBLE);
        hideQuestion();
        view.retake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartTimer();
                showQuestion();
                view.retake.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void hide() {
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void show() {
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(context.getString(R.string.waiting_message));
            progressDialog.show();
        }
    }

    @Override
    public void updateTimer(long secondsLeft) {
        if (secondsLeft > 1) {
            view.time_count.setText(String.valueOf(secondsLeft));
        } else if (secondsLeft == 1) {
            stopService();
            bindRetakeQuiz();
            view.time_count.setText(context.getString(R.string.times_up));
        }
    }
}
