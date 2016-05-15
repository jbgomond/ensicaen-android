package com.jbgomond.resultatsensicaen;

import java.util.ArrayList;

public class ResultsUE {

    private String name;
    private String code;
    private Integer minimalAverage;
    private Integer ects;
    private ArrayList<ResultsSubject> resultsSubject;

    public ResultsUE() {
        this.resultsSubject = new ArrayList<>();
    }

    public ResultsUE(String name, String code, Integer minimalAverage, Integer ects, ArrayList<ResultsSubject> resultsSubject) {
        this.name = name;
        this.code = code;
        this.minimalAverage = minimalAverage;
        this.ects = ects;
        this.resultsSubject = resultsSubject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getMinimalAverage() {
        return minimalAverage;
    }

    public void setMinimalAverage(Integer minimalAverage) {
        this.minimalAverage = minimalAverage;
    }

    public Integer getEcts() {
        return ects;
    }

    public void setEcts(Integer ects) {
        this.ects = ects;
    }

    public ArrayList<ResultsSubject> getResultsSubject() {
        return resultsSubject;
    }

    public void setResultsSubject(ArrayList<ResultsSubject> resultsSubject) {
        this.resultsSubject = resultsSubject;
    }

    @Override
    public String toString() {
        return "ResultsUE{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", minimalAverage='" + minimalAverage + '\'' +
                ", ects='" + ects + '\'' +
                ", resultsSubject=" + resultsSubject +
                '}';
    }
}