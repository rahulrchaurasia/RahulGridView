package com.example.rahul.gridview.kotlin.FileUpload

import android.R.id
import android.app.Activity
import android.app.Dialog
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import android.provider.Contacts
import android.provider.MediaStore
import android.view.View
import android.widget.LinearLayout
import android.widget.RemoteViews
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.example.rahul.gridview.Activity.BaseActivity
import com.example.rahul.gridview.R
import com.example.rahul.gridview.Utility.Utility
import com.example.rahul.gridview.core.APIResponse
import com.example.rahul.gridview.core.IResponseSubcriber
import com.example.rahul.gridview.databinding.ActivityFileUploadBinding
import com.example.rahul.gridview.kotlin.AuthenticationController
import com.example.rahul.gridview.kotlin.getFileName
import com.example.rahul.gridview.kotlin.request.UploadRequestBody
import com.example.rahul.gridview.kotlin.response.DocumentResponse
import com.example.rahul.gridview.kotlin.snackbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


class FileUploadActivity : BaseActivity(), BaseActivity.CustomPopUpListener , UploadRequestBody.UploalCallback , IResponseSubcriber {

    lateinit var binding : ActivityFileUploadBinding
    val PERMISSION_CAMERA_STORACGE_CONSTANT = 103
    private val CAMERA_REQUEST = 1888
    private val SELECT_PICTURE = 1800


    var Docfile: File? = null
    private var imageUri: Uri? = null
    private val cropImageUri: Uri? = null
    var perms = arrayOf(
            "android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE",
            "android.permission.READ_EXTERNAL_STORAGE"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFileUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

        binding.includedFile.progressBar.progress = 0
        registerCustomPopUp(this)

        binding.includedFile.imgChooser.setOnClickListener {

            openImageChooser()
        }

        binding.includedFile.btnUpload.setOnClickListener {
            uploadImage()
        }
    }


    private fun uploadImage(){

        if(imageUri == null ){

            binding.includedFile.lyParent.snackbar("Select Image First!!")
            return
        }

        val parcelFileDescriptor = contentResolver.openFileDescriptor(imageUri!!, "r", null) ?: return
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        var file = File(cacheDir, contentResolver.getFileName(imageUri!!))

        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)

        binding.includedFile.progressBar.progress = 0
        var body = UploadRequestBody(file, "image", this)

       var  part : MultipartBody.Part = Utility.getMultipartImage(file, body)
       var hashmap = Utility.getBody(this, 14, 1)

        AuthenticationController(this).uploadDocuments(part, hashmap, this@FileUploadActivity)

        //region Commented (Working Condition)

        /*
        MyAPI().uploadDocument(
                part,
                "2".toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                "14".toRequestBody("multipart/form-data".toMediaTypeOrNull())
        ).enqueue(object : Callback<DocumentResponse>{
            override fun onResponse(call: Call<DocumentResponse>, response: Response<DocumentResponse>) {


                    binding.includedFile.progressBar.progress = 100
                    binding.includedFile.lyParent.snackbar("Succssfully Upload")


                    var path = response.body()?.Data?.get(0)?.path

                    binding.includedFile.imgResult.visibility = View.VISIBLE


                    Glide.with(this@FileUploadActivity)
                            .load(path)
                            .into(binding.includedFile.imgResult)

            }

            override fun onFailure(call: Call<DocumentResponse>, t: Throwable) {

                binding.includedFile.progressBar.progress = 0
            }


        })

        */

