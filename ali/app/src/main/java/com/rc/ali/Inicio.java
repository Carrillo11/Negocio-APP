package com.rc.ali;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.sql.Timestamp;

import com.google.firebase.FirebaseApp;
import com.rc.ali.Modelo.Pesa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class Inicio extends AppCompatActivity {
    TextView tvProveedor, tvFecha, tvTimestamp;
    Spinner  comboProducto;
    EditText etCantidad;
    Button  btnSalir, btnFecha, btnEnviar, btnCompartir;
    String dato;
    String key="",proveedor="",producto="",cantidad="",fecha="",timestamp="", accion="";
    Timestamp  tiempo;
    double valor;
    long timeunix;
    int cyear, cday, cmonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        initializeUI();
        inicializar();
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.comobo_productos, android.R.layout.simple_spinner_item);
        comboProducto.setAdapter(adapter);


        if (accion.equals("a")) { //Agregar usando push()
            dato = getIntent().getStringExtra("dato");
            tvProveedor.setText(dato);
        }

        btnFecha.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Calendar calendar=Calendar.getInstance();
                cyear = calendar.get(Calendar.YEAR);
                cmonth = calendar.get(Calendar.MONTH);
                cday = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Inicio.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tvFecha.setText(year+"-"+(month+1)+"-"+dayOfMonth+" "+"00:00:00.0");
                        tiempo = Timestamp.valueOf(year+"-"+(month+1)+"-"+dayOfMonth+" "+"00:00:00.0");
                        timeunix = -tiempo.getTime();

                        tvTimestamp.setText(String.valueOf(timeunix));
                    }
                },cyear,cmonth,cday);
                datePickerDialog.show();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Proveedores.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
                finish();
            }
        });

    }
    private void valor(){
        if(tvProveedor.getText().toString().equals("ANDRES")){
            if (comboProducto.getSelectedItem().toString().equals("HUESO")){
                valor = 0.5;
            }else if(comboProducto.getSelectedItem().toString().equals("MENUDO")){
                valor = 3;
            }else if(comboProducto.getSelectedItem().toString().equals("PELLEJO")){
                valor = 0.4;
            }
        }else if(tvProveedor.getText().toString().equals("NELSON")){
            if (comboProducto.getSelectedItem().toString().equals("HUESO")){
                valor = 0.6;
            }else if(comboProducto.getSelectedItem().toString().equals("MENUDO")){
                valor = 3;
            }else if(comboProducto.getSelectedItem().toString().equals("PELLEJO")){
                valor = 0.5;
            }
        }
    }
    private void inicializar() {
        Bundle datos = getIntent().getExtras();
        key = datos.getString("key");
        proveedor = datos.getString("proveedor");
        producto=datos.getString("producto");
        cantidad=datos.getString("cantidad");
        fecha=datos.getString("fecha");
        timestamp = datos.getString("timestamp");


        accion=datos.getString("accion");
        tvProveedor.setText(proveedor);
       comboProducto.setSelection(0);
        etCantidad.setText(cantidad);
        tvFecha.setText(fecha);
        tvTimestamp.setText(timestamp);
    }


    public void compartir(View v) {

        if(tvProveedor.getText().toString().equals("") || comboProducto.getSelectedItem().toString().equals("SELECCIONE") || tvFecha.getText().toString().equals("") ||
                tvTimestamp.getText().toString().equals("") || etCantidad.getText().toString().equals("")){
            Toast.makeText(Inicio.this,
                    "Por favor ingrese todos los campos necesarios.",Toast.LENGTH_SHORT).show();
        }
        else{
            String proveedor = tvProveedor.getText().toString();
            String producto = comboProducto.getSelectedItem().toString();
            String cantidad = etCantidad.getText().toString();
            String fecha = tvFecha.getText().toString();

            if(tvProveedor.getText().toString().equals("ANDRES")) {
                String date_s = fecha;
                SimpleDateFormat dt = new SimpleDateFormat("yyyyy-MM-dd hh:mm:ss");
                Date date = null;
                try {
                    date = dt.parse(date_s);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat dt1 = new SimpleDateFormat("E dd/MMM/yy");

                Intent compartir = new Intent(android.content.Intent.ACTION_SEND);
                compartir.setType("text/plain");
                String mensaje = "La cuenta de " + producto + " del día " + dt1.format(date) + " fue de " + cantidad + " lb.";
                compartir.putExtra(android.content.Intent.EXTRA_SUBJECT, "Empleos Baja App");
                compartir.putExtra(android.content.Intent.EXTRA_TEXT, mensaje);
                startActivity(Intent.createChooser(compartir, "Compartir vía"));
            }
        }
    }

    public void guardar(View v) {

        if(tvProveedor.getText().toString().equals("") || comboProducto.getSelectedItem().toString().equals("SELECCIONE") || tvFecha.getText().toString().equals("") ||
           tvTimestamp.getText().toString().equals("") || etCantidad.getText().toString().equals("")){
            Toast.makeText(Inicio.this,
                    "Por favor ingrese todos los campos necesarios.",Toast.LENGTH_SHORT).show();
        }
        else{
            String proveedor = tvProveedor.getText().toString();
            String producto = comboProducto.getSelectedItem().toString();
            String cantidad = etCantidad.getText().toString();
            String fecha = tvFecha.getText().toString();
            String timestamp = tvTimestamp.getText().toString();
            String proveedor_timestamp = proveedor + "_" + timestamp;
            valor();
            String precio = String.format("%.2f",Double.parseDouble(cantidad) * valor);

        Pesa pesa = new Pesa(proveedor,producto,cantidad,fecha,timestamp,proveedor_timestamp,precio);

        if (accion.equals("a")) { //Agregar usando push()
            Ventas.refDetalle.push().setValue(pesa);
            Toast.makeText(getApplicationContext(), "Pesa ingresada.", Toast.LENGTH_LONG).show();
        }
        else // Editar usando setValue
        {
            Ventas.refDetalle.child(key).setValue(pesa);
            Toast.makeText(getApplicationContext(), "Pesa modificada.", Toast.LENGTH_LONG).show();
        }
        finish();
        }
    }

    private void initializeUI() {
       tvProveedor = findViewById(R.id.tvproveedor);
       comboProducto = findViewById(R.id.spn_producto);
       etCantidad = findViewById(R.id.et_cantidad);
       tvFecha = findViewById(R.id.tv_fecha);
       tvTimestamp = findViewById(R.id.tv_timestamp);
       btnFecha = findViewById(R.id.btnfecha);
       btnCompartir = findViewById(R.id.btncompartir);
       btnEnviar= findViewById(R.id.btnenviar);
       btnSalir = findViewById(R.id.btnsalir);
    }
}