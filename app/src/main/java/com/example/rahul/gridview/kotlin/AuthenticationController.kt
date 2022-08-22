package com.example.rahul.gridview.kotlin

import android.content.Context
import android.util.Log
import com.example.rahul.gridview.core.IResponseSubcriber
import com.example.rahul.gridview.kotlin.response.DocumentResponse
//import com.example.rahul.gridview.kotlin.response.DocumentResponse
import com.google.gson.Gson
import kotlinx.coroutines.*
import okhttp3.Dispatcher
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.*
import java.net.UnknownHostException
import java.util.*

/**
 * Created by Rahul on 31/08/2021.
 */
open class AuthenticationController(mCtx: Context) : IAuthentication {

//    var MESSAGE = "Unable to connect to server, Please try again"
//    var ERROR = "Server not responded, Try again later"

    var authenticationBuilder : AuthenticationBuilder.AuthenticationNetworkService
    init {

        authenticationBuilder = AuthenticationBuilder().getService()
    }

   // internal fun printLog(log: String) = Log.d("---log", log)
    override fun getUserEligibility(MobileNo: String, RegistrationNo: String, iResponseSubcriber: IResponseSubcriber) {
        TODO("Not yet implemented")
    }

    override fun uploadDocuments(document: MultipartBody.Part, body: HashMap<String, Int>, iResponseSubcriber: IResponseSubcriber?) {


        var docType =  "2".toRequestBody("multipart/form-data".toMediaTypeOrNull())
        var userid =  "14".toRequestBody("multipart/form-data".toMediaTypeOrNull())

        //region comment
//        authenticationBuilder.uploadDocument(document, docType ,userid).enqueue(object :
//                retrofit2.Callback<DocumentResponse> {
//            override fun onResponse(
//                    call: Call<DocumentResponse>,
//                    response: Response<DocumentResponse>
//            ) {
//
//                if (response!!.isSuccessful) {
//
//                   // printLog(Gson().toJson(response.body()))
//
//                    if (response.body() != null) {
//                        if (response.body()!!.status_code == 0) {
//
//                            iResponseSubcriber?.OnSuccess(response.body()!!, response.message())
//                        } else {
//                            iResponseSubcriber?.OnSuccess(response.body()!!, response.message())
//                        }
//                    } else {
//
//                        iResponseSubcriber?.OnFailure(RuntimeException("Server is Down"))
//                    }
//                } else {
//                    iResponseSubcriber?.OnFailure(RuntimeException(response.code().toString()))
//                }
//
//            }
//
//            override fun onFailure(call: Call<DocumentResponse>, t: Throwable) {
//                if (t is UnknownHostException) {
//
//                    iResponseSubcriber?.OnFailure(RuntimeException("Check your internet connection"))
//
//                } else {
//                    iResponseSubcriber?.OnFailure(RuntimeException(t.message.toString()))
//                }
//            }
//        })

        //endregion


        // region Added

        MyAPI().uploadDocument(
                document,
                "2".toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                "14".toRequestBody("multipart/form-data".toMediaTypeOrNull())
        ).enqueue(object : Callback<DocumentResponse> {
            override fun onResponse(call: Call<DocumentResponse>, response: Response<DocumentResponse>) {

                if (response!!.isSuccessful) {

                   // printLog(Gson().toJson(response.body()))

                    if (response.body() != null) {
                        if (response.body()!!.status_code == 0) {


                                iResponseSubcriber?.OnSuccess(response.body()!!, response.message())



                        } else {
                            iResponseSubcriber?.OnSuccess(response.body()!!, response.message())
                        }
                    } else {

                        iResponseSubcriber?.OnFailure(RuntimeException("Server is Down"))
                    }
                } else {
                    iResponseSubcriber?.OnFailure(RuntimeException(response.code().toString()))
                }

            }

            override fun onFailure(call: Call<DocumentResponse>, t: Throwable) {

                if (t is UnknownHostException) {

                    iResponseSubcriber?.OnFailure(RuntimeException("Check your internet connection"))

                } else {
                    iResponseSubcriber?.OnFailure(RuntimeException(t.message.toString()))
                }
            }


        })

       // endregion

        // region couroutine

//        CoroutineScope(Dispatchers.IO).launch {
//
//            val response =    MyAPI().uploadDocument(
//                        document,
//                       "2".toRequestBody("multipart/form-data".toMediaTypeOrNull()),
//                      "14".toRequestBody("multipart/form-data".toMediaTypeOrNull())
//               ).awaitResponse()
//
//            withContext(Dispatchers.Main){
//
//                try {
//
//                    if(response.isSuccessful){
//
//                        if (response.body() != null) {
//
//                            if (response.body()!!.status_code == 0) {
//
//                                iResponseSubcriber?.OnSuccess(response.body()!!, response.message())
//                            } else {
//                                iResponseSubcriber?.OnFailure(RuntimeException( response.message()))
//                            }
//                        }
//                    }
//                }
//                catch (e: HttpException) {
//
//                    iResponseSubcriber?.OnFailure(RuntimeException( e.message))
//                }
//                catch (e: Throwable) {
//
//                    iResponseSubcriber?.OnFailure(RuntimeException( "Ooops: Something else went wrong"))
//                }
//            }
//
//        }

        //endregion

    }
}