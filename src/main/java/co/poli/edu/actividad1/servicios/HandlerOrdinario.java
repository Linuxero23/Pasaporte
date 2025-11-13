package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.Pasaporte;
import co.poli.edu.actividad1.modelo.PasaporteOrdinario;

public class HandlerOrdinario extends HandlerBase {

    @Override
    protected boolean canHandle(Pasaporte p) {
        return p instanceof PasaporteOrdinario;
    }

    @Override
    protected String process(Pasaporte p) {
        PasaporteOrdinario po = (PasaporteOrdinario) p;
        return "Procesado por Handler Ordinario → Titular: " + po.getTitular();
    }
}
