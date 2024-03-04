package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView loadingText;
    private int progressStatus;
    private Handler handler;
    private ImageView appIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.loading_progress_bar);
        loadingText = findViewById(R.id.loading_txt);
        appIcon = findViewById(R.id.app_icon_image_view);
        progressStatus = 0;
        handler = new Handler();
        appIcon.animate().alpha(1f).setStartDelay(300).setDuration(2000);
        loadingText.animate().alpha(1f).setStartDelay(300).setDuration(2000);
        progressBar.animate().alpha(1f).setStartDelay(300).setDuration(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(progressStatus < 100){
                    progressStatus++;
                    android.os.SystemClock.sleep(50);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loadingText.setText("Loading complete, redirecting");
                    }

                });
            }
        }).start();

        new Handler().postDelayed((Runnable) () -> {
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        },6000);
    }
}