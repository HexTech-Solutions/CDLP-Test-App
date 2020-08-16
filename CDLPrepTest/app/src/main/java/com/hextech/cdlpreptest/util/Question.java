package com.hextech.cdlpreptest.util;

import java.util.Arrays;
import java.util.Collections;

public class Question {
    private int questionId;
    private String question, category, correctAnswer, wrongAnswer1, wrongAnswer2;
    private Boolean isFavorite;
    private String[] answerArr;

    public Question(int questionId, String question, String category, String correctAnswer, String wrongAnswer1, String wrongAnswer2, int isFavorite) {
        this.questionId = questionId;
        this.question = question;
        this.category = category;
        this.correctAnswer = correctAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.isFavorite = Encode(isFavorite);

        answerArr = new String[]{correctAnswer, wrongAnswer1, wrongAnswer2};
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public String getCategory() {
        return category;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public Boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String[] getAnswerArr() {
        Collections.shuffle(Arrays.asList(answerArr));
        return answerArr;
    }

    public Boolean Encode(int tempInt) {
        return tempInt == 1;
    }

    public int Decode(Boolean tempBool) {
        if(tempBool){
            return 1;
        }else{
            return 0;
        }
    }
}
