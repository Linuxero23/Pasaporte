package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.Pasaporte;

public class AdaptadorPasaporte implements InterfacePasaporte {
    private final Pasaporte pasaporte;

    public AdaptadorPasaporte(Pasaporte pasaporte) {
        this.pasaporte = pasaporte;
    }

    @Override
    public String getDescripcion() {
        return "Pasaporte ID: " + pasaporte.getId() +
                ", Titular ID: " + pasaporte.getTitular() +
                ", País ID: " + pasaporte.getPais() +
                ", Fecha de expedición: " + pasaporte.getFechaEx() +
                ", Elemento de seguridad: " + pasaporte.getIdElemento();
    }

    public Pasaporte getPasaporte() {
        return pasaporte;
    }
}
