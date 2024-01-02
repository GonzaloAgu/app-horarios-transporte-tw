package com.example.coles;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView botonHorarios;
    private TextView botonRecorridos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        botonHorarios = findViewById(R.id.botonHorarios);
        botonRecorridos = findViewById(R.id.botonRecorridos);

        botonRecorridos.setOnClickListener(this::onClick);
        botonHorarios.setOnClickListener(this::onClick);

    }

    @Override
    public void onClick(View view) {
        Animacion.clickBoton(view);

        switch (view.getId()) {
            case R.id.botonHorarios:
                abrirActividadHorarios();
                break;
            case R.id.botonRecorridos:
                Toast.makeText(this, "Funcionalidad en desarrollo.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void abrirActividadHorarios() {
        // Crea un Intent para la actividad de horarios
        Intent intent = new Intent(this, HorariosActivity.class);

        // Inicia la actividad de horarios
        startActivity(intent);
    }


}