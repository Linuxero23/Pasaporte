package co.poli.edu.actividad1.servicios;
import java.util.ArrayList;
import java.util.List;

public class FronteraCerrada implements EstadoPais{

    @Override
    public String mostrarEstado() {
        return "frontera cerrada";
    }

    @Override
    public void cambiarEstado(AdaptadorPais pais, EstadoPais nuevoEstado) {

        if (nuevoEstado instanceof EstadoNormal || nuevoEstado instanceof SolicitudVisa) {
            pais.setEstado(nuevoEstado);
        }
    }

    @Override
    public List<EstadoPais> obtenerEstadosDisponibles() {
        List<EstadoPais> disponibles = new ArrayList<>();
        disponibles.add(new EstadoNormal());
        disponibles.add(new SolicitudVisa());

        return disponibles;
    }
}
