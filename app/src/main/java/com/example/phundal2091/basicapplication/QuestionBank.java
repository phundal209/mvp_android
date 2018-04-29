package com.example.phundal2091.basicapplication;

import android.content.Context;
import android.util.Log;

import com.example.phundal2091.basicapplication.ui.ContentViewPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static com.example.phundal2091.basicapplication.Constants.QUESTION_EIGHT;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_EIGHTEEN;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_ELEVEN;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_FIFTEEN;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_FIVE;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_FOUR;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_FOURTEEN;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_NINE;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_NINETEEN;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_ONE;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_SEVEN;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_SEVENTEEN;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_SIX;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_SIXTEEN;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_TEN;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_THIRTEEN;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_THREE;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_TWELVE;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_TWENTY;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_TWENTYONE;
import static com.example.phundal2091.basicapplication.Constants.QUESTION_TWO;

/**
 * Created by phundal2091 on 4/28/18.
 */

public class QuestionBank implements IQuestionBank {

    private List<QuestionModel> listofQuestions;
    private Context context;

    public QuestionBank(Context context) {
        this.listofQuestions = new ArrayList<>();
        this.context = context;
    }

    @Override
    public List<QuestionModel> getListofQuestions() {
        return listofQuestions;
    }

    @Override
    public QuestionModel getRandomQuestion() {
        Random random = new Random();
        return listofQuestions.get(random.nextInt(listofQuestions.size()));
    }

    @Override
    public List<String> getRandomAnswers(QuestionModel questionModel) {
        List<String> randomAnswerList = new ArrayList<>();
        List<String> answers = questionModel.getAnswers();
        Random random = new Random();
        String randElem;

        while (randomAnswerList.size() < 4) {
            int randIndex = random.nextInt(answers.size());
            randElem = answers.get(randIndex);
            if (!randomAnswerList.contains(randElem)) {
                randomAnswerList.add(randElem);
            }
        }
//        for (int i = 0; i < answers.size(); i++) {
//            int randIndex = random.nextInt(answers.size());
//            randElem = answers.get(randIndex);
//            randomAnswerList.add(randElem);
//        }
        return randomAnswerList;
    }

