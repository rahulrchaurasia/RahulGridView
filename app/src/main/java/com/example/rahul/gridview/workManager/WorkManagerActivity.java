package com.example.rahul.gridview.workManager;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.rahul.gridview.R;
import com.example.rahul.gridview.workManager.Worker.MyWorker;
import com.example.rahul.gridview.workManager.Worker.MyWorkerA;

public class WorkManagerActivity extends AppCompatActivity {

    Button buttonEnqueue;
    TextView textView;
    public static final String TASK_TASK_DESC = "task_desc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_manager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        buttonEnqueue = findViewById(R.id.buttonEnqueue);
        textView = findViewById(R.id.textViewStatus);


        Data data = new Data.Builder()
                .putString(TASK_TASK_DESC, "Here I am sending work Data")
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiresBatteryNotLow(true)
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        final OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(data)
                .setConstraints(constraints)
                .build();

        buttonEnqueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Enqueuing the work request

                WorkManager.getInstance().enqueue(request);
            }
        });

        WorkManager.getInstance().getWorkInfoByIdLiveData(request.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {

                        if (workInfo != null  && workInfo.getState().isFinished()) {

                            Data data = workInfo.getOutputData();
                            String output = data.getString(MyWorkerA.KEY_TASK_OUTPUT);
                            textView.append(output + "\n");
                        }
                        String status = workInfo.getState().name();
                        textView.append(status + "\n");
                    }
                });


    }

}
