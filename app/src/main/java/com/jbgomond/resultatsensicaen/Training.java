package com.jbgomond.resultatsensicaen;

import java.util.ArrayList;

public class Training {

    private String name;
    private String manager;
    private ArrayList<Semester> semesters;

    public Training() {}

    public Training(String name) {
        this.name = name;
    }

    public Training(String name, String manager, ArrayList<Semester> semesters) {
        this.name = name;
        this.manager = manager;
        this.semesters = semesters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public ArrayList<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(ArrayList<Semester> semesters) {
        this.semesters = semesters;
    }

    @Override
    public String toString() {
        return "Year{" +
                "name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", semesters=" + semesters +
                '}';
    }
}