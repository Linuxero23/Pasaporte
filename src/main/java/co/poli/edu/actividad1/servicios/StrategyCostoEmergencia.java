package co.poli.edu.actividad1.servicios;


public class StrategyCostoEmergencia implements CalculoPrecio {

    @Override
    public double calcularPrecio(String pais) {

        if(pais.equals("Colombia")){
            return 50;
        }else if( pais.equals("Venezuela")){
            return 290;
        }else if(pais.equals("Perú")){
            return 40;
        }else{
            return -1;
        }
    }
}
