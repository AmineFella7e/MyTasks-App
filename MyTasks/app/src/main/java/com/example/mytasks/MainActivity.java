package com.example.mytasks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerTasks;
    private TextView textEmpty;
    private TaskAdapter adapter;
    private TaskRepository repository;
    private String username;


      //Méthode appelée à la création de l'activité.
     //Initialise l'interface, le repository et les composants principaux.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new TaskRepository();
        setupToolbar();
        setupUser();
        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTasks();
    }

    //Initialise la Toolbar
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.app_name);
        }
    }


    // Récupère le nom de l'utilisateur depuis l'intent ou l'application globale et met à jour la Toolbar en ajoutant le nom de l'utilisateur.
    private void setupUser() {
        username = getIntent().getStringExtra(LoginActivity.EXTRA_USERNAME);
        TaskApplication app = (TaskApplication) getApplication();
        if (username == null || username.trim().isEmpty()) {
            username = app.getCurrentUsername();
        } else {
            app.setCurrentUsername(username);
        }

        Toolbar toolbar = findViewById(R.id.toolbarMain);
        toolbar.setSubtitle(getString(R.string.toolbar_subtitle, username));
    }

    private void setupRecyclerView() {
        recyclerTasks = findViewById(R.id.recyclerTasks);
        textEmpty = findViewById(R.id.textEmpty);

        adapter = new TaskAdapter(this, new ArrayList<>(), task -> openTaskDetails(task.getId()));
        recyclerTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerTasks.setAdapter(adapter);
    }

    //Charge les tâches depuis le repository
    private void loadTasks() {
        ArrayList<Task> tasks = repository.getAllTasks();
        adapter.setTasks(tasks);

        boolean hasData = !tasks.isEmpty();
        recyclerTasks.setVisibility(hasData ? View.VISIBLE : View.GONE);
        textEmpty.setVisibility(hasData ? View.GONE : View.VISIBLE);

        InfoFragment infoFragment = (InfoFragment) getSupportFragmentManager().findFragmentById(R.id.infoFragmentContainer);
        if (infoFragment != null) {
            infoFragment.updateContent(username, repository.getTotalCount(), repository.getCompletedCount());
        }
    }

    //Ouvre l'écran de détail d'une tâche spécifique
    private void openTaskDetails(long taskId) {
        Intent intent = new Intent(this, DetailTaskActivity.class);
        intent.putExtra(DetailTaskActivity.EXTRA_TASK_ID, taskId);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.menu_add) {
            startActivity(new Intent(this, AddTaskActivity.class));
            return true;
        }

        if (itemId == R.id.menu_clear_all) {
            showDeleteAllDialog();
            return true;
        }

        if (itemId == R.id.menu_about) {
            showAboutDialog();
            return true;
        }

        if (itemId == R.id.menu_support) {
            openSupportDialer();
            return true;
        }

        if (itemId == R.id.menu_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showDeleteAllDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_delete_all_title)
                .setMessage(R.string.dialog_delete_all_message)
                .setPositiveButton(R.string.delete_all, (dialogInterface, i) -> {
                    repository.deleteAllTasks();
                    Toast.makeText(this, R.string.toast_all_deleted, Toast.LENGTH_SHORT).show();
                    loadTasks();
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.menu_about)
                .setMessage(R.string.about_message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    private void openSupportDialer() {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0522000000"));
        startActivity(intent);
    }

    private void logout() {
        ((TaskApplication) getApplication()).clearSession();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(this, R.string.toast_logout, Toast.LENGTH_SHORT).show();
        finish();
    }
}
