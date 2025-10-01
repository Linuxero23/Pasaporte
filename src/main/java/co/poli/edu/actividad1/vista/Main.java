package co.poli.edu.actividad1.vista;

import java.util.*;

import co.poli.edu.actividad1.modelo.*;
import co.poli.edu.actividad1.repositorio.*;
import co.poli.edu.actividad1.servicios.*;

public class Main  {
    private static PasaporteRepositorio repositorio = new PasaporteRepositorio();
    public static void main(String[] args) throws InterruptedException {
        init();
    }
    static void init(){
        /*BuilderPasaporteOrdinario b=new BuilderPasaporteOrdinario();
        b.setIdPasaporte("JUANCHOPOPO");
        b.setFechaPasaporte("05/20/2026");
        b.setIdPais(101);
        b.setIdTitular(101);
        b.setElemento("1");
        b.setRazonDeViaje("probando 1 2 3");
        eliminar(b.build().getId());*/
        InterfazTitular t=new AdaptadorTitular(new Titular("101","Edwar Soto","23/05/1979"));
        System.out.println(t.getDescripcion());
        t=new DecoradorClase(t,"Clase pobre");
        System.out.println(t.getDescripcion());
        t=new DecoradorMascota(t,"Perro Salvavidas");
        System.out.println(t.getDescripcion());
        t=new DecoradorClase(t,"Clase mega pobre");
        System.out.println(t.getDescripcion());
        t=new DecoradorMascota(t,"Perro Guardian");
        System.out.println(t.getDescripcion());
        t=new DecoradorClase(t,"Clase pobre");
        System.out.println(t.getDescripcion());
    }
    static String insertar(Pasaporte pasaporte) {return repositorio.insertar(pasaporte);}
    static String actualizar(String codigo,Pasaporte pasaporte) {return repositorio.actualizar(codigo,pasaporte);}
    static String eliminar(String id){return repositorio.eliminar(id);}
    static Pasaporte seleccionar(String id){return repositorio.seleccionar(id);}
    static List<Pasaporte> seleccionarTodos(){return repositorio.seleccionarTodos();}
    static List<Pasaporte> seleccionarConCaracter(char ch){return repositorio.seleccionarConCaracter(ch);}

}




