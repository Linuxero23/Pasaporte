package co.poli.edu.actividad1.servicios;
import java.util.ArrayList;
import java.util.List;

public class EstadoNormal implements EstadoPais {

    @Override
    public String mostrarEstado() {
        return "estado normal";
    }

    @Override
    public void cambiarEstado(AdaptadorPais pais, EstadoPais nuevoEstado) {

        if (nuevoEstado instanceof EstadoRevision || nuevoEstado instanceof SolicitudVisa || nuevoEstado instanceof FronteraCerrada) {
            pais.setEstado(nuevoEstado);

        }
    }

    @Override
    public List<EstadoPais> obtenerEstadosDisponibles() {
        List<EstadoPais> disponibles = new ArrayList<>();
        disponibles.add(new EstadoRevision());
        disponibles.add(new SolicitudVisa());
        disponibles.add(new FronteraCerrada());
        return disponibles;
    }
}
