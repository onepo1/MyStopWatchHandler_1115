package com.example.edu.mystopwatchhandler_1115;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button start,pause,save;
    TextView textView;
    long startTime, millisecondTime, updateTime;
    int minutes, seconds, milliSeconds;
    long timeBuff = 0;

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.btn_start);
        start.setOnClickListener(this);

        pause = (Button) findViewById(R.id.btn_pause);
        pause.setOnClickListener(this);

        save = (Button) findViewById(R.id.btn_save);
        save.setOnClickListener(this);

        textView = (TextView)findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_start:
                startTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);
                break;

            case R.id.btn_pause:
                timeBuff += millisecondTime;
                handler.removeCallbacks(runnable);
                break;
        }
    }
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            millisecondTime = SystemClock.uptimeMillis() - startTime;
            updateTime = timeBuff + millisecondTime;
            seconds = (int)(updateTime/1000);
            minutes = seconds/60;
            seconds = seconds % 60;
            milliSeconds = (int)(updateTime % 1000);
            textView.setText(minutes + ":" + String.format("%02d", seconds) + ":" + String.format("%03d", milliSeconds));
            handler.postDelayed(this,0);
        }
    };
}
