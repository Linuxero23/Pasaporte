package co.poli.edu.actividad1.servicios;
import co.poli.edu.actividad1.modelo.Ciudad;

public class AdaptadorCiudad extends CiudadLeaf {
    private Ciudad ciudad;
    public AdaptadorCiudad(Ciudad ciudad) {
        super(ciudad.getNombre());
    }
}
