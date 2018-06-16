package com.example.sala305_pc_13.lsitview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class verNoticia extends AppCompatActivity {

    TextView tvtitulo, txcontenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_noticia);
        Bundle bundle = this.getIntent().getExtras();
        tvtitulo = (TextView) this.findViewById(R.id.titulonoticia);
        tvtitulo.setText(bundle.getString("NOMBRE"));


    }
}
