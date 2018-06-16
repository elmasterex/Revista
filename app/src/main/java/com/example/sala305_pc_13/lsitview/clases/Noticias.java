package com.example.sala305_pc_13.lsitview.clases;

import org.json.JSONObject;

public class Noticias{
    private String titulo;
    private String subtitulo;
    public Noticias(String tit, String sub){
        titulo = tit;
        subtitulo = sub;
    }

    public String getTitulo(){
        return titulo;
    }
    public String getSubtitulo(){
        return subtitulo;
    }
}