package com.example.rahul.gridview.Activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Environment;
import android.provider.MediaStore;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.example.rahul.gridview.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ShareData extends BaseActivity implements View.OnClickListener {
    ActionBar actionBar;
    Button btnShare, btnLoad,btnCapture;
    WebView webView;


    StringBuilder Htmlbody = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_data);
        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnShare = (Button) findViewById(R.id.btnShare);
        btnLoad = (Button) findViewById(R.id.btnLoad);
        btnCapture = (Button) findViewById(R.id.btnCapture);
        webView = (WebView) findViewById(R.id.webview);

        btnShare.setOnClickListener(this);
        btnLoad.setOnClickListener(this);
        btnCapture.setOnClickListener(this);

//        webView.loadUrl("http://developer.android.com/reference/packages.html");
////      webView.loadUrl("http://developer.android.com/training/basics/firstapp/creating-project.html");
//
//        webView.setWebViewClient(new WebViewClient() {
//
//
//            @Override
//            public void onPageCommitVisible(WebView view, String url) {
//                super.onPageCommitVisible(view, url);
//            }
//
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//
//
//            }
//
//
//
//            public void onPageFinished(WebView view, String url) {
//                // do your stuff here
////                webView.measure(View.MeasureSpec.makeMeasureSpec(
////                                View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED),
////                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
////                webView.layout(0, 0, webView.getMeasuredWidth(),
////                        webView.getMeasuredHeight());
////                webView.setDrawingCacheEnabled(true);
////                webView.buildDrawingCache();
//                Bitmap bm = Bitmap.createBitmap(webView.getMeasuredWidth(),
//                        webView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
//
//                Canvas bigcanvas = new Canvas(bm);
//                Paint paint = new Paint();
//                int iHeight = bm.getHeight();
//                bigcanvas.drawBitmap(bm, 0, iHeight, paint);
//                webView.draw(bigcanvas);
//                System.out.println("1111111111111111111111="
//                        + bigcanvas.getWidth());
//                System.out.println("22222222222222222222222="
//                        + bigcanvas.getHeight());
//
//                if (bm != null) {
//                    try {
//                        String path = Environment.getExternalStorageDirectory().toString();
//                        OutputStream fOut = null;
//                        File file = new File(path, "/aaaa.png");
//                        fOut = new FileOutputStream(file);
//
//                        bm.compress(Bitmap.CompressFormat.PNG, 50, fOut);
//                        fOut.flush();
//                        fOut.close();
//                        bm.recycle();
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
    }


    private void sendingData() {
        PackageManager pm = getPackageManager();
        try {

            Intent waIntent = new Intent(Intent.ACTION_SEND);
            waIntent.setType("text/plain");
            String text = "YOUR TEXT HERE";

            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp");

            waIntent.putExtra(Intent.EXTRA_TEXT, text);
            startActivity(Intent.createChooser(waIntent, "Share with"));

        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                    .show();
        }
    }


    private void MessageViaWebView() {

        try {
            //  webView = new WebView(this);


            WebSettings settings = webView.getSettings();

            settings.setBuiltInZoomControls(true);
            settings.setUseWideViewPort(false);
            settings.setJavaScriptEnabled(true);
            settings.setSupportMultipleWindows(false);

            settings.setLoadsImagesAutomatically(true);
            settings.setLightTouchEnabled(true);
            settings.setDomStorageEnabled(true);
            settings.setLoadWithOverviewMode(true);
            settings.setJavaScriptEnabled(true);


            webView.loadUrl("http://erp.rupeeboss.com/home.aspx?did=34ff618f19bfe0c3");

            webView.setWebViewClient(new WebViewClient() {

                                         @Override
                                         public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                             super.onPageStarted(view, url, favicon);
                                             showProgressDialog();
                                         }

                                         @Override
                                         public void onPageFinished(WebView view, String url) {
                                             super.onPageFinished(view, url);
                                             dismissDialog();
                                         }

                                         @Override
                                         public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                             super.onReceivedError(view, errorCode, description, failingUrl);
                                             dismissDialog();
                                         }
                                     }


            );


        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    private void shareResultAsImage() {

        try {
            Bitmap bitmap = getBitmapOfWebView();
            String pathofBmp = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "data", null);
            Uri bmpUri = Uri.parse(pathofBmp);
            final Intent emailIntent1 = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent1.setType("image/png");
            emailIntent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            emailIntent1.putExtra(Intent.EXTRA_STREAM, bmpUri);

            startActivity(emailIntent1);
        } catch (Exception ex) {
            ex.printStackTrace();

        }

    }

    private Bitmap getBitmapOfWebView() {
        Picture picture = webView.capturePicture();
        Bitmap bitmap = Bitmap.createBitmap(picture.getWidth(), picture.getHeight(), Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(Color.WHITE);
        picture.draw(canvas);
        return bitmap;
    }


    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.btnLoad) {
            MessageViaWebView();
           getWebView();
        } else if (v.getId() == R.id.btnShare) {
            MessageViaWebView();
            shareResultAsImage();
        }
        else if (v.getId() == R.id.btnCapture) {
            getScreenShot(v);
            store( getScreenShot(v),"ImageWeb.png");
            File file = new File( Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots/ImageWeb.png");
            if(file.exists()) {
                shareImage(file);
            }

        }
//btnCapture
    }


    private void getWebView() {

        webView.loadUrl("http://developer.android.com/reference/packages.html");
//      webView.loadUrl("http://developer.android.com/training/basics/firstapp/creating-project.html");

        webView.setWebViewClient(new WebViewClient() {

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                webView.measure(View.MeasureSpec.makeMeasureSpec(
                        View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                webView.layout(0, 0, webView.getMeasuredWidth(),
                        webView.getMeasuredHeight());
                webView.setDrawingCacheEnabled(true);
                webView.buildDrawingCache();
                Bitmap bm = Bitmap.createBitmap(webView.getMeasuredWidth(),
                        webView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

                Canvas bigcanvas = new Canvas(bm);
                Paint paint = new Paint();
                int iHeight = bm.getHeight();
                bigcanvas.drawBitmap(bm, 0, iHeight, paint);
                webView.draw(bigcanvas);
                System.out.println("1111111111111111111111="
                        + bigcanvas.getWidth());
                System.out.println("22222222222222222222222="
                        + bigcanvas.getHeight());

                if (bm != null) {
                    try {
                        String path = Environment.getExternalStorageDirectory()
                                .toString();
                        OutputStream fOut = null;
                        File file = new File(path, "/aaaa.png");
                        fOut = new FileOutputStream(file);

                        bm.compress(Bitmap.CompressFormat.PNG, 50, fOut);
                        fOut.flush();
                        fOut.close();
                        bm.recycle();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    //////////////////////////////Get ScrrenShot and Capture Image ///////////

    public static Bitmap getScreenShot(View view) {
        View screenView = view.getRootView();
        screenView.setDrawingCacheEnabled(true);
        Bitmap bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public static void store(Bitmap bm, String fileName){
        final  String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Screenshots";
        File dir = new File(dirPath);
        if(!dir.exists())
            dir.mkdirs();
        File file = new File(dirPath, fileName);
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shareImage(File file){
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");

        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No App Available", Toast.LENGTH_SHORT).show();
        }
    }

}
