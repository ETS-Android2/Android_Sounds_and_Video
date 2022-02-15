package com.bawp.playvideo;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(this, R.raw.video);

        SurfaceView surfaceView = findViewById(R.id.surfaceView);
        surfaceView.setKeepScreenOn(true);

        SurfaceHolder holder = surfaceView.getHolder();
        holder.addCallback(this);
        holder.setFixedSize(400, 300);

        Button play = findViewById(R.id.buttonPlay);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });

        Button pause = findViewById(R.id.buttonPause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        Button skip = findViewById(R.id.buttonSkip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(mediaPlayer.getDuration() / 2);
            }
        });
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mediaPlayer.setDisplay(holder);
        mediaPlayer.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mediaPlayer != null) {
             mediaPlayer.pause();
             mediaPlayer.release();
             mediaPlayer = null;
        }
    }
}
