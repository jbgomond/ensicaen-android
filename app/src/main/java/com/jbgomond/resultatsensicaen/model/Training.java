package com.jbgomond.resultatsensicaen.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Training implements Parcelable {

    private int id;
    private String name;
    private String manager;
    private ArrayList<Semester> semesters;

    public Training() {}

    public Training(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Training(int id, String name, String manager, ArrayList<Semester> semesters) {
        this(id, name);
        this.manager = manager;
        this.semesters = semesters;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manager='" + manager + '\'' +
                ", semesters=" + semesters +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(manager);
        dest.writeTypedList(semesters);
    }
}