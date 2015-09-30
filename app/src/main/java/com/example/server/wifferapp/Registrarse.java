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
 * Created by server on 30/09/15.
 */
public class Registrarse extends AppCompatActivity {
    String URL = "http://192.168.80.106:4000/api/user/create";

    EditText nickname;
    EditText username;
    EditText email;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registrarse);
        nickname = (EditText) findViewById(R.id.registroNickname);
        username = (EditText) findViewById(R.id.registroUsername);
        email = (EditText) findViewById(R.id.registroEmail);
        password = (EditText) findViewById(R.id.registroPassword);
    }

    public void ejecutarRegistro(View view) {
        HiloRegistro registrar = new HiloRegistro();
        registrar.execute(nickname.getText().toString(),
                username.getText().toString(),
                email.getText().toString(),
                password.getText().toString());
    }

    public class HiloRegistro extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... params) {
            Uri.Builder builder = new Uri.Builder();
            builder.appendQueryParameter("nickname",params[0]).appendQueryParameter("username",params[1]).
                    appendQueryParameter("email",params[2]).appendQueryParameter("password",params[3]);
            try {
                String respuesta = WEBUtilDomi.POSTrequest(URL,builder);
                return  respuesta;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if(s.equals("USER_CREATED")) {
                Toast.makeText(getApplicationContext(), "Se ha creado el usuario, porfavor inicie sesion", Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
            else {
                Toast.makeText(getApplicationContext(), "ERROR:" + s, Toast.LENGTH_LONG).show();
            }
        }
    }


}
