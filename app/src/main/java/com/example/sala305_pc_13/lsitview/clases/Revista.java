package com.example.sala305_pc_13.lsitview.clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Revista {
        private String titulo;
        private String volumen;
        private String numero;
        private String url;
        private String publicacion;

        public Revista(JSONObject a) throws JSONException {
            titulo = a.getString("title").toString();
            volumen = a.getString("volume");
            url =  a.getString("portada").toString();
            publicacion = a.getString("date_published").toString();
            numero = a.getString("number").toString();
        }

        public String getTitulo(){
            return titulo;
        }
        public String getVolumen(){
            return volumen;
        }

    public String getURL(){ return url; }

    public static ArrayList<Revista> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Revista> revistas = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            revistas.add(new Revista(datos.getJSONObject(i)));
        }
        return revistas;
    }


    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
