package com.forgetgot.selftie.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.forgetgot.selftie.R;
import com.forgetgot.selftie.Database.SubTask;

import java.util.ArrayList;
import java.util.List;

public class UnfinishedTask extends AppCompatActivity {
    List<SubTask> subTaskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfinished_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        subTaskList = new ArrayList<>();

        subTaskList.add(new SubTask(1, "SubTask1", 0.25));
        subTaskList.add(new SubTask(1, "SubTask2", 0.30));
        subTaskList.add(new SubTask(1, "SubTask3", 0.10));

        TextView t;

        t=(TextView)findViewById(R.id.task_name);
        t.setText("Task1");

        t=(TextView)findViewById(R.id.task_category);
        t.setText("Category");

        t=(TextView)findViewById(R.id.task_prediction);
        t.setText("0:30");


        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.time_list);

        for(SubTask subTask : subTaskList){
            t=new TextView(this);

            t.setTextSize(20);
            t.setText(getString(R.string.subTask_format, subTask.getName(), subTask.getTime()));
            linearLayout.addView(t);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
