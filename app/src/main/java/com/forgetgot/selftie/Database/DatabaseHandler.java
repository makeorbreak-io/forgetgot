package com.forgetgot.selftie.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.forgetgot.selftie.Database.SubTask;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1; // Database Version
    private static final String DATABASE_NAME = "Tasks.db"; // Database Name
    private static final String TABLE_TASKS = "Tasks"; // Contacts table name
    private static final String TABLE_SUBTASKS = "SubTasks"; // Contacts table name

    // Tasks Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PREDICTION_TIME = "prediction_time";
    private static final String KEY_ISFINISHED = "is_finished";
    private static final String KEY_CATEGORY = "category";
    // SubTasks Table Columns names
    private static final String KEY_TIME = "time";
    private static final String KEY_TASKID = "taskid";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_PREDICTION_TIME + " DOUBLE," //TODO check this
                + KEY_ISFINISHED + " BOOLEAN default 0,"
                + KEY_CATEGORY + " TEXT" + ")";
        db.execSQL(CREATE_TASKS_TABLE);

        String CREATE_SUBTASKS_TABLE = "CREATE TABLE " + TABLE_SUBTASKS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_TIME + " DOUBLE," //TODO check this
                + KEY_TASKID + " INTEGER,"
                + "FOREIGN KEY(" + KEY_TASKID + ") REFERENCES " + TABLE_TASKS + "(" + KEY_ID + "))";
        db.execSQL(CREATE_SUBTASKS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);

        // Create tables again
        onCreate(db);
    }

    // Adding new task
    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, task.getName());
        values.put(KEY_PREDICTION_TIME, task.getPrediction());
        values.put(KEY_CATEGORY, task.getCategory());
        values.put(KEY_ISFINISHED, false);

        // Inserting Row
        db.insert(TABLE_TASKS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single task
    public Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TASKS, new String[] { KEY_ID,
                        KEY_NAME, KEY_CATEGORY, KEY_PREDICTION_TIME }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Task task = new Task(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), Double.parseDouble(cursor.getString(3)));

        db.close(); // Closing database connection
        cursor.close();

        // return task
        return task;
    }

    // Getting All Finished Tasks
    public List<Task> getAllFinishedTasks() {
        List<Task> taskList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS + " WHERE " + KEY_ISFINISHED + " = 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setID(Integer.parseInt(cursor.getString(0)));
                task.setName(cursor.getString(1));
                task.setPrediction(Double.parseDouble(cursor.getString(2)));
                task.setIsFinished(Boolean.parseBoolean(cursor.getString(3)));
                task.setCategory(cursor.getString(4));

                // Adding task to list
                taskList.add(task);
            } while (cursor.moveToNext());
        }

        db.close(); // Closing database connection
        cursor.close();

        // return task list
        return taskList;
    }

    // Getting All Unfinished Tasks
    public List<Task> getAllUninishedTasks() {
        List<Task> taskList = new ArrayList<Task>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS + " WHERE " + KEY_ISFINISHED + " = 0";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setID(Integer.parseInt(cursor.getString(0)));
                task.setName(cursor.getString(1));
                task.setPrediction(Double.parseDouble(cursor.getString(2)));
                task.setIsFinished(Boolean.parseBoolean(cursor.getString(3)));
                task.setCategory(cursor.getString(4));

                // Adding task to list
                taskList.add(task);
            } while (cursor.moveToNext());
        }

        db.close(); // Closing database connection
        cursor.close();

        // return task list
        return taskList;
    }

    // Getting contacts Count
    public int getTasksCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single contact
    public int finishTask(int taskId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ISFINISHED, "1");

        // updating row
        return db.update(TABLE_TASKS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(taskId) });
    }

    // Adding new task
    public void addSubTask(SubTask subtask) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, subtask.getName());
        values.put(KEY_TIME, subtask.getTime());
        values.put(KEY_TASKID, subtask.getTaskId());

        // Inserting Row
        db.insert(TABLE_SUBTASKS, null, values);
        db.close(); // Closing database connection
    }

    // Getting All Finished Tasks
    public List<SubTask> getAllSubTasks(int taskid) {
        List<SubTask> taskList = new ArrayList<SubTask>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SUBTASKS + " WHERE " + KEY_TASKID + " = " + taskid;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                SubTask subtask = new SubTask();
                subtask.setName(cursor.getString(1));
                subtask.setTime(Double.parseDouble(cursor.getString(2)));
                subtask.setTaskId(Integer.parseInt(cursor.getString(3)));

                // Adding task to list
                taskList.add(subtask);
            } while (cursor.moveToNext());
        }

        db.close(); // Closing database connection
        cursor.close();

        // return task list
        return taskList;
    }

    public List<String> getCategories(){
        List<String> Categories = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT " + KEY_CATEGORY + " FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                String category = cursor.getString(0);
                // Adding task to list
                Categories.add(category);
            } while (cursor.moveToNext());
        }

        db.close(); // Closing database connection
        cursor.close();

        // return task list
        return Categories;
    }
}
