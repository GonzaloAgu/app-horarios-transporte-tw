package com.example.coles;

public class Turno {
    final Parada paradaInicial;
    final Parada paradaFinal;

    public Turno(Parada paradaInicial, Parada paradaFinal) {
        this.paradaInicial = paradaInicial;
        this.paradaFinal = paradaFinal;
    }

    /**
     *
     * @param idParada identificador de la parada
     * @param ahora Hora actual
     * @param ordenParadas arreglo que enumera IDs de las paradas que abarca la línea
     * @param offsets referido al arreglo anterior; arreglo que enumera los minutos que se demora en llegar a cada parada.
     * @return [ hora de llegada, minuto de llegada]
     */
    public Hora obtenerProximoArriboAParada(int idParada, Hora ahora, int[] ordenParadas, int[] offsets) {

        // se crean dos "paradas teóricas", una siendo la parada de partida de la unidad y otra la del usuario
        Hora aux = new Hora(paradaInicial.getHorario().getHora(), paradaInicial.getHorario().getMinuto());
        Hora objetivo = new Hora(ahora.getHora(), ahora.getMinuto());

        // hallar posición en ordenParadas de la parada inicial y la parada del usuario, guardarla en j
        int j = Carga.posicionEnArreglo(ordenParadas, paradaInicial.getIdParada());
        int j2 = Carga.posicionEnArreglo(ordenParadas, idParada);

        // se suman los minutos desde la hora de salida del bondi hasta la parada del usuario
        while(j != j2){
            if(j == offsets.length-1)
                j = 0;
            else
                j++;
            aux.sumarMinutos(offsets[j]);
        }

        // se obtiene el tiempo de ciclo de la unidad (vuelta completa en el recorrido)
        int minutosEntrePasadas = 0;
        for (int offset : offsets) {
            minutosEntrePasadas += offset;
        }

        ProximasLlegadasLista listaLlegadas = ProximasLlegadasLista.getInstance();

        // se suman vueltas hasta llegar a un horario posterior a la hora actual y está dentro de horario
        while(aux.esAnteriorA(objetivo) && aux.esAnteriorA(paradaFinal.getHorario())){
            listaLlegadas.agregarLlegada(new Hora(aux.getHora(), aux.getMinuto()));
            listaLlegadas.incrementarPosActual();
            aux.sumarMinutos(minutosEntrePasadas);
        }

        // guardo respuesta a retornar
        Hora proximaLlegada = new Hora(aux.getHora(), aux.getMinuto());

        // si ninguna parada cumplió el requisito
        if(aux.esAnteriorA(objetivo) || paradaFinal.getHorario().esAnteriorA(aux)){
            if(aux.equals(paradaFinal))
                listaLlegadas.agregarLlegada(new Hora(aux.getHora(), aux.getMinuto()));
            return null;
        }

        // lleno el recyclerview con los horarios completos
        while((aux.esAnteriorA(paradaFinal.getHorario()) || aux.equals(paradaFinal.getHorario())) && aux.getHora() >= 5){
            listaLlegadas.agregarLlegada(new Hora(aux.getHora(), aux.getMinuto()));
            aux.sumarMinutos(minutosEntrePasadas);
        }
        return proximaLlegada;
    }
}
