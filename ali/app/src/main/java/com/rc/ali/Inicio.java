package com.rc.ali;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {
    private Button  btnSalir, btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        initializeUI();

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

    private void initializeUI() {
       btnSalir=findViewById(R.id.btnsalir);

    }
}