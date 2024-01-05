package com.example.coles;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class Linea {
    private byte nroLinea;
    private int[] paradas;
    private int[] offsets;

    private ArrayList<Turno> turnos = new ArrayList<>();

    public Linea(String nombre, int nroLinea){
        this.nroLinea = (byte) nroLinea;
    }

    /**
     * Genera un turno y lo guarda en la línea, en base a un arreglo con un formato específico
     * @param arr tal que arr.lenght = 4 y
     *            arr[0]: id de la parada de la primera salida
     *            arr[1]: horario de la primera salida
     *            arr[2]: id de la ultima parada
     *            arr[3]: horario de la ultima parada
     */
    public void agregarTurno(String[] arr){
        Parada paradaInicial = new Parada(
                Integer.parseInt(arr[0]),
                Integer.parseInt(arr[1].substring(0,2)),
                Integer.parseInt(arr[1].substring(3)));
        Parada paradaFinal = new Parada(
                Integer.parseInt(arr[2]),
                Integer.parseInt(arr[3].substring(0,2)),
                Integer.parseInt(arr[3].substring(3)));

        turnos.add(new Turno(paradaInicial, paradaFinal));

    }

    public byte getNroLinea() {
        return nroLinea;
    }

    public ArrayList<Turno> getTurnos() {
        return turnos;
    }

    public int[] getParadas() {
        return paradas;
    }

    public void setParadas(int[] paradas) {
        this.paradas = paradas;
    }

    public void setOffsets(int[] offsets) {
        this.offsets = offsets;
    }

    /**
     * Determina la hora de llegada de la próxima unidad de la línea a determinada parada
     * @param idParada
     * @return String con formato 24hs "HH:MM"
     * @throws FueraDeHorarioException
     */
    public String obtenerProximaHoraLlegada(int idParada) throws FueraDeHorarioException {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"));
        int horaActual = calendar.get(Calendar.HOUR_OF_DAY);
        int minutosActual = calendar.get(Calendar.MINUTE);

        ProximasLlegadasLista listaRecycler = ProximasLlegadasLista.getInstance();
        listaRecycler.limpiarLista();

        if(horaActual == 0)
            horaActual = 24; // para simplificar comparaciones entre horas

        // buscar el turno de la línea más proximo a la parada
        int[] llegadaLinea = null;
        for (Turno turno : turnos) {
            // TODO: Refactorizar utilizando la clase Hora
            int[] llegadaTurno = turno.obtenerProximoArriboAParada(idParada, horaActual, minutosActual, paradas, offsets);
            if(llegadaLinea == null)
                llegadaLinea = llegadaTurno;
            else if(llegadaTurno[0] < llegadaLinea[0])
                llegadaLinea = llegadaTurno;
            else if(llegadaTurno[0] == llegadaLinea[0] && llegadaTurno[1] < llegadaLinea[1])
                llegadaLinea = llegadaTurno;
        }

        listaRecycler.ordenarLista();

        if(llegadaLinea[0] == 24)
            llegadaLinea[0] = 0;

        return String.format("%d:%02d",
                llegadaLinea[0], llegadaLinea[1]);
    }
}
