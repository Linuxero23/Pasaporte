package co.poli.edu.actividad1.servicios;

import co.poli.edu.actividad1.modelo.*;
import co.poli.edu.actividad1.servicios.*;

public class AdaptadorPasaporte implements InterfacePasaporte {
    private PasaporteOrdinario pasaporteO;
    private PasaporteDiplomatico pasaporteD;

    public AdaptadorPasaporte(Pasaporte pasaporte) {
        if(pasaporte instanceof PasaporteOrdinario)
            pasaporteO=(PasaporteOrdinario)pasaporte;
        else
            pasaporteD=(PasaporteDiplomatico) pasaporte;
    }

    @Override
    public String getDescripcion() {
        if(pasaporteO==null)
            return pasaporteD.toString();
        return pasaporteO.toString();
    }
    public Memento save(){
        Memento cur=new Memento();
        if(pasaporteO!=null){
            cur.setId(pasaporteO.getId());
            cur.setPais(pasaporteO.getPais());
            cur.setTitular(pasaporteO.getTitular());
            cur.setIdElemento(pasaporteO.getIdElemento());
            cur.setFechaEx(pasaporteO.getFechaEx());
            cur.setRazonDeViaje(pasaporteO.getRazonDeViaje());
        }
        else{
            cur.setId(pasaporteD.getId());
            cur.setPais(pasaporteD.getPais());
            cur.setTitular(pasaporteD.getTitular());
            cur.setIdElemento(pasaporteD.getIdElemento());
            cur.setFechaEx(pasaporteD.getFechaEx());
            cur.setMision(pasaporteD.getMision());
        }
        return cur;
    }
    public Pasaporte restore(Memento memento){
        if(memento.getMision()==null){
            PasaporteOrdinario ord=new PasaporteOrdinario();
            ord.setId(memento.getId());
            ord.setPais(memento.getPais());
            ord.setTitular(memento.getTitular());
            ord.setElemento(memento.getIdElemento());
            ord.setFechaEx(memento.getFechaEx());
            ord.setRazonDeViaje(memento.getRazonDeViaje());
            return ord;
        }
        PasaporteDiplomatico ord=new PasaporteDiplomatico();
        ord.setId(memento.getId());
        ord.setPais(memento.getPais());
        ord.setTitular(memento.getTitular());
        ord.setElemento(memento.getIdElemento());
        ord.setFechaEx(memento.getFechaEx());
        ord.setMision(memento.getMision());
        return ord;
    }
}
