package com.jbgomond.resultatsensicaen;

import java.util.ArrayList;

public class CategoryUE {

    private String name;
    private ArrayList<ResultsUE> resultsUE;

    public CategoryUE(String number) {
        this.name = number;
        this.resultsUE = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ResultsUE> getResultsUE() {
        return resultsUE;
    }

    public void setResultsUE(ArrayList<ResultsUE> resultsUE) {
        this.resultsUE = resultsUE;
    }

    @Override
    public String toString() {
        return "CategoryUE{" +
                "name='" + name + '\'' +
                ", resultsUE=" + resultsUE +
                '}';
    }
}