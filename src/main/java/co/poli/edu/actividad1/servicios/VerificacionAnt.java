package co.poli.edu.actividad1.servicios;
import java.util.*;

public class VerificacionAnt{
    private HashSet<String> antecedentes = new HashSet<>();

    public VerificacionAnt() {
        antecedentes.add("67890");
        antecedentes.add("98765");
    }

    public boolean verificarAnt(String id) {

        if(antecedentes.contains(id)){
            return false;
        }
        return true;

    }
}

