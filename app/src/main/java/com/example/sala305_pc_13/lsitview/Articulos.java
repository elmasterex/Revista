package com.example.sala305_pc_13.lsitview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sala305_pc_13.lsitview.WebService.Asynchtask;
import com.example.sala305_pc_13.lsitview.WebService.WebService;
import com.example.sala305_pc_13.lsitview.adaptadores.AdaptadorArticulo;
import com.example.sala305_pc_13.lsitview.clases.Articulo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Articulos extends AppCompatActivity implements Asynchtask {

    ListView listaView;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 1 ;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listaView = (ListView) this.findViewById(R.id.lista);
        View header = getLayoutInflater().inflate(R.layout.encabezadoarticulo, null);
        listaView.addHeaderView(header);
        Bundle bundle = this.getIntent().getExtras();
        enviar(bundle.getString("VOLUMEN").toString(), bundle.getString("NUMERO").toString());

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);

            }
        }

    }


    public void enviar(String volumen, String numero)
    {
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("http://revistas.uteq.edu.ec/ws/getarticles.php?volumen="+volumen+"&num="+numero+"", datos,
                Articulos.this, Articulos.this);
        ws.execute("");


    }

    @Override
    public void processFinish(String result) throws JSONException {

        JSONObject jsonObject = new JSONObject(result);
        JSONArray contact = jsonObject.getJSONArray("articles");

        ArrayList<Articulo> articulos = Articulo.JsonObjectsBuild(contact);

        AdaptadorArticulo adaptadorRevista = new AdaptadorArticulo(this, articulos);

        listaView.setAdapter(adaptadorRevista);

    }
}
