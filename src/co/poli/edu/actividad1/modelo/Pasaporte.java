package co.poli.edu.actividad1.modelo;

public class Pasaporte {
    private String id;
    private String fechaEx;
    private String pais;

    public Pasaporte(String id, String fechaEx, String pais) {
        this.id = id;
        this.fechaEx = fechaEx;
        this.pais = pais;
    }

    public String getId() { return id; }
    public String getFechaEx() { return fechaEx; }
    public String getPais() { return pais; }
}

