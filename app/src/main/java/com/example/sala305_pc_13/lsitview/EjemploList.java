package com.example.sala305_pc_13.lsitview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sala305_pc_13.lsitview.WebService.Asynchtask;
import com.example.sala305_pc_13.lsitview.WebService.WebService;
import com.example.sala305_pc_13.lsitview.adaptadores.AdaptadorRevista;
import com.example.sala305_pc_13.lsitview.clases.Articulo;
import com.example.sala305_pc_13.lsitview.clases.Revista;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class EjemploList extends AppCompatActivity implements Asynchtask {

    ListView listaView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listaView = (ListView) this.findViewById(R.id.lista);
        enviar();
        View header = getLayoutInflater().inflate(R.layout.encabezado, null);
        listaView.addHeaderView(header);

        listaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Intent intent = new Intent(EjemploList.this, Articulos.class);
                Bundle b = new Bundle();
                b.putString("VOLUMEN", ((Revista)a.getItemAtPosition(position)).getVolumen());
                b.putString("NUMERO", ((Revista)a.getItemAtPosition(position)).getNumero());
                intent.putExtras(b);
                startActivity(intent);
            }
        });

    }


    public void enviar()
    {
        Map<String, String> datos = new HashMap<String, String>();
            WebService ws= new WebService("http://revistas.uteq.edu.ec/ws/getrevistas.php", datos,
                EjemploList.this, EjemploList.this);
        ws.execute("");


    }

    @Override
    public void processFinish(String result) throws JSONException {

        JSONObject jsonObject = new JSONObject(result);
        JSONArray contact = jsonObject.getJSONArray("issues");

        ArrayList<Revista> revistas = Revista.JsonObjectsBuild(contact);
        //Revista[] revistas = new Revista[contact.length()];

        AdaptadorRevista adaptadorRevista = new AdaptadorRevista(this, revistas);

        listaView.setAdapter(adaptadorRevista);

    }
}
