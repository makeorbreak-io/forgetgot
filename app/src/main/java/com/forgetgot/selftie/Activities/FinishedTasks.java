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

public class FinishedTasks extends AppCompatActivity {
    ListView simpleList;
    List<Task> taskList; /*[] = {new Task(1, "Proj1", "category1",0.5),
            new Task(2, "Proj2", "category2",0.25),
            new Task(3, "Proj3", "category1",1),
            new Task(4, "Proj4", "category1",0.5),
            new Task(5, "Proj5", "category3",2),
            new Task(6, "Proj6", "category1",1.5)};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_tasks);
        simpleList = (ListView)findViewById(R.id.simpleListView);

        DatabaseHandler db = new DatabaseHandler(this);

        taskList = db.getAllFinishedTasks();

        taskList.add(new Task(1, "Proj1", "category1", 2));

        TaskAdapter adapter = new TaskAdapter(getApplicationContext(), taskList);
        simpleList.setAdapter(adapter);

        simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Task task = (Task) adapterView.getItemAtPosition(i);

                Intent intent = new Intent(view.getContext(), FinishedTask.class);
                intent.putExtra(Task.TASK_EXTRA_ID, task.getID());

                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
