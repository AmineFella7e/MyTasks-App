package com.example.mytasks;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    public static final String EXTRA_USERNAME = "extra_username";

    private EditText editUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.editUsername);
        Button btnEnter = findViewById(R.id.btnEnter);
        Button btnQuit = findViewById(R.id.btnQuit);

        TaskApplication app = (TaskApplication) getApplication();
        String previousName = app.getCurrentUsername();
        if (!TextUtils.isEmpty(previousName) && !"Invité".equals(previousName)) {
            editUsername.setText(previousName);
        }

        btnEnter.setOnClickListener(view -> enterApplication());
        btnQuit.setOnClickListener(view -> finish());
    }

    private void enterApplication() {
        String username = editUsername.getText().toString().trim();
        if (username.isEmpty()) {
            editUsername.setError(getString(R.string.error_required_username));
            editUsername.requestFocus();
            return;
        }

        ((TaskApplication) getApplication()).setCurrentUsername(username);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(EXTRA_USERNAME, username);
        startActivity(intent);
        Toast.makeText(this, getString(R.string.welcome_message, username), Toast.LENGTH_SHORT).show();
        finish();
    }
}
