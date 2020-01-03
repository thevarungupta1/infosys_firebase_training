package com.thevarungupta.firebaseauthdemo.phoneAuthentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.thevarungupta.firebaseauthdemo.R;

import java.util.concurrent.TimeUnit;

public class VerificationActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonVerify;
    EditText editTextOtp;
    String mobile;

    FirebaseAuth auth;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        // initialize firebase auth
        auth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        mobile = intent.getStringExtra("MOBILE");
        //Toast.makeText(this, ""+ mobile, Toast.LENGTH_SHORT).show();

        // send verification code as sms
        sendVerificationCode(mobile);

        init();
    }

    private void init() {
        editTextOtp = findViewById(R.id.edit_text_otp);
        buttonVerify = findViewById(R.id.button_verify);
    }

    @Override
    public void onClick(View view) {
        String otp = editTextOtp.getText().toString().trim();
        if (otp.isEmpty() || otp.length() < 6) {
            Toast.makeText(this, "Enter Valid OTP", Toast.LENGTH_SHORT).show();
            return;
        }

        // verifying the otp
        verifyOtp(otp);
    }

    private void sendVerificationCode(String mobile) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if(code !=null){
                //editTextOtp.setText(code);
                verifyOtp(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.e("ff", e.getMessage());
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            verificationId = s;

        }
    };

    private void verifyOtp(String otp) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);

        auth.signInWithCredential(credential)
                .addOnCompleteListener(VerificationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(VerificationActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(VerificationActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
