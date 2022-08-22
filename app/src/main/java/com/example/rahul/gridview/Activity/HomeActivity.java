package com.example.rahul.gridview.Activity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.RecyclerHeadFoot.RecyclerMain;
import com.example.rahul.gridview.Utility.MySpannable;
import com.example.rahul.gridview.Utility.Utility;
import com.example.rahul.gridview.chat.ChatTabActivity;
import com.example.rahul.gridview.core.APIResponse;
import com.example.rahul.gridview.core.IResponseSubcriber;
import com.example.rahul.gridview.core.controller.sync.SyncController;
import com.example.rahul.gridview.core.response.ProductResponse;
import com.example.rahul.gridview.dashboard.DasboardKotlinActivity;
import com.example.rahul.gridview.dashboard.DashBoardActivity;
import com.example.rahul.gridview.demo.ActivityLifeCycleActivity;
import com.example.rahul.gridview.facebook.FacebookActivity;
import com.example.rahul.gridview.handlerDemo.HandlerActivity;
import com.example.rahul.gridview.javaScript.CommonWebViewActivity;
import com.example.rahul.gridview.kotlin.KotlinDemo;
import com.example.rahul.gridview.location.LocationActivity;
import com.example.rahul.gridview.location.LocationDemo2;
import com.example.rahul.gridview.location.LocationMainActivity;
import com.example.rahul.gridview.login.LoginNewActivity;
import com.example.rahul.gridview.model.LeadEntity;
import com.example.rahul.gridview.service.ForegroundServiceActivity;
import com.example.rahul.gridview.service.LocalBoundActivity;
import com.example.rahul.gridview.viewPagerCircular.CircleViewPageActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import io.realm.Realm;

public class HomeActivity extends BaseActivity implements IResponseSubcriber, View.OnClickListener {

    private Realm realm;
    Button btnJson, btnDemo, btnAlert, btnPopUp,btnLocation, btnLocationMain,btnDashboard,btnDashboardNew, btnfaceBook,btnWebView,btnKotlin;
    TextView txtReceiver;

    // MyBroadastReceiver myReceiver = new MyBroadastReceiver();
    // private BroadcastReceiver mHandleMessageReceiver;

    private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String receiver = intent.getStringExtra("RandomNumber");
            txtReceiver.setText("" + receiver);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm = Realm.getDefaultInstance();
        btnJson = (Button) findViewById(R.id.btnJson);
        btnDemo = (Button) findViewById(R.id.btnDemo);
        btnAlert = (Button) findViewById(R.id.btnAlert);
        btnPopUp = (Button) findViewById(R.id.btnPopUp);
        btnLocation = (Button) findViewById(R.id.btnLocation);
        btnLocationMain = (Button) findViewById(R.id.btnLocationMain);
        btnDashboard = (Button) findViewById(R.id.btnDashboard);
        btnDashboardNew  = (Button) findViewById(R.id.btnDashboardNew);
        btnfaceBook = (Button) findViewById(R.id.btnfaceBook);
        btnWebView  = (Button) findViewById(R.id.btnWebView);
        btnKotlin  = (Button) findViewById(R.id.btnKotlin);

