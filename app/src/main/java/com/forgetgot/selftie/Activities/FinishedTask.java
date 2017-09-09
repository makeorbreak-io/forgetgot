package com.forgetgot.selftie.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.forgetgot.selftie.Database.DatabaseHandler;
import com.forgetgot.selftie.Database.SubTask;
import com.forgetgot.selftie.R;
import com.forgetgot.selftie.Database.Task;

import java.util.List;

public class FinishedTask extends AppCompatActivity {

    Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_task);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle("  SELFTIE");

        Bundle extras = getIntent().getExtras();
        int id;

        if (extras != null && (id  = extras.getInt(Task.TASK_EXTRA_ID, -1)) != -1) {
            DatabaseHandler db = new DatabaseHandler(this);

            task = db.getTask(id);

            TextView t=(TextView)findViewById(R.id.task_name);
            t.setText(task.getName());

            t=(TextView)findViewById(R.id.task_category);
            t.setText(task.getCategory());

            t=(TextView)findViewById(R.id.task_prediction);
            t.setText(getString(R.string.hour_format, task.getPrediction()));

            t=(TextView)findViewById(R.id.task_realtime);
            t.setText(getString(R.string.hour_format, calculateRealTime(db, id)));

            t=(TextView)findViewById(R.id.task_error);
            t.setText(getString(R.string.percentage_format, calculateError(db,task)*100));
        }

        ImageButton removeBtn = (ImageButton)findViewById(R.id.deleteBtn);
        removeBtn.setOnClickListener(new View.OnClickListener()   {
            public void onClick(View v)  {
                try {
                    removeTask();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static double calculateError(DatabaseHandler db, Task task){
        return (double)Math.abs(calculateRealTime(db,task.getID()) - task.getPrediction()) / (double)task.getPrediction();
    }

    public static double calculateRealTime(DatabaseHandler db, int id){
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.about_option) {
            Intent myIntent = new Intent(this, AboutActivity.class);
            startActivity(myIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void removeTask() {
        final FinishedTask finishedTask = this;
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        DatabaseHandler db = new DatabaseHandler(finishedTask);
                        db.deleteTask(task);
                        Intent myIntent = new Intent(finishedTask, Homepage.class);
                        startActivity(myIntent);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
    }
}
