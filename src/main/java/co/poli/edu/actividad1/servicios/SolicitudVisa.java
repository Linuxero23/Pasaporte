package co.poli.edu.actividad1.servicios;

public class SolicitudVisa implements EstadoPais{

    @Override
    public String mostrarEstado() {
        return "solicitud de visa";
    }

    @Override
    public void cambiarEstado(AdaptadorPais pais, EstadoPais nuevoEstado) {

        if (nuevoEstado instanceof EstadoNormal || nuevoEstado instanceof FronteraCerrada) {
            pais.setEstado(nuevoEstado);

        }

    }
}
