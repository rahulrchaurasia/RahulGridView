package com.example.rahul.gridview.Utility;

import android.os.AsyncTask;
import android.util.Log;

public class DownloadTask extends AsyncTask<Integer, Integer, Integer> {

    String MYTASK = "mytask";
    int iValue = 0;
    ICallback iCallback;

    public DownloadTask(ICallback iCallback) {
        this.iCallback = iCallback;
    }

    @Override
    protected void onPreExecute() {

    }

    @Override
    protected Integer doInBackground(Integer... iy) {
        // Get the number of task
        int count = 10;
        // Initialize a new list

        // Loop through the task
        for (int i = 0; i < count; i++) {
            // Do the current task task here
            //Integer currentTask = tasks[i];
            iValue = iValue + i;

            // Sleep the UI thread for 1 second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Publish the async task progress
            // Added 1, because index start from 0
            publishProgress((int) (((i + 1) / (float) count) * 100));

            // If the AsyncTask cancelled
            if (isCancelled()) {
                break;
            }
        }
        // Return the task list for post execute
        return iValue;
    }

    @Override
    protected void onCancelled() {

        Log.d(MYTASK, "Operation is cancel...");
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        Log.d(MYTASK, "Operation is onProgressUpdate..." + progress[0] + "%");
    }

    @Override
    protected void onPostExecute(Integer result) {
        iCallback.getCount(result);
        Log.d(MYTASK, "Operation Done..." + result + "%");
    }
}
