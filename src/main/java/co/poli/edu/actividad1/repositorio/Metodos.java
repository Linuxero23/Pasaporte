package co.poli.edu.actividad1.repositorio;
import co.poli.edu.actividad1.modelo.Pasaporte;

import java.util.List;

public interface Metodos<T> extends Operacion<Pasaporte>{
    List<Pasaporte> seleccionarConCaracter(char c);
}
