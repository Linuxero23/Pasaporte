package co.poli.edu.actividad1.servicios;

public class Memento {
    private String id;
    private String titular;
    private String fechaEx;
    private String pais;
    private String idElemento;
    private String mision;
    private  String razonDeViaje;
    public Memento() {}

    public String getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public String getFechaEx() {
        return fechaEx;
    }

    public String getPais() {
        return pais;
    }

    public String getIdElemento() {
        return idElemento;
    }

    public String getMision() {
        return mision;
    }

    public String getRazonDeViaje() {
        return razonDeViaje;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitular(String id_titular) {
        this.titular = id_titular;
    }

    public void setFechaEx(String fechaEx) {
        this.fechaEx = fechaEx;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setIdElemento(String idElemento) {
        this.idElemento = idElemento;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public void setRazonDeViaje(String razonDeViaje) {
        this.razonDeViaje = razonDeViaje;
    }
}
