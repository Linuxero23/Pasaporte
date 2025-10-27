package co.poli.edu.actividad1.servicios;
import java.util.*;
public class FactoryPasaporteFlyweight {
    Map<String, PasaporteFlyweigth> cache;

    public FactoryPasaporteFlyweight() {
        cache=new HashMap<>();
    }
    public void set(PasaporteFlyweigth pf){
        cache.put(pf.getPais(),new PasaporteFlyweigth(pf.getColor(),pf.getIdioma()));
    }
    public PasaporteFlyweigth getFlyweight(String pais){
        return cache.get(pais);
    }

}
