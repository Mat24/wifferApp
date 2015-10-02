package com.example.server.wifferapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;



public class Adaptador extends BaseAdapter {
    ArrayList<WifferNetwork> network;
    Context c;

    public Adaptador(Context c, ArrayList<WifferNetwork> list) {
        network = list;
        this.c = c;
    }

    @Override
    public int getCount() {
        return network.size();
    }

    @Override
    public Object getItem(int position) {
        return network.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = null;
        LayoutInflater inflater = (LayoutInflater) c
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            row = inflater.inflate(R.layout.listview_item_redes, parent, false);
        } else {
            row = convertView;
        }
        WifferNetwork net = network.get(position);
        TextView name = (TextView) row.findViewById(R.id.noti_titular);
        name.setText(net.getEssid());
        TextView email = (TextView) row.findViewById(R.id.noti_contenido);
        email.setText(net.getSecurity_type());

        return row;
    }

}