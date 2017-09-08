package com.forgetgot.selftie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TaskAdapter extends BaseAdapter {
    Context context;
    Task tasks[];
    LayoutInflater inflter;

    public TaskAdapter(Context applicationContext, Task[] tasks) {
        this.context = context;
        this.tasks = tasks;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return tasks.length;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.listview, null);
        TextView name = (TextView) view.findViewById(R.id.task_name);
        TextView category = (TextView) view.findViewById(R.id.task_category);
        name.setText(tasks[i].getName());
        category.setText(tasks[i].getCategory());
        return view;
    }

    public Task getItem(int position){

        return tasks[position];
    }
}
