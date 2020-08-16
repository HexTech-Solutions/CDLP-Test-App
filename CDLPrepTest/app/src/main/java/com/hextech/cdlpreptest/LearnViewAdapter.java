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
    private OnLearnViewListner mOnLearnViewListner;

    public LearnViewAdapter(Context ct, String s1[], OnLearnViewListner onLearnViewListner){
        context = ct;
        dataArray1 = s1;
        this.mOnLearnViewListner = onLearnViewListner;
    }

    @NonNull
    @Override
    public LearnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.learn_row, parent, false);
        return new LearnViewAdapter.LearnViewHolder(view, mOnLearnViewListner);
    }

    @Override
    public void onBindViewHolder(@NonNull LearnViewHolder holder, int position) {
        holder.learnTitle.setText(dataArray1[position]);
    }

    @Override
    public int getItemCount() {
        System.out.println("array length - "+dataArray1.length);
        return dataArray1.length;
    }

    public class LearnViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView learnTitle;
        OnLearnViewListner onLearnViewListner;

        public LearnViewHolder(@NonNull View itemView, OnLearnViewListner onLearnViewListner) {
            super(itemView);
            learnTitle = itemView.findViewById(R.id.learnText);
            this.onLearnViewListner = onLearnViewListner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onLearnViewListner.onLearnViewClick(getAdapterPosition());

        }
    }

    public interface OnLearnViewListner{
        void onLearnViewClick(int position);
    }
}
