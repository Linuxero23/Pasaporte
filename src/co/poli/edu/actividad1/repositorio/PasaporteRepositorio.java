package co.poli.edu.actividad1.repositorio;

import co.poli.edu.actividad1.modelo.Pasaporte;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasaporteRepositorio implements Operacion<Pasaporte> {

    private final String url = "jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:6543/postgres?sslmode=require";
    private final String user = "postgres.jcrgllisixmujsjvxxht";
    private final String password = "edwar1032797762";



    @Override
    public String insertar(Pasaporte pasaporte) {
        String sql = "INSERT INTO pasaporte (id, fecha_ex, pais) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pasaporte.getId());
            stmt.setString(2, pasaporte.getFechaEx());
            stmt.setString(3, pasaporte.getPais());

            stmt.executeUpdate();
            return "✅ Pasaporte guardado correctamente";
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Error al guardar pasaporte: " + e.getMessage();
        }
    }

    @Override
    public String actualizar(Pasaporte pasaporte) {
        return "No implementado todavía";
    }

    @Override
    public String eliminar(String id) {
        return "No implementado todavía";
    }

    @Override
    public Pasaporte seleccionar(String id) {
        return null;
    }

    @Override
    public List<Pasaporte> seleccionarTodos() {
        return new ArrayList<>();
    }
}
