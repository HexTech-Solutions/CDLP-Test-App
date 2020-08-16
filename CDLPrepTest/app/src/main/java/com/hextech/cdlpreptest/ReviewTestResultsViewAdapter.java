package com.hextech.cdlpreptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hextech.cdlpreptest.util.QuestionResult;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewTestResultsViewAdapter extends RecyclerView.Adapter<ReviewTestResultsViewAdapter.TestResultsViewHolder>{

    Context context;
    ArrayList<QuestionResult> resultsList;

    public ReviewTestResultsViewAdapter(Context ct, ArrayList<QuestionResult> resultsList){
        this.context = ct;
        this.resultsList = resultsList;
    }

    @NonNull
    @Override
    public TestResultsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.review_test_results_row, parent, false);
        return new TestResultsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestResultsViewHolder holder, int position) {
        resetCardHolder(holder);

        holder.textViewQuestion.setText(resultsList.get(position).getQuestion());
        holder.textViewAnswer1.setText(resultsList.get(position).getAnswerArr()[0]);
        holder.textViewAnswer2.setText(resultsList.get(position).getAnswerArr()[1]);
        holder.textViewAnswer3.setText(resultsList.get(position).getAnswerArr()[2]);

        if(resultsList.get(position).isCorrect()){
            holder.textViewQuestion.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_check_24, 0, 0, 0);
        }else{
            holder.textViewQuestion.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_close_24, 0, 0, 0);
        }
        setCorrectAnswerDrawable(holder, resultsList.get(position).getCorrectAnswerPosition());
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    /**
     * Sets the drawable for the correct answer and adjust the padding of the other textViews
     * @param holder
     * @param correctAnswerPosition
     */
    private void setCorrectAnswerDrawable(TestResultsViewHolder holder, int correctAnswerPosition){
        switch(correctAnswerPosition){
            case 0:
                holder.textViewAnswer1.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_check_24, 0, 0, 0);
                holder.textViewAnswer2.setPadding(holder.textViewAnswer1.getTotalPaddingLeft(), 0, 0, 0);
                holder.textViewAnswer3.setPadding(holder.textViewAnswer1.getTotalPaddingLeft(), 0, 0, 0);
                break;
            case 1:
                holder.textViewAnswer2.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_check_24, 0, 0, 0);
                holder.textViewAnswer1.setPadding(holder.textViewAnswer2.getTotalPaddingLeft(), 0, 0, 0);
                holder.textViewAnswer3.setPadding(holder.textViewAnswer2.getTotalPaddingLeft(), 0, 0, 0);
                break;
            case 2:
                holder.textViewAnswer3.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_check_24, 0, 0, 0);
                holder.textViewAnswer1.setPadding(holder.textViewAnswer3.getTotalPaddingLeft(), 0, 0, 0);
                holder.textViewAnswer2.setPadding(holder.textViewAnswer3.getTotalPaddingLeft(), 0, 0, 0);
                break;
        }
    }

    /**
     * To reset the values of the card
     * @param holder
     */
    private static void resetCardHolder(TestResultsViewHolder holder){
        holder.textViewQuestion.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        holder.textViewAnswer1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        holder.textViewAnswer2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        holder.textViewAnswer3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);

        holder.textViewQuestion.setPadding(0, 0, 0, 0);
        holder.textViewAnswer1.setPadding(0, 0, 0, 0);
        holder.textViewAnswer2.setPadding(0, 0, 0, 0);
        holder.textViewAnswer3.setPadding(0, 0, 0, 0);
    }

    public class TestResultsViewHolder extends RecyclerView.ViewHolder{

        TextView textViewQuestion, textViewAnswer1, textViewAnswer2, textViewAnswer3;
        public TestResultsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewQuestion = itemView.findViewById(R.id.textViewTestResultQuestion);
            textViewAnswer1 = itemView.findViewById(R.id.textViewTestResultAnswer1);
            textViewAnswer2 = itemView.findViewById(R.id.textViewTestResultAnswer2);
            textViewAnswer3 = itemView.findViewById(R.id.textViewTestResultAnswer3);
        }
    }
}
