package co.poli.edu.actividad1.vista;

import co.poli.edu.actividad1.modelo.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co.poli.edu.actividad1/vista/menu.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestión de Pasaportes");
            primaryStage.show();
            ejecutarEjemplo("Visual (JavaFX)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//Builder + Prototype
    private void ejecutarEjemplo(String modo) {
        System.out.println("\n=== EJEMPLO (" + modo + ") ===");


        // PASAPORTE 1 (Builder completo)
        PasaporteOrdinario po = new PasaporteOrdinario();
        po.setId("ABC123");
        po.setTitular(101);
        po.setPais(57);
        po.setFechaEx("17/09/2025");
        po.setRazonDeViaje("Turismo");

        // Usar BUILDER con todos los atributos
        ElementoDeSeguridad seguridadCompleta = new ElementoDeSeguridad.Builder()
                .setHolograma("Holograma 3D")
                .setSelloAgua("Sello oficial")
                .setTintaEspecial("Tinta UV")
                .setChipElectronico("Chip NFC biométrico")
                .build();

        po.setElementoSeguridad(seguridadCompleta);

        // Clonar (Prototype)
        PasaporteOrdinario copia = po.clone();
        copia.setId("XYZ999");
        copia.setRazonDeViaje("Negocios");

        // Mostrar en consola
        System.out.println("Pasaporte original (seguridad completa):");
        System.out.println(po);

        System.out.println("Pasaporte clonado:");
        System.out.println(copia);


        // PASAPORTE 2 (Builder parcial)

        PasaporteOrdinario po2 = new PasaporteOrdinario();
        po2.setId("LMN456");
        po2.setTitular(202);
        po2.setPais(34);
        po2.setFechaEx("01/01/2026");
        po2.setRazonDeViaje("Estudios");

        // Builder solo con algunos atributos
        ElementoDeSeguridad seguridadParcial = new ElementoDeSeguridad.Builder()
                .setHolograma("Holograma básico")
                .setChipElectronico("Chip simple")
                .build();

        po2.setElementoSeguridad(seguridadParcial);

        // Clonar (Prototype)
        PasaporteOrdinario copia2 = po2.clone();
        copia2.setId("ZZZ111");
        copia2.setRazonDeViaje("Investigación");

        System.out.println("\nPasaporte original (seguridad parcial):");
        System.out.println(po2);

        System.out.println("Pasaporte clonado (desde seguridad parcial):");
        System.out.println(copia2);
    }


    //MODO CONSOLA PURO

    public static void main(String[] args) {
        System.out.println("Iniciando aplicación de Gestión de Pasaportes...");

        Main ejemplo = new Main();
        ejemplo.ejecutarEjemplo("Consola pura");

    }
}
