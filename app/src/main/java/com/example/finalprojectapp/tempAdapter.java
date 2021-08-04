package com.example.finalprojectapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class tempAdapter extends RecyclerView.Adapter<tempAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<tempreture> tempretureList;

    public tempAdapter(Context ctx, List<tempreture> tempretureList){
        this.inflater = LayoutInflater.from(ctx);
        this.tempretureList= tempretureList;
    }

    //binds data to the view
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_temp_display,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

        //binding data

        holder.textView_Date.setText(tempretureList.get(position).getCreated_at());
        holder.textView_entryID.setText(tempretureList.get(position).getEntry_ID());
        holder.textView_Date.setText(tempretureList.get(position).getTempValue());
    }

    @Override
    public int getItemCount() {
        return tempretureList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView_Date, textView_entryID, textView_TempValue;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            textView_Date = itemView.findViewById(R.id.textViewDate);
            textView_entryID = itemView.findViewById(R.id.textViewEntryID);
            textView_TempValue = itemView.findViewById(R.id.textViewTempValue);
        }
    }
}
