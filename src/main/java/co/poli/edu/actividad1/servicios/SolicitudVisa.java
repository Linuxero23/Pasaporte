package co.poli.edu.actividad1.servicios;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<EstadoPais> obtenerEstadosDisponibles() {
        List<EstadoPais> disponibles = new ArrayList<>();
        disponibles.add(new EstadoNormal());
        disponibles.add(new FronteraCerrada());

        return disponibles;
    }
}
