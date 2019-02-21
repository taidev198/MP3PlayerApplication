package com.example.mp3playerapplication.player;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;


import java.io.File;
import java.util.Arrays;

public class PlayerPresenter implements PlayerContract.Presenter {

    PlayerContract.View mPlayerView;
    Context mContext;

    PlayerPresenter(PlayerContract.View mPlayerView, Context context){
        this.mPlayerView = mPlayerView;
        this.mPlayerView.setPresenter(this);
        this.mContext = context;
    }



    @Override
    public void load() {
        this.mPlayerView.showProcessing();
        this.mPlayerView.hideProcessing();
        getAllSongsFromExternalStorage();
        this.mPlayerView.showListSongs();
    }

    private void showListFiles(Context context){
        File directory = context.getExternalFilesDir(MediaStore.Audio.Media.DATA);
        if (directory != null)
            System.out.println(Arrays.toString(directory.listFiles())+"HELLO");

    }

    private void getRealPathFromURI(Context context) {
        //Retrieve a list of Music files currently listed in the Media store DB via URI.

//Some audio may be explicitly marked as not being
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
            Cursor cursor;
            String[] projection = {
                    MediaStore.Audio.Media._ID,
                    MediaStore.Audio.Media.ARTIST,
                    MediaStore.Audio.Media.TITLE,
                    MediaStore.Audio.Media.DATA,
                    MediaStore.Audio.Media.DISPLAY_NAME,
                    MediaStore.Audio.Media.DURATION
            };

            cursor = mContext.getContentResolver().query(
                    MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    projection,
                    selection,
                    null,
                    null);

            while(cursor.moveToNext()){
                System.out.println((cursor.getString(0) + "||" + cursor.getString(1) + "||" +   cursor.getString(2) + "||" +   cursor.getString(3) + "||" +  cursor.getString(4) + "||" +  cursor.getString(5)));
            }
        }

    }
    void getAllSongsFromExternalStorage() {
        String[] STAR = { "*" };
        Cursor cursor;
        Uri allsongsuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        cursor = mContext.getContentResolver().query(allsongsuri, STAR,
                null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String song_name = cursor
                            .getString(cursor
                                    .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    int song_id = cursor.getInt(cursor
                            .getColumnIndex(MediaStore.Audio.Media._ID));

                    String fullpath = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Audio.Media.DATA));

                    String album_name = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Audio.Media.ALBUM));
                    int album_id = cursor.getInt(cursor
                            .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));

                    String artist_name = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Audio.Media.ARTIST));
                    int artist_id = cursor.getInt(cursor
                            .getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
                    long duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                    System.out.println("sonng name"+duration);
                } while (cursor.moveToNext());

            }
            cursor.close();
        }
    }

    @Override
    public void filter() {

    }

    @Override
    public void play() {

    }

    @Override
    public void open() {

    }

    @Override
    public void start() {

    }
}
