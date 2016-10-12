package com.example.igor_p.stopwatch;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class StopWatchActivity extends Activity {

    private boolean running;
    private int seconds;
    private boolean wasRunning;
//================================================================================================//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);
        if(savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTime();
    }
    public void onSaveInstanceState(Bundle savedInstanceState){
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }
//================================================================================================//

    public void onClickStart(View v){

              running = true;
    }

    public void onClickStop(View v){
        running = false;
    }

    public void onClicReset(View v){
        running = false;
        seconds = 0;
    }
//================================================================================================//
    private void runTime(){
        final TextView textTime = (TextView)findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds %3600) / 60;
                int secs = seconds %60;
                String time = String.format("%d:%02d:%02d",hours,minutes,secs);
                textTime.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }

        });
    }
//================================================================================================//

    @Override
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;

    }

    @Override
    protected void onStart() {
        super.onStart();
if(wasRunning)
      running = true;
    }
    //================================================================================================//
}
