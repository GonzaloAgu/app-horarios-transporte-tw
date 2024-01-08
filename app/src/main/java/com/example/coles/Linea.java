package com.example.coles;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class Linea {
    private final byte nroLinea;
    private final String nombre;
    private int[] paradas;
    private int[] offsets;

    private final ArrayList<Turno> turnos = new ArrayList<>();

    public Linea(String nombre, int nroLinea){
        this.nombre = nombre;
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
     * @param idParada identificador de la parada
     * @return String con formato 24hs "HH:MM"
     * @throws FueraDeHorarioException cuando no se esperan llegadas en el día
     */
    public String
    obtenerProximaHoraLlegada(int idParada) throws FueraDeHorarioException {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT-3:00"));
        Hora ahora = new Hora(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        ProximasLlegadasLista listaRecycler = ProximasLlegadasLista.getInstance();
        listaRecycler.limpiarLista();

        // buscar el turno de la línea más proximo a la parada
        Hora llegadaLinea = null;
        for (Turno turno : turnos) {
            // TODO: Refactorizar utilizando la clase Hora
            Hora llegadaTurno = turno.obtenerProximoArriboAParada(idParada, ahora, paradas, offsets);
            if(llegadaTurno != null){
                if(llegadaLinea == null)
                    llegadaLinea = llegadaTurno;
                else if(llegadaTurno.compareTo(llegadaLinea) == -1)
                    llegadaLinea = llegadaTurno;
            }
        }

        listaRecycler.ordenarLista();

        if(llegadaLinea == null)
            throw new FueraDeHorarioException();
        else
            return llegadaLinea.toString();
    }
}
