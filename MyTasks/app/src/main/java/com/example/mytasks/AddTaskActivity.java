package com.example.mytasks;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.Locale;

public class AddTaskActivity extends AppCompatActivity {

    private EditText editTitle;
    private EditText editDescription;
    private EditText editDueDate;
    private Spinner spinnerCategory;
    private RatingBar ratingPriority;
    private CheckBox checkCompleted;
    private TaskRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        repository = new TaskRepository();
        setupToolbar();
        initViews();
        setupDatePicker();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarAddTask);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.add_task_title);
        }
    }

    private void initViews() {
        editTitle = findViewById(R.id.editTaskTitle);
        editDescription = findViewById(R.id.editTaskDescription);
        editDueDate = findViewById(R.id.editTaskDueDate);
        spinnerCategory = findViewById(R.id.spinnerTaskCategory);
        ratingPriority = findViewById(R.id.ratingTaskPriority);
        checkCompleted = findViewById(R.id.checkTaskCompleted);

        Button btnSave = findViewById(R.id.btnSaveTask);
        Button btnCancel = findViewById(R.id.btnCancelTask);

        btnSave.setOnClickListener(view -> saveTask());
        btnCancel.setOnClickListener(view -> finish());
    }

    private void setupDatePicker() {
        editDueDate.setOnClickListener(view -> showDatePicker());
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                (datePicker, year, month, dayOfMonth) -> editDueDate.setText(formatDate(dayOfMonth, month + 1, year)),
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        dialog.show();
    }

    private String formatDate(int day, int month, int year) {
        return String.format(Locale.getDefault(), "%02d/%02d/%04d", day, month, year);
    }

    private void saveTask() {
        String title = editTitle.getText().toString().trim();
        if (title.isEmpty()) {
            editTitle.setError(getString(R.string.error_required_title));
            editTitle.requestFocus();
            return;
        }

        String description = editDescription.getText().toString().trim();
        String dueDate = editDueDate.getText().toString().trim();
        String category = String.valueOf(spinnerCategory.getSelectedItem());
        int priority = Math.round(ratingPriority.getRating());
        boolean completed = checkCompleted.isChecked();

        if (dueDate.isEmpty()) {
            dueDate = getString(R.string.no_date);
        }
        if (priority <= 0) {
            priority = 1;
        }

        repository.addTask(title, description, category, priority, dueDate, completed);
        Toast.makeText(this, R.string.toast_task_added, Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
