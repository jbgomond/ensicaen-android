package com.jbgomond.resultatsensicaen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class ResultsActivity extends AppCompatActivity {

    public ResultsAdapter resultsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ListView listView = (ListView) this.findViewById(R.id.results_list);
        int layoutId = R.layout.activity_results;

        /*resultsAdapter = new ResultsAdapter(ResultsActivity.this, layoutId, semester);
        listView.setAdapter(resultsAdapter);

        new EducationalBookletConnectorTask(ResultsActivity.this).execute("11");*/
    }
}
