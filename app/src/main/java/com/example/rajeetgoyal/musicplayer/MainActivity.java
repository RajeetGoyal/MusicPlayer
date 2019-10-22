package com.example.rajeetgoyal.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.play_button);
        Button button2 = findViewById(R.id.pause_button);
        Button button3 = findViewById(R.id.reset_button);


       final MediaPlayer mMediaPlayer = MediaPlayer.create(this, R.raw.song);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.start();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.pause();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMediaPlayer.seekTo(0);
                mMediaPlayer.pause();
            }
        });

        final SeekBar mSeekBar = findViewById(R.id.SeekBar);

        final Handler mHandler = new Handler();
        //Make sure you update seek bar on UI thread
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mMediaPlayer != null){
                    mSeekBar.setMax(mMediaPlayer.getDuration()/1000);
                    mSeekBar.setProgress(mMediaPlayer.getCurrentPosition() / 1000);
                }
                mHandler.postDelayed(this, 1000);
            }
        });

       mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(mMediaPlayer != null && fromUser){
                    mMediaPlayer.seekTo(progress * 1000);
                }
            }
        });
       mSeekBar.setMax(mMediaPlayer.getDuration());

       mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
           @Override
           public void onCompletion(MediaPlayer mp) {
               Toast.makeText(MainActivity.this,"I am done", Toast.LENGTH_SHORT).show();
           }
       });
    }
}