package com.example.coles;

import android.content.res.Resources;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class Carga {
    /**
     * Carga los datos básicos de los turnos para una línea, en caso de ser necesario.
     * @param res: elemento getResources()
     * @param lineaSeleccionada: linea actualmente seleccionada en el spinner de líneas
     * @param listaLineas: todas las líneas del sistema
     *
     */
    public static void cargarTurnos(Resources res, Linea lineaSeleccionada, ArrayList<Linea> listaLineas){
        switch(lineaSeleccionada.getNroLinea()){
            case 1:
                Linea l1 = listaLineas.get(0);
                if(l1.getTurnos().isEmpty()){
                    l1.agregarTurno(res.getStringArray(R.array.l1t1));
                    l1.agregarTurno(res.getStringArray(R.array.l1t2));
                    l1.agregarTurno(res.getStringArray(R.array.l1t3));
                    l1.agregarTurno(res.getStringArray(R.array.l1t4));
                    l1.setOffsets(res.getIntArray(R.array.offset_linea1));
                    listaLineas.set(0, l1);
                }
                break;
            case 2:
                Linea l2 = listaLineas.get(1);
                if(l2.getTurnos().isEmpty()){
                    l2.agregarTurno(res.getStringArray(R.array.l2t1));
                    l2.agregarTurno(res.getStringArray(R.array.l2t2));
                    l2.agregarTurno(res.getStringArray(R.array.l2t3));
                    l2.agregarTurno(res.getStringArray(R.array.l2t4));
                    l2.setOffsets(res.getIntArray(R.array.offset_linea2));
                    listaLineas.set(1, l2);
                }
                break;
            case 3:
                Linea l3 = listaLineas.get(2);
                if(l3.getTurnos().isEmpty()){
                    l3.agregarTurno(res.getStringArray(R.array.l3t1));
                    l3.agregarTurno(res.getStringArray(R.array.l3t2));
                    l3.agregarTurno(res.getStringArray(R.array.l3t3));
                    l3.setOffsets(res.getIntArray(R.array.offset_linea3));
                    listaLineas.set(2, l3);
                }
                break;
            case 4:
                Linea l4 = listaLineas.get(3);
                if(l4.getTurnos().isEmpty()){
                    l4.agregarTurno(res.getStringArray(R.array.l4t1));
                    l4.agregarTurno(res.getStringArray(R.array.l4t2));
                    l4.agregarTurno(res.getStringArray(R.array.l4t3));
                    l4.setOffsets(res.getIntArray(R.array.offset_linea4));
                    listaLineas.set(3, l4);
                }
                break;
        }
    }

    public static void cargarLineas(Resources res, ArrayList<Linea> listaLineas){
        String[] nombreLineas = res.getStringArray(R.array.lineas_array);

        int i = 1;
        for(String l : nombreLineas) {
            listaLineas.add(new Linea(l, i++));
        }

        listaLineas.get(0).setParadas(res.getIntArray(R.array.paradas_linea1));
        listaLineas.get(1).setParadas(res.getIntArray(R.array.paradas_linea2));
        listaLineas.get(2).setParadas(res.getIntArray(R.array.paradas_linea3));
        listaLineas.get(3).setParadas(res.getIntArray(R.array.paradas_linea4));

    }


    public static int posicionEnArreglo(int[] arr, int elemento) {
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == elemento)
                return i;
        }
        return -1;
    }
}
