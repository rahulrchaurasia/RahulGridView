package com.example.rahul.gridview.kotlin


//import com.example.rahul.gridview.kotlin.response.DocumentResponse
import com.example.rahul.gridview.kotlin.response.DocumentResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call

import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap


open class AuthenticationBuilder : RetroRequestBuilder() {

    fun getService(): AuthenticationNetworkService {
        return super.build().create(AuthenticationNetworkService::class.java)
    }

    interface AuthenticationNetworkService {




//        @POST()
//        fun getUploadFile(@Url url: String, @Body body: String) : Call<GlobalAssureLandmarkResponse>

//        @Multipart
//        @POST("/api/doc-upload")
//        fun uploadDocument(@Part doc: MultipartBody.Part, @PartMap partMap: Map<String, Int>): Call<DocumentResponse> //used
//

        @Multipart
        @POST("/api/doc-upload")
        fun uploadDocument
                (@Part doc: MultipartBody.Part,
                 @Part("doctype") doctype: RequestBody,
                 @Part("userid") userid: RequestBody
                ): Call<DocumentResponse> //used


    }
}