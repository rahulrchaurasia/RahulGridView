package com.example.rahul.gridview.login;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.Activity.HomeActivity;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.registration.RegistrationActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginNewActivity extends BaseActivity implements View.OnClickListener {

    //RegistrationActivity
    TextView tvRegistration, tvForgotPassword;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 1111;
    EditText etPassword, etEmail;
    Button btnSignIn;
    FirebaseAuth mAuth;

    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //  getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setTitle("Login");

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        initialize();

    }

    private void initialize() {

        etPassword = (EditText) findViewById(R.id.etPassword);
        etEmail = (EditText) findViewById(R.id.etEmail);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        tvRegistration = (TextView) findViewById(R.id.tvRegistration);
        tvForgotPassword = (TextView) findViewById(R.id.tvForgotPassword);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(this);
        tvRegistration.setOnClickListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseUser != null) {
            Intent intent = new Intent(LoginNewActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.tvForgotPassword:
                //startActivity(new Intent(LoginNewActivity.this, ForgotPasswordActivity.class));
                break;

            case R.id.tvRegistration:
                startActivity(new Intent(LoginNewActivity.this, RegistrationActivity.class));

                break;

            case R.id.btnSignIn:

                Intent intent = new Intent(LoginNewActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


//                if (!isEmpty(etEmail)) {
//                    etEmail.requestFocus();
//                    etEmail.setError("Enter EmailID");
//                    return;
//                }
//                if (!isEmpty(etPassword)) {
//                    etPassword.requestFocus();
//                    etPassword.setError("Enter Password");
//                    return;
//                }
//
//                showDialog();
//
//                mAuth.signInWithEmailAndPassword(etEmail.getText().toString().trim(), etPassword.getText().toString().trim())
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//
//                                cancelDialog();
//                                if (task.isSuccessful()) {
//
//                                    Intent intent = new Intent(LoginNewActivity.this, HomeActivity.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                                    startActivity(intent);
//
//                                    finish();
//                                } else {
//
//                                    Toast.makeText(LoginNewActivity.this, "Authantication Faile", Toast.LENGTH_SHORT).show();
//
//
//                                }
//                            }
//                        });

                break;
        }
    }
}
