package com.hextech.cdlpreptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewViewAdapter extends RecyclerView.Adapter<ReviewViewAdapter.ReviewViewHolder> {

    String dataArray1[], dataArray2[];
    int icons[];
    Context context;
    private OnReviweListener mOnReviewListener;

    public ReviewViewAdapter(Context ct, String reviewTitle[], String reviewDesc[], int icon[], OnReviweListener onReviweListener){
        context = ct;
        dataArray1 = reviewTitle;
        dataArray2 = reviewDesc;
        icons = icon;
        this.mOnReviewListener = onReviweListener;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.review_row, parent, false);
        return new ReviewViewHolder(view, mOnReviewListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        holder.title.setText(dataArray1[position]);
        holder.description.setText(dataArray2[position]);
        holder.icon.setImageResource(icons[position]);
    }

    @Override
    public int getItemCount() {
        return dataArray1.length;
    }

    public class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title, description;
        ImageView icon;
        OnReviweListener onReviweListener;

         public ReviewViewHolder(@NonNull View itemView, OnReviweListener onReviweListener) {
            super(itemView);
             title = itemView.findViewById(R.id.reviewTitle);
             description = itemView.findViewById(R.id.reviewQuestionCount);
             icon = itemView.findViewById(R.id.reviewRowIcon);
             this.onReviweListener = onReviweListener;
             itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
             onReviweListener.onReviewClick(getAdapterPosition());
        }

    }

    public interface OnReviweListener{
        void onReviewClick(int position);
    }
}
