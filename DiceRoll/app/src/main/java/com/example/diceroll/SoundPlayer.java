package com.example.diceroll;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPlayer {
    private static SoundPool soundPool;
    private static int rollSound;

    public SoundPlayer(Context context){
        soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);

        rollSound = soundPool.load(context, R.raw.sound, 1);
    }

    public void playSound(){
        soundPool.play(rollSound, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
