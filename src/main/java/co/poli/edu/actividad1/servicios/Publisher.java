package co.poli.edu.actividad1.servicios;
import java.util.*;
public class Publisher {
    List<EntidadGubernamental> suscribers;
    public Publisher(){
        suscribers = new LinkedList<>();
    }
    public String suscribe(EntidadGubernamental entidad){
        boolean existeMismoTipo = suscribers.stream()
                .anyMatch(f -> f.getClass().equals(entidad.getClass()));

        if (!existeMismoTipo) {
            suscribers.add(entidad);
            return ("Subcripcion correcta de: " + entidad.getClass().getSimpleName());
        }
        return ("Ya esta suscrito la: " + entidad.getClass().getSimpleName());
    }
    public String unsuscribe(EntidadGubernamental entidad) {
        // Busca si existe alguna entidad del mismo tipo en la lista
        boolean eliminado = suscribers.removeIf(f -> f.getClass().equals(entidad.getClass()));

        if (eliminado) {
            return "Se eliminó la suscripción de: " + entidad.getClass().getSimpleName();
        } else {
            return "No hay suscripción de tipo: " + entidad.getClass().getSimpleName();
        }
    }
    public String notify(String id){
        String s="Fueron notificadas las siguientes entidades:\n";
        for(EntidadGubernamental entidad : suscribers){
            System.out.println(entidad.notificado(id));
            s+=entidad.getClass().getSimpleName()+"\n";
        }
        return s;
    }

}
