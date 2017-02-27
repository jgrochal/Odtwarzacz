package com.example.odtwarzacz;


import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class AudioPlayerActivity extends Activity {

    private Button playButton, pauseButton, restartButton;
    private TextView songTitle;
    private MediaPlayer mediaPlayer;
    private SeekBar seekbar;
    private double finalTime;
    private double startTime;
    private Handler myHandler = new Handler();

    private static int oneTimeOnly = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audioplayer);

        pauseButton = (Button) findViewById(R.id.pauseButton);
        playButton = (Button) findViewById(R.id.playButton);
        restartButton = (Button) findViewById(R.id.restartButton);

        songTitle = (TextView) findViewById(R.id.song);

        String audioFilePath = getIntent().getStringExtra(Constants.FILE_PATH);
        String audioFileName = getIntent().getStringExtra(Constants.FILE_NAME);

        songTitle.setText(audioFileName);

        mediaPlayer = MediaPlayer.create(this, Uri.parse(audioFilePath));

        seekbar = (SeekBar)findViewById(R.id.seekBar);
        seekbar.setClickable(false);

        pauseButton.setEnabled(false);

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
                pauseButton.setEnabled(false);
                playButton.setEnabled(true);
            }
        });

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(0);
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                pauseButton.setEnabled(true);
                playButton.setEnabled(false);

                finalTime = mediaPlayer.getDuration();
                startTime = mediaPlayer.getCurrentPosition();

                if (oneTimeOnly == 0) {
                    seekbar.setMax((int) finalTime);
                    oneTimeOnly = 1;
                }

                seekbar.setProgress((int)startTime);
                myHandler.postDelayed(UpdateSongTime,100);
            }
        });



    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            startTime = mediaPlayer.getCurrentPosition();
            seekbar.setProgress((int) startTime);
            myHandler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        mediaPlayer.stop();
        oneTimeOnly = 0;
    }
}
