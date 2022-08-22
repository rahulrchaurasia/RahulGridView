package com.example.rahul.gridview.facebook;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class FacebookActivity extends BaseActivity implements View.OnClickListener {

    LoginButton loginButton;
    private CallbackManager callbackManager;
    TextView txtName,txtLast,txtEmail,txtID;
    AccessTokenTracker tokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        loginButton = (LoginButton)findViewById(R.id.login_button);

        txtName = (TextView) findViewById(R.id.txtName);
        txtLast = (TextView) findViewById(R.id.txtLast);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtID = (TextView) findViewById(R.id.txtID);

        loginButton.setReadPermissions(Arrays.asList("email","public_profile"));
       // loginButton.setOnClickListener(this);

       // FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

         tokenTracker  = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {

                if(currentAccessToken == null)
                {
                    txtName.setText("");
                    txtLast.setText("");
                    txtEmail.setText("");
                    txtID.setText("");

                    Toast.makeText(FacebookActivity.this,"User Logged Out",Toast.LENGTH_SHORT).show();
                }else {
                    loadUserProfile(currentAccessToken);
                }

            }
        };

        tokenTracker.startTracking();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }




    private void loadUserProfile (AccessToken newaccessToken){

        GraphRequest request = GraphRequest.newMeRequest(newaccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                try {
                    String first_name = object.getString("first_name");
                    String last_name = object.getString("last_name");
                    String email = object.getString("email");
                    String id = object.getString("id");


                    txtName.setText(""+ first_name);
                    txtLast.setText(""+ last_name);
                    txtEmail.setText(""+ email);
                    txtID.setText(""+ id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Bundle parameters = new Bundle();
        parameters.putString("fields","first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case  R.id.login_button :

        }

    }
}
