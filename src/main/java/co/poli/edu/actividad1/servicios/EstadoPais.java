package co.poli.edu.actividad1.servicios;
import java.util.List;

public interface EstadoPais {
    public String mostrarEstado();
    public void cambiarEstado(AdaptadorPais pais, EstadoPais nuevoEstado);
    List<EstadoPais> obtenerEstadosDisponibles();
}
