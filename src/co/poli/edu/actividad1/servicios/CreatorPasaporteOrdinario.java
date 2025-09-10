package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.Pasaporte;
import co.poli.edu.actividad1.modelo.PasaporteOrdinario;

public class CreatorPasaporteOrdinario implements FactoriaPasaporte {

    @Override
    public Pasaporte crearPasaporte() {
        return new PasaporteOrdinario();
    }
}
