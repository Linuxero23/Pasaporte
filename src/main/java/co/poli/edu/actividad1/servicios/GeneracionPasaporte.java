package co.poli.edu.actividad1.servicios;

public class GeneracionPasaporte {
    private VerificacionId verificacionId;


    public GeneracionPasaporte(VerificacionId verificacionId) {
        this.verificacionId = verificacionId;
    }


    public boolean generarPasaporte(String id) {
        System.out.println("Generando Pasaporte para el documento: "+id);
        return true;
    }
}
