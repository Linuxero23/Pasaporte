package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.Pasaporte;
import co.poli.edu.actividad1.modelo.PasaporteDiplomatico;

public class HandlerDiplomatico extends HandlerBase {

    @Override
    protected boolean canHandle(Pasaporte p) {
        return p instanceof PasaporteDiplomatico;
    }

    @Override
    protected String process(Pasaporte p) {
        PasaporteDiplomatico pd = (PasaporteDiplomatico) p;
        String mision = (pd.getMision() != null && !pd.getMision().isEmpty())
                ? pd.getMision()
                : "Embajada";
        return "Procesado por Handler Diplomático → Misión: " + mision;
    }

}
