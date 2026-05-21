package com.example.mytasks;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.Locale;

public class DetailTaskActivity extends AppCompatActivity {

    public static final String EXTRA_TASK_ID = "extra_task_id";

    private EditText editTitle;
    private EditText editDescription;
    private EditText editDueDate;
    private Spinner spinnerCategory;
    private RatingBar ratingPriority;
    private CheckBox checkCompleted;
    private TaskRepository repository;
    private long taskId = -1L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);

        repository = new TaskRepository();
        taskId = getIntent().getLongExtra(EXTRA_TASK_ID, -1L);

        setupToolbar();
        initViews();
        setupDatePicker();

        if (taskId == -1L) {
            Toast.makeText(this, R.string.error_task_not_found, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        loadTask();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbarDetailTask);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(R.string.detail_task_title);
        }
    }

    private void initViews() {
        editTitle = findViewById(R.id.editDetailTaskTitle);
        editDescription = findViewById(R.id.editDetailTaskDescription);
        editDueDate = findViewById(R.id.editDetailTaskDueDate);
        spinnerCategory = findViewById(R.id.spinnerDetailTaskCategory);
        ratingPriority = findViewById(R.id.ratingDetailTaskPriority);
        checkCompleted = findViewById(R.id.checkDetailTaskCompleted);

        Button btnUpdate = findViewById(R.id.btnUpdateTask);
        Button btnDelete = findViewById(R.id.btnDeleteTask);
        Button btnCancel = findViewById(R.id.btnBackTask);

        btnUpdate.setOnClickListener(view -> updateTask());
        btnDelete.setOnClickListener(view -> confirmDelete());
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

    private void loadTask() {
        Task task = repository.getTaskById(taskId);
        if (task == null) {
            Toast.makeText(this, R.string.error_task_not_found, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        editTitle.setText(task.getTitle());
        editDescription.setText(task.getDescription());
        editDueDate.setText(task.getDueDate());
        ratingPriority.setRating(task.getPriority());
        checkCompleted.setChecked(task.isCompleted());
        setSpinnerSelection(task.getCategory());
    }

    private void setSpinnerSelection(String value) {
        SpinnerAdapter adapter = spinnerCategory.getAdapter();
        if (adapter == null || value == null) {
            return;
        }
        for (int i = 0; i < adapter.getCount(); i++) {
            String item = String.valueOf(adapter.getItem(i));
            if (item.equalsIgnoreCase(value.trim())) {
                spinnerCategory.setSelection(i);
                break;
            }
        }
    }

    private void updateTask() {
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

        boolean success = repository.updateTask(taskId, title, description, category, priority, dueDate, completed);
        if (success) {
            Toast.makeText(this, R.string.toast_task_updated, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, R.string.error_task_not_found, Toast.LENGTH_SHORT).show();
        }
    }

    private void confirmDelete() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.dialog_delete_task_title)
                .setMessage(R.string.dialog_delete_task_message)
                .setPositiveButton(R.string.delete_task, (dialogInterface, i) -> {
                    boolean success = repository.deleteTask(taskId);
                    if (success) {
                        Toast.makeText(this, R.string.toast_task_deleted, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(this, R.string.error_task_not_found, Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
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
