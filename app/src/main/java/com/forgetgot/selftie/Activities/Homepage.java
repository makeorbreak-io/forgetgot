package com.forgetgot.selftie.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.forgetgot.selftie.Database.DatabaseHandler;
import com.forgetgot.selftie.R;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends AppCompatActivity {
   private List<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);
// Array of choices
         categories = db.getCategories();

// Selection of the spinner
        Spinner spinner = (Spinner) findViewById(R.id.mainSpinner);

// Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, categories);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
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
