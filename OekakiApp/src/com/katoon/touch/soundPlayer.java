package com.katoon.touch;

import java.util.Random;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;

/**
 * 責務：
 * 音楽、効果音の再生
 * ランダムに交換を再生、指定して再生が可能
 * 曲のリストを保持する
 * 効果音のリストを保持する
 * 
 * @author k23
 *
 */
public class soundPlayer {
    private static String TAG = "soundPlayer";

	private SoundPool soundp = null;
	private int[] soundIds;
	private int soundId = 1;
	
	private MediaPlayer mediaPlayer = null;
	private int musicNum = 0;
	
	private Context thisContext;

    public soundPlayer(Context context){
    	thisContext = context;

        soundp = new SoundPool(soundKoukaon2.length, AudioManager.STREAM_MUSIC, 0);
        soundIds = new int[soundKoukaon2.length];
    	for (int i=0; i<soundKoukaon2.length; i++) {
    		soundIds[i] = soundp.load(context, soundKoukaon2[i], 1);
    	}
    	
		//mediaPlayer = MediaPlayer.create(context, soundIds[new Random().nextInt(soundResouces.length)]);
		//mediaPlayer = MediaPlayer.create(context, soundIds[new Random().nextInt(soundResouces.length)]);
    	//mediaPlayer.setLooping(true);
    }
    
	private static final int[] soundMusic = {
	//	R.raw.muci_action_r01,
		R.raw.muci_bara_r02,
		R.raw.muci_hono_r01,
	//	R.raw.muci_keikai_r01,
	};
	
	private static final int[] soundKoukaon2 = {
//		R.raw.muci_action_r01,
//		R.raw.muci_bara_r02,
//		R.raw.muci_hono_r01,
//		R.raw.muci_keikai_r01,
		R.raw.ta_ta_kirarara01,
		R.raw.ta_ge_kotaiko01,
		R.raw.ta_ge_kotaiko02,
		R.raw.ta_ge_ootaiko01,
		R.raw.ta_ge_ootaiko02,
		R.raw.ta_ge_tambourine01,
		R.raw.ta_ge_tambourine02,
	//	R.raw.ta_ta_pi02,
	};
	
	public boolean changeSoundRandom(){
		Random random = new Random();
		soundId = soundIds[random.nextInt(soundKoukaon2.length)];
		//soundId = soundIds[1];
		Log.d(TAG,"change()" + Integer.toString(soundId));

		return true;
	}
	
	public boolean startSound(int no) {
		int count = 0;
		Log.d(TAG,"start()" + Integer.toString(no));
		//soundp.play(no, 100, 100, 1, count , 1);
		soundp.play(no, 25, 25, 1, count , 1);
		return true;
	}
	
	public boolean startSoundRandum() {
		int count = 0;
		this.changeSoundRandom();
		Log.d(TAG,"start()" + Integer.toString(soundId));
		soundp.play(soundId, 100, 100, 1, count , 1);
		return true;
	}
	
	public boolean startMusic(){
		//mediaPlayer.stop();
		if ( mediaPlayer != null ) {
			mediaPlayer.stop();
			mediaPlayer.reset();
		}

		Random ramdom = new Random();
		int num = ramdom.nextInt(soundMusic.length);
		Log.d(TAG,"startMusic()" + Integer.toString(num));
		//mediaPlayer = MediaPlayer.create(thisContext, soundIds[num]);
		mediaPlayer = MediaPlayer.create(thisContext, soundMusic[num]);
		mediaPlayer.start();
    	mediaPlayer.setLooping(true);

		return true;
	}
	
	public boolean pauseMusic(){
		Log.d(TAG,"pauseMusic()");
		mediaPlayer.pause();
		return true;
	}
	
	public boolean stopMusic(){
		Log.d(TAG,"stopMusic");
		if ( mediaPlayer != null ) {
			mediaPlayer.stop();
		}
		return true;
	}
	
}
