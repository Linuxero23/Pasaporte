package co.poli.edu.actividad1.controlador;

import co.poli.edu.actividad1.modelo.*;
import co.poli.edu.actividad1.repositorio.PasaporteRepositorio;
import co.poli.edu.actividad1.servicios.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class Controladormenu {

    // ----------------------------
    // 🧱 SECCIÓN UI PRINCIPAL
    // ----------------------------
    @FXML private Button btt1; // Crear
    @FXML private Button btt2; // Actualizar
    @FXML private Button btt3; // Eliminar
    @FXML private Button btt4; // Consultar uno
    @FXML private Button btt5; // Consultar todos
    @FXML private Button btt6;
    @FXML private Button btt7;
    @FXML public Button btt8;
    @FXML public Button btt9;
    @FXML public Button bttCommand;
    @FXML public Button btt10;

    @FXML private SplitMenuButton split;
    @FXML private TextField txt1; // ID
    @FXML private TextField txt2; // Nombre o titular
    @FXML private TextField txt3; // Misión o motivo de viaje
    @FXML private TreeView<EspacioGeografico> treePaises;
    @FXML ListView<Memento> flist;

    ObservableList<Memento> mementos = FXCollections.observableArrayList();
    private EspacioGeografico cur;
    private final PasaporteRepositorio repo = new PasaporteRepositorio();
    private String tipoSeleccionado = "";
    private Publisher publisher;
    CareTaker CT;
    AdaptadorPasaporte aapp = new AdaptadorPasaporte(null);
    String codigo, nombre, mision, tipo;

    // ----------------------------
    // 🧭 NUEVO: MENSAJERÍA ENTRE ENTIDADES
    // ----------------------------
    @FXML private ComboBox<EntidadGubernamental> comboEmisor;
    @FXML private ComboBox<EntidadGubernamental> comboReceptor;
    @FXML private TextField txtMensaje;
    @FXML private TextArea txtHistorial;

    private EntidadMediator mediator;
    private EntidadGubernamental policia;
    private EntidadGubernamental cancilleria;
    private EntidadGubernamental migracion;

    @FXML
    public void initialize() {
        // Inicializar entidades y mediador
        mediator = new EntidadMediator();
        policia = new Policia();
        cancilleria = new Cancilleria();
        migracion = new MigracionColombia();

        policia.setMediator(mediator);
        cancilleria.setMediator(mediator);
        migracion.setMediator(mediator);

        comboEmisor.getItems().addAll(policia, cancilleria, migracion);
        comboReceptor.getItems().addAll(policia, cancilleria, migracion);
    }

    @FXML
    private void enviarMensajePersonalizado() {
        EntidadGubernamental emisor = comboEmisor.getValue();
        EntidadGubernamental receptor = comboReceptor.getValue();
        String mensaje = txtMensaje.getText();

        if (emisor == null || receptor == null) {
            mostrarAlerta("Error", "Debe seleccionar un emisor y un receptor.");
            return;
        }
        if (mensaje.isEmpty()) {
            mostrarAlerta("Error", "Debe escribir un mensaje.");
            return;
        }

        emisor.enviarMensaje(mensaje, receptor);
        txtHistorial.appendText(emisor.toString() + " → " + receptor.toString() + ": " + mensaje + "\n");
        txtMensaje.clear();
    }

    // ----------------------------
    // ⚙️ MÉTODOS EXISTENTES
    // ----------------------------
    @FXML
    void select(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        tipoSeleccionado = item.getText();
        split.setText(tipoSeleccionado);
    }

    boolean ini = true;

    @FXML
    void Click(ActionEvent event) {
        Object source = event.getSource();
        if (source == btt1) crearPasaporte();
        else if (source == btt2) actualizarPasaporte();
        else if (source == btt3) eliminarPasaporte();
        else if (source == btt4) consultarPasaporte();
        else if (source == btt5) consultarTodos();
        else if (source == btt6) showTree();
        else if (source == btt7) {
            if (ini) {
                init();
                ini = false;
            }
            guardar();
        } else if (source == btt8) restaurar();
        else if (source == btt9) mostrarMemento();
        else if (source == bttCommand) cosoVisa();
        else if (source == btt10) costos();
    }

    private void costos() {
        if (tipoSeleccionado.equals("Ordinario") && !txt2.getText().isEmpty()) {
            PrecioPasaporte costo = new PrecioPasaporte(new StrategyCostoOrdinario());
            double precio = costo.CalcularPrecio(txt2.getText());
            if (precio == -1)
                mostrarAlerta("Error", "Ingrese un país válido");
            else
                mostrarAlerta("Precio", "El precio de su pasaporte en dólares es: $" + precio);
            return;
        } else if (tipoSeleccionado.equals("Diplomático") && !txt2.getText().isEmpty()) {
            PrecioPasaporte costo = new PrecioPasaporte(new StrategyCostoDiplomatico());
            double precio = costo.CalcularPrecio(txt2.getText());
            if (precio == -1)
                mostrarAlerta("Error", "Ingrese un país válido");
            else
                mostrarAlerta("Precio", "El precio de su pasaporte en dólares es: $" + precio);
            return;
        } else if (tipoSeleccionado.equals("Emergencia") && !txt2.getText().isEmpty()) {
            PrecioPasaporte costo = new PrecioPasaporte(new StrategyCostoEmergencia());
            double precio = costo.CalcularPrecio(txt2.getText());
            if (precio == -1)
                mostrarAlerta("Error", "Ingrese un país válido");
            else
                mostrarAlerta("Precio", "El precio de su pasaporte en dólares es: $" + precio);
        }
    }

    private void cosoVisa() {
        String id = txt1.getText();
        String nombre = txt2.getText();
        Titular t = new Titular(id, nombre, "Ayer");
        AdaptadorTitular at = new AdaptadorTitular(t);
        CommandAceptarVisa ca = new CommandAceptarVisa(at);
        CommandNegarVisa cn = new CommandNegarVisa(at);
        ConsuladoGringo cg = new ConsuladoGringo();
        double r = Math.random();
        if (r <= 0.3) {
            cg.setCommand(ca);
            mostrarAlerta("Decisión del consulado", cg.ejecutarCommand());
        } else {
            cg.setCommand(cn);
            mostrarAlerta("Decisión del consulado", cg.ejecutarCommand());
        }
    }

    private void init() {
        publisher = new Publisher();
        publisher.suscribe(new MigracionColombia());
        publisher.suscribe(new Policia());
        publisher.suscribe(new Cancilleria());
        mementos = FXCollections.observableArrayList();
        flist.setItems(mementos);
        flist.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Memento item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    int index = getIndex() + 1;
                    setText("Estado #" + index);
                }
            }
        });
    }

    private void mostrarMemento() {
        int indice = flist.getSelectionModel().getSelectedIndex();
        if (indice == -1) {
            mostrarAlerta("Error", "No seleccionaste ningún elemento para restaurar");
            return;
        }
        Memento m = CT.undo(indice);
        String s = "";
        s += "Codigo: " + m.getId() + "\n";
        s += "Titular: " + m.getTitular() + "\n";
        s += "Pais: " + m.getPais() + "\n";
        s += "Fecha de expedicion: 5/11/2025\n";
        s += "Elemento de seguridad: Biometrico\n";
        if (m.getMision() != null)
            s = "Pasaporte Diplomático\n" + s + "Misión: " + m.getMision();
        else
            s = "Pasaporte Ordinario\n" + s + "Razón de viaje: " + m.getRazonDeViaje();
        mostrarAlerta("Estado Seleccionado", s);
    }

    private void restaurar() {
        int indice = flist.getSelectionModel().getSelectedIndex();
        if (indice == -1) {
            mostrarAlerta("Error", "No seleccionaste ningún elemento para restaurar");
            return;
        }
        Pasaporte p = aapp.restore(CT.undo(indice));
        txt1.setText(p.getId());
        txt2.setText(p.getTitular());
        if (p instanceof PasaporteDiplomatico pd)
            txt3.setText(pd.getMision());
        else if (p instanceof PasaporteOrdinario po)
            txt3.setText(po.getRazonDeViaje());
    }

    private void guardar() {
        boolean flag = false;
        if (CT == null) {
            CT = new CareTaker();
            flag = true;
        }
        if (tipoSeleccionado.isEmpty()) {
            mostrarAlerta("Error", "Debe seleccionar un tipo de pasaporte.");
            return;
        }
        Pasaporte pasaporte;
        if (tipoSeleccionado.equals("Ordinario")) {
            PasaporteOrdinario po = new PasaporteOrdinario();
            po.setId(txt1.getText());
            po.setTitular(txt2.getText());
            po.setFechaEx("14/09/2025");
            po.setPais(cur.toString());
            po.setRazonDeViaje(txt3.getText());
            pasaporte = po;
        } else {
            PasaporteDiplomatico pd = new PasaporteDiplomatico();
            pd.setId(txt1.getText());
            pd.setTitular(txt2.getText());
            pd.setFechaEx("14/09/2025");
            pd.setPais(cur.toString());
            pd.setMision(txt3.getText());
            pasaporte = pd;
        }
        aapp = new AdaptadorPasaporte(pasaporte);
        CT.add(aapp.save());
        mementos.clear();
        mementos.addAll(CT.getHistory());
        if (flag)
            crearPasaporte();
        else {
            actualizarPasaporte();
            mostrarAlerta("Entidades notificadas del cambio", publisher.notify(pasaporte.getId()));
        }
    }

    public void showTree() {
        Region raiz = new Region("Colombia");
        Region region1 = new Region("Andina");
        Region region2 = new Region("Orinoquia");
        Region region3 = new Region("Cundinamarca");
        Region region4 = new Region("Tolima");
        Region region5 = new Region("Meta");
        AdaptadorCiudad ciudad1 = new AdaptadorCiudad(new Ciudad("1", "Bogota"));
        AdaptadorCiudad ciudad2 = new AdaptadorCiudad(new Ciudad("2", "Chia"));
        AdaptadorCiudad ciudad3 = new AdaptadorCiudad(new Ciudad("3", "Acacias"));
        AdaptadorCiudad ciudad4 = new AdaptadorCiudad(new Ciudad("4", "Villavicencio"));
        AdaptadorCiudad ciudad5 = new AdaptadorCiudad(new Ciudad("5", "Ibague"));
        AdaptadorCiudad ciudad6 = new AdaptadorCiudad(new Ciudad("6", "Mariquita"));
        AdaptadorCiudad ciudad7 = new AdaptadorCiudad(new Ciudad("7", "Cartagena"));
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

        TreeItem<EspacioGeografico> rootItem = new TreeItem<>(raiz);
        rootItem.setExpanded(true);
        treePaises.setRoot(rootItem);

        treePaises.setOnMouseClicked(event -> {
            TreeItem<EspacioGeografico> selectedItem = treePaises.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                EspacioGeografico eg = selectedItem.getValue();
                cur = eg;
                if (eg instanceof Region region) {
                    if (selectedItem.getChildren().isEmpty()) {
                        for (EspacioGeografico hijo : region.getChildren()) {
                            selectedItem.getChildren().add(new TreeItem<>(hijo));
                        }
                    } else selectedItem.getChildren().clear();
                }
            }
        });
    }

    private void crearPasaporte() {
        if (tipoSeleccionado.isEmpty()) {
            mostrarAlerta("Error", "Debe seleccionar un tipo de pasaporte.");
            return;
        }
        Pasaporte pasaporte;
        if (tipoSeleccionado.equals("Ordinario")) {
            PasaporteOrdinario po = new PasaporteOrdinario();
            po.setId(txt1.getText());
            po.setTitular(txt2.getText());
            po.setFechaEx("14/09/2025");
            po.setPais(cur.toString());
            po.setRazonDeViaje(txt3.getText());
            pasaporte = po;
        } else {
            PasaporteDiplomatico pd = new PasaporteDiplomatico();
            pd.setId(txt1.getText());
            pd.setTitular(txt2.getText());
            pd.setFechaEx("14/09/2025");
            pd.setPais(cur.toString());
            pd.setMision(txt3.getText());
            pasaporte = pd;
        }
        String resultado = repo.insertar(pasaporte);
        mostrarAlerta("Resultado", resultado);
    }

    private void actualizarPasaporte() {
        String id = txt1.getText();
        Pasaporte pas = repo.seleccionar(id);
        if (pas == null) {
            mostrarAlerta("Error", "No existe un pasaporte con ID " + id);
            return;
        }
        if (pas instanceof PasaporteOrdinario po)
            po.setRazonDeViaje(txt3.getText());
        else if (pas instanceof PasaporteDiplomatico pd)
            pd.setMision(txt3.getText());

        pas.setTitular((txt2.getText()));
        pas.setPais(cur.toString());
        pas.setFechaEx("14/09/2025");

        String resultado = repo.actualizar(id, pas);
        mostrarAlerta("Resultado", resultado);
    }

    private void eliminarPasaporte() {
        String id = txt1.getText();
        String resultado = repo.eliminar(id);
        mostrarAlerta("Resultado", resultado);
    }

    private void consultarPasaporte() {
        String id = txt1.getText();
        Pasaporte p = repo.seleccionar(id);
        if (p == null)
            mostrarAlerta("Consulta", "No se encontró el pasaporte con ID " + id);
        else
            mostrarAlerta("Consulta", p.toString());
    }

    private void consultarTodos() {
        var lista = repo.seleccionarTodos();
        StringBuilder sb = new StringBuilder();
        for (Pasaporte p : lista)
            sb.append(p.toString()).append("\n");
        mostrarAlerta("Todos los pasaportes", sb.toString());
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
