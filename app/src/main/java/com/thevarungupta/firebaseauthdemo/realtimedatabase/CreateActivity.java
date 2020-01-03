package com.thevarungupta.firebaseauthdemo.realtimedatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.thevarungupta.firebaseauthdemo.R;

public class CreateActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonInsert;
    EditText editName, editTextPhone, editTextEmail;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Initializing object
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        //databaseReference = firebaseDatabase.getReference("demo");

        init();
    }

    private void init() {
        editName = findViewById(R.id.edit_text_name);
        editTextPhone = findViewById(R.id.edit_text_phone);
        editTextEmail = findViewById(R.id.edit_text_email);
        buttonInsert = findViewById(R.id.button_insert);

        buttonInsert.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String name = editName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        //databaseReference.setValue("this is demo message");

        User user = new User(name, phone, email);
        String userId = databaseReference.push().getKey();
        databaseReference.child(userId).setValue(user);
        Toast.makeText(this, "record inserted", Toast.LENGTH_SHORT).show();




    }
}
