package com.example.sala305_pc_13.lsitview.clases;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Articulo {
    private String titulo;
    private String subtitulo;
    private String url;
    private String fecha;

    public Articulo(JSONObject a) throws JSONException {
        titulo = a.getString("title").toString();
        subtitulo = a.getString("section_title").toString();
        fecha = a.getString("year").toString();
        url =  a.getString("pdf").toString();
    }
    public String getTitulo(){
        return titulo;
    }
    public String getSubtitulo(){
        return subtitulo;
    }
    public String getFecha() {
        return fecha;
    }

    public String getURL(){ return url; }

    public static ArrayList<Articulo> JsonObjectsBuild(JSONArray datos) throws JSONException {
        ArrayList<Articulo> articulo = new ArrayList<>();
        for (int i = 0; i < datos.length(); i++) {
            articulo.add(new Articulo(datos.getJSONObject(i)));
        }
        return articulo;
    }


}
