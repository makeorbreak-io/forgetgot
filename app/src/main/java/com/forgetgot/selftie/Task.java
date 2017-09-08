package com.forgetgot.selftie;

import java.io.Serializable;
import java.util.ArrayList;

public class Task implements Serializable{
    static public String TASK_EXTRA = "com.forgetgot.selftie.TASK_EXTRA";

    private int id;
    private String name;
    private String category;
    private double prediction;
    private ArrayList<Double> times;
    private boolean isFinished;

    public Task() {
        this.times = new ArrayList<>();
    }

    public Task(String name, String category, double prediction) {
        this.name = name;
        this.category = category;
        this.prediction = prediction;
        this.times = new ArrayList<>();
        this.isFinished = false;
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

    public double getRealtime(){
        double realtime = 0;

        for(double time: times)
            realtime += time;

        return realtime;
    }

    public double getError(){
        double realtime = getRealtime();
        double error = Math.abs(realtime - prediction)/prediction;

        return error;
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
