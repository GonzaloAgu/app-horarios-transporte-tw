package com.example.coles;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

public class HorariosActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] listaParadas;
    private ArrayList<Linea> listaLineas = new ArrayList<>();
    private Linea lineaSeleccionada;
    private int idParadaSeleccionada;

    private TextView mensajeInicial;
    private TextView proximaLlegada;
    private RecyclerView otrasLlegadas;
    private TextView botonConsultar;

    private Spinner spinnerLineas;
    private Spinner spinnerZonas;

    private ArrayAdapter<CharSequence> adapterLineas;
    private ArrayAdapter<CharSequence> adapterZonas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios);
        Objects.requireNonNull(getSupportActionBar()).hide();
        Resources res = getResources();

        configurarVistas();
        configurarSpinners();
        Carga.cargarLineas(res, listaLineas);
        listaParadas = res.getStringArray(R.array.paradas_array);
    }


    @Override
    public void onClick(View view) {
        Animacion.clickBoton(view);
        if(view.getId() == R.id.botonConsultar) {
            mensajeInicial.setText("\nPróxima llegada...");
            Carga.cargarTurnos(getResources(), lineaSeleccionada, listaLineas);
            try {
                proximaLlegada.setText(lineaSeleccionada.obtenerProximaHoraLlegada(idParadaSeleccionada));
                proximaLlegada.setVisibility(View.VISIBLE);
            } catch(FueraDeHorarioException e) {
                mensajeInicial.setText(String.format("La linea %d no pasará por %s hasta mañana",
                        lineaSeleccionada.getNroLinea(), listaParadas[idParadaSeleccionada]));
                proximaLlegada.setVisibility(View.GONE);
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

            LlegadaRecyclerViewAdapter RVadapter = new LlegadaRecyclerViewAdapter(this, ProximasLlegadasLista.getInstance().getLlegadas());
            otrasLlegadas.setAdapter(RVadapter);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);

            otrasLlegadas.setLayoutManager(layoutManager);
            int posScroll = ProximasLlegadasLista.getInstance().getPosActual();
            layoutManager.scrollToPositionWithOffset(posScroll+1, 0);
        }
    }

    private void configurarSpinners(){
        // Asignar spinners
        spinnerLineas = findViewById(R.id.spinnerLineas);
        spinnerZonas = findViewById(R.id.spinnerZonas);

        // Spinner de parada desactivado hasta que se elija una línea
        setDisponibilidad(spinnerZonas, false);

        // Adaptadores para spinners
        adapterLineas = ArrayAdapter.createFromResource(
                this,
                R.array.lineas_array,
                android.R.layout.simple_spinner_dropdown_item
        );

        adapterZonas = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item);


        // Aplica adaptadores a Spinners
        spinnerLineas.setAdapter(adapterLineas);
        spinnerZonas.setAdapter(adapterZonas);

        spinnerLineas.bringToFront();
        spinnerZonas.bringToFront();

        spinnerLineas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // ajusta el texto seleccionado en el spinner
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) parentView.getChildAt(0)).setTextSize(20);

                if(position != 0){
                    lineaSeleccionada = listaLineas.get(position-1);
                    actualizarParadas();
                }
                // desactiva y activa segundo spinner y botón de consulta
                setDisponibilidad(spinnerZonas, position != 0);
                setDisponibilidad(botonConsultar, position != 0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                lineaSeleccionada = null;
            }
        });

        spinnerZonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id){
                ((TextView) parentView.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) parentView.getChildAt(0)).setTextSize(20);

                idParadaSeleccionada = lineaSeleccionada.getParadas()[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });
    }

    /** Actualiza el spinner de paradas de acuerdo a la línea que se seleccionó
     * */
    private void actualizarParadas(){
        adapterZonas.clear();
        for(int p : lineaSeleccionada.getParadas()){
            adapterZonas.add(listaParadas[p]);
        }
        spinnerZonas.setAdapter(adapterZonas);
    }

    private void setDisponibilidad(View view, boolean esVisible) {
        view.setEnabled(esVisible);
        if(esVisible)
            view.setAlpha((float)1);
        else
            view.setAlpha((float)0.2);
    }

    private void configurarVistas(){
        mensajeInicial = findViewById(R.id.mensajeInicial);
        botonConsultar = findViewById(R.id.botonConsultar);
        proximaLlegada = findViewById(R.id.proximaLlegada);
        otrasLlegadas = findViewById(R.id.otrasLlegadas);

        botonConsultar.setOnClickListener(this::onClick);
        // desactiva botón de consulta
        setDisponibilidad(botonConsultar, false);
    }

}