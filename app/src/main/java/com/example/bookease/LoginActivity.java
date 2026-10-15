package com.example.bookease;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin, btnGoogleSignIn;
    private TextView tvGoToSignUp;

    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn);
        tvGoToSignUp = findViewById(R.id.tvGoToSignUp);

        // Configure Google Sign-In options to request email and default sign in
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Standard Login Button Click
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, BookingActivity.class);
                    startActivity(intent);
                }
            }
        });

        // Google Sign-In Button Click -> Opens Account Chooser Dialog
        btnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });

        // Navigate to Sign Up screen
        tvGoToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void signInWithGoogle() {
        // Clear previous sign-in state so the account picker always shows up
        mGoogleSignInClient.signOut().addOnCompleteListener(this, task -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    Toast.makeText(this, "Welcome, " + account.getDisplayName(), Toast.LENGTH_SHORT).show();
                    // Navigate to Booking Activity dashboard
                    Intent intent = new Intent(LoginActivity.this, BookingActivity.class);
                    startActivity(intent);
                    finish();
                }
            } catch (ApiException e) {
                Toast.makeText(this, "Google Sign-In Cancelled or Failed: " + e.getStatusCode(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}