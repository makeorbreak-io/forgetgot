package com.forgetgot.selftie.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

        setSubTasks();
    }

    public void addTask(View view) {
        //Error found
        if (((EditText)findViewById(R.id.task_new_name)).getText().toString().matches("") ||
                ((EditText)findViewById(R.id.task_new_time)).getText().toString().matches("")) {
            Toast.makeText(this,getString(R.string.required_fields), Toast.LENGTH_LONG).show();
            return;
        }

        //Add SubTask to database
        String nameSubTask = ((EditText)findViewById(R.id.task_new_name)).getText().toString();
        double timeSubTask = Double.parseDouble(((EditText)findViewById(R.id.task_new_time)).getText().toString());
        db.addSubTask(new SubTask(idTask, nameSubTask, timeSubTask));
        setSubTasks();

        //Delete input fields
        ((EditText)findViewById(R.id.task_new_name)).setText("");
        ((EditText)findViewById(R.id.task_new_time)).setText("");
    }

    public void finishTask(View view){
        db.finishTask(idTask);

        //Go to homepage
        Intent intent = new Intent(this, Homepage.class);

        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void setSubTasks() {
        subTaskList = new ArrayList<>();
        List<SubTask> subTasks = db.getAllSubTasks(idTask);
        for (SubTask subTask: subTasks) subTaskList.add(subTask);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.time_list);
        linearLayout.removeAllViews();
        for(SubTask subTask : subTaskList) {
            TextView t = new TextView(this);
            t.setTextSize(20);
            t.setText(getString(R.string.subTask_format, subTask.getName(), subTask.getTime()));
            linearLayout.addView(t);
        }

        if (subTaskList.isEmpty()) {
            TextView t = new TextView(this);
            t.setTextSize(20);
            t.setText(R.string.no_subtask);
            linearLayout.addView(t);
        }
    }
}
