package co.poli.edu.actividad1.servicios;

public class Policia implements EntidadGubernamental{
    @Override
    public String notificado(String id) {
        return "La policia a sido notificada de los cambios del pasaporte del usuario con ID "+id;
    }
}
