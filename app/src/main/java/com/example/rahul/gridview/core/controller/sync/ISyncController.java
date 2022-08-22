package com.example.rahul.gridview.core.controller.sync;

import com.example.rahul.gridview.core.IResponseSubcriber;

/**
 * Created by IN-RB on 21-02-2017.
 */

public interface ISyncController {


    void  getProduct(String brokerId, String code , String pgNo ,IResponseSubcriber iResponseSubcriber);
}
