package com.example.mp3playerapplication.audio;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mp3playerapplication.R;
import com.example.mp3playerapplication.audio.model.Audio;
import com.example.mp3playerapplication.audio.service.PlayTrackService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.MyHolder> {
    List<Audio> mAudio;



    public void setAudioList(List<Audio> audio){
        if (mAudio != null){
            mAudio.clear();
        }
        mAudio = new ArrayList<>(audio);
    }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.card_view,viewGroup, false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
        final Audio audio = mAudio.get(i);
        myHolder.textView.setText(audio.getTitle());
        myHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myHolder.textView.getContext(), PlayTrackService.class);
                intent.putExtra("path_file", audio.getArtist());
                myHolder.textView.getContext().startService(intent);
                Toast.makeText(myHolder.textView.getContext(), audio.getTitle() + " " +i, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAudio.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        TextView textView;
        MyHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.content);

        }
    }


}
