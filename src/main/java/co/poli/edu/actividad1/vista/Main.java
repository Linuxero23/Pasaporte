package co.poli.edu.actividad1.vista;

import java.security.NoSuchAlgorithmException;
import java.util.*;

import co.poli.edu.actividad1.modelo.*;
import co.poli.edu.actividad1.repositorio.*;
import co.poli.edu.actividad1.servicios.*;

public class Main  {
    private static PasaporteRepositorio repositorio = new PasaporteRepositorio();
    public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException {
        init();
    }
    static void init() throws NoSuchAlgorithmException {
        /*BuilderPasaporteOrdinario b=new BuilderPasaporteOrdinario();
        b.setIdPasaporte("JUANCHOPOPO");
        b.setFechaPasaporte("05/20/2026");
        b.setIdPais(101);
        b.setIdTitular(101);
        b.setElemento("1");
        b.setRazonDeViaje("probando 1 2 3");
        eliminar(b.build().getId());*/
        ElementoDeSeguridad e=new Blockchainhash(new Titular("101","lina","ayer"));
        System.out.println(e);
    }
    static String insertar(Pasaporte pasaporte) {return repositorio.insertar(pasaporte);}
    static String actualizar(String codigo,Pasaporte pasaporte) {return repositorio.actualizar(codigo,pasaporte);}
    static String eliminar(String id){return repositorio.eliminar(id);}
    static Pasaporte seleccionar(String id){return repositorio.seleccionar(id);}
    static List<Pasaporte> seleccionarTodos(){return repositorio.seleccionarTodos();}
    static List<Pasaporte> seleccionarConCaracter(char ch){return repositorio.seleccionarConCaracter(ch);}

}




