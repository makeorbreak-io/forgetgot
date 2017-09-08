package com.forgetgot.selftie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class UnfinishedTasks extends AppCompatActivity {
    ListView simpleList;
    String nameList[] = {"Proj7", "Proj8", "Proj9"};
    String categories[] = {"category2", "category3", "category1"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unfinished_tasks);
        simpleList = (ListView)findViewById(R.id.simpleListView);

        TaskAdapter adapter = new TaskAdapter(getApplicationContext(), nameList, categories);
        simpleList.setAdapter(adapter);
    }
}
