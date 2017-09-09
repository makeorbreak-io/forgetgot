package com.forgetgot.selftie.Database;

import java.io.Serializable;

public class Task implements Serializable{
    static public String TASK_EXTRA_ID = "com.forgetgot.selftie.TASK_EXTRA_ID";

    private int id;
    private String name;
    private String category;
    private double prediction;
    private double realTime = 0;
    private boolean isFinished = false;

    public Task() { }

    public Task(int id, String name, String category, double prediction) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.prediction = prediction;
    }

    public String getName(){
        return name;
    }

    public String getCategory(){
        return category;
    }

    public double getPrediction(){
        return prediction;
    }

    public double getRealTime(){
        return realTime;
    }

    public void setRealTime(double realTime) {
        this.realTime = realTime;
    }

    public void setID(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setPrediction(double prediction) {
        this.prediction = prediction;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public int getID() {
        return id;
    }
}
