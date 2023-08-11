package com.example.newtodolist;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AllTasksViewModel extends AndroidViewModel {

    private TaskDatabase taskDatabase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AllTasksViewModel(@NonNull Application application) {
        super(application);
        taskDatabase = TaskDatabase.getInstance(application);
    }

    public LiveData<List<Task>> getTasks() {
        return taskDatabase.tasksDao().getTasks();
    }

    public void remove(int id) {
        Disposable disposable = taskDatabase.tasksDao().removeTask(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Throwable {
                        Log.d("AllTasksViewModel", "Remove task " + id);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        Log.d("AllTasksViewModel", "Error remove");
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }
}
