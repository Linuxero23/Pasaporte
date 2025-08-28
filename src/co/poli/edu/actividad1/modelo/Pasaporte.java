package co.poli.edu.actividad1.modelo;
import co.poli.edu.actividad1.modelo.Titular;
import co.poli.edu.actividad1.modelo.Pais;
public class Pasaporte {

    private String id;
    private String id_titular;
    private String fechaEx;
    private String pais;

    public Pasaporte(String id, String id_titular, String id_pais, String fechaEx) {
        this.id = id;
        this.fechaEx = fechaEx;
        this.pais = id_pais;
        this.id_titular = id_titular;
    }
    public Pasaporte(String Id,String fecha,Titular persona, Pais pais) {
        id=Id;
        fechaEx=fecha;
        this.pais=pais.getCodigo();
        id_titular=persona.getId();
    }
    public String toString(){
        return "["+id+","+id_titular+","+fechaEx+","+pais+"]";
    }
    public String getTitular(){return id_titular;}
    public String getId() { return id; }
    public String getFechaEx() { return fechaEx; }
    public String getPais() { return pais; }
}

