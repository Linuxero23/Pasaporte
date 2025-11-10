package co.poli.edu.actividad1.servicios;

public class CommandAceptarVisa implements Command {
    private AdaptadorTitular titular;
    public CommandAceptarVisa(AdaptadorTitular titular) {
        this.titular = titular;
    }
    public String ejecutar(){
        return titular.aceptar();
    }
}