        txtReceiver = (TextView) findViewById(R.id.txtReceiver);
        //
        btnJson.setOnClickListener(this);
        btnDemo.setOnClickListener(this);
        btnAlert.setOnClickListener(this);
        btnPopUp.setOnClickListener(this);
        btnLocation.setOnClickListener(this);
        btnLocationMain.setOnClickListener(this);
        btnDashboard.setOnClickListener(this);
        btnDashboardNew.setOnClickListener(this);
        btnfaceBook.setOnClickListener(this);
        btnWebView.setOnClickListener(this);
        btnKotlin.setOnClickListener(this);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void getProductData() {
        showProgressDialog();
        new SyncController(this, realm).getProduct("0", "RB40000628", "1", this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_add:
                setTitle(R.string.add_emp);
                intent = new Intent(HomeActivity.this, AddEmployee.class);
                startActivity(intent);
                // return  true;
                break;

            case R.id.action_chat:
                intent = new Intent(HomeActivity.this, ChatTabActivity.class);
                startActivity(intent);

                break;
            case R.id.action_logout:

                dialogLogout(HomeActivity.this);

                break;
            //LocationActivity


//            case R.id.action_grid:
//                 intent = new Intent(HomeActivity.this, MainActivity.class);
//                startActivity(intent);
//                //return  true;
//                break;
//            case R.id.action_ImgCapture:
//                intent = new Intent(HomeActivity.this, CropImage.class);
//                startActivity(intent);
//                break;

//            case R.id.action_Permission:
//                intent = new Intent(HomeActivity.this, Marshmellow_Permission.class);
//                startActivity(intent);
//                break;


//            case R.id.bar_chart:
//                intent = new Intent(HomeActivity.this, BarChart.class);
//                startActivity(intent);
//                break;

            case R.id.share:
                intent = new Intent(HomeActivity.this, ShareData.class);
                startActivity(intent);
                break;

//            case R.id.contact:
//                intent = new Intent(HomeActivity.this, ContactsActivity.class);
//                startActivity(intent);
//                break;

            case R.id.cropImage:
                intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
                break;



            case R.id.track:
                intent = new Intent(HomeActivity.this, LocationTracker.class);
                startActivity(intent);
                break;

            case R.id.dynamic:
                intent = new Intent(HomeActivity.this, DynamicText.class);
                startActivity(intent);
                break;

            case R.id.realmDemo:
                intent = new Intent(HomeActivity.this, RealmDemo.class);
                startActivity(intent);
                break;

            case R.id.realmJson:
                intent = new Intent(HomeActivity.this, RealmWithJson.class);
                startActivity(intent);
                break;


            case R.id.autoComplete:
                intent = new Intent(HomeActivity.this, Autocompletetextview.class);
                startActivity(intent);
                break;

            case R.id.recycler:
                intent = new Intent(HomeActivity.this, RecyclerMain.class);
                startActivity(intent);
                break;

            //handler

            case R.id.handler:
                intent = new Intent(HomeActivity.this, HandlerActivity.class);
                startActivity(intent);
                break;

            case R.id.recycler_filter:
                intent = new Intent(HomeActivity.this, HandlerActivity.class);
                startActivity(intent);
                break;

            case R.id.broadCast:
                intent = new Intent(HomeActivity.this, BroadCastActivity.class);
                startActivity(intent);
                break;

            case R.id.cust_progress:
                intent = new Intent(HomeActivity.this, CustomProgressActivity.class);
                startActivity(intent);
                break;


            case R.id.window_popup2:
                intent = new Intent(HomeActivity.this, PopUpWindowActivity2.class);
                startActivity(intent);
                break;

            case R.id.rotate:
                intent = new Intent(HomeActivity.this, RotateActivity.class);
                startActivity(intent);
                break;

            case R.id.viewPager:
                intent = new Intent(HomeActivity.this, CircleViewPageActivity.class);
                startActivity(intent);
                break;


            case R.id.gson:
                intent = new Intent(HomeActivity.this, GsonJsonActivity.class);
                startActivity(intent);
                break;

            case R.id.NotifyCount:
                intent = new Intent(HomeActivity.this, NotifCountActivity.class);
                startActivity(intent);
                break;

            case R.id.boundService:
                intent = new Intent(HomeActivity.this, LocalBoundActivity.class);
                startActivity(intent);
                break;


            case R.id.foregroundService:
                intent = new Intent(HomeActivity.this, ForegroundServiceActivity.class);
                startActivity(intent);
                break;

            // region Comment
//            case R.id.spinner:
//                intent = new Intent(HomeActivity.this, SpinnerActivity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.gps:
//                intent = new Intent(HomeActivity.this, GPSActivity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.popup:
//                intent = new Intent(HomeActivity.this, PopUpMenuActivity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.gesture:
//                intent = new Intent(HomeActivity.this, GestureActivity.class);
//                startActivity(intent);
//                break;
            //endregion


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        dialogLogout(HomeActivity.this);

    }

    public void dialogLogout(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("");
        builder.setMessage("Do you really want to logout?");
        builder.setCancelable(false);

        builder.setPositiveButton(
                "LOGOUT",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(HomeActivity.this, LoginNewActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }
                });

        builder.setNegativeButton(
                "CANCEL",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        AlertDialog alert11 = builder.create();
        alert11.show();
    }

    public void onShowQuitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);

