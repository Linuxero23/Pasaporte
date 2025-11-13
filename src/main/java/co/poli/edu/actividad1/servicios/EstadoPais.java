package co.poli.edu.actividad1.servicios;

public interface EstadoPais {
    String mostrarEstado();
    void cambiarEstado(AdaptadorPais pais, EstadoPais nuevoEstado);
}
