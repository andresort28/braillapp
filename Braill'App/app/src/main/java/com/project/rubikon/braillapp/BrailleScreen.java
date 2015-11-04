package com.project.rubikon.braillapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class BrailleScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_braille_screen);
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
