package com.example.rahul.gridview.javaScript;

import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.ParcelFileDescriptor;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.MimeTypeMap;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.rahul.gridview.Activity.BaseActivity;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.Utility.CircleTransform;
import com.example.rahul.gridview.Utility.PrefManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;



public class CommonWebViewActivity extends BaseActivity implements View.OnClickListener {

    WebView webView;
    ImageView imglogo;
    TextView txtTitle;
    String url = "";
    String name = "";
    String title = "";
    CountDownTimer countDownTimer;
    public static boolean isActive = false;
    File file;
    PrefManager prefManager;

    String[] perms = {
            "android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE"

    };
    public static final int PERMISSION_CAMERA_STORACGE_CONSTANT = 103;
    private static final int CAMERA_REQUEST = 1888;
    private static final int SELECT_PICTURE = 1800;

    File Docfile;
    private Uri imageUri;
    private Uri cropImageUri;
   static String ProfileName = "profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        url = getIntent().getStringExtra("URL");
        name = getIntent().getStringExtra("NAME");
        title = getIntent().getStringExtra("TITLE");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setTitle(title);


        getSupportActionBar().setTitle(title);
        prefManager =new PrefManager(this);

        webView = (WebView) findViewById(R.id.webView);
        imglogo = (ImageView) findViewById(R.id.imglogo);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        FloatingActionButton fab = findViewById(R.id.fab);

        imglogo.setOnClickListener(this);

        txtTitle.setText(title);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (isNetworkConnected()) {
            settingWebview();
            startCountDownTimer();
        } else {
            Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();
        }

