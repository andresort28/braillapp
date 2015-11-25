package com.project.rubikon.braillapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.gesture.GestureOverlayView;
import android.os.Vibrator;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterAdapter;
import twitter4j.TwitterException;
import twitter4j.TwitterListener;
import twitter4j.TwitterMethod;
import com.project.rubikon.braillapp.Model.Tweet;

import java.util.ArrayList;

public class BrailleScreenActivity extends Activity implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private boolean tweetsLoaded = false;
    private RelativeLayout touchview;
    private int count;
    //    float x1=0, y1=0;
    private boolean lock;

    private final static int[] STATE_PRESSED = {
            android.R.attr.state_pressed,
            android.R.attr.state_focused
                    | android.R.attr.state_enabled};

    private static int defaultStates[];
    private GestureDetector gd;
    private static ArrayList[] listaTweets = new ArrayList[20];
    private String segment = "1000101010001010100101101011010100001010100000110001010101";
    int tweetCounter = 0; //Columnas del array de arraylist que es un tweet con su lista de paginas
    int segmentCounter = 0;  // Filas que son las paginas de un tweet

    private GestureDetectorCompat gestures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG-RUBIKON", "BrailleScreenActivity created");

        setContentView(R.layout.layout_braille_screen);

        touchview = (RelativeLayout) findViewById(R.id.braille);
        defaultStates = findViewById(R.id.dot1).getBackground().getState();

        gestures = new GestureDetectorCompat(this, this);
        gestures.setOnDoubleTapListener(this);

        String[] tweets =  new String[20];
        tweets = getIntent().getStringArrayExtra("Tweets");
        for (int i = 0; i < tweets.length; i++) {
            Tweet tweety = new Tweet(tweets[i]);
            listaTweets[i] = tweety.getTraslation();
        }

        updateBrailleScreen((String) listaTweets[0].get(0));
//
//        Thread closeActivity = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//
//                    Thread.sleep(10000);
//                    // Do some stuff
//                } catch (Exception e) {
//                    e.getLocalizedMessage();
//                }
//            }
//        });

        final View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton button = (ImageButton) v;
                Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vib.vibrate(100);
            }
        };

        View.OnTouchListener t = new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                int x = (int) event.getX();
                int y = (int) event.getY();
                count++;


                if(lock) {
                    for (int i = 0; i < touchview.getChildCount(); i++) {
                        View current = touchview.getChildAt(i);
                        if (current instanceof ImageButton) {
                            ImageButton b = (ImageButton) current;

                            if (!isPointWithin(x, y, b.getLeft(), b.getRight(), b.getTop(),
                                    b.getBottom())) {
                                b.getBackground().setState(defaultStates);
                            }

                            if (isPointWithin(x, y, b.getLeft(), b.getRight(), b.getTop(),
                                    b.getBottom())) {
                                b.getBackground().setState(STATE_PRESSED);
                                if ((int) b.getTag() == R.drawable.punto_lleno) {
                                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                    v.vibrate(100);
                                }
                            }

                        }
                    }
                }
                gestures.onTouchEvent(event);
                return true;
            }
        };
        touchview.setOnTouchListener(t);
    }

    public void updateBrailleScreen(String segment) {

        Log.d("Translation Segment",segment);
        int childCount = touchview.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View current = touchview.getChildAt(i);
            if (current instanceof ImageButton) {
                ImageButton b = (ImageButton) current;
                String resourceName = getResources().getResourceName(b.getId());
                String idName = resourceName.split("/")[1];
                int num = Integer.parseInt(idName.substring(3, idName.length()));

                if (segment.charAt(num - 1) == '1') {
                    b.setBackgroundResource(R.drawable.punto_lleno);
                    b.setTag(R.drawable.punto_lleno);
                } else {
                    b.setBackgroundResource(R.drawable.punto_vacio);
                    b.setTag(R.drawable.punto_vacio);
                }
            }
        }
    }

    static boolean isPointWithin(int x, int y, int x1, int x2, int y1, int y2) {
        return (x <= x2 && x >= x1 && y <= y2 && y >= y1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_braille_screen, menu);
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

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        lock = !lock;
        Log.d("doubleTap", lock + "");
        ImageView lockImg = (ImageView) findViewById(R.id.imageView2);
        if (lock)
            lockImg.setBackgroundResource(R.drawable.lock);
        else
            lockImg.setBackgroundResource(R.drawable.unlock);
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        Log.d("Gesture","Swipe");
        if (!lock) {
            float x1 = e1.getX();
            float y1 = e1.getY();

            float x2 = e2.getX();
            float y2 = e2.getY();

            float diffX = x2 - x1;
            float diffY = y2 - y1;

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (diffX > 0) {
                    prevTweet();
                    Log.d("TAG-RUBIKON", "Left");
                } else {
                    nextTweet();
                    Log.d("TAG-RUBIKON", "Right");
                }
            } else {
                if (diffY > 0) {
                    prevPage();
                    Log.d("TAG-RUBIKON", "Up");
                } else {
                    nextPage();
                    Log.d("TAG-RUBIKON", "Down");
                }

            }
        }
        return true;
    }

    public void prevPage(){
        if(segmentCounter>0)
            segmentCounter--;
        ArrayList currentTweet = listaTweets[tweetCounter];
        updateBrailleScreen((String) currentTweet.get(segmentCounter));
    }

    public void nextPage(){
        if(segmentCounter<listaTweets[tweetCounter].size()-1)
            segmentCounter++;
        ArrayList currentTweet = listaTweets[tweetCounter];
        updateBrailleScreen((String) currentTweet.get(segmentCounter));

    }
    public void prevTweet() {
        if(tweetCounter>0)
            tweetCounter--;
        segmentCounter = 0;
        ArrayList currentTweet = listaTweets[tweetCounter];
        updateBrailleScreen((String) currentTweet.get(segmentCounter));
    }

    public void nextTweet() {
        if(tweetCounter<19)
            tweetCounter++;
        else{
            Intent newfront = new Intent(BrailleScreenActivity.this, LoadTweetsActivity.class);
            startActivity(newfront);
        }
        segmentCounter = 0;
        ArrayList currentTweet = listaTweets[tweetCounter];
        updateBrailleScreen((String) currentTweet.get(segmentCounter));
    }
}