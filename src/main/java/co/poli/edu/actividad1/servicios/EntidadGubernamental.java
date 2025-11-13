package co.poli.edu.actividad1.servicios;

public interface EntidadGubernamental {
    void setMediator(EntidadMediator mediator);
    String notificado(String id);
    String enviarMensaje(String mensaje, EntidadGubernamental receptor);
}
