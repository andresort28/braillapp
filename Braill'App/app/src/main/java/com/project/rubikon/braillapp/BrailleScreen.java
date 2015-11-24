package com.project.rubikon.braillapp;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class BrailleScreen extends AppCompatActivity {

    private RelativeLayout touchview;

    private final static int[] STATE_PRESSED = {
            android.R.attr.state_pressed,
            android.R.attr.state_focused
                    | android.R.attr.state_enabled };

    private static int defaultStates[];

    private String segment = "100000110000100100100110100010110100110110110000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_braille_screen);

        touchview = (RelativeLayout) findViewById(R.id.braille);
        defaultStates = findViewById(R.id.dot1).getBackground().getState();

        updateBrailleScreen(segment);

        final View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton button = (ImageButton)v;
                Vibrator vib= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vib.vibrate(100);
            }
        };

        View.OnTouchListener t = new View.OnTouchListener() {

            private boolean isInside = false;

            @Override
            public boolean onTouch(View view, MotionEvent event) {

                int x = (int) event.getX();
                int y = (int) event.getY();

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
                            if ( (int)b.getTag() == R.drawable.filled13) {
                                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                v.vibrate(100);
                            }
                        }

                    }
                }
                return true;

            };
        };

        touchview.setOnTouchListener(t);
    }

    public void updateBrailleScreen ( String segment ) {

        int childCount = touchview.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View current = touchview.getChildAt(i);
            if (current instanceof ImageButton) {
                ImageButton b = (ImageButton) current;
                String resourceName = getResources().getResourceName(b.getId());
                String idName = resourceName.split("/")[1];
                int num = Integer.parseInt(idName.substring(3, idName.length()));

                if ( segment.charAt(num-1) == '1'){
                    b.setBackgroundResource(R.drawable.filled13);
                    b.setTag(R.drawable.filled13);
                }
                else{
                    b.setBackgroundResource(R.drawable.blank36);
                    b.setTag(R.drawable.blank36);
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

    public void pasarTweet(){

        // va a haber una variable bandera que va a indicar si esta la pantalla bloqueada
        // para realizar movimientos de desplazamiento o esta desbloqueada
        // True representa que si se pueden realizar movimientos

        if(true){
            // aqui se especifica hacia donde se dirige el movimiento
            // dependiendo de la informacion brindada por la accion realizada por el usuario
            // puede cambiar la informacion si va hacia arriba sigue leyendo el tweet
            // si va hacia la izquierda lee el siguiente tweet, si va a la derecha
            // pasa al anterior tweet y si va hacia abajo vuelve a la parte superior del tweet que
            // esta leyendo
            // Si al hacer un movimiento buscando un tweet nuevo no se encuentran mas tweets por leer
            // Se cambia la bandera de Si hay mas tweets por leer a TRUE y entra al if que
            // Cambia a la activity de cargando tweets.....


            // Este seria el if de la bandera que indica que no hay mas tweets por leer
            //
            if(true){


                Intent newfront = new Intent(BrailleScreen.this, CargaTweets.class);
                startActivity(newfront);
            }


            Intent newfront = new Intent(BrailleScreen.this, BrailleScreen.class);
            startActivity(newfront);
        }




    }
}
