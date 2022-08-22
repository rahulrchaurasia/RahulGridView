package com.example.rahul.gridview.registration;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.Activity.HomeActivity;
import com.example.rahul.gridview.R;


import com.example.rahul.gridview.login.LoginNewActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegistrationActivity extends BaseActivity implements View.OnClickListener {


    String TAG = "firebase";
    EditText etFullName, etEmail, etPassword, etconfirmPassword;
    Button btnSubmit;
    FirebaseAuth mAuth;
    DatabaseReference dbreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        mAuth = FirebaseAuth.getInstance();
        init_widets();
        setListener();

    }

    private void init_widets() {


        etFullName = (EditText) findViewById(R.id.etFullName);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etconfirmPassword = (EditText) findViewById(R.id.etconfirmPassword);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        //endregion
    }

    private void setListener() {
        btnSubmit.setOnClickListener(this);


    }

    private boolean validateRegistration() {
        if (!isEmpty(etFullName)) {
            etFullName.requestFocus();
            etFullName.setError("Enter Name");
            return false;
        }


        if (!isEmpty(etEmail)) {
            etEmail.requestFocus();
            etEmail.setError("Enter Email");
            return false;
        }
        if (!isValideEmailID(etEmail)) {
            etEmail.requestFocus();
            etEmail.setError("Enter Valid Email");
            return false;
        }

        if (!isEmpty(etPassword)) {
            etPassword.requestFocus();
            etPassword.setError("Enter Password");
            return false;
        }
        if (etPassword.getText().toString().trim().length() < 3) {
            etPassword.requestFocus();
            etPassword.setError("Minimum length should be 3");
            return false;
        }


        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnSubmit:
                if (validateRegistration() == true) {

                    register(etFullName.getText().toString().trim(),etEmail.getText().toString().trim(),etPassword.getText().toString().trim());

                }
                break;

        }
    }

    private void register(final String userName, String email, String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            showDialog();
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userID = firebaseUser.getUid();

                            dbreference = FirebaseDatabase.getInstance().getReference("Users").child(userID);

                            HashMap<String,String> hashMap = new HashMap<>();
                            hashMap.put("id",userID);
                            hashMap.put("username",userName);
                            hashMap.put("imageUrl","default");


                            dbreference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Intent intent = new Intent(RegistrationActivity.this, LoginNewActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                        dismissDialog();
                                        Toast.makeText(RegistrationActivity.this, "Registered Successfully...", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            cancelDialog();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                        }


                    }
                });
    }
}
