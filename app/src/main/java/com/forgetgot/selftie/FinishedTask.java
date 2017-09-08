package com.forgetgot.selftie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class FinishedTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_task);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        Task task;

        if (extras != null && (task = (Task) extras.getSerializable(Task.TASK_EXTRA)) != null) {
            //If task is found display its information

            TextView t=new TextView(this);

            t=(TextView)findViewById(R.id.task_name);
            t.setText(task.getName());

            t=(TextView)findViewById(R.id.task_category);
            t.setText(task.getCategory());

            t=(TextView)findViewById(R.id.task_prediction);
            t.setText(task.getPrediction() + "h");

            t=(TextView)findViewById(R.id.task_realtime);
            t.setText(task.getRealtime() + "h");

            t=(TextView)findViewById(R.id.task_error);
            t.setText(task.getError() + "");
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
