package com.thevarungupta.firebaseauthdemo.phoneAuthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.thevarungupta.firebaseauthdemo.R;

public class PhoneAuthActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonContinue;
    EditText editTextPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        init();
    }

    private void init() {
        editTextPhoneNumber = findViewById(R.id.edit_text_phone);
        buttonContinue = findViewById(R.id.button_continue);

        buttonContinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String mobile = editTextPhoneNumber.getText().toString().trim();

        if(mobile.isEmpty() || mobile.length() <10){
            Toast.makeText(getApplicationContext(), "Enter valid number", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, VerificationActivity.class);
        intent.putExtra("MOBILE", mobile);
        startActivity(intent);
    }
}