        if(prefManager.getProfilePath() !=""){

            //Toast.makeText(this,"file"+ file1.getName(),Toast.LENGTH_SHORT).show();
            Glide.with(this)
                    .asBitmap()
                    .load(prefManager.getProfilePath())
                    .transform(new CircleTransform(this)) // applying the image transformer
                    .into(imglogo);
        }

    }


    private void startCountDownTimer() {
        countDownTimer = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                try {
                    cancelDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        countDownTimer.start();
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    private void downloadPdf(String url, String name) {
        Toast.makeText(this, "Download started..", Toast.LENGTH_LONG).show();
        DownloadManager.Request r = new DownloadManager.Request(Uri.parse(url));
        r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name + ".pdf");
        r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        r.setMimeType(MimeTypeMap.getFileExtensionFromUrl(url));
        DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        dm.enqueue(r);
    }

    private void settingWebview() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setBuiltInZoomControls(true);
        settings.setUseWideViewPort(false);
        settings.setJavaScriptEnabled(true);
        settings.setSupportMultipleWindows(false);

        settings.setLoadsImagesAutomatically(true);
        settings.setLightTouchEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setJavaScriptEnabled(true);


      /*  MyWebViewClient webViewClient = new MyWebViewClient(this);
        webView.setWebViewClient(webViewClient);*/
        webView.setWebViewClient(new WebViewClient() {


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO show you progress image
                if (isActive)
                    showDialog();
                // new ProgressAsync().execute();
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO hide your progress image
                cancelDialog();
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.endsWith(".pdf")) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(url), "application/pdf");
                    try {
                        startActivity(intent);
                    } catch (ActivityNotFoundException e) {
                        //user does not have a pdf viewer installed
                        String googleDocs = "https://docs.google.com/viewer?url=";
                        webView.loadUrl(googleDocs + url);
                    }
                }
                /*qacamp@gmail.com/01011980
                download policy QA user
                878769 crn
                */
                return false;
            }
        });
        webView.getSettings().setBuiltInZoomControls(true);
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");
        Log.d("URL", url);

        if (url.endsWith(".pdf")) {

            webView.loadUrl("https://docs.google.com/viewer?url=" + url);
            //webView.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + url);
        } else {
            webView.loadUrl(url);
        }
        //webView.loadUrl(url);
    }


    @Override
    protected void onResume() {
        super.onResume();
        isActive = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isActive = false;
        if (countDownTimer != null)
            countDownTimer.cancel();
    }


    private void galleryCamPopUp() {

        if (!checkPermission()) {

            if (checkRationalePermission()) {
                //Show Information about why you need the permission
                requestPermission();

            } else {
                //Previously Permission Request was cancelled with 'Dont Ask Again',
                // Redirect to Settings after showing Information about why you need the permission
                //  permissionAlert(navigationView,"Need Call Permission","This app needs Call permission.");
                openPopUp(webView, "Need  Permission", "This app needs all permissions.", "GRANT", "DENNY", false, true);


            }
        } else {

            showCamerGalleryPopUp();
        }
    }


    private void showCamerGalleryPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialog);

        LinearLayout lyCamera, lyGallery;
        LayoutInflater inflater = this.getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.layout_cam_gallery, null);

        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();
        // set the custom dialog components - text, image and button
        lyCamera = (LinearLayout) dialogView.findViewById(R.id.lyCamera);
        lyGallery = (LinearLayout) dialogView.findViewById(R.id.lyGallery);

        lyCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCamera();
                alertDialog.dismiss();

            }
        });

        lyGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
                alertDialog.dismiss();

            }
        });
        alertDialog.setCancelable(true);
        alertDialog.show();
        //  alertDialog.getWindow().setLayout(900, 600);

        // for user define height and width..
    }

    private void launchCamera() {




//
        Docfile = createFile(ProfileName);
        imageUri = FileProvider.getUriForFile(this,
                getString(R.string.file_provider_authority), Docfile);


        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                imageUri);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }


    private void openGallery() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMultiTouchEnabled(true)
                .start(this);
    }


    @Override
    @SuppressLint("NewApi")
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Below For Cropping The Camera Image
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            //extractTextFromImage();
            startCropImageActivity(imageUri);
        }
        // Below For Cropping The Gallery Image
        else if (requestCode == SELECT_PICTURE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            startCropImageActivity(selectedImageUri);
        }

        // Below  handle result of CropImageActivity

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                try {
                    cropImageUri = result.getUri();
                    Bitmap mphoto = null;

                    new MyAsyncTask(cropImageUri,this).execute();
//                    try {
//                        mphoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), cropImageUri);
//
//
//
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }


                } catch (Exception e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }


    }


    // region permission
    private boolean checkPermission() {

        int camera = ActivityCompat.checkSelfPermission(getApplicationContext(), perms[0]);

        int WRITE_EXTERNAL = ActivityCompat.checkSelfPermission(getApplicationContext(), perms[1]);
        int READ_EXTERNAL = ActivityCompat.checkSelfPermission(getApplicationContext(), perms[2]);

        return camera == PackageManager.PERMISSION_GRANTED
                && WRITE_EXTERNAL == PackageManager.PERMISSION_GRANTED
                && READ_EXTERNAL == PackageManager.PERMISSION_GRANTED;
    }

    private boolean checkRationalePermission() {

        boolean camera = ActivityCompat.shouldShowRequestPermissionRationale(CommonWebViewActivity.this, perms[0]);

        boolean write_external = ActivityCompat.shouldShowRequestPermissionRationale(CommonWebViewActivity.this, perms[1]);
        boolean read_external = ActivityCompat.shouldShowRequestPermissionRationale(CommonWebViewActivity.this, perms[2]);

        return camera || write_external || read_external;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, perms, PERMISSION_CAMERA_STORACGE_CONSTANT);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {
            case PERMISSION_CAMERA_STORACGE_CONSTANT:
                if (grantResults.length > 0) {

                    //boolean writeExternal = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                    boolean camera = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeExternal = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean readExternal = grantResults[2] == PackageManager.PERMISSION_GRANTED;

                    if (camera && writeExternal && readExternal) {

                        showCamerGalleryPopUp();

                    }

                }
                break;


        }
    }

    //endregion


    private Bitmap uriToBitmap(Uri selectedFileUri) {
        try {
            ParcelFileDescriptor parcelFileDescriptor =
                    getContentResolver().openFileDescriptor(selectedFileUri, "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);


            parcelFileDescriptor.close();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.imglogo){

            if(prefManager.getProfilePath() !=""){

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Intent shareIntent = new Intent(CommonWebViewActivity.this, ViewImageActivity.class);
                    shareIntent.putExtra("PATH",prefManager.getProfilePath());
                    Pair[] pairs = new Pair[1];
                    pairs[0] = new Pair<View, String>(imglogo, "profileTransition");
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(CommonWebViewActivity.this, pairs);
                    startActivity(shareIntent, options.toBundle());
                } else {
                    startActivity(new Intent(CommonWebViewActivity.this, ViewImageActivity.class));
                }

            }

        }
    }


    class MyAsyncTask extends AsyncTask<Void, Void, Bitmap> {
        Uri ImageUri;
        Bitmap bitmap;
        Context mcontext;

        public MyAsyncTask(Uri imageUri ,Context context) {
            ImageUri = imageUri;
            mcontext = context;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {

                bitmap = uriToBitmap(ImageUri);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap ;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {


            Glide.with(mcontext)
                    .asBitmap()
                    .load(bitmap)
                    .transform(new CircleTransform(mcontext)) // applying the image transformer
                    .into(imglogo);



            deleteAll();
            file =   saveImageToStorage(bitmap, ProfileName);
            if (file.exists()){

              //  Toast.makeText(mcontext,""+ file.getPath(),Toast.LENGTH_LONG ).show();
                prefManager.setProfilePathe(file.getPath());
            }


        }


    }

    class MyJavaScriptInterface {


        @JavascriptInterface
        public void showcamera() { //Android.AddNewMotorQuote();

            //Toast.makeText(CommonWebViewActivity.this, "Camera Clicked",Toast.LENGTH_SHORT).show();

            galleryCamPopUp();
        }


        @JavascriptInterface
        public void showToast(String strDetail) {

            datashareList(CommonWebViewActivity.this, null, "Finmart", strDetail);
        }

    }


}
