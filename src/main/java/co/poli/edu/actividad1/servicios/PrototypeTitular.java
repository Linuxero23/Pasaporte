package co.poli.edu.actividad1.servicios;
import co.poli.edu.actividad1.modelo.Titular;
public class PrototypeTitular {
    private Titular titular;

    public PrototypeTitular(Titular titular) {
        this.titular = titular;
    }
    @Override
    public Titular clone() {
        // Creamos una nueva instancia con los mismos valores
        return new Titular(
                titular.getId(),
                titular.getNombre(),
                titular.getFechaNacimiento()
        );
    }
    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }
}
