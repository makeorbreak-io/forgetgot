package com.forgetgot.selftie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import static com.forgetgot.selftie.R.layout.activity_create_task;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goCreateTask(View v){
        Intent myIntent = new Intent(this, CreateTask.class);
        startActivity(myIntent);
    }

    public void goFinished(View v){
        Intent myIntent = new Intent(this, FinishedTasks.class);
        startActivity(myIntent);
    }

    public void goUnfinished(View v){
        Intent myIntent = new Intent(this, UnfinishedTasks.class);
        startActivity(myIntent);
    }

}
