package co.poli.edu.actividad1.repositorio;

import co.poli.edu.actividad1.modelo.Pasaporte;
import co.poli.edu.actividad1.config.ConexionBD;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class PasaporteRepositorio implements Operacion<Pasaporte> {

    @Override
    public String insertar(Pasaporte pasaporte) {
        String sql = "INSERT INTO PrPasaporte (id_pasaporte, id_persona, id_pais, fecha_exp) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pasaporte.getId());
            stmt.setString(2, pasaporte.getTitular());
            stmt.setString(3, pasaporte.getPais());
            stmt.setString(4, pasaporte.getFechaEx());

            stmt.executeUpdate();
            return "✅ Pasaporte guardado correctamente";

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Error al guardar pasaporte: " + e.getMessage();
        }
    }

    @Override
    public String actualizar(String id, Pasaporte pasaporte) {
        pasaporte.setId(id);
        eliminar(id);
        insertar(pasaporte);
        return "Pasaporte acualizado con exito";
    }

    @Override
    public String eliminar(String idPasaporte) {
        String sql = "DELETE FROM PrPasaporte WHERE id_pasaporte = ?";
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, idPasaporte);
            int filasAfectadas = stmt.executeUpdate();

            if (filasAfectadas > 0) {
                return "✅ Pasaporte eliminado correctamente";
            } else {
                return "⚠️ No se encontró un pasaporte con el ID proporcionado";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Error al eliminar pasaporte: " + e.getMessage();
        }
    }

    @Override
    public Pasaporte seleccionar(String id) {
        Pasaporte pasaporte = null;
        String sql = "SELECT * FROM PrPasaporte WHERE id_pasaporte = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                pasaporte = new Pasaporte(
                        rs.getString("id_pasaporte"),
                        rs.getString("id_persona"),
                        rs.getString("id_pais"),
                        rs.getString("fecha_exp")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pasaporte;
    }

    @Override
    public List<Pasaporte> seleccionarTodos() {
        List<Pasaporte> pasaportes = new LinkedList<>();
        String sql = "SELECT * FROM PrPasaporte";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Pasaporte p = new Pasaporte(
                        rs.getString("id_pasaporte"),
                        rs.getString("id_persona"),
                        rs.getString("id_pais"),
                        rs.getString("fecha_exp")
                );
                pasaportes.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pasaportes;
    }
    @Override
    public List<Pasaporte> seleccionarConCaracter(char c){
        List<Pasaporte> pasaportes = seleccionarTodos();
        List<Pasaporte> filtro=new LinkedList<>();
        for(Pasaporte p:pasaportes){
            if(p.getId().contains(""+c))
                filtro.add(p);
        }
        return filtro;
    }

}
