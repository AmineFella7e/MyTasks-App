package com.example.mytasks;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class TaskRepository {

    public void addTask(String title, String description, String category, int priority, String dueDate, boolean completed) {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(transactionRealm -> {
                Number maxId = transactionRealm.where(Task.class).max("id");
                long nextId = maxId == null ? 1L : maxId.longValue() + 1L;
                Task task = transactionRealm.createObject(Task.class, nextId);
                task.setTitle(safeText(title));
                task.setDescription(safeText(description));
                task.setCategory(safeText(category));
                task.setPriority(priority);
                task.setDueDate(safeText(dueDate));
                task.setCompleted(completed);
            });
        } finally {
            realm.close();
        }
    }

    public ArrayList<Task> getAllTasks() {
        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<Task> results = realm.where(Task.class)
                    .sort("id", Sort.DESCENDING)
                    .findAll();
            return new ArrayList<>(realm.copyFromRealm(results));
        } finally {
            realm.close();
        }
    }

    public Task getTaskById(long id) {
        Realm realm = Realm.getDefaultInstance();
        try {
            Task managedTask = realm.where(Task.class)
                    .equalTo("id", id)
                    .findFirst();
            return managedTask == null ? null : realm.copyFromRealm(managedTask);
        } finally {
            realm.close();
        }
    }

    public boolean updateTask(long id, String title, String description, String category, int priority, String dueDate, boolean completed) {
        Realm realm = Realm.getDefaultInstance();
        final boolean[] updated = {false};
        try {
            realm.executeTransaction(transactionRealm -> {
                Task task = transactionRealm.where(Task.class)
                        .equalTo("id", id)
                        .findFirst();
                if (task != null) {
                    task.setTitle(safeText(title));
                    task.setDescription(safeText(description));
                    task.setCategory(safeText(category));
                    task.setPriority(priority);
                    task.setDueDate(safeText(dueDate));
                    task.setCompleted(completed);
                    updated[0] = true;
                }
            });
            return updated[0];
        } finally {
            realm.close();
        }
    }

    public boolean deleteTask(long id) {
        Realm realm = Realm.getDefaultInstance();
        final boolean[] deleted = {false};
        try {
            realm.executeTransaction(transactionRealm -> {
                Task task = transactionRealm.where(Task.class)
                        .equalTo("id", id)
                        .findFirst();
                if (task != null) {
                    task.deleteFromRealm();
                    deleted[0] = true;
                }
            });
            return deleted[0];
        } finally {
            realm.close();
        }
    }

    public void deleteAllTasks() {
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.executeTransaction(transactionRealm -> transactionRealm.delete(Task.class));
        } finally {
            realm.close();
        }
    }

    public int getTotalCount() {
        Realm realm = Realm.getDefaultInstance();
        try {
            return (int) realm.where(Task.class).count();
        } finally {
            realm.close();
        }
    }

    public int getCompletedCount() {
        Realm realm = Realm.getDefaultInstance();
        try {
            return (int) realm.where(Task.class)
                    .equalTo("completed", true)
                    .count();
        } finally {
            realm.close();
        }
    }

    private String safeText(String value) {
        return value == null ? "" : value.trim();
    }
}
