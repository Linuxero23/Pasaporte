package co.poli.edu.actividad1.servicios;
import java.util.ArrayList;
import java.util.List;

public class EstadoRevision implements EstadoPais{

    @Override
    public String mostrarEstado() {
        return "estado de revisión";
    }

    @Override
    public void cambiarEstado(AdaptadorPais pais, EstadoPais nuevoEstado) {

        if (nuevoEstado instanceof SolicitudVisa) {
            pais.setEstado(nuevoEstado);
        }
    }

    @Override
    public List<EstadoPais> obtenerEstadosDisponibles() {
        List<EstadoPais> disponibles = new ArrayList<>();
        disponibles.add(new SolicitudVisa());

        return disponibles;
    }
}
