package com.example.server.wifferapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by server on 1/10/15.
 */
public class Wiffer extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wiffer);
    }

    public void verRedes(View view) {
        Intent i = new Intent(getApplicationContext(),VerRedes.class);
        startActivity(i);
    }

    public void capturarRedes(View view) {
        Toast.makeText(this,"Funcionalidad disponible en breve :)",Toast.LENGTH_LONG).show();
    }

    public void ingresarRedManualmente(View view) {
        Intent i = new Intent(getApplicationContext(),IngresarRed.class);
        startActivity(i);
    }
}
