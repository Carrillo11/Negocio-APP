package com.rc.ali;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rc.ali.Adaptador.AdaptadorPesa;
import com.rc.ali.Modelo.Pesa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Timestamp;

public class Ventas extends AppCompatActivity {
    TextView tvFecha_I, tvFecha_F;
    Button btnFecha_I, btnFecha_F, btnProveedores, btnFiltro;
    Spinner comboProveedor;
    Timestamp  tiempo_i, tiempo_f;
    String tmp_i, tmp_f, tmp_is, tmp_fs;
    long timeunix_i, timeunix_f;
    int cyear, cday, cmonth;

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference refDetalle = database.getReference("pesa");

    Query consultaOrdenada = refDetalle.orderByChild("timestamp");
    Query filtrarNombreA = refDetalle.orderByChild("proveedor").equalTo("ANDRES");

    Query filtrarNombreN = refDetalle.orderByChild("proveedor").equalTo("NELSON");

    List<Pesa> pesas;
    ListView listaDestinos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);

        initializeUI();
        inicializar();


        btnProveedores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Proveedores.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
                finish();
            }
        });

        btnFecha_I.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                cyear = calendar.get(Calendar.YEAR);
                cmonth = calendar.get(Calendar.MONTH);
                cday = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Ventas.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvFecha_I.setText(year+"-"+(month+1)+"-"+dayOfMonth+" "+"00:00:00.0");
                        tiempo_i = Timestamp.valueOf(year+"-"+(month+1)+"-"+dayOfMonth+" "+"00:00:00.0");
                        timeunix_i = -tiempo_i.getTime();
                        tmp_i = String.valueOf(timeunix_i) ;
                    }
                },cyear,cmonth,cday);
                datePickerDialog.show();
            }
        });
        btnFecha_F.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                cyear = calendar.get(Calendar.YEAR);
                cmonth = calendar.get(Calendar.MONTH);
                cday = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Ventas.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvFecha_F.setText(year+"-"+(month+1)+"-"+dayOfMonth+" "+"00:00:00.0");
                        tiempo_f = Timestamp.valueOf(year+"-"+(month+1)+"-"+dayOfMonth+" "+"00:00:00.0");
                        timeunix_f = -tiempo_f.getTime();
                        tmp_f = String.valueOf(timeunix_f);
                    }
                },cyear,cmonth,cday);
                datePickerDialog.show();
            }
        });
        btnFiltro.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                /*filtrarNombreA.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Procedimiento que se ejecuta cuando hubo algun cambio
                        // en la base de datos
                        // Se actualiza la coleccion de personas
                        pesas.removeAll(pesas);
                        for (DataSnapshot dato : dataSnapshot.getChildren()) {
                            Pesa pesa = dato.getValue(Pesa.class);
                            pesa.setKey(dato.getKey());
                            pesas.add(pesa);
                        }

                        AdaptadorPesa adapter = new AdaptadorPesa(Ventas.this,
                                pesas);
                        listaDestinos.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/

                refDetalle.orderByChild("proveedor_timestamp").startAt(comboProveedor.getSelectedItem().toString()+"_"+tmp_i).endAt(comboProveedor.getSelectedItem().toString()+"_"+tmp_f).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Procedimiento que se ejecuta cuando hubo algun cambio
                        // en la base de datos
                        // Se actualiza la coleccion de personas
                        pesas.removeAll(pesas);

                        for (DataSnapshot dato : dataSnapshot.getChildren()) {
                            Pesa pesa = dato.getValue(Pesa.class);
                            pesa.setKey(dato.getKey());
                            pesas.add(pesa);
                        }

                        AdaptadorPesa adapter = new AdaptadorPesa(Ventas.this,
                                pesas);
                        listaDestinos.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
           /*     filtrarNombreN.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // Procedimiento que se ejecuta cuando hubo algun cambio
                        // en la base de datos
                        // Se actualiza la coleccion de personas
                        pesas.removeAll(pesas);
                        for (DataSnapshot dato : dataSnapshot.getChildren()) {
                            Pesa pesa = dato.getValue(Pesa.class);
                            pesa.setKey(dato.getKey());
                            pesas.add(pesa);
                        }

                        AdaptadorPesa adapter = new AdaptadorPesa(Ventas.this,
                                pesas);
                        listaDestinos.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });*/
            }
        });

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.comobo_proveedor, android.R.layout.simple_spinner_item);
        comboProveedor.setAdapter(adapter);

    }

    private void inicializar() {


        // Cuando el usuario haga clic en la lista (para editar registro)
        listaDestinos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getBaseContext(), Inicio.class);

                intent.putExtra("accion", "e"); // Editar
                intent.putExtra("key", pesas.get(i).getKey());
                intent.putExtra("proveedor", pesas.get(i).getProveedor());
                intent.putExtra("producto", pesas.get(i).getProducto());
                intent.putExtra("cantidad", pesas.get(i).getCantidad());
                intent.putExtra("fecha", pesas.get(i).getFecha());
                intent.putExtra("timestamp", pesas.get(i).getTimestamp());
                startActivity(intent);
            }
        });
        listaDestinos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                // Preparando cuadro de dialogo para preguntar al usuario
                // Si esta seguro de eliminar o no el registro
                AlertDialog.Builder ad = new AlertDialog.Builder(Ventas.this);
                ad.setMessage("Está seguro de eliminar esta pesa?")
                        .setTitle("Confirmación");

                ad.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Ventas.refDetalle.child(pesas.get(position).getKey()).removeValue();

                        Toast.makeText(Ventas.this,
                                "Pesa borrado!",Toast.LENGTH_SHORT).show();
                    }
                });
                ad.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(Ventas.this,
                                "Operación de borrado cancelada!",Toast.LENGTH_SHORT).show();
                    }
                });

                ad.show();
                return true;
            }
        });

        pesas = new ArrayList<>();

        // Cambiarlo refProductos a consultaOrdenada para ordenar lista
        consultaOrdenada.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Procedimiento que se ejecuta cuando hubo algun cambio
                // en la base de datos
                // Se actualiza la coleccion de personas
                pesas.removeAll(pesas);
                for (DataSnapshot dato : dataSnapshot.getChildren()) {
                    Pesa pesa = dato.getValue(Pesa.class);
                    pesa.setKey(dato.getKey());
                    pesas.add(pesa);
                }

                AdaptadorPesa adapter = new AdaptadorPesa(Ventas.this,
                        pesas);
                listaDestinos.setAdapter(adapter);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    private void initializeUI() {
        btnProveedores=findViewById(R.id.btnproveedor);
        listaDestinos = findViewById(R.id.lvpesas);
        btnFecha_I = findViewById(R.id.btnfecha_i);
        btnFecha_F = findViewById(R.id.btnfecha_f);
        btnFiltro = findViewById(R.id.btnfiltrar);
        tvFecha_I = findViewById(R.id.tvfecha_i);
        tvFecha_F = findViewById(R.id.tvfecha_f);
        comboProveedor = findViewById(R.id.spn_proveedor);
       // btnPesas = findViewById(R.id.btnpesas);
    }

}