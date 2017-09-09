package com.forgetgot.selftie.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.forgetgot.selftie.Database.DatabaseHandler;
import com.forgetgot.selftie.R;
import com.forgetgot.selftie.Database.Task;
import com.forgetgot.selftie.Database.TaskAdapter;

import java.util.List;

public class UnfinishedTasks extends AppCompatActivity {
    ListView simpleList;
    List<Task> taskList; /*= {new Task(7, "Proj7", "category2",0.5),
            new Task(8, "Proj8", "category3",0.25),
            new Task(9, "Proj9", "category1",1)};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DatabaseHandler db = new DatabaseHandler(this);
        taskList = db.getAllUninishedTasks();

        //Get List
        simpleList = (ListView)findViewById(R.id.simpleListView);

        //Fill list
        TaskAdapter adapter = new TaskAdapter(getApplicationContext(), taskList);
        simpleList.setAdapter(adapter);

        //Connect with UnfinishedTask activity
        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = (Task) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(view.getContext(), UnfinishedTask.class);
                intent.putExtra(Task.TASK_EXTRA_ID, task);

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();

        View empty = findViewById(R.id.empty);
        ListView list = (ListView) findViewById(R.id.simpleListView);
        list.setEmptyView(empty);
    }
}
