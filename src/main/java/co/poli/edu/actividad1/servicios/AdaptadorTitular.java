package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.Titular;

public class AdaptadorTitular implements InterfazTitular {
    private Titular t;
    public AdaptadorTitular(Titular titular){
        t=titular;
    }
    @Override
    public String getDescripcion() {
        return t.toString();
    }
}
