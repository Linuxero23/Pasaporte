package co.poli.edu.actividad1.servicios;

public class CommandNegarVisa implements Command {
    private AdaptadorTitular titular;
    public CommandNegarVisa(AdaptadorTitular titular) {
        this.titular = titular;
    }
    public String ejecutar(){
        return titular.negar();
    }
}
