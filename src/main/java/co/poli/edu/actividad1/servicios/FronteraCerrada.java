package co.poli.edu.actividad1.servicios;

public class FronteraCerrada implements EstadoPais{

    @Override
    public String mostrarEstado() {
        return "frontera cerrada";
    }

    @Override
    public void cambiarEstado(AdaptadorPais pais, EstadoPais nuevoEstado) {

        if (nuevoEstado instanceof EstadoNormal || nuevoEstado instanceof SolicitudVisa) {
            pais.setEstado(nuevoEstado);
        }
    }
}
