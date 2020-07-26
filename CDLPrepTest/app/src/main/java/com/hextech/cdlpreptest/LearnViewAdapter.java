package com.hextech.cdlpreptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LearnViewAdapter extends RecyclerView.Adapter<LearnViewAdapter.LearnViewHolder> {

    String dataArray1[];
    Context context;

    public LearnViewAdapter(Context ct, String s1[]){
        context = ct;
        dataArray1 = s1;
    }

    @NonNull
    @Override
    public LearnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.learn_row, parent, false);
        return new LearnViewAdapter.LearnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnViewHolder holder, int position) {
        holder.learnTitle.setText(dataArray1[position]);
    }

    @Override
    public int getItemCount() {
        return dataArray1.length;
    }

    public class LearnViewHolder extends RecyclerView.ViewHolder {
        TextView learnTitle;

        public LearnViewHolder(@NonNull View itemView) {
            super(itemView);
            learnTitle = itemView.findViewById(R.id.learnText);
        }
    }
}
