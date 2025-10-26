package co.poli.edu.actividad1.servicios;
import co.poli.edu.actividad1.modelo.Ciudad;

public class AdaptadorCiudad extends EspacioGeografico {
    private Ciudad ciudad;
    public AdaptadorCiudad(Ciudad ciudad) {
        this.ciudad=ciudad;
        this.setNombre(ciudad.getNombre());
    }
}
