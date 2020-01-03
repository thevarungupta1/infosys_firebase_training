package com.thevarungupta.firebaseauthdemo.emailAuthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.thevarungupta.firebaseauthdemo.R;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonUpdateEmail, buttonUpdatePassword, buttonDeleteAccount, buttonSignOut;
    TextView textViewName;
    EditText editTextEmailPassword;

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        init();
    }

    private void init() {
        editTextEmailPassword = findViewById(R.id.edit_text_email_password);
        textViewName = findViewById(R.id.text_view_name);

        textViewName.setText(user.getEmail());

        buttonUpdateEmail = findViewById(R.id.button_update_email);
        buttonUpdatePassword = findViewById(R.id.button_update_password);
        buttonDeleteAccount = findViewById(R.id.button_delete_account);
        buttonSignOut  =findViewById(R.id.button_signout);

        buttonDeleteAccount.setOnClickListener(this);
        buttonUpdatePassword.setOnClickListener(this);
        buttonUpdateEmail.setOnClickListener(this);
        buttonSignOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String editTexValue = editTextEmailPassword.getText().toString();
        switch (view.getId()){
            case R.id.button_update_email:
                updateEmail(editTexValue);
                break;
            case R.id.button_update_password:
                updatePassword(editTexValue);
                break;
            case R.id.button_delete_account:
                deleteAccount();
                break;
            case R.id.button_signout:
                signOut();
                break;
        }
    }

    private void signOut(){
        auth.signOut();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void updateEmail(String newEmail){
        user.updateEmail(newEmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
    }

    private void updatePassword(String newPassword){
        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
    }

    private void deleteAccount(){
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                        }else{

                        }
                    }
                });
    }
}
