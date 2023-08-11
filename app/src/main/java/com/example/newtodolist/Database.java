package com.example.newtodolist;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

public class Database {

    private ArrayList<Task> tasksList = new ArrayList<>();

    private static Database databaseInstance = null;

    private Database() {
        Random random = new Random();
        for (int i = 0; i < 17; i++) {
            tasksList.add(new Task(i, "Task " + i, random.nextInt(3)));
        }
    }

    public static Database getDatabaseInstance() {
        if (databaseInstance == null)
            databaseInstance = new Database();
        return databaseInstance;
    }

    public ArrayList<Task> getTasksList() {
        return new ArrayList<>(tasksList);
    }

    public void addTask(Task task) {
        tasksList.add(task);
        Log.e("ADDED", "ADDED");
    }

    public void removeTask(Task task) {
        tasksList.remove(task);
    }

}
