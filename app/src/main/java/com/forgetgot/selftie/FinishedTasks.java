package com.forgetgot.selftie;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FinishedTasks extends AppCompatActivity {
    ListView simpleList;
    Task taskList[] = {new Task(1, "Proj1", "category1",0.5),
            new Task(2, "Proj2", "category2",0.25),
            new Task(3, "Proj3", "category1",1),
            new Task(4, "Proj4", "category1",0.5),
            new Task(5, "Proj5", "category3",2),
            new Task(6, "Proj6", "category1",1.5)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_tasks);
        simpleList = (ListView)findViewById(R.id.simpleListView);

        TaskAdapter adapter = new TaskAdapter(getApplicationContext(), taskList);
        simpleList.setAdapter(adapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = (Task) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(view.getContext(), FinishedTask.class);
                intent.putExtra(Task.TASK_EXTRA, task);

                startActivity(intent);
            }
        });
    }
}
