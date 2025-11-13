package co.poli.edu.actividad1.servicios;
import co.poli.edu.actividad1.modelo.Pais;
import java.util.List;
import java.util.ArrayList;


public class AdaptadorPais {
    private Pais pais;
    private EstadoPais estadoActual;

    public AdaptadorPais(Pais pais){
        this.pais = pais;
        this.estadoActual = new EstadoNormal();
    }

    public void setEstado(EstadoPais estado) {
        this.estadoActual = estado;
    }

    public EstadoPais getEstado() {
        return estadoActual;
    }

    public Pais getPais() {
        return pais;
    }

    public String mostrarEstado() {
        return estadoActual.mostrarEstado();
    }

    public void cambiarEstado(EstadoPais nuevoEstado) {
        estadoActual.cambiarEstado(this, nuevoEstado);
    }

    public List<EstadoPais> obtenerEstadosDisponibles() {
        List<EstadoPais> disponibles = new ArrayList<>();

        if (estadoActual instanceof EstadoNormal) {
            disponibles.add(new EstadoRevision());
            disponibles.add(new SolicitudVisa());
            disponibles.add(new FronteraCerrada());
        } else if (estadoActual instanceof EstadoRevision) {
            disponibles.add(new SolicitudVisa());
        } else if (estadoActual instanceof SolicitudVisa) {
            disponibles.add(new EstadoNormal());
            disponibles.add(new FronteraCerrada());
        } else if (estadoActual instanceof FronteraCerrada) {
            disponibles.add(new EstadoNormal());
            disponibles.add(new SolicitudVisa());
        }

        return disponibles;
    }

}
