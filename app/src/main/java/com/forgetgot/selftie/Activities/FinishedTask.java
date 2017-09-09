package com.forgetgot.selftie.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.forgetgot.selftie.Database.DatabaseHandler;
import com.forgetgot.selftie.Database.SubTask;
import com.forgetgot.selftie.R;
import com.forgetgot.selftie.Database.Task;

import java.util.List;

public class FinishedTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_task);

        Bundle extras = getIntent().getExtras();
        int id;

        if (extras != null && (id  = extras.getInt(Task.TASK_EXTRA_ID, -1)) != -1) {
            DatabaseHandler db = new DatabaseHandler(this);

            Task task = db.getTask(id);

            TextView t=(TextView)findViewById(R.id.task_name);
            t.setText(task.getName());

            t=(TextView)findViewById(R.id.task_category);
            t.setText(task.getCategory());

            t=(TextView)findViewById(R.id.task_prediction);
            t.setText(getString(R.string.hour_format, task.getPrediction()));

            t=(TextView)findViewById(R.id.task_realtime);
            t.setText(getString(R.string.hour_format, calculateRealTime(db, id)));

            t=(TextView)findViewById(R.id.task_error);
            t.setText(getString(R.string.percentage_format, calculateError(db,id,task)*100));
        }
    }

    public double calculateError(DatabaseHandler db, int id,  Task task){
        return Math.abs(calculateRealTime(db, id) - task.getPrediction()) / task.getPrediction();
    }

    public double calculateRealTime(DatabaseHandler db, int id){
        double realTime = 0;
        List<SubTask> subTasks = db.getAllSubTasks(id);
        for (SubTask subTask: subTasks) realTime += subTask.getTime();
        return realTime;
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
