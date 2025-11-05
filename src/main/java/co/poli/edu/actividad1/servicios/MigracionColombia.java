package co.poli.edu.actividad1.servicios;

public class MigracionColombia implements EntidadGubernamental{
    @Override
    public String notificado(String id) {
        return "MigracionColombia a sido notificada de los cambios del pasaporte del usuario con ID "+id;
    }
}
