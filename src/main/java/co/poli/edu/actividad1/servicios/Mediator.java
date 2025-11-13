package co.poli.edu.actividad1.servicios;

public interface Mediator {
    void enviarMensaje(String mensaje, EntidadGubernamental origen, EntidadGubernamental destino);
    void registrarEntidad(String nombre, EntidadGubernamental entidad);
}
