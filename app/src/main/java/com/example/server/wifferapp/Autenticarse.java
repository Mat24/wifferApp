package com.example.server.wifferapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by server on 1/10/15.
 */
public class Autenticarse extends AppCompatActivity {
    String URL = "http://blackserver.me:4000/api/user/login";
    EditText username;
    EditText password;
    ImageView locker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autenticarse);

        username = (EditText) findViewById(R.id.inicioUsuario);
        password = (EditText) findViewById(R.id.inicioPassword);

        locker = (ImageView) findViewById(R.id.locker);
        locker.setImageResource(R.drawable.lock);
    }

    public void login(View view) {
        HiloLogin login = new HiloLogin();
        login.execute(username.getText().toString(),password.getText().toString());
    }

    class HiloLogin extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... params) {
            Uri.Builder builder = new Uri.Builder();
            builder.appendQueryParameter("username",params[0]).appendQueryParameter("password",params[1]);
            try {
                String respuesta = WEBUtilDomi.POSTrequest(URL, builder);
                return  respuesta;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String[] parts = s.split(":");
            if(parts[0].equals("AUTORIZADO")){
                locker.setImageResource(R.drawable.unlock);
                String TOKEN = parts[1];
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("token",TOKEN);
                editor.commit();
                Toast.makeText(getApplicationContext(),parts[1],Toast.LENGTH_LONG).show();
                // Inicia el activity wiffer ( Menu principal de operaciones de la APP )
                Intent i = new Intent(getApplicationContext(),Wiffer.class);
                startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(),parts[1],Toast.LENGTH_LONG).show();
            }
        }
    }
}
