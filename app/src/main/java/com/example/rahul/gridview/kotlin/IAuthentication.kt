package com.example.rahul.gridview.kotlin

import com.example.rahul.gridview.core.IResponseSubcriber
import okhttp3.MultipartBody

import java.util.*


interface IAuthentication {


    fun getUserEligibility(MobileNo: String, RegistrationNo: String, iResponseSubcriber: IResponseSubcriber)

    fun uploadDocuments(document: MultipartBody.Part, body: HashMap<String, Int>, iResponseSubcriber: IResponseSubcriber?)
}