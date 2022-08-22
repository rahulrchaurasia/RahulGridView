package com.example.rahul.gridview.AsyncTaskDemo;

public interface TaskListener {
        void onTaskStarted();

        void onTaskFinished(String result);
    }