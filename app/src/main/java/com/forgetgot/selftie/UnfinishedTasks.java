package com.forgetgot.selftie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class UnfinishedTasks extends AppCompatActivity {
    ListView simpleList;
    Task taskList[] = {new Task("Proj7", "category2",0.5),
            new Task("Proj8", "category3",0.25),
            new Task("Proj9", "category1",1)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfinished_tasks);
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
