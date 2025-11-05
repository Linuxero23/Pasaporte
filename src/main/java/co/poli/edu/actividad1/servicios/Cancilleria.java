package co.poli.edu.actividad1.servicios;

public class Cancilleria implements EntidadGubernamental{
    @Override
    public String notificado(String id) {
        return "La cancilleria a sido notificada de los cambios del pasaporte del usuario con ID "+id;
    }
}
