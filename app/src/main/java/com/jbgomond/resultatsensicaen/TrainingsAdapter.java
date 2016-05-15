package com.jbgomond.resultatsensicaen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TrainingsAdapter extends RecyclerView.Adapter<TrainingsAdapter.ViewHolder> {
    public ArrayList<Training> trainings;

    public TrainingsAdapter(ArrayList<Training> trainings) {
        this.trainings = trainings;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.trainings_list, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.trainingName.setText(trainings.get(position).getName());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * Get the size of items in adapter
     *
     * @return the size of items in adapter
     */
    @Override
    public int getItemCount() {
        return trainings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView trainingName;
        private Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            trainingName = (TextView) itemView.findViewById(R.id.training_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, SemestersActivity.class);
            intent.putExtra("TRAINING_ID", getLayoutPosition());
            context.startActivity(intent);
        }
    }
}