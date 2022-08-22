package com.example.rahul.gridview.core.controller.sync;

import android.content.Context;


import com.example.rahul.gridview.Utility.Utility;
import com.example.rahul.gridview.core.IResponseSubcriber;
import com.example.rahul.gridview.core.requestbuilder.SyncRequestBuilder;
import com.example.rahul.gridview.core.response.ProductResponse;
import com.example.rahul.gridview.model.LeadEntity;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.internal.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by IN-RB on 21-02-2017.
 */

public class SyncController implements ISyncController {

    Context mContext;
    private  Realm realm;

    SyncRequestBuilder.SyncNetworkService syncNetworkService;

    public SyncController(Context context, Realm realm) {
        this.mContext = context;
        syncNetworkService = new SyncRequestBuilder().getService();
        this.realm = realm;
    }


    @Override
    public void getProduct(String brokerId, String code, String pgNo, final IResponseSubcriber iResponseSubcriber) {

        HashMap<String, String> bodyParameter = new HashMap<>();
        bodyParameter.put("brokerId", brokerId);
        bodyParameter.put("code", code);
        bodyParameter.put("pgNo", pgNo);

        syncNetworkService.getProduct(bodyParameter).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.body() != null) {

                    try {
                        iResponseSubcriber.OnSuccess(response.body(), response.body().getMessage());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                    }

                } else {
                    //failure
                    iResponseSubcriber.OnFailure(new RuntimeException("Enable to reach server, Try again later"));
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                if (t instanceof ConnectException) {
                    iResponseSubcriber.OnFailure(t);
                } else if (t instanceof SocketTimeoutException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof UnknownHostException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Check your internet connection"));
                } else if (t instanceof NumberFormatException) {
                    iResponseSubcriber.OnFailure(new RuntimeException("Unexpected server response"));
                } else {
                    iResponseSubcriber.OnFailure(new RuntimeException(t.getMessage()));
                }
            }
        });


//        syncNetworkService.getProduct(bodyParameter).enqueue(new Callback<ProductResponse>() {
//            @Override
//            public void onResponse(final Response<ProductResponse> response, Retrofit retrofit) {
//
//
//                if (response.isSuccess()) {
//                    if (response.body().getStatusId() == 0) {
//                        realm.executeTransaction(new Realm.Transaction() {
//                            @Override
//                            public void execute(Realm realm) {
//
//                                try {
//                                   realm.deleteAll();
//                                   realm.copyToRealmOrUpdate(response.body().getResult());
//
//
//                                } catch (IOException e) {
//                                    throw new RuntimeException(e);
//                                } finally {
//
//                                }
//                            }
//                        });
//
//                        try {
//                            iResponseSubcriber.OnSuccess(response.body(), response.message());
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                    } else {
//                        iResponseSubcriber.OnFailure(new RuntimeException(response.body().getMessage()));
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                t.printStackTrace();
//            }
//        });

    }
}
