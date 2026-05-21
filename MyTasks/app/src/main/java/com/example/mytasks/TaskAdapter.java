package com.example.mytasks;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    public interface OnTaskClickListener {
        void onTaskClick(Task task);
    }

    private final Context context;
    private final List<Task> tasks;
    private final OnTaskClickListener listener;

    public TaskAdapter(Context context, List<Task> tasks, OnTaskClickListener listener) {
        this.context = context;
        this.tasks = new ArrayList<>(tasks);
        this.listener = listener;
    }

    public void setTasks(List<Task> newTasks) {
        tasks.clear();
        tasks.addAll(newTasks);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);

        holder.textTitle.setText(task.getTitle());
        holder.textDescription.setText(task.getDescription().isEmpty() ? context.getString(R.string.no_description) : task.getDescription());
        holder.textMeta.setText(context.getString(R.string.task_meta_format, task.getCategory(), task.getDueDate()));
        holder.textPriority.setText(context.getString(R.string.priority_format, task.getPriority()));
        holder.textStatus.setText(task.isCompleted() ? R.string.completed : R.string.pending);

        if (task.isCompleted()) {
            holder.textTitle.setPaintFlags(holder.textTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.textStatus.setTextColor(ContextCompat.getColor(context, R.color.status_done));
            holder.iconTask.setImageResource(android.R.drawable.checkbox_on_background);
        } else {
            holder.textTitle.setPaintFlags(holder.textTitle.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.textStatus.setTextColor(ContextCompat.getColor(context, R.color.status_pending));
            holder.iconTask.setImageResource(android.R.drawable.ic_menu_agenda);
        }

        holder.itemView.setOnClickListener(view -> listener.onTaskClick(task));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder {
        ImageView iconTask;
        TextView textTitle;
        TextView textDescription;
        TextView textMeta;
        TextView textPriority;
        TextView textStatus;

        TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            iconTask = itemView.findViewById(R.id.iconTask);
            textTitle = itemView.findViewById(R.id.textTaskTitle);
            textDescription = itemView.findViewById(R.id.textTaskDescription);
            textMeta = itemView.findViewById(R.id.textTaskMeta);
            textPriority = itemView.findViewById(R.id.textTaskPriority);
            textStatus = itemView.findViewById(R.id.textTaskStatus);
        }
    }
}
