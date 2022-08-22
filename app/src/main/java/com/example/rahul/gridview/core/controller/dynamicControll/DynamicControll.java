package com.example.rahul.gridview.core.controller.dynamicControll;

import android.content.Context;

import com.example.rahul.gridview.core.IResponseSubcriber;
import com.example.rahul.gridview.core.requestbuilder.DynamicRequestBuilder;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rahul on 04/05/2019.
 */
public class DynamicControll implements IDynamic {


    DynamicRequestBuilder.DynamicNetworkService dynamicNetworkService;
    Context mContext;

    public DynamicControll(Context mContext) {
        this.mContext = mContext;
        dynamicNetworkService = new DynamicRequestBuilder().getService();
    }


    @Override
    public void downloadFile() {
        String BaseURL = "https://download.learn2crack.com/";

        String url = BaseURL + "/api/save-moter-lead-details";
        dynamicNetworkService.downloadFile(url).enqueue(new Callback<ResponseBody>() {


            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {


                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
