package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.Pasaporte;
import co.poli.edu.actividad1.modelo.PasaporteDiplomatico;
import co.poli.edu.actividad1.modelo.PasaporteOrdinario;

import java.util.Arrays;

public class AdaptadorPasaporte implements InterfacePasaporte {
    private final Pasaporte pasaporte;

    public AdaptadorPasaporte(Pasaporte pasaporte) {
        this.pasaporte = pasaporte;
    }

    @Override
    public String getDescripcion() {
        String s=  pasaporte.getId() +
                "\n" + pasaporte.getTitular() +
                "\n" + pasaporte.getPais() +
                "\n" + pasaporte.getFechaEx() +
                "\n" + pasaporte.getIdElemento();
        if(pasaporte instanceof PasaporteOrdinario){
            PasaporteOrdinario ord = (PasaporteOrdinario) pasaporte;
            s+="\nRZ "+ord.getRazonDeViaje();
        }
        else {
            PasaporteDiplomatico ord = (PasaporteDiplomatico) pasaporte;
            s+="\nMS "+ord.getMision();
        }
        return s;
    }
    public Memento save(){
        return new Memento(this);
    }
    public Pasaporte restore(Memento memento){
        String s=memento.getState();
        String[] arr = s.split("\\R");
        if (arr[arr.length - 1].contains("RZ")) {
            PasaporteOrdinario p = new PasaporteOrdinario();
            p.setId(arr[0]);
            p.setTitular(arr[1]);
            p.setPais(arr[2]);
            p.setFechaEx(arr[3]);
            p.setElemento(arr[4]);
            String[] arr2 = arr[arr.length - 1].split(" ", 2);
            p.setRazonDeViaje(arr2[1]);
            return p;
        } else {
            PasaporteDiplomatico p = new PasaporteDiplomatico();
            p.setId(arr[0]);
            p.setTitular(arr[1]);
            p.setPais(arr[2]);
            p.setFechaEx(arr[3]);
            p.setElemento(arr[4]);
            String[] arr2 = arr[arr.length - 1].split(" ", 2);
            p.setMision(arr2[1]);
            return p;
        }

    }
    public Pasaporte getPasaporte() {
        return pasaporte;
    }
}
