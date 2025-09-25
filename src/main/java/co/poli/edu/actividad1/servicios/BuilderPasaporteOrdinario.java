package co.poli.edu.actividad1.servicios;
import co.poli.edu.actividad1.modelo.*;

public class BuilderPasaporteOrdinario implements Builder<Pasaporte>{
    private PasaporteOrdinario p;
    public BuilderPasaporteOrdinario(){
        p=new PasaporteOrdinario();
    }
    @Override
    public Builder<Pasaporte> setIdPasaporte(String id) {
        this.p.setId(id);
        return this;
    }

    @Override
    public Builder<Pasaporte> setFechaPasaporte(String fecha) {
        this.p.setFechaEx(fecha);
        return this;
    }

    @Override
    public Builder<Pasaporte> setIdTitular(int idTitular) {
        this.p.setTitular(idTitular);
        return this;
    }

    @Override
    public Builder<Pasaporte> setIdPais(int idPais) {
        this.p.setPais(idPais);
        return this;
    }

    public Builder<Pasaporte> setRazonDeViaje(String razonDeViaje) {
        this.p.setRazonDeViaje(razonDeViaje);
        return this;
    }
    public Builder<Pasaporte> setElemento(String idElemento) {
        this.p.setElemento(idElemento);
        return this;
    }

    @Override
    public PasaporteOrdinario build() {
        return p;
    }
}