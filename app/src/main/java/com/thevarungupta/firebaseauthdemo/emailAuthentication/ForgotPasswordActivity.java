package com.thevarungupta.firebaseauthdemo.emailAuthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.thevarungupta.firebaseauthdemo.R;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth auth;

    Button buttonResetPassword;
    EditText editTextEmail;
    TextView textViewCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        auth = FirebaseAuth.getInstance();

        init();
    }

    private void init() {
        buttonResetPassword = findViewById(R.id.button_reset_password);
        editTextEmail = findViewById(R.id.edit_text_email);
        textViewCancel = findViewById(R.id.text_view_cancel);

        buttonResetPassword.setOnClickListener(this);
        textViewCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_reset_password:
                String email = editTextEmail.getText().toString();
                resetPassword(email);
                break;
            case R.id.text_view_cancel:

                finish();

                break;
        }
    }

    private void resetPassword(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPasswordActivity.this, "We have send passsword reset email", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "Oops! something wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
