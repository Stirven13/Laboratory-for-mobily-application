package com.badlogic.sokolovtask_lab5;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    VideoView MyvideoPlayer;
    MediaPlayer mediaPlayer;
    Button buttonAudioStart, buttonAudioPause, buttonAudioStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyvideoPlayer = (VideoView) findViewById(R.id.videoView);
        Uri myVideoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.random_video);
        MyvideoPlayer.setVideoURI(myVideoUri);
        MediaController mediaController = new MediaController(this);
        MyvideoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(MyvideoPlayer);

        // Delete menu, witch was if tap on screen
        MyvideoPlayer.setOnTouchListener((v, event) -> true);

        mediaPlayer = MediaPlayer.create(this, R.raw.random_video);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopAudioPlay();
            }
        });
        buttonAudioStart = (Button) findViewById(R.id.buttonAudioStart);
        buttonAudioPause = (Button) findViewById(R.id.buttonAudioPause);
        buttonAudioStop = (Button) findViewById(R.id.buttonAudioStop);
        buttonAudioPause.setEnabled(false);
        buttonAudioStop.setEnabled(false);
    }

    public void stopAudioPlay() {
        mediaPlayer.stop();
        buttonAudioPause.setEnabled(false);
        buttonAudioStop.setEnabled(false);
        try {
            mediaPlayer.prepare();
            mediaPlayer.seekTo(0);
            buttonAudioStart.setEnabled(true);
        } catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onAudioStartClick(View view) {
        mediaPlayer.start();
        buttonAudioStart.setEnabled(false);
        buttonAudioPause.setEnabled(true);
        buttonAudioStop.setEnabled(true);
    }

    public void onAudioPauseClick(View view) {
        mediaPlayer.pause();
        buttonAudioStart.setEnabled(true);
        buttonAudioPause.setEnabled(false);
        buttonAudioStop.setEnabled(true);
    }

    public void onAudioStopClick(View view) {
        stopAudioPlay();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer.isPlaying()) {
            stopAudioPlay();
        }
    }

    public void onVideoStartClick(View view) {
        MyvideoPlayer.start();
    }

    public void onVideoPauseClick(View view) {
        MyvideoPlayer.pause();
    }

    public void onVideoStopClick(View view) {
        MyvideoPlayer.stopPlayback();
        MyvideoPlayer.resume();
    }
}