package co.poli.edu.actividad1.servicios;
import java.util.*;
public class Publisher {
    List<EntidadGubernamental> suscribers;
    Publisher(){
        suscribers = new LinkedList<>();
    }
    String suscribe(EntidadGubernamental entidad){
        boolean existeMismoTipo = suscribers.stream()
                .anyMatch(f -> f.getClass().equals(entidad.getClass()));

        if (!existeMismoTipo) {
            suscribers.add(entidad);
            return ("Subcripcion correcta de: " + entidad.getClass().getSimpleName());
        }
        return ("Ya esta suscrito la: " + entidad.getClass().getSimpleName());

    }

}
