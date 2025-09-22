package co.poli.edu.actividad1.vista;

import java.util.*;

import co.poli.edu.actividad1.modelo.*;
import co.poli.edu.actividad1.repositorio.*;
import co.poli.edu.actividad1.servicios.*;

public class Main  {
    static CreatorPasaporteOrdinario cpo=new CreatorPasaporteOrdinario();
    static CreatorPasaporteDiplomatico dpo=new CreatorPasaporteDiplomatico();
    private static PasaporteRepositorio repositorio = new PasaporteRepositorio();
    public static void main(String[] args) throws InterruptedException {
        BuilderPasaporteOrdinario bld = new BuilderPasaporteOrdinario();
        bld.setIdPasaporte("SOTO1234");
        bld.setFechaPasaporte("14/05/2031");
        bld.setRazonDeViaje("Soto lo pidio");
        bld.setIdTitular(101);
        bld.setIdPais(101);
        bld.setElemento("1021");
        System.out.println(insertar(bld.build()));
    }
    static String insertar(Pasaporte pasaporte) {return repositorio.insertar(pasaporte);}
    static String actualizar(String codigo,Pasaporte pasaporte) {return repositorio.actualizar(codigo,pasaporte);}
    static String eliminar(String id){
        return repositorio.eliminar(id);
    }
    static Pasaporte seleccionar(String id){
        return repositorio.seleccionar(id);
    }
    static List<Pasaporte> seleccionarTodos(){
        return repositorio.seleccionarTodos();
    }
    static List<Pasaporte> seleccionarConCaracter(char ch){return repositorio.seleccionarConCaracter(ch);}

}