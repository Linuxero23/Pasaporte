package co.poli.edu.actividad1.modelo;

public class Chip extends ElementoDeSeguridad{
    private String tipoDeChip;
    public Chip(String id,String descripcion,String tipoDeChip ) {
        this.setDescripcion(descripcion);
        this.setId(id);
        this.tipoDeChip = tipoDeChip;
    }
    public String toString() {
        return super.toString() + ", Chip="+tipoDeChip;
    }
}
