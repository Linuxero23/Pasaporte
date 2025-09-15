package co.poli.edu.actividad1.modelo;

public class PasaporteOrdinario extends Pasaporte {
    private String razonDeViaje;
    public PasaporteOrdinario(){}
    public String getRazonDeViaje() { return razonDeViaje; }
    public void setRazonDeViaje(String razonDeViaje) { this.razonDeViaje = razonDeViaje; }
    public String toString(){
        return super.toString() + " -> O " + razonDeViaje;
    }
}
