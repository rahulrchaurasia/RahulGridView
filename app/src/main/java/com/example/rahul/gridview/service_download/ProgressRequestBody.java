package com.example.rahul.gridview.service_download;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * Created by Rahul on 04/05/2019.
 */
public class ProgressRequestBody  extends RequestBody {
    @Override
    public MediaType contentType() {
        return null;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {

    }
}
