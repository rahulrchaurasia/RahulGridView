package com.example.rahul.gridview.core.requestbuilder;



import com.example.rahul.gridview.core.RetroRequestBuilder;
import com.example.rahul.gridview.core.response.ProductResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/**
 * Created by IN-RB on 21-02-2017.
 */

public class SyncRequestBuilder extends RetroRequestBuilder {

    public  SyncNetworkService getService(){
        return super.build().create(SyncNetworkService.class);
    }

    public interface SyncNetworkService{


        @POST("/LoginDtls.svc/XMLService/dsplyBrokerLeads")
        Call<ProductResponse> getProduct(@Body HashMap<String,String> bodyParameter);

        @POST("/LoginDtls.svc/XMLService/dsplyEmpLeadData")
        Call<ProductResponse> geteadDtls(@Body HashMap<String,String> bodyParameter);

    }
}
