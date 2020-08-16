package com.hextech.cdlpreptest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hextech.cdlpreptest.util.DBHelper;
import com.hextech.cdlpreptest.util.Question;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class QuestionStatsViewAdapter extends RecyclerView.Adapter<QuestionStatsViewAdapter.QuestionStatsViewHolder>{

    Context context;
    ArrayList<Question> questionList;
    DBHelper database;

    public QuestionStatsViewAdapter(Context ct, ArrayList<Question> questionList){
        this.context = ct;
        this.questionList = questionList;
        this.database = new DBHelper(ct);
    }

    @NonNull
    @Override
    public QuestionStatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.question_stats_row, parent, false);
        return new QuestionStatsViewAdapter.QuestionStatsViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final QuestionStatsViewHolder holder, final int position) {
        holder.textViewQStatQuestion.setText(questionList.get(position).getQuestion());
        holder.textViewQStatCorrectCount.setText("You got it " + String.valueOf(questionList.get(position).getCorrectCount()) + " times right");
        holder.textViewQStatWrongCount.setText("You got it " + String.valueOf(questionList.get(position).getWrongCount()) + " times wrong");

        if(questionList.get(position).isFavorite()){
            holder.imgQStatFavorite.setImageDrawable(this.context.getResources().getDrawable(R.drawable.ic_baseline_favorite_24, null));
        }else{
            holder.imgQStatFavorite.setImageDrawable(this.context.getResources().getDrawable(R.drawable.ic_baseline_favorite_border_24, null));
        }
        holder.imgQStatFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleFavorite(questionList.get(position), questionList.get(position).isFavorite(), holder.imgQStatFavorite);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    private void toggleFavorite(Question question, Boolean currentFavorite, ImageView imgView){
        if(currentFavorite){
            question.setFavorite(false);
            imgView.setImageDrawable(this.context.getResources().getDrawable(R.drawable.ic_baseline_favorite_border_24, null));
            database.addToFavorites(question.getQuestionId(), 0);
            Toast.makeText(this.context, "Removed from favorites", Toast.LENGTH_SHORT).show();
        }else{
            question.setFavorite(true);
            imgView.setImageDrawable(this.context.getResources().getDrawable(R.drawable.ic_baseline_favorite_24, null));
            database.addToFavorites(question.getQuestionId(), 1);
            Toast.makeText(this.context, "Added to favorites", Toast.LENGTH_SHORT).show();
        }
    }

    public class QuestionStatsViewHolder extends RecyclerView.ViewHolder {

        TextView textViewQStatQuestion, textViewQStatCorrectCount, textViewQStatWrongCount;
        ImageView imgQStatFavorite;

        public QuestionStatsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewQStatQuestion = itemView.findViewById(R.id.textViewQStatQuestion);
            textViewQStatCorrectCount = itemView.findViewById(R.id.textViewQStatCorrectCount);
            textViewQStatWrongCount = itemView.findViewById(R.id.textViewQStatWrongCount);
            imgQStatFavorite = itemView.findViewById(R.id.imageViewQStatFavorite);
        }
    }
}
