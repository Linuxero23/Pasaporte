package co.poli.edu.actividad1.modelo;

public class PasaporteDiplomatico extends Pasaporte {
    private String mision;

    public PasaporteDiplomatico(){}

    public String getMision() { return mision; }
    public void setMision(String mision) { this.mision = mision; }
    public String toString(){
        return super.toString() + " -> D " + mision;
    }
}
