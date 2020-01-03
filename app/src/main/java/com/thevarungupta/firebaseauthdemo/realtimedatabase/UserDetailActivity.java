package com.thevarungupta.firebaseauthdemo.realtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thevarungupta.firebaseauthdemo.R;

public class UserDetailActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextName, editTextPhone, editTextEmail;
    Button buttonUpdate, buttonDelete;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);

        Intent intent = getIntent();
        id =  intent.getStringExtra("DATA");

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        readUser(id);

        init();
    }

    private void init() {
        editTextName = findViewById(R.id.edit_text_name);
        editTextPhone = findViewById(R.id.edit_text_phone);
        editTextEmail = findViewById(R.id.edit_text_email);

        buttonUpdate = findViewById(R.id.button_user_edit);
        buttonDelete = findViewById(R.id.button_user_delete);
        buttonDelete.setOnClickListener(this);
        buttonUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_user_edit:
                updateUser(id);
                break;
            case R.id.button_user_delete:
                deleteUser(id);
                break;
        }
    }

    private void readUser(String id){
        databaseReference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                editTextName.setText(user.getName());
                editTextPhone.setText(user.getPhone());
                editTextEmail.setText(user.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void deleteUser(String id) {
        databaseReference.child(id).setValue(null);
    }

    private void updateUser(String id) {
        String name = editTextName.getText().toString();
        String phone = editTextPhone.getText().toString();
        String email = editTextEmail.getText().toString();
        User updateUser = new User(name, phone, email);

        databaseReference.child(id).setValue(updateUser);

//        databaseReference.child(id).child("name").setValue(name);
//        databaseReference.child(id).child("phone").setValue(phone);
//        databaseReference.child(id).child("email").setValue(email);

        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

    }
}
