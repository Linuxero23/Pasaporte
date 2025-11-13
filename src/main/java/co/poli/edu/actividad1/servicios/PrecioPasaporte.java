package co.poli.edu.actividad1.servicios;

public class PrecioPasaporte {

    private CalculoPrecio precio;

    public PrecioPasaporte(CalculoPrecio precio){
        this.precio = precio;
    }

    public double CalcularPrecio(String pais){

        return precio.calcularPrecio(pais);
    }
}
