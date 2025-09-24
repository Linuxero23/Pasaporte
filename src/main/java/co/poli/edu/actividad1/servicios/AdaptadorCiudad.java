package co.poli.edu.actividad1.servicios;
import co.poli.edu.actividad1.modelo.Ciudad;

public class AdaptadorCiudad extends CiudadL {
    private Ciudad ciudadLugar;

    public AdaptadorCiudad(Ciudad ciudad) {
        super(ciudad.getNombre());
        this.ciudadLugar = ciudad;
    }

    public Ciudad getCiudadL() {
        return ciudadLugar;
    }
}
