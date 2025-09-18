package co.poli.edu.actividad1.modelo;

public class ElementoDeSeguridad implements Cloneable {
    private String holograma;
    private String selloAgua;
    private String tintaEspecial;
    private String chipElectronico;

    private ElementoDeSeguridad(Builder builder) {
        this.holograma = builder.holograma;
        this.selloAgua = builder.selloAgua;
        this.tintaEspecial = builder.tintaEspecial;
        this.chipElectronico = builder.chipElectronico;
    }

    @Override
    public String toString() {
        return "ElementoDeSeguridad {holograma='" + holograma + "', selloAgua='" + selloAgua
                + "', tintaEspecial='" + tintaEspecial + "', chipElectronico='" + chipElectronico + "'}";
    }

    // Prototype
    @Override
    public ElementoDeSeguridad clone() {
        try {
            return (ElementoDeSeguridad) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar ElementoDeSeguridad", e);
        }
    }

    // Builder
    public static class Builder {
        private String holograma;
        private String selloAgua;
        private String tintaEspecial;
        private String chipElectronico;

        public Builder setHolograma(String holograma) {
            this.holograma = holograma;
            return this;
        }

        public Builder setSelloAgua(String selloAgua) {
            this.selloAgua = selloAgua;
            return this;
        }

        public Builder setTintaEspecial(String tintaEspecial) {
            this.tintaEspecial = tintaEspecial;
            return this;
        }

        public Builder setChipElectronico(String chipElectronico) {
            this.chipElectronico = chipElectronico;
            return this;
        }

        public ElementoDeSeguridad build() {
            return new ElementoDeSeguridad(this);
        }
    }
}
