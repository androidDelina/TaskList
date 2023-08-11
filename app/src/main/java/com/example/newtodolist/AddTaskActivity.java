package com.example.newtodolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTextEnterTask;

    private RadioButton radioButtonLowPriority;
    private RadioButton radioButtonMediumPriority;

    private Button buttonSave;

    private AddTaskViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        initViews();

        viewModel = new ViewModelProvider(this).get(AddTaskViewModel.class);
        viewModel.getShouldClose().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldClose) {
                if (shouldClose)
                    finish();
            }
        });

        radioButtonLowPriority.setChecked(true);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = createNewTask();
                saveTask(task);
            }
        });

    }

    private void saveTask(Task task) {
        viewModel.addNewTask(task);
    }

    private Task createNewTask() {
        String text = editTextEnterTask.getText().toString().trim();
        int priority;
        if (radioButtonLowPriority.isChecked())
            priority = 0;
        else if (radioButtonMediumPriority.isChecked())
            priority = 1;
        else
            priority = 3;
        Task task = new Task(text, priority);
        return task;
    }

    public static Intent getNewIntent(Context context) {
        Intent intent = new Intent(context, AddTaskActivity.class);
        return intent;
    }

    private void initViews() {
        editTextEnterTask = findViewById(R.id.editTextEnterTask);

        radioButtonLowPriority = findViewById(R.id.radioButtonLowPriority);
        radioButtonMediumPriority = findViewById(R.id.radioButtonMediumPriority);

        buttonSave = findViewById(R.id.buttonSave);
    }
}