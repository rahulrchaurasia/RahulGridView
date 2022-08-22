package com.example.rahul.gridview.core;

/**
 * Created by IN-RB on 21-02-2017.
 */

public interface IResponseSubcriber {

    void OnSuccess(APIResponse response, String message) throws InterruptedException;

    void OnFailure(Throwable t);
}
