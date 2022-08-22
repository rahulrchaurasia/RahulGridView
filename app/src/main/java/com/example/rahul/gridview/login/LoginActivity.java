package com.example.rahul.gridview.login;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.Activity.HomeActivity;
import com.example.rahul.gridview.Activity.LocationTracker;
import com.example.rahul.gridview.Activity.Marshmellow_Permission;
import com.example.rahul.gridview.Activity.ProfileActivity;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.core.APIResponse;
import com.example.rahul.gridview.core.IResponseSubcriber;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener ,IResponseSubcriber{

    private static final String TAG = HomeActivity.class.getSimpleName();
    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;
    private SignInButton btnSignIn;
    Button btnGoogleLogin;
    private Button btnSignOut, btnRevokeAccess,btnlogin;
    private LinearLayout llProfileLayout;
    private ImageView imgProfilePic;
    private TextView txtName, txtEmail,txtMobile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


        btnSignIn = (SignInButton) findViewById(R.id.btn_sign_in);
        btnGoogleLogin = (Button) findViewById(R.id.btn_google_login);

        btnSignOut = (Button) findViewById(R.id.btn_sign_out);
        btnRevokeAccess = (Button) findViewById(R.id.btn_revoke_access);
        btnlogin  = (Button) findViewById(R.id.btn_login);
        llProfileLayout = (LinearLayout) findViewById(R.id.llProfile);
        imgProfilePic = (ImageView) findViewById(R.id.imgProfilePic);
        txtName = (TextView) findViewById(R.id.txtName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtMobile = (TextView) findViewById(R.id.txtMobile);

        btnSignIn.setOnClickListener(this);
        btnGoogleLogin.setOnClickListener(this);
        btnlogin.setOnClickListener(this);

        btnSignOut.setOnClickListener(this);
        btnRevokeAccess.setOnClickListener(this);


        /************************************************************************************************
         * For FireBase Sevice : when Apps in backround than it will call By default Launch Activity
         * Hence we can get the Notify in Launch Activity By using getIntent
         *****************************************************************************************************/

        try {

            if (getIntent().getExtras() != null) {
                if(getIntent().hasExtra("notifyFlag")) {
                    // Log.d("Notify",getIntent().getStringExtra("notifyFlag"));
                    String type = getIntent().getStringExtra("notifyFlag");
                    if (type.matches("F")) {
                        Intent intent = new Intent(this, ProfileActivity.class);
                        startActivity(intent);
                    } else if (type.matches("L")) {
                        Intent intent = new Intent(this, Marshmellow_Permission.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(this, LocationTracker.class);
                        startActivity(intent);
                    }
                }

            }
        }catch (Exception ex)
        {

        }

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addOnConnectionFailedListener(this)

                .build();

        // Customizing G+ button
        btnSignIn.setSize(SignInButton.SIZE_STANDARD);
        btnSignIn.setScopes(gso.getScopeArray());

          subscribeToPushService();

    }

    private void subscribeToPushService() {

       try {
           FirebaseMessaging.getInstance().subscribeToTopic("notify");

           Log.d("AndroidBash", "Subscribed");
           Toast.makeText(this, "Subscribed", Toast.LENGTH_SHORT).show();
       }catch (Exception ex)
       {
           Toast.makeText(this, "Firebase Error "+ ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
       }

    }
    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {

            case R.id.btn_login:

                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_google_login:
                signIn();
                break;
            case R.id.btn_sign_in:
                signIn();
                break;

            case R.id.btn_sign_out:
                signOut();
                break;

            case R.id.btn_revoke_access:
                revokeAccess();
                break;
    }


}

    private void signOut() {

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateUI(false);
                    }
                }
        );
    }

    private void signIn() {

       // signOut();
        if (mGoogleApiClient.isConnected())
        {
            signOut();
        }
           // mGoogleApiClient.connect();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,RC_SIGN_IN);
    }

    private void revokeAccess() {
        Auth.GoogleSignInApi.revokeAccess(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        updateUI(false);
                    }
                });
    }



    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);

        if(requestCode == RC_SIGN_IN)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            Log.e(TAG, "display name: " + acct.getDisplayName());

            String personName = acct.getDisplayName();
            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();
            String lname = acct.getFamilyName();
            String fname =  acct.getGivenName();


            Log.e(TAG, "Name: " + personName + ", email: " + email
                    + ", Image: " + personPhotoUrl
                    + ", Lname: " + lname
                    + ", Fname: " + fname);

            txtName.setText(personName);
            txtEmail.setText(email);
            txtMobile.setText(lname);
            Glide.with(getApplicationContext()).load(personPhotoUrl)
                    .thumbnail(0.5f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgProfilePic);

           updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            Toast.makeText(this,"Connection Failed",Toast.LENGTH_SHORT).show();
            updateUI(false);
        }
    }

    private void updateUI(boolean isSignedIn) {
        if (isSignedIn) {
            btnSignIn.setVisibility(View.GONE);
            btnGoogleLogin.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.VISIBLE);
            btnRevokeAccess.setVisibility(View.VISIBLE);
            llProfileLayout.setVisibility(View.VISIBLE);
        } else {
            btnSignIn.setVisibility(View.VISIBLE);
            btnGoogleLogin.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.GONE);
            btnRevokeAccess.setVisibility(View.GONE);
            llProfileLayout.setVisibility(View.GONE);
        }
    }

    private boolean isAppAlive() {
        ActivityManager activityManager = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);
        if ((serviceList.size() > 0)) {
            for (int i = 0; i < serviceList.size(); i++) {
                ActivityManager.RunningServiceInfo serviceInfo = serviceList.get(i);
                ComponentName serviceName = serviceInfo.service;
                if (serviceName.toString().contains("com.example.rahul.gridview")) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void OnSuccess(APIResponse response, String message) throws InterruptedException {

    }

    @Override
    public void OnFailure(Throwable t) {



    }




}
