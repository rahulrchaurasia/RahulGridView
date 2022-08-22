package com.example.rahul.gridview.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.Utility.PrefManager;
import com.example.rahul.gridview.Utility.Utility;


import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

import okhttp3.MultipartBody;


public class ProfileActivity extends BaseActivity implements View.OnClickListener ,BaseActivity.CustomPopUpListener{

    private static final int CAMERA_REQUEST = 1888;
    private static final int SELECT_PICTURE = 1800;
    private int PICK_PDF_REQUEST = 1805;

    File Docfile;
    File file;
    Uri imageUri;
    private Uri cropImageUri;    // for Crop
    Uri selectedImageUri;      // for File and Gallery
    InputStream inputStream;
    ExifInterface ei;
    Bitmap bitmapPhoto = null;
    private String PHOTO_File = "EliteDoc";
    MultipartBody.Part part;
    HashMap<String, Integer> body;
    private int PROFILE = 1;
    int RequestID = 0;
    String DOC_PATH = "";


    ///////////


    PrefManager prefManager;

    Button btnSubmit;
    EditText et_Status;
    ImageView ivUser, ivCross, ivProfile;
    String ORDER_ID = "";

    String[] perms = {
            "android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE"

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        prefManager = new PrefManager(this);

        setOnClickListener();

        if (getIntent().getStringExtra("ORDER_ID") != null) {
            ORDER_ID = getIntent().getStringExtra("ORDER_ID");
        } else {

            Toast.makeText(this, "Something went wrong on Server Side", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    private void initilize() {


        btnSubmit = findViewById(R.id.btn_submit);
        ivProfile = findViewById(R.id.ivProfile);
        ivUser = findViewById(R.id.ivUser);
        ivCross = findViewById(R.id.ivCross);
        et_Status = findViewById(R.id.et_Status);

    }

    private void setOnClickListener() {
        btnSubmit.setOnClickListener(this);
        ivProfile.setOnClickListener(this);
        ivCross.setOnClickListener(this);
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
                openPopUp(ivUser, "Need  Permission", "This app needs all permissions.", "GRANT", "", false, true);


            }
        } else {

            showCamerGalleryPopUp();
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

        boolean camera = ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this, perms[0]);

        boolean write_external = ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this, perms[1]);
        boolean read_external = ActivityCompat.shouldShowRequestPermissionRationale(ProfileActivity.this, perms[2]);

        return camera || write_external || read_external;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, perms, Utility.PERMISSION_CAMERA_STORACGE_CONSTANT);
    }

    private void setDocumentUpload() {

//        DOC_PATH = docUploadEntity.getPath();
//
//        if (docUploadEntity.getType().toLowerCase().equals("pdf")) {
//
//            ivUser.setBackground(ContextCompat.getDrawable(UploadPopUpActivity.this, R.drawable.pdf_icon_red_bg));
//
//        } else {
//            Glide.with(UploadPopUpActivity.this)
//                    .load(docUploadEntity.getPath())
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .placeholder(R.drawable.circle_placeholder)
//                    .skipMemoryCache(true)
//                    .override(120, 120)
//                    .transform(new CircleTransform(UploadPopUpActivity.this)) // applying the image transformer
//                    .into(ivUser);
//        }


    }



    @Override
    public void onClick(View view) {


    }

    @Override
    public void onPositiveButtonClick(Dialog dialog, View view) {

        dialog.cancel();
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, Utility.REQUEST_PERMISSION_SETTING);
    }

    @Override
    public void onCancelButtonClick(Dialog dialog, View view) {

        dialog.cancel();
    }
    //endregion


    private void showCamerGalleryPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.CustomDialog);

        LinearLayout lyCamera, lyGallery, lyPdf;
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


        String FileName = "PHOTO_File";


        Docfile = createFile(FileName);

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            imageUri = Uri.fromFile(Docfile);
        } else {
            imageUri = FileProvider.getUriForFile(ProfileActivity.this,
                    getString(R.string.file_provider_authority), Docfile);
        }


        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT,
                imageUri);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }


    private void openGallery() {

        String FileName = "PHOTO_File";


        Docfile = createFile(FileName);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }


    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }






}