        builder.setMessage("Do You Want To Quit?");
        builder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                });
        builder.setNegativeButton(android.R.string.no,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        builder.create().show();
    }


    @Override
    public void OnSuccess(final APIResponse response, String message) throws InterruptedException {
        cancelDialog();
        if (response instanceof ProductResponse)
            if (response.getStatusId() == 0) {
                if (((ProductResponse) response).getResult() != null) {

                    Snackbar.make(btnJson, "Record save successfully..", Snackbar.LENGTH_SHORT).show();

                    // Updating the  list and modified the realm db
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            for (LeadEntity leadEntity : ((ProductResponse) response).getResult().getLstLeads()) {
                                leadEntity.setColor(Utility.getRandomMaterialColor(HomeActivity.this, "400"));
                                realm.copyToRealmOrUpdate(leadEntity);
                            }
                        }
                    });


                }
            } else {

                Snackbar.make(btnJson, "No data available", Snackbar.LENGTH_SHORT).show();
            }
    }

    @Override
    public void OnFailure(Throwable t) {

        cancelDialog();
        Snackbar.make(btnJson, "No data available", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnJson) {
            getProductData();
        } else if (v.getId() == R.id.btnAlert) {
            Intent i = new Intent("com.android.vending.INSTALL_REFERRER");
            //Set Package name
            i.setPackage("com.rb.elite");
            //referrer is a composition of the parameter of the campaing
            i.putExtra("referrer", "company=1");
            sendBroadcast(i);
        } else if (v.getId() == R.id.btnPopUp) {
            Intent intent = new Intent(HomeActivity.this, LocationActivity.class);
            startActivity(intent);

        } else if (v.getId() == R.id.btnLocation) {
            Intent intent = new Intent(HomeActivity.this, LocationDemo2.class);
            startActivity(intent);

        } else if (v.getId() == R.id.btnLocationMain) {
            Intent intent = new Intent(HomeActivity.this, LocationMainActivity.class);
            startActivity(intent);

        } else if (v.getId() == R.id.btnDashboard) {
            Intent intent = new Intent(HomeActivity.this, DashBoardActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnDashboardNew) {
            Intent intent = new Intent(HomeActivity.this, DasboardKotlinActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnDemo) {
            Intent intent = new Intent(HomeActivity.this, ActivityLifeCycleActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btnfaceBook) {
            Intent intent = new Intent(HomeActivity.this, FacebookActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.btnKotlin) {
            Intent intent = new Intent(HomeActivity.this, KotlinDemo.class);
            startActivity(intent);
        }
        //btnKotlin
        else if (v.getId() == R.id.btnWebView) {

            startActivity(new Intent(HomeActivity.this, CommonWebViewActivity.class)
                    .putExtra("URL", "http://qa.mgfm.in/images/rbasalesmaterial/testpage.html")
                    .putExtra("NAME", " Web View Demo")
                    .putExtra("TITLE", "Web View Demo"));
        }


        //
    }

    private void getAlert() {

        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Alert 3");
        alertDialog.setMessage("00:10");
        alertDialog.show();   //

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                alertDialog.setMessage("00:" + (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                //info.setVisibility(View.GONE);
                alertDialog.setMessage("Finish");
            }
        }.start();
    }


    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean viewMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {

                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    int lineEndIndex = tv.getLayout().getLineEnd(0);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    int lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                } else {
                    int lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    String text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                    tv.setText(text);
                    tv.setMovementMethod(LinkMovementMethod.getInstance());
                    tv.setText(
                            addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                    viewMore), TextView.BufferType.SPANNABLE);
                }
            }
        });

    }

    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {


            ssb.setSpan(new MySpannable(false) {
                @Override
                public void onClick(View widget) {
                    if (viewMore) {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, -1, "See Less", false);
                    } else {
                        tv.setLayoutParams(tv.getLayoutParams());
                        tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                        tv.invalidate();
                        makeTextViewResizable(tv, 3, ".. See More", true);
                    }
                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }


    @Override
    protected void onResume() {
        super.onResume();
        // region IN One Step method
        LocalBroadcastManager.getInstance(this).registerReceiver(
                new BroadcastReceiver() {

                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String receiver = intent.getStringExtra("RandomNumber");
                        txtReceiver.setText("" + receiver);
                    }
                }, new IntentFilter("BROADCAST_RANDOM_NUMBER"));
        //endregion


//        LocalBroadcastManager.getInstance(this).registerReceiver(
//                mHandleMessageReceiver, new IntentFilter("BROADCAST_RANDOM_NUMBER"));

    }


    @Override
    protected void onStart() {
        super.onStart();


//        IntentFilter intentFilter = new IntentFilter(
//                "BROADCAST_RANDOM_NUMBER");
//        mHandleMessageReceiver = new BroadcastReceiver() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//
//                String receiver = intent.getStringExtra("RandomNumber");
//                txtReceiver.setText("" + receiver);
//            }
//        };
//        LocalBroadcastManager.getInstance(this).registerReceiver(mHandleMessageReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                mHandleMessageReceiver);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();


    }

}
