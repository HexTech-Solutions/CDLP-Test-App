package com.hextech.cdlpreptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewViewAdapter extends RecyclerView.Adapter<ReviewViewAdapter.ReviewViewHolder> {

    String dataArray1[], dataArray2[];
    int icons[];
    Context context;

    public ReviewViewAdapter(Context ct, String reviewTitle[], String reviewDesc[], int icon[]){
        context = ct;
        dataArray1 = reviewTitle;
        dataArray2 = reviewDesc;
        icons = icon;
    }

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.review_row, parent, false);
        return new ReviewViewHolder(view);
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

    public class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;
        ImageView icon;

         public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
             title = itemView.findViewById(R.id.reviewTitle);
             description = itemView.findViewById(R.id.reviewQuestionCount);
             icon = itemView.findViewById(R.id.reviewRowIcon);
        }
    }
}