        //endregion

    }


    // region Permission
    private fun checkPermission(): Boolean {
        val camera = ActivityCompat.checkSelfPermission(applicationContext, perms.get(0))
        val WRITE_EXTERNAL = ActivityCompat.checkSelfPermission(applicationContext, perms.get(1))

        val READ_EXTERNAL = ActivityCompat.checkSelfPermission(applicationContext, perms.get(2))

        return if (VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            (camera == PackageManager.PERMISSION_GRANTED
                    && READ_EXTERNAL == PackageManager.PERMISSION_GRANTED)
        } else {
            camera == PackageManager.PERMISSION_GRANTED && WRITE_EXTERNAL == PackageManager.PERMISSION_GRANTED && READ_EXTERNAL == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun checkRationalePermission(): Boolean {
        val camera = ActivityCompat.shouldShowRequestPermissionRationale(this@FileUploadActivity, perms[0])
        val write_external = ActivityCompat.shouldShowRequestPermissionRationale(this@FileUploadActivity, perms[1])
        val read_external = ActivityCompat.shouldShowRequestPermissionRationale(this@FileUploadActivity, perms[2])

        return if (VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            camera || read_external
        } else {
            camera || write_external || read_external
        }
    }

    private fun checkRationale() {
        if (checkRationalePermission()) {
            //Show Information about why you need the permission
            Snackbar.make(binding.includedFile.lyParent, R.string.camera_access_required, Snackbar.LENGTH_INDEFINITE)
                    .setAction("Ok", View.OnClickListener { // Request the permission
                        requestPermission()
                    }).show()
        } else {
            openPopUp(binding.includedFile.lyParent, "Need  Permission", "This app needs all permissions.", "GRANT", "DENNY", false, true)
        }
    }

    private fun requestPermission() {

        //below android 11
        ActivityCompat.requestPermissions(this, perms, PERMISSION_CAMERA_STORACGE_CONSTANT)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_CAMERA_STORACGE_CONSTANT -> if (grantResults.size > 0) {

                //boolean writeExternal = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                val camera = grantResults[0] == PackageManager.PERMISSION_GRANTED
                val writeExternal = grantResults[1] == PackageManager.PERMISSION_GRANTED
                val readExternal = grantResults[2] == PackageManager.PERMISSION_GRANTED
                val minSdk29 = VERSION.SDK_INT >= Build.VERSION_CODES.Q

                if (camera && (writeExternal || minSdk29) && readExternal) {
                    showCamerGalleryPopUp()
                }
            }

        }
    }


    //endregion

    // region Camera and Gallery Opening
    private fun openImageChooser() {
        if (!checkPermission()) {
            checkRationale()
        } else {
            showCamerGalleryPopUp()

        }
    }

    private fun showCamerGalleryPopUp() {
        val builder = AlertDialog.Builder(this, R.style.CustomDialog)
        val lyCamera: LinearLayout
        val lyGallery: LinearLayout
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.layout_cam_gallery, null)
        builder.setView(dialogView)
        val alertDialog = builder.create()
        // set the custom dialog components - text, image and button
        lyCamera = dialogView.findViewById<View>(R.id.lyCamera) as LinearLayout
        lyGallery = dialogView.findViewById<View>(R.id.lyGallery) as LinearLayout
        lyCamera.setOnClickListener {
            launchCamera()
            alertDialog.dismiss()
        }
        lyGallery.setOnClickListener {
            openGallery()
            alertDialog.dismiss()
        }
        alertDialog.setCancelable(true)
        alertDialog.show()
        //  alertDialog.getWindow().setLayout(900, 600);

        // for user define height and width..
    }

    private fun launchCamera() {
        val FileName = "DemoDoc"

        Docfile = createImageFile(FileName)
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            imageUri = Uri.fromFile(Docfile)
        } else {
            imageUri = Docfile?.let {
                FileProvider.getUriForFile(this,
                        getString(R.string.file_provider_authority), it)
            }
        }

        imageUri.let {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                    imageUri)
            startActivityForResult(cameraIntent, CAMERA_REQUEST)
        }

    }

    private fun openGallery() {

        val mimeType = "image/*"
        val collection: Uri
        collection = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Video.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL
            )
        } else {
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        }
        try {
            val intent = Intent(Intent.ACTION_PICK, collection)
            intent.type = mimeType
            intent.resolveActivity(packageManager)
            startActivityForResult(intent, SELECT_PICTURE)
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(this, ex.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }

    //endregion

    // region Event
    override fun onPositiveButtonClick(dialog: Dialog?, view: View?) {
        dialog!!.cancel()
        openAppSetting()
    }

    override fun onCancelButtonClick(dialog: Dialog?, view: View?) {
        dialog!!.cancel()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


      if(resultCode == Activity.RESULT_OK){

          when(requestCode){
              CAMERA_REQUEST -> {

                  imageUri?.let {
                      binding.includedFile.imgChooser.setImageURI(imageUri)
                  }
              }
              SELECT_PICTURE -> {
                  imageUri = data?.data
                  imageUri?.let {
                      binding.includedFile.imgChooser.setImageURI(imageUri)
                  }
              }

          }
      }

    }

    override fun onProgressUpdate(percent: Int) {

        binding.includedFile.progressBar.progress = percent - 5

    }

    override  fun OnSuccess(response: APIResponse?, message: String?) {


        if(response is DocumentResponse){

            binding.includedFile.progressBar.progress = 100
            binding.includedFile.lyParent.snackbar("Succssfully Upload")


            var path = response.Data.get(0).path

            binding.includedFile.imgResult.visibility = View.VISIBLE


            Glide.with(this)
                    .load(path)
                    .into(binding.includedFile.imgResult)
        }
    }

    override fun OnFailure(t: Throwable?) {
        binding.includedFile.progressBar.progress = 0
    }

    //endregion


}