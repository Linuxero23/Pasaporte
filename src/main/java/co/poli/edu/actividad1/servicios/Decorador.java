package co.poli.edu.actividad1.servicios;

public class Decorador implements InterfazTitular {
    InterfazTitular w;
    public Decorador(InterfazTitular w) {
        this.w = w;
    }
    public String getDescripcion() {
        return w.getDescripcion();
    }
}
