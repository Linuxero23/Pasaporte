package co.poli.edu.actividad1.modelo;
public abstract class Pasaporte {
    protected String id;
    protected String id_titular;
    protected String fechaEx;
    protected String pais;
    private String idElemento;

    public void setElemento(String id) {
        idElemento = id;
    }
    public String getIdElemento(){
        return idElemento;
    }
    public String toString(){
        return "["+id+","+id_titular+","+pais+","+fechaEx+"]";
    }
    public String getTitular(){
        return id_titular;
    }
    public String getId() { return id; }
    public String getFechaEx() { return fechaEx; }
    public String getPais() { return pais; }
    public void setId(String id){this.id=id;}
    public void setTitular(String id_titular){this.id_titular=id_titular;}
    public void setFechaEx(String fechaEx){this.fechaEx=fechaEx;}
    public void setPais(String pais){this.pais=pais;}
}

