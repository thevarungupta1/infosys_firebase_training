package com.thevarungupta.firebaseauthdemo.realtimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thevarungupta.firebaseauthdemo.R;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    AdapterUser adapterUser;

    ArrayList<User> users = new ArrayList<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");

        init();
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        adapterUser = new AdapterUser(this, users);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterUser);

        readData();
    }

    private void readData() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<String> keys = new ArrayList<>();
                for (DataSnapshot keyNode : dataSnapshot.getChildren()) {
                    // adding all the keys in keys arrays
                    // once we read all the keys we can start reading data using keys
                    //keys.add(keyNode.getKey());

                    // read the data using keys and save in users array
                    User user = keyNode.getValue(User.class);
                    users.add(user);
                }
                adapterUser.setData(users);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ReadActivity.this, "on cancel", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
