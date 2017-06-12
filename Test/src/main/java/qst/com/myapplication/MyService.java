package qst.com.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * Created by lenovo on 2017/6/1.
 */

public class MyService extends Service{
    private MyBinder iBinder;
    private MediaPlayer musicplayer;


    @Override
    public void onCreate() {
        super.onCreate();
        musicplayer=MediaPlayer.create(this,R.raw.taylor);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        iBinder=new MyBinder();
        return iBinder;
    }
    public class MyBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(musicplayer.isPlaying()||musicplayer!=null){
            musicplayer.stop();
            musicplayer.release();
        }
    }

    @Override
    //START_STICKY_COMPATIBILITY,   //被系统回收后，系统资源充足后，不会重新启动
    // Service.START_STICKY,       //立即重新启动，intent=null
    // Service.START_NOT_STICKY,    //会重新启动，需要等待新intent传过来
    // Service.START_REDELIVER_INTENT   //立即重新启动, intent = intent
    public int onStartCommand(Intent intent, int flags, int startId) {
        playMusic();
        return START_REDELIVER_INTENT;
    }

    public void playMusic() {
        try {
            musicplayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (IllegalStateException e) {
            e.printStackTrace();
        }

        musicplayer.start();
    }
}
