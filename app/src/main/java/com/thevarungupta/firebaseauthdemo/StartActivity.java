package com.thevarungupta.firebaseauthdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thevarungupta.firebaseauthdemo.emailAuthentication.MainActivity;
import com.thevarungupta.firebaseauthdemo.phoneAuthentication.PhoneAuthActivity;
import com.thevarungupta.firebaseauthdemo.realtimedatabase.RealtimeDatabaseActivity;

public class StartActivity extends AppCompatActivity  implements View.OnClickListener {

    Button buttonEmailAuth, buttonPhoneAuth, buttonRealTimeDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        init();
    }

    private void init() {
        buttonEmailAuth = findViewById(R.id.button_email_auth);
        buttonPhoneAuth = findViewById(R.id.button_phone_auth);
        buttonRealTimeDatabase = findViewById(R.id.button_realtime_database);

        buttonEmailAuth.setOnClickListener(this);
        buttonPhoneAuth.setOnClickListener(this);
        buttonRealTimeDatabase.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()){
            case R.id.button_email_auth:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.button_phone_auth:
                intent = new Intent(this, PhoneAuthActivity.class);
                startActivity(intent);
                break;
            case R.id.button_realtime_database:
                intent = new Intent(this, RealtimeDatabaseActivity.class);
                startActivity(intent);
                break;
        }
    }
}
