package com.example.testapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {

    private ArrayList<ToDisplay> Display;

    public DisplayAdapter(ArrayList<ToDisplay> Display) {
        this.Display = Display;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_survey, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ToDisplay item = Display.get(position);

        holder.Id.setText(String.valueOf(item.getID()));
        holder.TreeName.setText(item.getTreeName());
        holder.TreeAge.setText(item.getTreeAge());
        holder.PlantingDate.setText(item.getPlantingDate());
    }

    @Override
    public int getItemCount() {
        if(Display != null) {
            return Display.size();
        } else {
            return 0;
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final View view;
        public final TextView Id;
        public final TextView TreeName;
        public final TextView TreeAge;
        public final TextView PlantingDate;


        public ViewHolder(View view){
            super(view);
            this.view = view;
            Id = view.findViewById(R.id.i_tv_CurrentID);
            TreeName = view.findViewById(R.id.i_tv_CurrentTreeName);
            TreeAge = view.findViewById(R.id.i_tv_CurrentTreeAge);
            PlantingDate = view.findViewById(R.id.i_tv_CurrentPlantingDate);
        }
    }
}

