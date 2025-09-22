package co.poli.edu.actividad1.servicios;
import co.poli.edu.actividad1.modelo.*;

public class BuilderPasaporteOrdinario implements Builder<Pasaporte>{
    private String idPasaporte;
    private String fechaPasaporte;
    private int idTitular;
    private int idPais;
    private String razonDeViaje;
    private String idElemento;
    @Override
    public Builder<Pasaporte> setIdPasaporte(String id) {
        this.idPasaporte = id;
        return this;
    }

    @Override
    public Builder<Pasaporte> setFechaPasaporte(String fecha) {
        this.fechaPasaporte = fecha;
        return this;
    }

    @Override
    public Builder<Pasaporte> setIdTitular(int idTitular) {
        this.idTitular = idTitular;
        return this;
    }

    @Override
    public Builder<Pasaporte> setIdPais(int idPais) {
        this.idPais = idPais;
        return this;
    }

    public Builder<Pasaporte> setRazonDeViaje(String razonDeViaje) {
        this.razonDeViaje = razonDeViaje;
        return this;
    }
    public Builder<Pasaporte> setElemento(String idElemento) {
        this.idElemento = idElemento;
        return this;
    }

    @Override
    public PasaporteOrdinario build() {
        PasaporteOrdinario p = new PasaporteOrdinario();
        p.setId(this.idPasaporte);
        p.setFechaEx( this.fechaPasaporte);
        p.setTitular(this.idTitular);
        p.setPais(this.idPais);
        p.setRazonDeViaje(this.razonDeViaje);
        p.setElemento(this.idElemento);
        return p;
    }
}