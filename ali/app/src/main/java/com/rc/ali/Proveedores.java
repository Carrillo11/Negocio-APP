package com.rc.ali;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Proveedores extends AppCompatActivity {
    TextView tvnombre_A, tvnombre_N;
    Button btnagregar_A, btnagregar_N, btnCuentas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedores);
        initializeUI();

       /* btnPesas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inicio.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
                finish();
            }
        });*/
        btnagregar_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inicio.class);
                intent.putExtra("dato", tvnombre_A.getText().toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
                //finish();
            }
        });

        btnagregar_N.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Inicio.class);
                intent.putExtra("dato", tvnombre_N.getText().toString());
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
                //finish();
            }
        });

        btnCuentas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Ventas.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(intent, 0);
                overridePendingTransition(0,0);
                finish();
            }
        });


    }
    private void initializeUI() {
        tvnombre_A = findViewById(R.id.tvnombre_a);
        tvnombre_N = findViewById(R.id.tvnombre_n);
        btnCuentas=findViewById(R.id.btncuentas);
        btnagregar_A=findViewById(R.id.btnagregar_a);
        btnagregar_N=findViewById(R.id.btnagregar_n);
        //btnPesas = findViewById(R.id.btnpesas);
    }
}