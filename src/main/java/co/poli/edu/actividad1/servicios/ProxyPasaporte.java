package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.Pasaporte;

public class ProxyPasaporte implements InterfacePasaporte {

    private AdaptadorPasaporte adaptadorPasaporte;
    private boolean accesoPermitido = false;

    public ProxyPasaporte(Pasaporte pasaporte, String rolUsuario) {
        if ("ADMIN".equalsIgnoreCase(rolUsuario) || "FUNCIONARIO".equalsIgnoreCase(rolUsuario)) {
            accesoPermitido = true;
            this.adaptadorPasaporte = new AdaptadorPasaporte(pasaporte);
        }
    }

    @Override
    public String getDescripcion() {
        if (!accesoPermitido) {
            return "Acceso denegado: no tiene permisos para ver la información del pasaporte.";
        }
        return adaptadorPasaporte.getDescripcion();
    }

    public void registrarAcceso(String usuario) {
        System.out.println("Registro de acceso al pasaporte por: " + usuario);
    }
}
