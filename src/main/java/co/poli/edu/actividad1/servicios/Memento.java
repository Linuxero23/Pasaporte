package co.poli.edu.actividad1.servicios;

public class Memento {
    private String state;
    public Memento(AdaptadorPasaporte p) {
        state=p.getDescripcion();
    }
    public String getState() {
        return state;
    }
}
