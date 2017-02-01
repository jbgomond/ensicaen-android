package com.jbgomond.resultatsensicaen;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MarksYearsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marks_years);

        Intent intent = getIntent();
        String username = intent.getStringExtra("ENT_USERNAME");
        String password = intent.getStringExtra("ENT_PASSWORD");
        new EntConnectorTask().execute("", "");
    }
}
