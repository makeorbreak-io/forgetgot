package com.forgetgot.selftie.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.forgetgot.selftie.Database.DatabaseHandler;
import com.forgetgot.selftie.Database.Task;
import com.forgetgot.selftie.R;

public class CreateTask extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);
    }

    public void createTask(View v){
        String name = ((EditText)findViewById(R.id.taskNameInput)).getText().toString();
        String category = ((EditText)findViewById(R.id.taskCategoryInput)).getText().toString();

        //Form validation
        if(name.isEmpty() || category.isEmpty() || ((EditText)findViewById(R.id.taskPredictionInput)).getText().toString().isEmpty()){
            Toast.makeText(this,getString(R.string.required_fields), Toast.LENGTH_LONG).show();
            return;
        }

        double predictionTime = Double.parseDouble(((EditText)findViewById(R.id.taskPredictionInput)).getText().toString());

        Task task = new Task(-1, name, category, predictionTime);

        DatabaseHandler db = new DatabaseHandler(this);
        db.addTask(task);

        Intent myIntent = new Intent(this, UnfinishedTasks.class);
        startActivity(myIntent);
    }
}
