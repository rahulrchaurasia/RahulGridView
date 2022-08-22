package com.example.rahul.gridview.kotlin

import android.content.ContentResolver
import android.net.Uri
import android.provider.OpenableColumns
import android.view.View
import androidx.customview.widget.Openable
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Rahul on 03/09/2021.
 */

fun View.snackbar(mesage : String) {
        Snackbar.make(
                this,
                mesage,
                Snackbar.LENGTH_LONG
        ).also { snackbar ->
            snackbar.setAction("Ok"){

                snackbar.dismiss()
            }
        }.show()
}

fun ContentResolver.getFileName(uri : Uri) : String{

    var name = "";
    val cursor = query(uri,null,null,null,null)
    cursor?.use {
        it.moveToFirst()
        name = cursor.getString(it.getColumnIndex(OpenableColumns.DISPLAY_NAME))
    }
    return name
}