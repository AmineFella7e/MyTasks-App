package com.example.mytasks;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class TaskApplication extends Application {

    private String currentUsername = "";

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        
        // Configuration par défaut de Realm
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("tasks.realm")
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .compactOnLaunch()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public String getCurrentUsername() {
        if (currentUsername == null || currentUsername.trim().isEmpty()) {
            return "Invité";
        }
        return currentUsername;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public void clearSession() {
        this.currentUsername = "";
    }
}
