package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.Pasaporte;
import co.poli.edu.actividad1.modelo.PasaporteEmergencia;

public class HandlerEmergencia extends HandlerBase {

    @Override
    protected boolean canHandle(Pasaporte p) {
        return p instanceof PasaporteEmergencia;
    }

    @Override
    protected String process(Pasaporte p) {
        return "Procesado por Handler de Emergencia → Pasaporte temporal.";
    }
}
