package com.example.rahul.gridview.service_download;




import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;


/**
 * Created by Rahul on 03/05/2019.
 */
public interface RetrofitInterface {

    @GET("files/Node-Android-Chat.zip")
    @Streaming
    Call<ResponseBody> downloadFile();
}
