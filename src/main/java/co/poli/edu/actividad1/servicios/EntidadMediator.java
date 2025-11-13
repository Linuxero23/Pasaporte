package co.poli.edu.actividad1.servicios;

import java.util.HashMap;
import java.util.Map;

public class EntidadMediator {
    private Map<String, EntidadGubernamental> entidades = new HashMap<>();

    public void registrarEntidad(String nombre, EntidadGubernamental entidad) {
        entidades.put(nombre, entidad);
        entidad.setMediator(this);
    }

    public String enviarMensaje(EntidadGubernamental emisor, EntidadGubernamental receptor, String mensaje) {
        for (Map.Entry<String, EntidadGubernamental> entry : entidades.entrySet()) {
            if (entry.getValue() == receptor) {
                return entry.getKey() + " recibió mensaje de " + obtenerNombre(emisor) + ": " + mensaje;
            }
        }
        return "Receptor no encontrado.";
    }

    private String obtenerNombre(EntidadGubernamental entidad) {
        for (Map.Entry<String, EntidadGubernamental> entry : entidades.entrySet()) {
            if (entry.getValue() == entidad) {
                return entry.getKey();
            }
        }
        return "Entidad desconocida";
    }
}
