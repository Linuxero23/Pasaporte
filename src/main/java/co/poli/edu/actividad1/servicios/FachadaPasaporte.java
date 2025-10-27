package co.poli.edu.actividad1.servicios;

public class FachadaPasaporte {

    private VerificacionId verificacionId;
    private VerificacionAnt verificacionAnt;
    private GeneracionPasaporte generacionPasaporte;

    public FachadaPasaporte() {
        this.verificacionId = new VerificacionId();
        this.verificacionAnt = new VerificacionAnt();
        this.generacionPasaporte = new GeneracionPasaporte();
    }

    public String procesarSolicitud(String id) {

        if (!verificacionId.verificarId(id)) {
            return "ID invalido :c";
        }
        System.out.println("Verificación de ID existosa");

        if (!verificacionAnt.verificarAnt(id)) {
            return "Verificación de Antecedentes fallida: Es ladronzuelo >:c";
        }
        System.out.println("Verificación de Antecedentes existosa: Sin antecendentes");

        generacionPasaporte.generarPasaporte(id);

        return ("Pasaporte generado exitosamente");
    }
}
