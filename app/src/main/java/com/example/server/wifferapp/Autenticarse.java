package com.example.server.wifferapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by server on 1/10/15.
 */
public class Autenticarse extends AppCompatActivity {
    String URL = "http://192.168.80.106:4000/api/user/login";
    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.autenticarse);

        username = (EditText) findViewById(R.id.inicioUsuario);
        password = (EditText) findViewById(R.id.inicioPassword);
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
                String TOKEN = parts[1];
                Toast.makeText(getApplicationContext(),parts[1],Toast.LENGTH_LONG).show();
                // Inicia el activity menu
                // Intent i = new Intent(getApplicationContext(),MainActivity.class);
                // startActivity(i);
            } else {
                Toast.makeText(getApplicationContext(),parts[1],Toast.LENGTH_LONG).show();
            }
        }
    }
}
