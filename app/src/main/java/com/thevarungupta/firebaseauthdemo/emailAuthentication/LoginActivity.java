package com.thevarungupta.firebaseauthdemo.emailAuthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.thevarungupta.firebaseauthdemo.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth auth;

    TextView textViewForgotPassword, textViewNewUser;
    EditText editTextEmail, editTextPassword;
    Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // firebase auth instance
        auth = FirebaseAuth.getInstance();

        init();
    }

    private void init() {
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonLogin = findViewById(R.id.button_login);
        textViewNewUser = findViewById(R.id.text_view_new_user);
        textViewForgotPassword = findViewById(R.id.text_view_forgot_password);

        buttonLogin.setOnClickListener(this);
        textViewNewUser.setOnClickListener(this);
        textViewForgotPassword.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                login(email, password);
                break;
            case R.id.text_view_new_user:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.text_view_forgot_password:
                Intent forgotIntent = new Intent(this, ForgotPasswordActivity.class);
                startActivity(forgotIntent);
                break;
        }
    }

    private void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
