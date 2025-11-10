package co.poli.edu.actividad1.servicios;


public class StrategyCostoOrdinario implements CalculoPrecio {
    @Override
    public double calcularPrecio(String pais) {

        if(pais.equals("Colombia")){
            return 42.22;
        }else if(pais.equals("Venezuela")){
            return 164;
        }else if(pais.equals("Perú")){
            return 35.85;
        }else{
            return -1;
        }

    }


}
