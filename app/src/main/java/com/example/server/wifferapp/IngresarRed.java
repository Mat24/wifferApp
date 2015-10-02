package com.example.server.wifferapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by server on 1/10/15.
 */
public class IngresarRed extends AppCompatActivity {
    String TOKEN = "";
    String URL ="http://blackserver.me:4000/api/networks";
    EditText essid;
    EditText bssid;
    EditText banda;
    EditText seguridad;
    EditText wps;
    EditText longitude;
    EditText latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_red);

        essid = (EditText) findViewById(R.id.ETessid);
        bssid = (EditText) findViewById(R.id.ETbssid);
        banda = (EditText) findViewById(R.id.ETbanda);
        seguridad = (EditText) findViewById(R.id.ETseguridad);
        wps = (EditText) findViewById(R.id.ETwps);

        // Especificos para la ubicacion
        longitude = (EditText) findViewById(R.id.ETlongitud);
        latitude = (EditText) findViewById(R.id.ETlatitud);

        LocationManager mlocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        LocationListener mlocListener = new MyLocationListener();
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mlocListener);

        // Obtengo el token almacenado
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        TOKEN = preferences.getString("token","NULL_TOKEN");

    }

    public void crearNuevaRed(View view) {
        HiloCrear crear = new HiloCrear();
        crear.execute(essid.getText().toString(),bssid.getText().toString(),
                banda.getText().toString(),seguridad.getText().toString(),wps.getText().toString(),
                longitude.getText().toString(),latitude.getText().toString(),TOKEN);
    }

    public class HiloCrear extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            Uri.Builder builder = new Uri.Builder();
            builder.appendQueryParameter("bssid",params[0]).appendQueryParameter("essid",params[1]).appendQueryParameter("band",params[2])
                    .appendQueryParameter("security_type",params[3]).appendQueryParameter("is_wps",params[4])
                    .appendQueryParameter("longitude",params[5]).appendQueryParameter("latitude",params[6])
            .appendQueryParameter("token",params[7]);
            try {
                String respuesta = WEBUtilDomi.POSTrequest(URL,builder);
                return respuesta;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        }
    }

    // Listener para capturar datos del GPS
    public class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location loc) {

            longitude.setText(""+loc.getLongitude());
            latitude.setText("" + loc.getLatitude());

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText( getApplicationContext(), "Gps Disabled", Toast.LENGTH_SHORT ).show();
        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText( getApplicationContext(), "Gps Enabled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
    }
}
//params.permit(:essid, :bssid, :band, :channel, :security_type, :is_wps, :longitude, :latitude, :first_seen, :last_seen, :user_id)