    private List<String> convertJsonArrayToString(JSONArray array) {
        List<String> stringList = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            try {
                stringList.add(array.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return stringList;
    }

    private void getQuestions(JSONObject jsonObject) {
        listofQuestions.clear();
        try {
            JSONArray item = jsonObject.getJSONArray(QUESTION_ONE);
            List<String> stringOne = convertJsonArrayToString(item);
            QuestionModel one = new QuestionModel(QUESTION_ONE, stringOne);
            listofQuestions.add(one);
            JSONArray item2 = jsonObject.getJSONArray(QUESTION_TWO);
            List<String> stringTwo = convertJsonArrayToString(item2);
            QuestionModel two = new QuestionModel(QUESTION_TWO, stringTwo);
            listofQuestions.add(two);
            JSONArray item3 = jsonObject.getJSONArray(QUESTION_THREE);
            List<String> stringThree = convertJsonArrayToString(item3);
            QuestionModel three = new QuestionModel(QUESTION_THREE, stringThree);
            listofQuestions.add(three);
            JSONArray item4 = jsonObject.getJSONArray(QUESTION_FOUR);
            List<String> stringFour = convertJsonArrayToString(item4);
            QuestionModel four = new QuestionModel(QUESTION_FOUR, stringFour);
            listofQuestions.add(four);
            JSONArray item5 = jsonObject.getJSONArray(QUESTION_FIVE);
            List<String> stringFive = convertJsonArrayToString(item5);
            QuestionModel five = new QuestionModel(QUESTION_FIVE, stringFive);
            listofQuestions.add(five);
            JSONArray item6 = jsonObject.getJSONArray(QUESTION_SIX);
            List<String> stringSix = convertJsonArrayToString(item6);
            QuestionModel six = new QuestionModel(QUESTION_SIX, stringSix);
            listofQuestions.add(six);
            JSONArray item7 = jsonObject.getJSONArray(QUESTION_SEVEN);
            List<String> stringSeven = convertJsonArrayToString(item7);
            QuestionModel seven = new QuestionModel(QUESTION_SEVEN, stringSeven);
            listofQuestions.add(seven);
            JSONArray item8 = jsonObject.getJSONArray(QUESTION_EIGHT);
            List<String> stringEight = convertJsonArrayToString(item8);
            QuestionModel eight = new QuestionModel(QUESTION_EIGHT, stringEight);
            listofQuestions.add(eight);
            JSONArray item9 = jsonObject.getJSONArray(QUESTION_NINE);
            List<String> stringNine = convertJsonArrayToString(item9);
            QuestionModel nine = new QuestionModel(QUESTION_NINE, stringNine);
            listofQuestions.add(nine);
            JSONArray item10 = jsonObject.getJSONArray(QUESTION_TEN);
            List<String> stringTen = convertJsonArrayToString(item10);
            QuestionModel ten = new QuestionModel(QUESTION_TEN, stringTen);
            listofQuestions.add(ten);
            JSONArray item11 = jsonObject.getJSONArray(QUESTION_ELEVEN);
            List<String> stringEleven = convertJsonArrayToString(item11);
            QuestionModel eleven = new QuestionModel(QUESTION_ELEVEN, stringEleven);
            listofQuestions.add(eleven);
            JSONArray item12 = jsonObject.getJSONArray(QUESTION_TWELVE);
            List<String> stringTwelve = convertJsonArrayToString(item12);
            QuestionModel tweleve = new QuestionModel(QUESTION_TWELVE, stringTwelve);
            listofQuestions.add(tweleve);
            JSONArray item13 = jsonObject.getJSONArray(QUESTION_THIRTEEN);
            List<String> stringThirteen = convertJsonArrayToString(item13);
            QuestionModel thirteen = new QuestionModel(QUESTION_THIRTEEN, stringThirteen);
            listofQuestions.add(thirteen);
            JSONArray item14 = jsonObject.getJSONArray(QUESTION_FOURTEEN);
            List<String> stringFourteen = convertJsonArrayToString(item14);
            QuestionModel fourteen = new QuestionModel(QUESTION_FOURTEEN, stringFourteen);
            listofQuestions.add(fourteen);
            JSONArray item15 = jsonObject.getJSONArray(QUESTION_FIFTEEN);
            List<String> stringFifteen = convertJsonArrayToString(item15);
            QuestionModel fifteen = new QuestionModel(QUESTION_FIFTEEN, stringFifteen);
            listofQuestions.add(fifteen);
            JSONArray item16 = jsonObject.getJSONArray(QUESTION_SIXTEEN);
            List<String> stringSixteen = convertJsonArrayToString(item16);
            QuestionModel sixteen = new QuestionModel(QUESTION_SIXTEEN, stringSixteen);
            listofQuestions.add(sixteen);
            JSONArray item17 = jsonObject.getJSONArray(QUESTION_SEVENTEEN);
            List<String> stringSeventeen = convertJsonArrayToString(item17);
            QuestionModel seventeen = new QuestionModel(QUESTION_SEVENTEEN, stringSeventeen);
            listofQuestions.add(seventeen);
            JSONArray item18 = jsonObject.getJSONArray(QUESTION_EIGHTEEN);
            List<String> stringEighteen = convertJsonArrayToString(item18);
            QuestionModel eighteen = new QuestionModel(QUESTION_EIGHTEEN, stringEighteen);
            listofQuestions.add(eighteen);
            JSONArray item19 = jsonObject.getJSONArray(QUESTION_NINETEEN);
            List<String> stringNineteen = convertJsonArrayToString(item19);
            QuestionModel nineteen = new QuestionModel(QUESTION_NINETEEN, stringNineteen);
            listofQuestions.add(nineteen);
            JSONArray item20 = jsonObject.getJSONArray(QUESTION_TWENTY);
            List<String> stringTwenty = convertJsonArrayToString(item20);
            QuestionModel twenty = new QuestionModel(QUESTION_TWENTY, stringTwenty);
            listofQuestions.add(twenty);
            JSONArray item21 = jsonObject.getJSONArray(QUESTION_TWENTYONE);
            List<String> stringTwentyOne = convertJsonArrayToString(item21);
            QuestionModel twentyone = new QuestionModel(QUESTION_TWENTYONE, stringTwentyOne);
            listofQuestions.add(twentyone);
        } catch (JSONException j) {
            j.printStackTrace();
        }
    }

    @Override
    public QuestionModel getQuestion() {
        QuestionModel questionModel = new QuestionModel();
        try {
            JSONObject jsonOfAsset = new JSONObject(readJSONFromAsset(context));
            getQuestions(jsonOfAsset);
            QuestionModel randomQuestion = getRandomQuestion();
            List<String> randomAnswers = getRandomAnswers(randomQuestion);
            questionModel.setQuestion(randomQuestion.getQuestion());
            questionModel.setAnswers(randomAnswers);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return questionModel;
    }

    public String readJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
