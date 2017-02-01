package com.jbgomond.resultatsensicaen.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Semester implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeTypedList(categoriesUE);
    }
}