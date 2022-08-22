package com.example.rahul.gridview.AsyncTaskDemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.rahul.gridview.MainActivity;
import com.example.rahul.gridview.R;
import com.example.rahul.gridview.Utility.DownloadTask;
import com.example.rahul.gridview.Utility.ICallback;
import com.example.rahul.gridview.custom_switch.SwitchActivity;

import java.util.ArrayList;
import java.util.List;

public class AsyncTaskDemoActivity extends AppCompatActivity {

    private Context mContext;
    private Activity mActivity;

    private Button mButtonDo;
    private Button mButtonCancel,btn_Next;
    private TextView mTextView;

    private AsyncTask mMyTask;

    String MYTASK = "mytask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task_demo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mContext = getApplicationContext();
        mActivity = AsyncTaskDemoActivity.this;

        // Get the widget reference from XML layout

        mButtonDo = (Button) findViewById(R.id.btn_do);
        mButtonCancel = (Button) findViewById(R.id.btn_cancel);
        btn_Next =  (Button) findViewById(R.id.btn_Next);
        mTextView = (TextView) findViewById(R.id.tv);

        btn_Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Empty the TextView
                 finish();
//                mMyTask.cancel(true);
//                startActivity(new Intent(AsyncTaskDemoActivity.this, SwitchActivity.class));

            }
        });

        mButtonDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Empty the TextView
                mTextView.setText("");
                // Execute the async task
//                mMyTask = new com.example.rahul.gridview.Utility.DownloadTask(new ICallback() {
//                    @Override
//                    public void getCount(int i) {
//                        mTextView.setText(""+i);
//                    }
//                }).execute(12);


                mMyTask = new AsyncTaskDemoActivity.DownloadTask()
                        .execute("Task 1","Task 2","Task 3","Task 4", "Task 5",
                                "Task 6","Task 7","Task 8","Task 9", "Task 10",
                                "Task 11","Task 12","Task 13","Task 14", "Task 15",
                                "Task 16","Task 17","Task 18","Task 19", "Task 20",
                                "Task 21","Task 22","Task 23","Task 24", "Task 25",
                                "Task 6","Task 7","Task 8","Task 9", "Task 10",
                                "Task 11","Task 12","Task 13","Task 14", "Task 15",
                                "Task 16","Task 17","Task 18","Task 19", "Task 20");


            }
        });


        mButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                    cancel
                        boolean cancel (boolean mayInterruptIfRunning)
                            Attempts to cancel execution of this task. This attempt will fail if
                            the task has already completed, already been cancelled, or could not be
                            cancelled for some other reason. If successful, and this task has not
                            started when cancel is called, this task should never run. If the task
                            has already started, then the mayInterruptIfRunning parameter determines
                            whether the thread executing this task should be interrupted in an
                            attempt to stop the task.
                */
                mMyTask.cancel(true);
            }
        });

    }

    //

    private class DownloadTask extends AsyncTask<String,Integer,List<String>>{
        // Before the tasks execution
        protected void onPreExecute(){
            mTextView.setTextColor(Color.BLUE);
            mTextView.setText(mTextView.getText() + "\nStarting task....");
        }

        // Do the task in background/non UI thread
        protected List<String> doInBackground(String...tasks){
            // Get the number of task
            int count = tasks.length;
            // Initialize a new list
            List<String> taskList= new ArrayList<>(count);

            // Loop through the task
            for(int i =0;i<count;i++){
                // Do the current task task here
                String currentTask = tasks[i];
                taskList.add(currentTask);

                // Sleep the UI thread for 1 second
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Publish the async task progress
                // Added 1, because index start from 0
                publishProgress((int) (((i+1) / (float) count) * 100));

                // If the AsyncTask cancelled
                if(isCancelled()){
                    break;
                }
            }
            // Return the task list for post execute
            return taskList;
        }

        /*
            onCancelled
                void onCancelled ()
                    Applications should preferably override onCancelled(Object). This method
                    is invoked by the default implementation of onCancelled(Object).

                    Runs on the UI thread after cancel(boolean) is invoked and
                    doInBackground(Object[]) has finished.
        */
        protected void onCancelled(){
            // Do something when async task is cancelled
            mTextView.setTextColor(Color.RED);
            mTextView.setText(mTextView.getText() + "\n\nOperation is cancel..." );
            Log.d(MYTASK, "Operation is cancel...");
        }

        // After each task done
        protected void onProgressUpdate(Integer... progress){
            mTextView.setText(mTextView.getText() + "\nCompleted...." + progress[0] + "%");
            Log.d(MYTASK, "Operation is onProgressUpdate..." + progress[0] + "%");
        }

        // When all async task done
        protected void onPostExecute(List<String> result){
            mTextView.setText(mTextView.getText() + "\n\nDone....");
            for (int i=0;i<result.size();i++){
                mTextView.setText(mTextView.getText() + "\n" + result.get(i));
            }
            Log.d(MYTASK, "Operation Done..." + result + "%");
        }
    }
}




