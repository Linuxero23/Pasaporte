package co.poli.edu.actividad1.servicios;

public class VerificacionId {

    public boolean verificarId(String id) {

        if(id==null){
            return false;
        }
        if(id.length() > 5 ){
            return false;
        }
        return true;
    }
}
