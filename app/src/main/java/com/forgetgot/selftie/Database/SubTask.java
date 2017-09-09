package com.forgetgot.selftie.Database;

/**
 * Created by ines on 08-09-2017.
 */

class SubTask {
    private String name;
    private double time;
    private int taskId;

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
