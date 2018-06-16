package com.example.sala305_pc_13.lsitview.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sala305_pc_13.lsitview.R;
import com.example.sala305_pc_13.lsitview.clases.Revista;

import java.util.ArrayList;

public class AdaptadorRevista extends ArrayAdapter<Revista> {
        public AdaptadorRevista(Context context, ArrayList<Revista> datos) {
            super(context, R.layout.activity_list_view, datos);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.revista, null);
            TextView lblTitulo = (TextView)item.findViewById(R.id.titulo_revista);
            lblTitulo.setText(getItem(position).getTitulo());
            TextView lblSubtitulo = (TextView)item.findViewById(R.id.subtitulo_revista);
            lblSubtitulo.setText("Fecha de publicacion: "+getItem(position).getPublicacion());

            TextView fecha = (TextView)item.findViewById(R.id.tv_fecha_revista);
            fecha.setText(getItem(position).getTitulo());

            TextView url = (TextView)item.findViewById(R.id.url_revista);
            url.setText("Volumen "+ getItem(position).getVolumen() + "  Numero: " + getItem(position).getNumero());

            ImageView imagen = (ImageView)item.findViewById(R.id.imagen);
            Glide.with(this.getContext())
                    .load(getItem(position).getURL())
                    .error(R.drawable.imgnotfound)
                    .into(imagen);

            return(item);
        }


}
