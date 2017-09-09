package com.forgetgot.selftie.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.forgetgot.selftie.Database.DatabaseHandler;
import com.forgetgot.selftie.Database.Task;
import com.forgetgot.selftie.R;
import com.forgetgot.selftie.Database.SubTask;

import java.util.ArrayList;
import java.util.List;

public class UnfinishedTask extends AppCompatActivity {
    List<SubTask> subTaskList;
    DatabaseHandler db;
    int idTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfinished_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        idTask = extras.getInt(Task.TASK_EXTRA_ID, -1);
        db = new DatabaseHandler(this);
        Task task = db.getTask(idTask);

        TextView t =(TextView)findViewById(R.id.task_name);
        t.setText(task.getName());

        t=(TextView)findViewById(R.id.task_category);
        t.setText(task.getCategory());

        t=(TextView)findViewById(R.id.task_prediction);
        t.setText(getString(R.string.hour_format, task.getPrediction()));

        subTaskList = new ArrayList<>();
        List<SubTask> subTasks = db.getAllSubTasks(idTask);
        for (SubTask subTask: subTasks) subTaskList.add(subTask);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.time_list);

        for(SubTask subTask : subTaskList) {
            t = new TextView(this);
            t.setTextSize(20);
            t.setText(getString(R.string.subTask_format, subTask.getName(), subTask.getTime()));
            linearLayout.addView(t);
        }
        if (subTaskList.isEmpty()) {
            t = new TextView(this);
            t.setTextSize(20);
            t.setText("No sub tasks.");
            linearLayout.addView(t);
        }
    }

    public void addTask(View view) {
        switch (view.getId()) {
            case R.id.task_new_time_btn:
                if (((EditText)findViewById(R.id.task_new_name)).getText().toString().matches("") ||
                        ((EditText)findViewById(R.id.task_new_time)).getText().toString().matches("")) {
                    return;
                }
                String nameSubTask = ((EditText)findViewById(R.id.task_new_name)).getText().toString();
                double timeSubTask = Double.parseDouble(((EditText)findViewById(R.id.task_new_time)).getText().toString());
                db.addSubTask(new SubTask(idTask, nameSubTask, timeSubTask));

                Task task = db.getTask(idTask);
                task.setRealTime(task.getRealTime() + timeSubTask);

                TextView t = new TextView(this);
                t.setTextSize(20);
                t.setText(getString(R.string.subTask_format, nameSubTask, timeSubTask));
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.time_list);
                linearLayout.addView(t);
                break;
            case R.id.finish_button:
                db.finishTask(idTask);
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
