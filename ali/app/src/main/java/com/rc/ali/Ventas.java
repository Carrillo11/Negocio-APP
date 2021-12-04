package com.rc.ali;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class Ventas extends AppCompatActivity {
    TextView tvFecha_I, tvFecha_F;
    Button btnFecha_I, btnFecha_F, btnProveedores;
    Spinner comboProveedor;
    int cyear, cday, cmonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventas);

        initializeUI();

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
                        tvFecha_I.setText(dayOfMonth+"/"+(month+1)+"/"+year);
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
                        tvFecha_F.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },cyear,cmonth,cday);
                datePickerDialog.show();
            }
        });

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.comobo_proveedor, android.R.layout.simple_spinner_item);
        comboProveedor.setAdapter(adapter);
    }

    private void initializeUI() {
        btnProveedores=findViewById(R.id.btnproveedor);
        btnFecha_I = findViewById(R.id.btnfecha_i);
        btnFecha_F = findViewById(R.id.btnfecha_f);
        tvFecha_I = findViewById(R.id.tvfecha_i);
        tvFecha_F = findViewById(R.id.tvfecha_f);
        comboProveedor = findViewById(R.id.spn_proveedor);
       // btnPesas = findViewById(R.id.btnpesas);
    }

}