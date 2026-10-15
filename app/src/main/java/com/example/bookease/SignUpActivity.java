package com.example.bookease;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText etFullName, etRegEmail, etRegPassword;
    private Button btnRegister;
    private TextView tvBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etFullName = findViewById(R.id.etFullName);
        etRegEmail = findViewById(R.id.etRegEmail);
        etRegPassword = findViewById(R.id.etRegPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvBackToLogin = findViewById(R.id.tvBackToLogin);

        // Register button click listener
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etFullName.getText().toString().trim();
                String email = etRegEmail.getText().toString().trim();
                String password = etRegPassword.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUpActivity.this, "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                    finish(); // Go back to login screen
                }
            }
        });

        // Navigate back to login screen
        tvBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}