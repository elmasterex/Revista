package com.example.sala305_pc_13.lsitview.adaptadores;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sala305_pc_13.lsitview.clases.Articulo;
import com.example.sala305_pc_13.lsitview.R;

import java.util.ArrayList;

public class AdaptadorArticulo extends ArrayAdapter<Articulo> {

    public AdaptadorArticulo(Context context, ArrayList<Articulo> datos) {
        super(context, R.layout.activity_list_view, datos);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.revista, null);
        TextView lblTitulo = (TextView)item.findViewById(R.id.titulo_revista);
        lblTitulo.setText(getItem(position).getTitulo());
        TextView lblSubtitulo = (TextView)item.findViewById(R.id.subtitulo_revista);
        lblSubtitulo.setText(getItem(position).getSubtitulo());
        TextView fecha = (TextView)item.findViewById(R.id.tv_fecha_revista);
        fecha.setText(getItem(position).getFecha());
        TextView url = (TextView)item.findViewById(R.id.url_revista);
        url.setText(getItem(position).getSubtitulo());
        ImageView imagen = (ImageView)item.findViewById(R.id.imagen);
        Glide.with(this.getContext())
                .load(getItem(position).getURL())
                .error(R.drawable.pdf)
                .into(imagen);

        final String pdf = getItem(position).getURL();

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = v.getTag().toString();
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(pdf));
                request.setDescription("PDF Paper");
                request.setTitle("Pdf Artcilee");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                }
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "filedownload.pdf");
                DownloadManager manager = (DownloadManager) getContext().getSystemService(Context.DOWNLOAD_SERVICE);
                try {
                    manager.enqueue(request);
                } catch (Exception e) {
                    Toast.makeText(getContext(),
                            e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        return(item);
    }

}