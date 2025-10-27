package co.poli.edu.actividad1.servicios;
import java.util.*;
public class FactoryPasaporteFlyweight {
    Map<String, PasaporteFlyweigth> cache;

    public FactoryPasaporteFlyweight() {
        cache=new HashMap<>();
    }
    public void set(String pais,String color, String idioma){
        cache.put(pais,new PasaporteFlyweigth(color,idioma));
    }
    public PasaporteFlyweigth getFlyweight(String pais){
        return cache.get(pais);
    }

}
