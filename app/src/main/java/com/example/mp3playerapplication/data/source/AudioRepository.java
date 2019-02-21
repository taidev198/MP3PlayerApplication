package com.example.mp3playerapplication.data.source;

import android.support.annotation.NonNull;

import com.example.mp3playerapplication.audio.model.Audio;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AudioRepository implements AudioDataSource {


    private static AudioRepository INSTANCE = null;
    private final AudioDataSource mAudioLocalDataSource;
    private final AudioDataSource mAudioRemoteDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, Audio> mCachedAudios;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    public AudioRepository(AudioDataSource mAudioLocalDataSource,
                           AudioDataSource mAudioRemoteDataSource) {
        this.mAudioLocalDataSource = mAudioLocalDataSource;
        this.mAudioRemoteDataSource = mAudioRemoteDataSource;
    }


    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param audioRemoteDataSource the backend data source
     * @param audioLocalDataSource  the device storage data source
     * @return the {@link AudioRepository} instance
     */
    public static AudioRepository getInstance(AudioDataSource audioRemoteDataSource,
                                              AudioDataSource audioLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new AudioRepository(audioRemoteDataSource, audioLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(AudioDataSource, AudioDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }
    @Override
    public void getAudioList(@NonNull final LoadAudioCallback callback) {
        // Respond immediately with cache if available and not dirty
        if (mCachedAudios != null && !mCacheIsDirty){
            callback.onAudioLoaded(new ArrayList<>(mCachedAudios.values()));
            return;
        }
        mAudioLocalDataSource.getAudioList(new LoadAudioCallback() {
            @Override
            public void onAudioLoaded(List<Audio> audio) {
                refreshCache(audio);
                refreshLocalDataSource(audio);
                callback.onAudioLoaded(audio);
            }

            @Override
            public void onAudioNotAvailable() {
                callback.onAudioNotAvailable();
            }
        });


    }

    private void refreshCache(List<Audio> audio) {
        if (mCachedAudios == null) {
            mCachedAudios = new LinkedHashMap<>();
        }
        mCachedAudios.clear();
        for (Audio au : audio) {
            mCachedAudios.put(au.getId(), au);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<Audio> audio) {
        //may be deleted all audio.
        for (Audio au: audio) {
            mAudioLocalDataSource.saveAudio(au);
        }
    }

    @Override
    public void getAudio(@NonNull String audioId, @NonNull GetAudioCallback callback) {

    }

    @Override
    public void saveAudio(@NonNull Audio audio) {

    }

    @Override
    public void completeAudio(@NonNull Audio audio) {

    }

    @Override
    public void completeAudio(@NonNull String audioId) {

    }

    @Override
    public void activateAudio(@NonNull Audio audio) {

    }

    @Override
    public void activateAudio(@NonNull String audioId) {

    }

    @Override
    public void refreshAudios() {
        mCacheIsDirty = true;
    }

    @Override
    public void deleteAudio(@NonNull String audioId) {

    }
}
