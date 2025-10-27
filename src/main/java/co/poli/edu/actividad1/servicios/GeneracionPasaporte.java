package co.poli.edu.actividad1.servicios;

public class GeneracionPasaporte {
    private VerificacionId verificacionId;
    private VerificacionAnt verificacionAnt;


    public GeneracionPasaporte(VerificacionId verificacionId, VerificacionAnt verificacionAnt) {
        this.verificacionId = verificacionId;
        this.verificacionAnt = verificacionAnt;
    }


    public boolean generarPasaporte(String id) {

        if (verificacionId.verificarId(id) && verificacionAnt.verificarAnt(id)) {
            return true;
        }

        return false;
    }
}
