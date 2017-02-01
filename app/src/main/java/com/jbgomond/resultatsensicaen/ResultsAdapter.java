package com.jbgomond.resultatsensicaen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.jbgomond.resultatsensicaen.model.ResultsUE;

import java.util.ArrayList;

public class ResultsAdapter extends ArrayAdapter<ResultsUE>
{
    ArrayList<ResultsUE> results;

    public ResultsAdapter(Context context, int textViewResourceId, ArrayList<ResultsUE> results) {
        super(context, textViewResourceId, results);
        this.results = results;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.results_list, null);
        }

        ResultsUE currentResult = results.get(position);
        TextView ueCode = (TextView) convertView.findViewById(R.id.ue_code);
        TextView ueName = (TextView) convertView.findViewById(R.id.ue_name);
        TextView ueMinimalAverage = (TextView) convertView.findViewById(R.id.ue_minimal_average);
        TextView ueEcts = (TextView) convertView.findViewById(R.id.ue_ects);

        ueCode.setText(currentResult.getCode());
        ueName.setText(currentResult.getName());
        ueMinimalAverage.setText(currentResult.getMinimalAverage());
        ueEcts.setText(currentResult.getEcts());

        return convertView;
    }
}
