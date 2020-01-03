package com.thevarungupta.firebaseauthdemo.emailAuthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.thevarungupta.firebaseauthdemo.R;

public class RegisterActivity extends AppCompatActivity {


    EditText editTextEmail, editTextPassword;
    Button buttonRegister;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Get firebase auth instance
        auth = FirebaseAuth.getInstance();

        init();
    }

    private void init() {
        editTextEmail = findViewById(R.id.edit_text_email);
        editTextPassword = findViewById(R.id.edit_text_password);
        buttonRegister = findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                // create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "Authentication Failed",
                                            Toast.LENGTH_SHORT)
                                            .show();
                                }else{
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "Authentication Suucessfull",
                                            Toast.LENGTH_SHORT)
                                            .show();
                                }
                            }
                        });

            }
        });

    }


}
