package com.example.rahul.gridview.kotlin

import com.example.rahul.gridview.kotlin.response.DocumentResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * Created by Rajeev Ranjan on 07/09/2021.
 */
interface MyAPI {


    @Multipart
    @POST("/api/doc-upload")
    fun uploadDocument(@Part doc: MultipartBody.Part,
                       @Part("doctype") doctype: RequestBody,
                       @Part("userid") userid: RequestBody
    ): Call<DocumentResponse> //used

//
//    @Multipart
//    @POST("/api/doc-upload")
//    fun uploadDocument(@Part doc: MultipartBody.Part,
//                       @Part("doctype") doctype: RequestBody,
//                       @Part("userid") userid: RequestBody
//    ): Response<DocumentResponse>




    companion object {
        val url =  "http://elite.rupeeboss.com"
        operator fun invoke() :MyAPI {

            return Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MyAPI::class.java)

        }
    }

    
}