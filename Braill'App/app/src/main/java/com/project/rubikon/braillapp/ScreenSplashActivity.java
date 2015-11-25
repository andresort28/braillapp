package com.project.rubikon.braillapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class ScreenSplashActivity extends ActionBarActivity {


    public int seconds;
    public int miliseconds;
    public static final int delay=2;
    public MediaPlayer reproductor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG-RUBIKON", "ScreenSplashActivity created");
        setContentView(R.layout.layout_screen_splash);
        Log.d("RUBIKON", "ScreenSplashActivity created");
        reproductor= MediaPlayer.create(this, R.raw.bienvenido);
        reproductor.start();
        seconds=reproductor.getDuration();
        miliseconds=seconds;
        empezaranimacion();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_screen_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void empezaranimacion(){
        new CountDownTimer(miliseconds, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                Intent newfront = new Intent(ScreenSplashActivity.this, LoadTweetsActivity.class);
                startActivity(newfront);
                finish();
            }
        }.start();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(reproductor.isPlaying()){
            reproductor.stop();
            reproductor.release();
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        reproductor.start();
    }

    @Override
    protected void onPause(){
        super.onPause();
        reproductor.pause();
    }
}
