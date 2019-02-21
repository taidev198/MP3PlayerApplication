package com.example.mp3playerapplication.player;

import com.example.mp3playerapplication.BasePresenter;
import com.example.mp3playerapplication.BaseView;

public interface PlayerContract {
    interface View extends BaseView<Presenter>{

        void showProcessing();
        void hideProcessing();
        void showListSongs();
        void showNotification();
        void showPlayTrack();



    }

    interface Presenter extends BasePresenter{
        void load();
        void filter();
        void play();
        void open();
    }
}
