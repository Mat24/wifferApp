package com.example.server.wifferapp;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by server on 1/10/15.
 */
public class VerRedes extends AppCompatActivity {

    String URL ="http://blackserver.me:4000/api/networks";
    String TOKEN = "";
    ListView LV;
    Adaptador adaptador;
    ArrayList array_redes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_redes);

        LV = (ListView) findViewById(R.id.IVRedes);
        array_redes = new ArrayList<>();
        adaptador = new Adaptador(this,array_redes);
        LV.setAdapter(adaptador);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TOKEN = preferences.getString("token","NULL_TOKEN");

        HiloRedes hiloRedes = new HiloRedes();
        hiloRedes.execute(TOKEN);
    }

    public class HiloRedes extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            try {
                String respuesta = WEBUtilDomi.GETrequest(URL+"?token="+TOKEN);
                return respuesta;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            Gson gson = new Gson();
            Type tipo = new TypeToken<ArrayList<WifferNetwork>>(){}.getType();
            ArrayList<WifferNetwork> redes = gson.fromJson(s.trim(),tipo);
            for (int i = 0; i < redes.size(); i++){
                array_redes.add(redes.get(i));
            }
            adaptador.notifyDataSetChanged();
        }
    }


}
