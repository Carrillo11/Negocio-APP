package com.rc.ali.Adaptador;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.rc.ali.Modelo.Pesa;
import com.rc.ali.R;

import java.util.List;

public class AdaptadorPesa extends ArrayAdapter<Pesa> {
    List<Pesa> pesas;
    private Activity context;

    public AdaptadorPesa(@NonNull Activity context, @NonNull List<Pesa> pesas) {
        super(context, R.layout.lista_pesas, pesas);
        this.context = context;
        this.pesas = pesas;
}
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        // Método invocado tantas veces como elementos tenga la coleccion personas
        // para formar a cada item que se visualizara en la lista personalizada
        LayoutInflater layoutInflater = context.getLayoutInflater();
        View rowview=null;
        // optimizando las diversas llamadas que se realizan a este método
        // pues a partir de la segunda llamada el objeto view ya viene formado
        // y no sera necesario hacer el proceso de "inflado" que conlleva tiempo y
        // desgaste de bateria del dispositivo
        if (view == null)
            rowview = layoutInflater.inflate(R.layout.lista_pesas,null);
        else rowview = view;

        TextView tvProveedor = rowview.findViewById(R.id.tvproveedor);
        TextView tvProducto = rowview.findViewById(R.id.tvproducto);
        TextView tvCantidad = rowview.findViewById(R.id.tvprecio);
        TextView tvfecha = rowview.findViewById(R.id.tvfecha);

        tvProveedor.setText(pesas.get(position).getProveedor());
        tvProducto.setText(pesas.get(position).getProducto());
        tvCantidad.setText(pesas.get(position).getCantidad());
        tvfecha.setText(pesas.get(position).getFecha());

        return rowview;
    }
}
