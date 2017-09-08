package com.forgetgot.selftie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TaskAdapter extends BaseAdapter {
    Context context;
    String nameList[];
    String categories[];
    LayoutInflater inflter;

    public TaskAdapter(Context applicationContext, String[] nameList, String[] categories) {
        this.context = context;
        this.nameList = nameList;
        this.categories = categories;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return nameList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
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
        name.setText(nameList[i]);
        category.setText(categories[i]);
        return view;
    }
}
