package com.thevarungupta.firebaseauthdemo.emailAuthentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.thevarungupta.firebaseauthdemo.R;

public class WelcomeActivity extends AppCompatActivity {

    Button buttonProfile;
    TextView textViewName;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        auth = FirebaseAuth.getInstance();

        init();
    }

    private void init() {
        textViewName = findViewById(R.id.text_view_name);
        user = FirebaseAuth.getInstance().getCurrentUser();
        textViewName.setText(user.getEmail());

        buttonProfile = findViewById(R.id.button_profile);
        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(WelcomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
