package co.poli.edu.actividad1.vista;

import java.util.*;

import co.poli.edu.actividad1.modelo.*;
import co.poli.edu.actividad1.repositorio.*;
import co.poli.edu.actividad1.servicios.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/poli/edu/actividad1/vista/menu.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestión de Pasaportes");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*


public class Main  {
    static CreatorPasaporteOrdinario cpo=new CreatorPasaporteOrdinario();
    static CreatorPasaporteDiplomatico dpo=new CreatorPasaporteDiplomatico();
    private static PasaporteRepositorio repositorio = new PasaporteRepositorio();
    public static void main(String[] args) throws InterruptedException {
        String id="KKKKKK";
        int idPais=102;
        int idTitular=102;
        String fecha="13/09/2025";
        String razon="PA LEONAS";
        PasaporteDiplomatico pd= (PasaporteDiplomatico) dpo.crearPasaporte();
        PasaporteOrdinario po = (PasaporteOrdinario) cpo.crearPasaporte();
        po.setId(id);
        po.setFechaEx(fecha);
        po.setPais(idPais);
        po.setTitular(idTitular);
        po.setRazonDeViaje(razon);
        System.out.println(insertar(po));
        //System.out.println(eliminar("PKSP123NA"));
        //System.out.println(seleccionar("NAD812JAW12"));
        for(Pasaporte p:seleccionarConCaracter('W'))
            System.out.println(p);
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
*/



