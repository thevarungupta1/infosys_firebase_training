package com.thevarungupta.firebaseauthdemo.realtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.thevarungupta.firebaseauthdemo.R;

public class RealtimeDatabaseActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonCreate, buttonRead, buttonUpdate, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_database);

        init();
    }

    private void init() {
        buttonCreate = findViewById(R.id.button_create);
        buttonRead = findViewById(R.id.button_read);
        buttonUpdate = findViewById(R.id.button_update);
        buttonDelete = findViewById(R.id.button_delete);

        buttonCreate.setOnClickListener(this);
        buttonRead.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.button_create:
                intent = new Intent(this, CreateActivity.class);
                startActivity(intent);
                break;
            case R.id.button_read:
                intent = new Intent(this, ReadActivity.class);
                startActivity(intent);
                break;
            case R.id.button_update:
                break;
            case R.id.button_delete:
                break;
        }
    }
}
