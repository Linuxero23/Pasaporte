package co.poli.edu.actividad1.servicios;


public class StrategyCostoDiplomatico implements CalculoPrecio {

    @Override
    public double calcularPrecio(String pais) {

        if(pais.equals("Colombia") || pais.equals("Venezuela") || pais.equals("Perú")){
            return 0;
        }else{
            return -1;
        }
    }
}
