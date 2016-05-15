package com.jbgomond.resultatsensicaen;

import java.util.ArrayList;

public class Semester {

    private Integer number;
    private ArrayList<CategoryUE> categoriesUE;

    public Semester() {}

    public Semester(Integer number) {
        this.number = number;
        this.categoriesUE = new ArrayList<>();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public ArrayList<CategoryUE> getCategoriesUE() {
        return categoriesUE;
    }

    public void setCategoriesUE(ArrayList<CategoryUE> categoriesUE) {
        this.categoriesUE = categoriesUE;
    }

    @Override
    public String toString() {
        return "Semestre{" +
                "number='" + number + '\'' +
                ", categoriesUE=" + categoriesUE +
                '}';
    }
}