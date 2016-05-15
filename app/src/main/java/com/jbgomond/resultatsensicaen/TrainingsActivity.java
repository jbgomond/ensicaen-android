package com.jbgomond.resultatsensicaen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TrainingsActivity extends AppCompatActivity {

    private ArrayList<Training> trainings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings);

        // Initialisation de la liste des formations
        initTrainingsList();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.trainings_view);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);

            TrainingsAdapter adapter = new TrainingsAdapter(trainings);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private void initTrainingsList() {
        trainings.add(new Training("Electronique et Physique appliquée"));
        trainings.add(new Training("Génie Industriel"));
        trainings.add(new Training("Informatique"));
        trainings.add(new Training("Informatique - Apprentissage"));
        trainings.add(new Training("Matériaux et Mécanique"));
        trainings.add(new Training("Matériaux et Chimie"));
    }
}
