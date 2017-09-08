package com.forgetgot.selftie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class UnfinishedTask extends AppCompatActivity {
    ArrayList<Float> times;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        times = new ArrayList<>();

        times.add(Float.valueOf("0.25"));
        times.add(Float.valueOf("0.30"));
        times.add(Float.valueOf("0.10"));

        TextView t;

        t=(TextView)findViewById(R.id.task_name);
        t.setText("Task1");

        t=(TextView)findViewById(R.id.task_category);
        t.setText("Category");

        t=(TextView)findViewById(R.id.task_prediction);
        t.setText("0:30");


        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.time_list);

        for(int i = 0; i < times.size(); i++){
            String text = "" + (i+1);

            switch (i){
                case 0:
                    text += "st";
                    break;
                case 1:
                    text += "nd";
                    break;
                case 2:
                    text += "rd";
                    break;
                default:
                    text += "th";
                    break;

            }

            text += " -> " + times.get(i).toString();
            t=new TextView(this);

            t.setTextSize(30);
            t.setText(text);
            linearLayout.addView(t);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
