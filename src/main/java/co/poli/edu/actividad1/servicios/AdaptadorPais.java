    package co.poli.edu.actividad1.servicios;
    import co.poli.edu.actividad1.modelo.Pais;
    import java.util.List;


    public class AdaptadorPais {
        private Pais pais;
        private EstadoPais estadoActual;

        public AdaptadorPais(Pais pais){
            this.pais = pais;
            this.estadoActual = new EstadoNormal();
        }

        public void setEstado(EstadoPais estado) {
            this.estadoActual = estado;
        }

        public EstadoPais getEstado() {
            return estadoActual;
        }

        public Pais getPais() {
            return pais;
        }

        public String mostrarEstado() {
            return estadoActual.mostrarEstado();
        }

        public void cambiarEstado(EstadoPais nuevoEstado) {
            estadoActual.cambiarEstado(this, nuevoEstado);
        }

        public List<EstadoPais> obtenerEstadosDisponibles() {
            return estadoActual.obtenerEstadosDisponibles();
        }

    }
