package co.poli.edu.actividad1.servicios;

public class EstadoRevision implements EstadoPais{

    @Override
    public String mostrarEstado() {
        return "estado de revisión";
    }

    @Override
    public void cambiarEstado(AdaptadorPais pais, EstadoPais nuevoEstado) {

        if (nuevoEstado instanceof SolicitudVisa) {
            pais.setEstado(nuevoEstado);
        }



    }
}
