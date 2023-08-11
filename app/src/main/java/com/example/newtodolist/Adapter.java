package com.example.newtodolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.TaskHolder> {


    private List<Task> tasksList = new ArrayList<>();

    private OnTaskClickListener onTaskClickListener = null;

    public void setTasksList(List<Task> tasksList) {
        this.tasksList = tasksList;
        notifyDataSetChanged();
    }

    public void setOnTaskClickListener(OnTaskClickListener onTaskClickListener) {
        this.onTaskClickListener = onTaskClickListener;
    }

    public List<Task> getTasksList() {
        return new ArrayList<>(tasksList);
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.task_item,
                parent,
                false
        );
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        Task task = tasksList.get(position);
        holder.textViewTask.setText(task.getText());
        int res = 0;
        switch (task.getPriority()) {
            case 0:
                res = R.color.low_priority_color;
                break;
            case 1:
                res = R.color.medium_priority_color;
                break;
            default:
                res = R.color.higth_priority_color;
        }
        int color = ContextCompat.getColor(holder.itemView.getContext(), res);
        holder.textViewTask.setBackgroundColor(color);
        holder.textViewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onTaskClickListener != null) {
                    onTaskClickListener.onTaskClick(task);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }

    class TaskHolder extends RecyclerView.ViewHolder {

        TextView textViewTask;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            textViewTask = itemView.findViewById(R.id.textViewTask);
        }
    }

    interface OnTaskClickListener {
        void onTaskClick(Task task);
    }
}
