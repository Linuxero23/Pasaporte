package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.Pasaporte;


public class PasaporteService {

    private final Handler cadena;

    public PasaporteService() {

        Handler ordinario = new HandlerOrdinario();
        Handler diplomatico = new HandlerDiplomatico();
        Handler emergencia = new HandlerEmergencia();


        ordinario.setNext(diplomatico);
        diplomatico.setNext(emergencia);


        this.cadena = ordinario;
    }

    /**
     * Envía el pasaporte a través de la cadena de responsabilidad.
     * Cada handler decidirá si puede procesarlo o lo pasa al siguiente.
     *
     * @param pasaporte objeto del modelo a procesar
     * @return mensaje indicando qué handler lo procesó
     */
    public String procesarPasaporte(Pasaporte pasaporte) {
        if (pasaporte == null) {
            return "Error: no se recibió ningún pasaporte.";
        }
        return cadena.handle(pasaporte);
    }
}
