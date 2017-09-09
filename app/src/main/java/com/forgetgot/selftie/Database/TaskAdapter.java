package com.forgetgot.selftie.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.forgetgot.selftie.Database.Task;
import com.forgetgot.selftie.R;

import java.util.List;

public class TaskAdapter extends BaseAdapter {
    Context context;
    List<Task> tasks;
    LayoutInflater inflter;

    public TaskAdapter(Context applicationContext, List<Task> tasks) {
        this.context = applicationContext;
        this.tasks = tasks;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return tasks.size();
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
        name.setText(tasks.get(i).getName());
        category.setText(tasks.get(i).getCategory());
        return view;
    }

    public Task getItem(int position){

        return tasks.get(position);
    }
}
