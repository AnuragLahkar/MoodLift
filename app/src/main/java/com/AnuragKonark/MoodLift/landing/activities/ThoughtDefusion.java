package com.AnuragKonark.MoodLift.landing.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.AnuragKonark.MoodLift.R;

public class ThoughtDefusion extends AppCompatActivity {

    private ImageView playPause;
    private TextView textCurrentTime, textTotalDuration;
    private SeekBar playerSeekBar;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    ImageButton backtd;
    TextView popuptd;
    @SuppressLint("ClickableViewAccessibility")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thought_defusion);
        playPause=findViewById(R.id.playPause4);
        textCurrentTime=findViewById(R.id.textCurrentTime4);
        textTotalDuration=findViewById(R.id.textTotalDuration4);
        playerSeekBar=findViewById(R.id.playSeekBar4);
        mediaPlayer = new MediaPlayer();

        playerSeekBar.setMax(100);

        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    playPause.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                }else{
                    mediaPlayer.start();
                    playPause.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24);
                    updateSeekBar();
                }
            }
        });

        prepareMediaPlayer();

        playerSeekBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                SeekBar seekBar = (SeekBar) view;
                int playPosition = (mediaPlayer.getDuration()/100) * seekBar.getProgress();
                mediaPlayer.seekTo(playPosition);
                textCurrentTime.setText(milliSecondsToTimer(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                playerSeekBar.setSecondaryProgress(i);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                playerSeekBar.setProgress(0);
                playPause.setImageResource(R.drawable.ic_baseline_play_circle_filled_24);
                textCurrentTime.setText(R.string.zero);
                textTotalDuration.setText(R.string.zero);
                mediaPlayer.reset();
                prepareMediaPlayer();
            }
        });
        backtd=findViewById(R.id.backtd);
        backtd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        popuptd = findViewById(R.id.popuptd);
        popuptd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPopUpWindow();
            }
        });
    }

    private void openPopUpWindow() {
        Intent popupwindow = new Intent(ThoughtDefusion.this, PopUpTd.class);
        startActivity(popupwindow);
    }

    private void prepareMediaPlayer() {
        try {
            mediaPlayer.setDataSource("https://firebasestorage.googleapis.com/v0/b/test-3c769.appspot.com/o/activities%2Fthought%20defusion.mp3?alt=media&token=91dd43d8-36d7-4746-aa5c-61aa4575408c");
            mediaPlayer.prepare();
            textTotalDuration.setText(milliSecondsToTimer(mediaPlayer.getDuration()));
        } catch (Exception exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentDuration = mediaPlayer.getCurrentPosition();
            textCurrentTime.setText(milliSecondsToTimer(currentDuration));
        }
    };

    private void updateSeekBar() {
        if (mediaPlayer.isPlaying()) {
            playerSeekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration())* 100));
            handler.postDelayed(updater,1000);
        }
    }

    private String milliSecondsToTimer(long milliSeconds) {
        String timerString = "";
        String secondsString;

        int hours = (int) (milliSeconds / (1000 * 60 * 60));
        int minutes = (int) (milliSeconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliSeconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);

        if (hours > 0) {
            timerString = hours + ":";
        }
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }
        timerString = timerString + minutes + ":" + secondsString;
        return timerString;
    }

    @Override
    public void onBackPressed() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
            super.onBackPressed();
        }else {
            super.onBackPressed();
        }
    }
}