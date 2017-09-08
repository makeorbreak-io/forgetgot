package com.forgetgot.selftie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FinishedTasks extends AppCompatActivity {
    ListView simpleList;
    String nameList[] = {"Proj1", "Proj2", "Proj3", "Proj4", "Proj5", "Proj6"};
    String categories[] = {"category1", "category2", "category1", "category1", "category3", "category1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_tasks);
        simpleList = (ListView)findViewById(R.id.simpleListView);

        TaskAdapter adapter = new TaskAdapter(getApplicationContext(), nameList, categories);
        simpleList.setAdapter(adapter);
    }
}
