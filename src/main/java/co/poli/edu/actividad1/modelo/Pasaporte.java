package co.poli.edu.actividad1.modelo;

public abstract class Pasaporte implements Cloneable {
    protected String id;
    protected int id_titular;
    protected String fechaEx;
    protected int pais;
    protected ElementoDeSeguridad elementoSeguridad;

    @Override
    public String toString(){
        return "[" + id + ", " + id_titular + ", " + pais + ", " + fechaEx + "]"
                + (elementoSeguridad != null ? " " + elementoSeguridad.toString() : "");
    }

    public int getTitular(){ return id_titular; }
    public String getId() { return id; }
    public String getFechaEx() { return fechaEx; }
    public int getPais() { return pais; }
    public ElementoDeSeguridad getElementoSeguridad() { return elementoSeguridad; }

    public void setId(String id){ this.id = id; }
    public void setTitular(int id_titular){ this.id_titular = id_titular; }
    public void setFechaEx(String fechaEx){ this.fechaEx = fechaEx; }
    public void setPais(int pais){ this.pais = pais; }
    public void setElementoSeguridad(ElementoDeSeguridad elementoSeguridad){
        this.elementoSeguridad = elementoSeguridad;
    }

    // Método Prototype
    @Override
    public Pasaporte clone() {
        try {
            Pasaporte copia = (Pasaporte) super.clone();

            // Deep clone del elemento de seguridad
            if (this.elementoSeguridad != null) {
                copia.setElementoSeguridad(this.elementoSeguridad.clone());
            }

            return copia;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Error al clonar Pasaporte", e);
        }
    }
}
