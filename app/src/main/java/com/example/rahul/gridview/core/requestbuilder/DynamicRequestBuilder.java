package com.example.rahul.gridview.core.requestbuilder;

import com.example.rahul.gridview.core.RetroRequestBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by Rahul on 04/05/2019.
 */
public class DynamicRequestBuilder extends RetroRequestBuilder {

    public DynamicNetworkService getService()
    {
        return super.build().create(DynamicNetworkService.class);
    }


    public interface DynamicNetworkService {


        @GET()
        @Streaming
        Call<ResponseBody> downloadFile(@Url String strUrl);
    }
}


