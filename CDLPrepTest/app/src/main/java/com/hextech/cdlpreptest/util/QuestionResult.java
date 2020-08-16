package com.hextech.cdlpreptest.util;

import java.io.Serializable;

public class QuestionResult  implements Serializable {
    private String question;
    private Boolean isCorrect;
    private int correctAnswerPosition;
    private String[] answerArr;

    public QuestionResult(String question, Boolean isCorrect, int correctAnswerPosition, String[] answerArr) {
        this.question = question;
        this.isCorrect = isCorrect;
        this.correctAnswerPosition = correctAnswerPosition;
        this.answerArr = answerArr;
    }

    public QuestionResult(String question, Boolean isCorrect, String[] answerArr, String correctAnswer) {
        this.question = question;
        this.isCorrect = isCorrect;
        this.answerArr = answerArr;

        if(!isCorrect)setCorrectAnswerPos(correctAnswer);
    }

    public String getQuestion() {
        return question;
    }

    public Boolean isCorrect() {
        return isCorrect;
    }

    public int getCorrectAnswerPosition() {
        return correctAnswerPosition;
    }

    public String[] getAnswerArr() {
        return answerArr;
    }

    private void setCorrectAnswerPos(String correctAnswer){
        for(int x = 0; x < answerArr.length; x++){
            if(answerArr[x].equals(correctAnswer))correctAnswerPosition = x;
        }
    }
}
