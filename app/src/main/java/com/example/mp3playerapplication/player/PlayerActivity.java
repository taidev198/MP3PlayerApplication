package com.example.mp3playerapplication.player;

import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mp3playerapplication.R;
import com.example.mp3playerapplication.utils.PermissionInfo;

import java.util.zip.Inflater;

public class PlayerActivity extends AppCompatActivity implements PlayerContract.View {

    RecyclerView playerListView;
    PlayerPresenter playerPresenter;
    PlayerListViewAdapter playerListViewAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestPermission();
//        playerPresenter = new PlayerPresenter(this, this);
//        playerPresenter.load();
       // new LoadingAudioAsyncTask(getContentResolver(), getResources(), new Handler());
    }

    @Override
    public void showProcessing() {

    }

    @Override
    public void hideProcessing() {

    }

    @Override
    public void showListSongs() {
        playerListView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        playerListView.setHasFixedSize(true);
        playerListView.setLayoutManager(layoutManager);
        playerListViewAdapter = new PlayerListViewAdapter();
        playerListView.setAdapter(playerListViewAdapter);

    }

    @Override
    public void showNotification() {

    }

    @Override
    public void showPlayTrack() {

    }

    @Override
    public void setPresenter(PlayerContract.Presenter presenter) {

    }

    private class PlayerListViewAdapter extends RecyclerView.Adapter<PlayerListViewAdapter.MyHolder> {


        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate( R.layout.card_view,viewGroup, false);

            return new MyHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
                myHolder.textView.setText("this is content");
        }

        @Override
        public int getItemCount() {
            return 10;
        }


        class MyHolder extends RecyclerView.ViewHolder{
            TextView textView;
            MyHolder(@NonNull View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.content);
                textView.setText("this is content");
            }
        }
    }

    private void requestPermission(){
        PermissionInfo.PERMISSION_COUNT = 0;
        for (int i = 0; i < PermissionInfo.permissions.length; i++) {
            if(ContextCompat.checkSelfPermission(this, PermissionInfo.permissions[i])
            != PackageManager.PERMISSION_GRANTED){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(PermissionInfo.permissions,
                            PermissionInfo.PERMISSION_REQUESCODE[i]);
                }
            } else{
                PermissionInfo.PERMISSION_COUNT++;
            }
        }
//        playerPresenter = new PlayerPresenter(this, this);
//        playerPresenter.load();

        new LoadingAudioAsyncTask(getContentResolver(), getResources(), new Handler()).execute();
        PermissionInfo.PERMISSION_COUNT = 0;
    }


}
