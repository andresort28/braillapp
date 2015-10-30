package com.project.rubikon.braillapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CargaTweets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_tweets);
        cargandoTweets();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_carga_tweets, menu);
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
    public void cargandoTweets(){
        //Aqui va el evento de cuando se cargan los tweets, una vez ya cargados el pasa a la activity
        // de lectura
        // Si en el evento de cargar tweets, si existen tweets para cargar el retorno es true
        // y pasa a la activity de lectura, si no existen tweets para cargar el retorno es false
        // se pasa a la activity de no hay tweets.

        if(true){
            Intent newfront = new Intent(CargaTweets.this, CargaTweets.class);
            startActivity(newfront);
        }else{
            Intent newfront = new Intent(CargaTweets.this, NoHayTweets.class);
            startActivity(newfront);
        }


    }
}
