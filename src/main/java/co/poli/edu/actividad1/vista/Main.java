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
        Region raiz=new Region("Colombia");
        Region region1=new Region("Andina");
        Region region2=new Region("Orinoquia");
        Region region3=new Region("Cundinamarca");
        Region region4=new Region("Tolima");
        Region region5=new Region("Meta");
        CiudadLeaf ciudad1=new AdaptadorCiudad(new Ciudad("1","Bogota"));
        CiudadLeaf ciudad2=new AdaptadorCiudad(new Ciudad("2","Chia"));
        CiudadLeaf ciudad3=new AdaptadorCiudad(new Ciudad("3","Acacias"));
        CiudadLeaf ciudad4=new AdaptadorCiudad(new Ciudad("4","Villavicencio"));
        CiudadLeaf ciudad5=new AdaptadorCiudad(new Ciudad("5","Ibague"));
        CiudadLeaf ciudad6=new AdaptadorCiudad(new Ciudad("6","Mariquita"));
        CiudadLeaf ciudad7=new AdaptadorCiudad(new Ciudad("7","Cartagena"));
        raiz.add(region1);
        raiz.add(region2);
        raiz.add(ciudad7);
        region1.add(region3);
        region1.add(region4);
        region2.add(region5);
        region3.add(ciudad1);
        region3.add(ciudad2);
        region5.add(ciudad3);
        region5.add(ciudad4);
        region4.add(ciudad5);
        region4.add(ciudad6);
        System.out.println(ciudad7.getArbolJerarquico());
    }
    static String insertar(Pasaporte pasaporte) {return repositorio.insertar(pasaporte);}
    static String actualizar(String codigo,Pasaporte pasaporte) {return repositorio.actualizar(codigo,pasaporte);}
    static String eliminar(String id){return repositorio.eliminar(id);}
    static Pasaporte seleccionar(String id){return repositorio.seleccionar(id);}
    static List<Pasaporte> seleccionarTodos(){return repositorio.seleccionarTodos();}
    static List<Pasaporte> seleccionarConCaracter(char ch){return repositorio.seleccionarConCaracter(ch);}

}




