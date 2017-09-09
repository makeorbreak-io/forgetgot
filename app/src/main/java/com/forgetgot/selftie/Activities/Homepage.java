package com.forgetgot.selftie.Activities;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.forgetgot.selftie.Database.DatabaseHandler;
import com.forgetgot.selftie.Database.Task;
import com.forgetgot.selftie.R;

import java.util.ArrayList;
import java.util.List;

import static com.forgetgot.selftie.Activities.FinishedTask.calculateError;

public class Homepage extends AppCompatActivity {
   private List<String> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setTitle("  SELFTIE");

        DatabaseHandler db = new DatabaseHandler(this);
        // Array of choices
        categories = new ArrayList<>();
        categories.add("All");
        categories.addAll(db.getCategories());

        // Selection of the spinner
        Spinner spinner = (Spinner) findViewById(R.id.mainSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DatabaseHandler db = new DatabaseHandler(view.getContext());

                Spinner spinner = (Spinner) findViewById(R.id.mainSpinner);
                String category = spinner.getSelectedItem().toString();

                List<Task> t= db.getAllTasksFromCategory(category);

                TextView textView =(TextView)findViewById(R.id.data);

                textView.setText(getString(R.string.percentage_format, getAverageError(t)*100));

                return;

            }

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, categories);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
    }


    public double getAverageError(List<Task> tasks){
        DatabaseHandler db = new DatabaseHandler(this);
        double sum=0;

        for (Task task: tasks) sum+=calculateError(db,task);

        if(tasks.size()!=0) return sum/tasks.size();
        else return 0;
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
}
