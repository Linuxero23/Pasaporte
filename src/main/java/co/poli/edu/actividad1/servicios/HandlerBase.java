package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.Pasaporte;

public abstract class HandlerBase implements Handler {
    protected Handler next;

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public String handle(Pasaporte p) {
        if (canHandle(p)) {
            return process(p);
        } else if (next != null) {
            return next.handle(p);
        } else {
            return "No se pudo procesar el pasaporte: tipo desconocido.";
        }
    }

    protected abstract boolean canHandle(Pasaporte p);
    protected abstract String process(Pasaporte p);
}
