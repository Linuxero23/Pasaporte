package co.poli.edu.actividad1.servicios;

public class Policia implements EntidadGubernamental {
    private EntidadMediator mediator;

    @Override
    public void setMediator(EntidadMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String notificado(String id) {
        return "La Policía ha sido notificada de los cambios del pasaporte con ID " + id;
    }

    @Override
    public String enviarMensaje(String mensaje, EntidadGubernamental receptor) {
        return mediator.enviarMensaje(this, receptor, mensaje);
    }
}
