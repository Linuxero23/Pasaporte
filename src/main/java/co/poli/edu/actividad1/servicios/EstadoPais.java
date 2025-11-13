package co.poli.edu.actividad1.servicios;
import java.util.List;

public interface EstadoPais {
    String mostrarEstado();
    void cambiarEstado(AdaptadorPais pais, EstadoPais nuevoEstado);
    List<EstadoPais> obtenerEstadosDisponibles();
}
