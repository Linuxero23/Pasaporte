package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.Pasaporte;

public interface Handler {
    void setNext(Handler next);
    String handle(Pasaporte p);
}
