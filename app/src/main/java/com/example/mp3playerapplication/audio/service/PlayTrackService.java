package com.example.mp3playerapplication.audio.service;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.mp3playerapplication.R;

import java.io.IOException;
import java.util.Objects;

public class PlayTrackService extends Service {
    MediaPlayer mediaPlayer  = new MediaPlayer();
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        System.out.println(Objects.requireNonNull(intent.getExtras()).get("path_file") +"path");
        try {
            mediaPlayer.setDataSource(String.valueOf(intent.getExtras().get("path_file")));
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.onStartCommand(intent, flags, startId);
    }



    @Override
    public void onDestroy() {
        mediaPlayer.stop();


    }
}
