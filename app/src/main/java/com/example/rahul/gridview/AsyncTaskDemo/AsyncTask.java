package com.example.rahul.gridview.AsyncTaskDemo;

import android.util.Log;

/**
 * Created by Rahul on 17/12/2019.
 */
public class AsyncTask extends android.os.AsyncTask<String,Integer,String> {


    private final TaskListener taskListener;

    public AsyncTask(TaskListener taskListener) {
        this.taskListener = taskListener;
    }

    @Override
    protected void onPreExecute() {
       taskListener.onTaskStarted();
    }

    @Override
    protected String doInBackground(String... strings) {
        for (int i = 1; i <= 10; i++) {
            Log.d("mytask", "AsyncTask is working: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "All Done!";
    }

    @Override
    protected void onPostExecute(String result) {

        taskListener.onTaskFinished(result);
    }
}