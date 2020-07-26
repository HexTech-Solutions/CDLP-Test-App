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

    public StateViewAdapter(Context ct, String s1[]){
        context = ct;
        dataArray1 = s1;
    }

    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.state_row, parent, false);
        return new StateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, int position) {
        holder.stateTitle.setText(dataArray1[position]);
    }

    @Override
    public int getItemCount() {
        return dataArray1.length;
    }

    public class StateViewHolder extends RecyclerView.ViewHolder {

        TextView stateTitle;

        public StateViewHolder(@NonNull View itemView) {
            super(itemView);
            stateTitle = itemView.findViewById(R.id.stateName);
        }
    }

    public interface OnStateListner{
        void onStateClick(int position);

    }
}
