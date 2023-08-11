package com.example.newtodolist;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    int id;

    private String text;
    private int priority;

    @Ignore
    public Task(int id, String text, int priority) {
        this.id = id;
        this.text = text;
        this.priority = priority;
    }

    public Task(String text, int priority) {
        this(0, text, priority);
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getPriority() {
        return priority;
    }
}
