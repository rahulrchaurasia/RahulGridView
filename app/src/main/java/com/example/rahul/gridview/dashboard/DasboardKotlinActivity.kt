package com.example.rahul.gridview.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.rahul.gridview.Activity.BaseActivity
import com.example.rahul.gridview.R
import com.example.rahul.gridview.Utility.Constant
import com.example.rahul.gridview.Utility.Utility.hideKeyBoard
import com.example.rahul.gridview.databinding.ActivityDasboardKotlinBinding
import com.example.rahul.gridview.kotlin.FileUpload.FileUploadActivity
import com.facebook.internal.Utility

class DasboardKotlinActivity : BaseActivity(), View.OnClickListener {


    lateinit var binding : ActivityDasboardKotlinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDasboardKotlinBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar).also {
            title = "Dashboard NEW"

        }
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


        setListner()

    }

    fun setListner(){

        binding.includedLayout.lyFileUpload.setOnClickListener(this)
    }

    override fun onClick(view: View?) {

        hideKeyBoard(binding.includedLayout.lyFileUpload,this)

        when(view?.id){

            R.id.lyFileUpload -> {

                val intent = Intent(this, FileUploadActivity ::class.java).apply {

                    intent.putExtra(Constant.SERVICE_TYPE,"FILE")
                }
                startActivity(intent)

            }
        }
    }
}