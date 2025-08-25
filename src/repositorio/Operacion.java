package repositorio;

public interface Operacion<T> {
    String insertar(T entidad);
    String actualizar(T entidad);
    String eliminar(String id);
    T seleccionar(String id);
    java.util.List<T> seleccionarTodos();
}
