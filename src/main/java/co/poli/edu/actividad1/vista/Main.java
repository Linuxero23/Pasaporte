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
        /*PasaporteOrdinario pasaporte = new PasaporteOrdinario();
        pasaporte.setId("P123");
        pasaporte.setTitular(101);
        pasaporte.setPais(57);
        pasaporte.setFechaEx("26/10/2025");
        pasaporte.setElemento("Chip123");
        // usuario autorizado
        ProxyPasaporte proxyAdmin = new ProxyPasaporte(new AdaptadorPasaporte(pasaporte), "ADMIN");
        System.out.println(proxyAdmin.getDescripcion());

        // usuario NO autorizado
        ProxyPasaporte proxyInvitado = new ProxyPasaporte(new AdaptadorPasaporte(pasaporte), "INVITADO");
        System.out.println(proxyInvitado.getDescripcion());

        FachadaPasaporte fachada = new FachadaPasaporte();
        System.out.println();
        System.out.println(fachada.procesarSolicitud("67890"));
        System.out.println();
        System.out.println(fachada.procesarSolicitud("12345"));

        */
        PasaporteOrdinario pasaporte = new PasaporteOrdinario();
        pasaporte.setId("P123");
        pasaporte.setTitular(101);
        pasaporte.setPais(57);
        pasaporte.setFechaEx("26/10/2025");
        pasaporte.setElemento("Chip123");
        FactoryPasaporteFlyweight factory=new FactoryPasaporteFlyweight();
        factory.set("Colombia","Vinotinto","Español");
        factory.set("Argentina","Azul","Español");
        PasaporteFlyweigth pf=factory.getFlyweight(pasaporte.getPaisNombre());
        pf.getDetails(pasaporte);
    }
    static String insertar(Pasaporte pasaporte) {return repositorio.insertar(pasaporte);}
    static String actualizar(String codigo,Pasaporte pasaporte) {return repositorio.actualizar(codigo,pasaporte);}
    static String eliminar(String id){return repositorio.eliminar(id);}
    static Pasaporte seleccionar(String id){return repositorio.seleccionar(id);}
    static List<Pasaporte> seleccionarTodos(){return repositorio.seleccionarTodos();}
    static List<Pasaporte> seleccionarConCaracter(char ch){return repositorio.seleccionarConCaracter(ch);}

}




