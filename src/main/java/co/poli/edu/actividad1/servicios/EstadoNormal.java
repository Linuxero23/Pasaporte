package co.poli.edu.actividad1.servicios;


public class EstadoNormal implements EstadoPais {

    @Override
    public String mostrarEstado() {
        return "estado normal";
    }

    @Override
    public void cambiarEstado(AdaptadorPais pais, EstadoPais nuevoEstado) {

        if (nuevoEstado instanceof EstadoRevision || nuevoEstado instanceof SolicitudVisa || nuevoEstado instanceof FronteraCerrada) {
            pais.setEstado(nuevoEstado);

        }
    }
}
