package com.hextech.cdlpreptest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hextech.cdlpreptest.util.DBHelper;
import com.hextech.cdlpreptest.util.Question;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class TestQuestionsActivity extends AppCompatActivity {

    Boolean instantFeedback, stopOnMaxMistakes, skip;
    int currentQuestionNum, maxQuestionNum, maxMistakeNum, currentMistakeNum;
    String selectedAnswer;
    Question currentQuestion;
    TextView textViewQuestion, textViewAnswer1, textViewAnswer2, textViewAnswer3, textViewQuestionNumber;
    CardView cardViewAnswer1, cardViewAnswer2, cardViewAnswer3;
    ImageView imgViewFavorite;
    Button btnSkip;

    DBHelper database;
    ArrayList<Question> questionList;
    ArrayList<Integer> wrongQuestionList, correctQuestionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_questions);

        textViewQuestionNumber = this.findViewById(R.id.textViewQuestionNumber);
        textViewQuestion = this.findViewById(R.id.textViewQuestion);
        textViewAnswer1 = this.findViewById(R.id.textViewAnswer1);
        textViewAnswer2 = this.findViewById(R.id.textViewAnswer2);
        textViewAnswer3 = this.findViewById(R.id.textViewAnswer3);
        cardViewAnswer1 = this.findViewById(R.id.cardViewAnswer1);
        cardViewAnswer2 = this.findViewById(R.id.cardViewAnswer2);
        cardViewAnswer3 = this.findViewById(R.id.cardViewAnswer3);
        imgViewFavorite = this.findViewById(R.id.imgViewFavorite);

        btnSkip = this.findViewById(R.id.btnSkip);
        database = new DBHelper(TestQuestionsActivity.this);
        wrongQuestionList = new ArrayList<>();
        correctQuestionList = new ArrayList<>();

        SharedPreferences pref = getApplicationContext().getSharedPreferences("cdlpref", Context.MODE_PRIVATE);
        currentQuestionNum = pref.getInt("currentQuestion", 0);
        maxQuestionNum = pref.getInt("numQuestions", 50);
        maxMistakeNum = pref.getInt("numMistakes", 10);
        instantFeedback = pref.getBoolean("instantFeedback", Boolean.FALSE);
        stopOnMaxMistakes = pref.getBoolean("stopOnMaxMistakes", Boolean.FALSE);
        skip = true;

        setOnClickListeners();
        getQuestionList();
        setNextQuestion(currentQuestionNum);
    }

    /**
     * Sets on click listeners for the card views, favorite image view and the skip/continue button
     */
    private void setOnClickListeners() {

        imgViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFavorite();
            }
        });

        cardViewAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = String.valueOf(textViewAnswer1.getText());
                btnSkip.setText(getResources().getString(R.string.btn_continue));
                skip = false;

                if(instantFeedback){
                    toggleCardViewListeners(false);
                    showCorrectAnswer(cardViewAnswer1, String.valueOf(textViewAnswer1.getText()));
                }else{
                    resetColors();
                    cardViewAnswer1.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_grey, null));
                }
            }
        });

        cardViewAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = String.valueOf(textViewAnswer2.getText());
                btnSkip.setText(getResources().getString(R.string.btn_continue));
                skip = false;

                if(instantFeedback){
                    toggleCardViewListeners(false);
                    showCorrectAnswer(cardViewAnswer2, String.valueOf(textViewAnswer2.getText()));
                }else{
                    resetColors();
                    cardViewAnswer2.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_grey, null));
                }
            }
        });

        cardViewAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedAnswer = String.valueOf(textViewAnswer3.getText());
                btnSkip.setText(getResources().getString(R.string.btn_continue));
                skip = false;

                if(instantFeedback){
                    toggleCardViewListeners(false);
                    showCorrectAnswer(cardViewAnswer3, String.valueOf(textViewAnswer3.getText()));
                }else{
                    resetColors();
                    cardViewAnswer3.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.light_grey, null));
                }
            }
        });

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(skip){
                    //Logic to skip current question
                }else{
                    if(!instantFeedback){
                        if(!isCorrectAnswer(currentQuestion.getCorrectAnswer(), selectedAnswer)){
                            currentMistakeNum++;
                            wrongQuestionList.add(currentQuestion.getQuestionId());
                        }else{
                            correctQuestionList.add(currentQuestion.getQuestionId());
                        }
                    }

                    if(stopOnMaxMistakes && (currentMistakeNum >= maxMistakeNum)){
                        endTest();
                    }

                    if(currentQuestionNum + 1 < maxQuestionNum){
                        setNextQuestion(++currentQuestionNum);
                    }else{
                        endTest();
                    }
                }
            }
        });
    }

    /**
     * Shows the correct answer if instant feedback was chosen
     */
    private void showCorrectAnswer(CardView selectedCard, String selectedAnswer){
        if(isCorrectAnswer(currentQuestion.getCorrectAnswer(), selectedAnswer)){
            selectedCard.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.correct_answer_bg, null));
            correctQuestionList.add(currentQuestion.getQuestionId());
        }else{
            selectedCard.setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.wrong_answer_bg, null));
            getCorrectAnswerCard().setCardBackgroundColor(getApplicationContext().getResources().getColor(R.color.correct_answer_bg, null));
            currentMistakeNum++;
            wrongQuestionList.add(currentQuestion.getQuestionId());
        }
    }

    /**
     * Returns the card view object of the correct answer
     * @return - the card view object
     */
    private CardView getCorrectAnswerCard(){
        if(isCorrectAnswer(currentQuestion.getCorrectAnswer(), String.valueOf(textViewAnswer1.getText()))){
            return cardViewAnswer1;
        }else if(isCorrectAnswer(currentQuestion.getCorrectAnswer(), String.valueOf(textViewAnswer2.getText()))){
            return cardViewAnswer2;
        }else {
            return cardViewAnswer3;
        }
    }

    /**
     * Reset the colors of the card views to the default white color
     */
    private void resetColors(){
        cardViewAnswer1.setCardBackgroundColor(Color.WHITE);
        cardViewAnswer2.setCardBackgroundColor(Color.WHITE);
        cardViewAnswer3.setCardBackgroundColor(Color.WHITE);
    }

    /**
     * Ends the test simulator and goes to the Test Result Activity
     */
    private void endTest() {
        if(wrongQuestionList.size() > 0){
            database.increaseAnswerCount(wrongQuestionList, Boolean.TRUE);
        }
        if(correctQuestionList.size() > 0){
            database.increaseAnswerCount(correctQuestionList, Boolean.FALSE);
        }
        finish();
    }

    /**
     * Toggles the clickable status of the card views
     * @param bool - the status to be toggled to
     */
    private void toggleCardViewListeners(Boolean bool){
        cardViewAnswer1.setClickable(bool);
        cardViewAnswer2.setClickable(bool);
        cardViewAnswer3.setClickable(bool);
    }

    /**
     * Gets the list of questions from the database
     */
    private void getQuestionList(){
        questionList = database.getQuestionDataWithSelection(null);
    }

    /**
     * Get the next question and sets it to the view objects
     * @param questionNo - Next question index from the question list
     */
    @SuppressLint("DefaultLocale")
    private void setNextQuestion(int questionNo){
        currentQuestion = questionList.get(questionNo);

        textViewQuestionNumber.setText(String.format("Question %d/%d", questionNo + 1, maxQuestionNum));
        setQuestionAndAnswersToTextViews(currentQuestion.getQuestion(), currentQuestion.getAnswerArr());
        toggleCardViewListeners(true);
        resetColors();
    }

    /**
     * Sets the text of question and the three answers
     * @param question - Question text
     * @param answerArr - Array of answers
     */
    private void setQuestionAndAnswersToTextViews(String question, String[] answerArr){
        textViewQuestion.setText(question);
        textViewAnswer1.setText(answerArr[0]);
        textViewAnswer2.setText(answerArr[1]);
        textViewAnswer3.setText(answerArr[2]);
    }

    /**
     * Checks if the chosen answer is correct
     * @param correctAnswer - Correct answer
     * @param selectedAnswer - User selected answer
     * @return - Returns a boolean
     */
    private Boolean isCorrectAnswer(String correctAnswer, String selectedAnswer){
        return correctAnswer.equals(selectedAnswer);
    }

    /**
     * Toggles the favorite boolean of the question and the image of the favorite image view
     */
    private void toggleFavorite(){
        if(currentQuestion.isFavorite()){
            currentQuestion.setFavorite(false);
            imgViewFavorite.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.ic_baseline_favorite_border_24, null));
            Toast.makeText(TestQuestionsActivity.this, "Removed from favorites", Toast.LENGTH_SHORT).show();
        }else{
            currentQuestion.setFavorite(true);
            imgViewFavorite.setImageDrawable(getApplicationContext().getResources().getDrawable(R.drawable.ic_baseline_favorite_24, null));
            Toast.makeText(TestQuestionsActivity.this, "Added to favorites", Toast.LENGTH_SHORT).show();
        }
    }
}