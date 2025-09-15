package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.Pasaporte;
import co.poli.edu.actividad1.modelo.PasaporteDiplomatico;

public class CreatorPasaporteDiplomatico implements FactoriaPasaporte{

    public Pasaporte crearPasaporte() {
        return new PasaporteDiplomatico();
    }
}
