package co.poli.edu.actividad1.servicios;
import co.poli.edu.actividad1.modelo.Pasaporte;
public interface Builder <Pasaporte>{
    Pasaporte build();
    Builder<Pasaporte> setIdPasaporte(String id);
    Builder<Pasaporte> setFechaPasaporte(String fecha);
    Builder<Pasaporte> setIdTitular(String idTitular);
    Builder<Pasaporte>  setIdPais(String idPais);
    Builder<Pasaporte> setElemento(String el);
}