package co.poli.edu.actividad1.controlador;

import co.poli.edu.actividad1.modelo.*;
import co.poli.edu.actividad1.repositorio.PasaporteRepositorio;
import co.poli.edu.actividad1.servicios.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controladormenu {

    @FXML
    private Button btt1; //Crear

    @FXML
    private Button btt2; //Actualizar

    @FXML
    private Button btt3; //Eliminar

    @FXML
    private Button btt4; //Consultar uno

    @FXML
    private Button btt5; //Consultar todos

    @FXML
    private Button btt6;

    @FXML
    private Button btt7;

    @FXML
    public Button btt8;

    @FXML
    public Button btt9;

    @FXML
    public Button bttCommand;

    @FXML
    private SplitMenuButton split;

    @FXML
    private TextField txt1; // ID

    @FXML
    private TextField txt2; // Nombre o titular

    @FXML
    private TreeView<EspacioGeografico> treePaises;
    // ListView que usa la lista observable
    ObservableList<Memento> mementos = FXCollections.observableArrayList();
    @FXML
    ListView<Memento> flist;
    @FXML
    private TextField txt3; // Misión o motivo de viaje

    private EspacioGeografico cur;

    private final PasaporteRepositorio repo = new PasaporteRepositorio();

    private String tipoSeleccionado = "";

    private Publisher publisher;

    @FXML
    void select(ActionEvent event) {
        MenuItem item = (MenuItem) event.getSource();
        tipoSeleccionado = item.getText();
        split.setText(tipoSeleccionado);
    }
    boolean ini=true;
    @FXML
    void Click(ActionEvent event) {
        Object source = event.getSource();
        if (source == btt1) { // Crear
            crearPasaporte();
        } else if (source == btt2) { // Actualizar
            actualizarPasaporte();
        } else if (source == btt3) { // Eliminar
            eliminarPasaporte();
        } else if (source == btt4) { // Consultar
            consultarPasaporte();
        } else if (source == btt5) { // Consultar todos
            consultarTodos();
        } else if (source == btt6) { //Show tree
            showTree();
        } else if (source == btt7) { //Memento
            if(ini) {
                init();
                ini=false;
            }
            guardar();
        }else if (source == btt8) {
            restaurar();
        }
        else if (source == btt9) {
            mostrarMemento();
        }
        else if(source==bttCommand){
            cosoVisa();
        }
    }
    private void cosoVisa(){
        String id=txt1.getText();
        String nombre=txt2.getText();
        Titular t=new Titular(id,nombre, "Ayer");
        AdaptadorTitular at=new AdaptadorTitular(t);
        CommandAceptarVisa ca=new CommandAceptarVisa(at);
        CommandNegarVisa cn=new CommandNegarVisa(at);
        ConsuladoGringo cg=new ConsuladoGringo();
        double r=Math.random();
        if(r<=0.3){
            cg.setCommand(ca);
            mostrarAlerta("Desicion del consulado",cg.ejecutarCommand());
        }
        else{
            cg.setCommand(cn);
            mostrarAlerta("Desicion del consulado",cg.ejecutarCommand());
        }
    }
    CareTaker CT;
    AdaptadorPasaporte aapp=new AdaptadorPasaporte(null);
    String codigo, nombre, mision, tipo;
    private void init(){
        publisher=new Publisher();
        publisher.suscribe(new MigracionColombia());
        publisher.suscribe(new Policia());
        publisher.suscribe(new Cancilleria());
        mementos = FXCollections.observableArrayList();
        flist.setItems(mementos);
        flist.setCellFactory(lv  -> new ListCell<>() {
            @Override
            protected void updateItem(Memento item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    // Mostrar el índice 1-indexado
                    int index = getIndex() + 1;
                    setText("Estado #" + index);
                }
            }
        });
    }
    private void mostrarMemento(){
        int indice = flist.getSelectionModel().getSelectedIndex();
        if(indice==-1){
            mostrarAlerta("Error","No seleccionaste ningun elemento para restauras");
            return;
        }
        Memento m=CT.undo(indice);
        String s="";
        s+="Codigo: "+m.getId()+"\n";
        s+="Titular: "+m.getTitular()+"\n";
        s+="Pais: "+m.getPais()+"\n";
        s+="Fecha de expedicion: 5/11/2025\n";
        s+="Elemento de seguridad: Biometrico\n";
        if(m.getMision()!=null)
            s = "Pasaporte Diplomatico\n"+s+"Mision: "+m.getMision();
        else
            s = "Pasaporte Ordinario\n"+s+"Razon de viaje: "+m.getRazonDeViaje();
        mostrarAlerta("Estado Seleccionado",s);
    }
    private void restaurar(){
        int indice = flist.getSelectionModel().getSelectedIndex();
        if(indice==-1){
            mostrarAlerta("Error","No seleccionaste ningun elemento para restauras");
            return;
        }
        Pasaporte p=aapp.restore(CT.undo(indice));
        txt1.setText(p.getId());
        txt2.setText(p.getTitular());
        if(p instanceof PasaporteDiplomatico){
            PasaporteDiplomatico pd=(PasaporteDiplomatico)p;
            txt3.setText(pd.getMision());
        }
        else {
            PasaporteOrdinario pd=(PasaporteOrdinario)p;
            txt3.setText(pd.getRazonDeViaje());
        }
    }

    private void guardar() {
        boolean flag=false;
        if(CT==null) {
            CT = new CareTaker();
            flag=true;
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
        aapp=new AdaptadorPasaporte(pasaporte);
        CT.add(aapp.save());
        mementos.clear();
        mementos.addAll(CT.getHistory());
        if(flag)
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
        // Supongamos que tienes un EspacioGeografico raíz

        // Lo envuelves en un TreeItem
        TreeItem<EspacioGeografico> rootItem = new TreeItem<>(raiz);
        rootItem.setExpanded(true);

        // Asignar raíz al tree
        treePaises.setRoot(rootItem);

        // Manejar clicks en nodos
        treePaises.setOnMouseClicked(event -> {
            TreeItem<EspacioGeografico> selectedItem = treePaises.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                EspacioGeografico eg = selectedItem.getValue();
                cur = eg;
                if (eg instanceof Region) {
                    if (selectedItem.getChildren().isEmpty()) {
                        // Expandir: agregar los hijos al tree
                        Region region = (Region) eg;
                        for (EspacioGeografico hijo : region.getChildren()) {
                            selectedItem.getChildren().add(new TreeItem<>(hijo));
                        }
                    } else {
                        // Contraer: eliminar los hijos
                        selectedItem.getChildren().clear();
                    }
                }
                // Si es un país u otro tipo de EspacioGeografico, no pasa nada
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

        if (pas instanceof PasaporteOrdinario) {
            ((PasaporteOrdinario) pas).setRazonDeViaje(txt3.getText());
        } else if (pas instanceof PasaporteDiplomatico) {
            ((PasaporteDiplomatico) pas).setMision(txt3.getText());
        }

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

        if (p == null) {
            mostrarAlerta("Consulta", "No se encontró el pasaporte con ID " + id);
        } else {
            mostrarAlerta("Consulta", p.toString());
        }
    }

    private void consultarTodos() {
        var lista = repo.seleccionarTodos();
        StringBuilder sb = new StringBuilder();
        for (Pasaporte p : lista) {
            sb.append(p.toString()).append("\n");
        }
        mostrarAlerta("Todos los pasaportes", sb.toString());
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}