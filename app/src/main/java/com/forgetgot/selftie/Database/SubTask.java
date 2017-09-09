package com.forgetgot.selftie.Database;

public class SubTask {
    private String name;
    private double time;
    private int taskId;

    public SubTask(){}

    public SubTask(int task, String name, double time){
        this.taskId = task;
        this.name = name;
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public double getTime() {
        return time;
    }

    public int getTaskId() {
        return taskId;
    }
}
