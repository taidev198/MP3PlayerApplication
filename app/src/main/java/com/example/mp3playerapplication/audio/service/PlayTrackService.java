package com.example.mp3playerapplication.audio.service;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.mp3playerapplication.R;

public class PlayTrackService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }


}
