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

        TextView t=new TextView(this);

        t=(TextView)findViewById(R.id.task_name);
        t.setText("Task1");

        t=(TextView)findViewById(R.id.task_category);
        t.setText("Category");

        t=(TextView)findViewById(R.id.task_prediction);
        t.setText("0:30");

        t=(TextView)findViewById(R.id.task_realtime);
        t.setText("0:45");

        t=(TextView)findViewById(R.id.task_error);
        t.setText("50%");
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
