package co.poli.edu.actividad1.modelo;

public class Biometrico extends ElementoDeSeguridad{
    private String tipoDeBiometria;
    public Biometrico(String id,String descripcion,String tipoDeBiometria ) {
        this.setDescripcion(descripcion);
        this.setId(id);
        this.tipoDeBiometria = tipoDeBiometria;
    }
    public String toString() {
        return super.toString() + ", Biometria="+tipoDeBiometria;
    }
}
