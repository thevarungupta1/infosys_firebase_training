package com.thevarungupta.firebaseauthdemo.emailAuthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.thevarungupta.firebaseauthdemo.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonLogin, buttonRegister;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!=null){
            // User logged in
            Intent intent = new Intent(this, WelcomeActivity.class);
            startActivity(intent);
        }

        init();
    }

    private void init() {
        buttonLogin = findViewById(R.id.button_login);
        buttonRegister = findViewById(R.id.button_register);
        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
                Intent intentlogin = new Intent(this, LoginActivity.class);
                startActivity(intentlogin);
                break;
            case R.id.button_register:
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }
}
