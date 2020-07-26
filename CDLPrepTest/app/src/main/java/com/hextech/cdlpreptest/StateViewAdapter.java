package com.hextech.cdlpreptest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StateViewAdapter extends RecyclerView.Adapter<StateViewAdapter.StateViewHolder> {

    String dataArray1[];
    Context context;
    private OnStateListner mOnStateListner;

    public StateViewAdapter(Context ct, String s1[], OnStateListner onStateListner){
        context = ct;
        dataArray1 = s1;
        this.mOnStateListner = onStateListner;
    }

    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.state_row, parent, false);
        return new StateViewHolder(view, mOnStateListner);
    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int position) {
        holder.stateTitle.setText(dataArray1[position]);
    }

    @Override
    public int getItemCount() {
        return dataArray1.length;
    }

    public class StateViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView stateTitle;
        OnStateListner onStateListner;

        public StateViewHolder(@NonNull View itemView, OnStateListner onStateListner) {
            super(itemView);
            stateTitle = itemView.findViewById(R.id.stateName);
            this.onStateListner = onStateListner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onStateListner.onStateClick(getAdapterPosition());
        }
    }

    public interface OnStateListner{
        void onStateClick(int position);

    }
}
