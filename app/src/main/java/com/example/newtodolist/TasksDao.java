package com.example.newtodolist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface TasksDao {

    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getTasks();

    @Insert
    Completable addTask(Task task);

    @Query("DELETE FROM tasks WHERE id = :id")
    Completable removeTask(int id);
}
