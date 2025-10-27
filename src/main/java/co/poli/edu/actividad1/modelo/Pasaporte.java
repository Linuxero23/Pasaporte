package co.poli.edu.actividad1.modelo;
public abstract class Pasaporte {
    protected String id;
    protected int id_titular;
    protected String fechaEx;
    protected int pais;
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
    public int getTitular(){
        return id_titular;
    }
    public String getId() { return id; }
    public String getFechaEx() { return fechaEx; }
    public int getPais() { return pais; }
    public void setId(String id){this.id=id;}
    public String getPaisNombre(){
        if(pais==57)
            return "Colombia";
        return "Argentina";
    }
    public void setTitular(int id_titular){this.id_titular=id_titular;}
    public void setFechaEx(String fechaEx){this.fechaEx=fechaEx;}
    public void setPais(int pais){this.pais=pais;}
}

