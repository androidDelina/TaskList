package com.example.newtodolist;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskDatabase extends RoomDatabase {

    private static TaskDatabase instance = null;
    private static final String BD_NAME = "tasks.db";

    public static TaskDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(application, TaskDatabase.class, BD_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract TasksDao tasksDao();
}
