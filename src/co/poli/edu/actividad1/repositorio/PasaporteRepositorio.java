package co.poli.edu.actividad1.repositorio;

import co.poli.edu.actividad1.modelo.*;
import co.poli.edu.actividad1.servicios.*;

import java.sql.*;
import java.util.*;

public class PasaporteRepositorio implements Operacion<Pasaporte> {
    CreatorPasaporteOrdinario cpo=new CreatorPasaporteOrdinario();
    CreatorPasaporteDiplomatico cpd=new CreatorPasaporteDiplomatico();
    boolean flag=true;
    @Override
    public String insertar(Pasaporte pasaporte) {
        String sqlPasaporte = "INSERT INTO pasaporte (codigo_pasaporte,id_titular, id_pais, fecha_de_expiracion) VALUES (?,?, ?, ?)";
        String sqlOrdinario = "INSERT INTO pasaporte_ordinario (id_pasaporte, motivo_de_viaje) VALUES (?, ?)";
        String sqlDiplomatico = "INSERT INTO pasaporte_diplomatico (id_pasaporte, mision) VALUES (?, ?)";

        try (Connection conn = ConexionBD.getConnection()) {
            conn.setAutoCommit(false);

            long idPasaporteGenerado = -1;

            try (PreparedStatement stmt = conn.prepareStatement(sqlPasaporte, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, pasaporte.getId());
                stmt.setInt(2, pasaporte.getTitular()); // FK al titular
                stmt.setInt(3, pasaporte.getPais());    // FK al país
                stmt.setString(4, pasaporte.getFechaEx());
                stmt.executeUpdate()        ;

                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        idPasaporteGenerado = rs.getLong(1);
                        rs.close();
                    }
                }
            }

            if (idPasaporteGenerado == -1) {
                conn.rollback();
                return "❌ No se pudo generar el ID del pasaporte";
            }

            if (pasaporte instanceof PasaporteOrdinario) {
                PasaporteOrdinario p = (PasaporteOrdinario) pasaporte;
                try (PreparedStatement stmt = conn.prepareStatement(sqlOrdinario)) {
                    stmt.setLong(1, idPasaporteGenerado);
                    stmt.setString(2, p.getRazonDeViaje());
                    stmt.executeUpdate();
                }
            } else if (pasaporte instanceof PasaporteDiplomatico) {
                PasaporteDiplomatico p = (PasaporteDiplomatico) pasaporte;
                try (PreparedStatement stmt = conn.prepareStatement(sqlDiplomatico)) {
                    stmt.setLong(1, idPasaporteGenerado);
                    stmt.setString(2, p.getMision());
                    stmt.executeUpdate();
                }
            }
            conn.commit();
            return "✅ Pasaporte guardado correctamente con ID: " + idPasaporteGenerado;

        } catch (Exception e) {
            if(flag){
                flag=false;
                return insertar(pasaporte);
            }
            e.printStackTrace();
            return "❌ Error al guardar pasaporte: " + e.getMessage();
        }
    }

    @Override
    public String actualizar(String codigoPasaporte, Pasaporte pasaporte) {
        String sqlSelect = "SELECT id_pasaporte FROM pasaporte WHERE codigo_pasaporte = ?";
        String sqlUpdatePasaporte = "UPDATE pasaporte SET id_titular = ?, id_pais = ?, fecha_de_expiracion = ? WHERE id_pasaporte = ?";
        String sqlUpdateOrdinario = "UPDATE pasaporte_ordinario SET motivo_de_viaje = ? WHERE id_pasaporte = ?";
        String sqlUpdateDiplomatico = "UPDATE pasaporte_diplomatico SET mision = ? WHERE id_pasaporte = ?";
        try (Connection conn = ConexionBD.getConnection()) {
            conn.setAutoCommit(false);

            long idPasaporte = -1;
            try (PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
                stmt.setString(1, codigoPasaporte);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        idPasaporte = rs.getLong("id_pasaporte");
                    }
                }
            }

            if (idPasaporte == -1) {
                return "❌ No se encontró pasaporte con código: " + codigoPasaporte;
            }
            try (PreparedStatement stmt = conn.prepareStatement(sqlUpdatePasaporte)) {
                stmt.setInt(1, pasaporte.getTitular());
                stmt.setInt(2, pasaporte.getPais());
                stmt.setString(3, pasaporte.getFechaEx());
                stmt.setLong(4, idPasaporte);
                stmt.executeUpdate();
            }
            if (pasaporte instanceof PasaporteOrdinario) {
                PasaporteOrdinario p = (PasaporteOrdinario) pasaporte;
                try (PreparedStatement stmt = conn.prepareStatement(sqlUpdateOrdinario)) {
                    stmt.setString(1, p.getRazonDeViaje());
                    stmt.setLong(2, idPasaporte);
                    stmt.executeUpdate();
                }
            } else if (pasaporte instanceof PasaporteDiplomatico) {
                PasaporteDiplomatico p = (PasaporteDiplomatico) pasaporte;
                try (PreparedStatement stmt = conn.prepareStatement(sqlUpdateDiplomatico)) {
                    stmt.setString(1, p.getMision());
                    stmt.setLong(2, idPasaporte);
                    stmt.executeUpdate();
                }
            }
            conn.commit();
            return "✅ Pasaporte actualizado correctamente (ID: " + idPasaporte + ")";
        } catch (Exception e) {
            if(flag){
                flag=false;
                return actualizar(codigoPasaporte,pasaporte);
            }
            e.printStackTrace();
            return "❌ Error al actualizar pasaporte: " + e.getMessage();
        }
    }


    @Override
    public String eliminar(String codigoPasaporte) {
        String sqlSelect = "SELECT id_pasaporte FROM pasaporte WHERE codigo_pasaporte = ?";
        String sqlDeleteOrdinario = "DELETE FROM pasaporte_ordinario WHERE id_pasaporte = ?";
        String sqlDeleteDiplomatico = "DELETE FROM pasaporte_diplomatico WHERE id_pasaporte = ?";
        String sqlDeletePasaporte = "DELETE FROM pasaporte WHERE id_pasaporte = ?";

        try (Connection conn = ConexionBD.getConnection()) {
            conn.setAutoCommit(false);

            long idPasaporte = -1;
            try (PreparedStatement stmt = conn.prepareStatement(sqlSelect)) {
                stmt.setString(1, codigoPasaporte);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        idPasaporte = rs.getLong("id_pasaporte");
                    }
                }
            }
            if (idPasaporte == -1) {
                return "❌ No se encontró pasaporte con código: " + codigoPasaporte;
            }
            try (PreparedStatement stmt = conn.prepareStatement(sqlDeleteOrdinario)) {
                stmt.setLong(1, idPasaporte);
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = conn.prepareStatement(sqlDeleteDiplomatico)) {
                stmt.setLong(1, idPasaporte);
                stmt.executeUpdate();
            }
            try (PreparedStatement stmt = conn.prepareStatement(sqlDeletePasaporte)) {
                stmt.setLong(1, idPasaporte);
                stmt.executeUpdate();
            }

            conn.commit();
            return "✅ Pasaporte eliminado correctamente (ID: " + idPasaporte + ")";

        } catch (Exception e) {
            if(flag){
                flag=false;
                return eliminar(codigoPasaporte);
            }
            e.printStackTrace();
            return "❌ Error al eliminar pasaporte: " + e.getMessage();
        }
    }


    public Pasaporte seleccionar(String codigoPasaporte) {
        String sqlSelectPasaporte = "SELECT id_pasaporte, codigo_pasaporte, id_titular, id_pais, fecha_de_expiracion " +
                "FROM pasaporte WHERE codigo_pasaporte = ?";
        String sqlSelectOrdinario = "SELECT motivo_de_viaje FROM pasaporte_ordinario WHERE id_pasaporte = ?";
        String sqlSelectDiplomatico = "SELECT mision FROM pasaporte_diplomatico WHERE id_pasaporte = ?";

        try (Connection conn = ConexionBD.getConnection()) {
            long idPasaporte = -1;
            String codigo = null;
            int idTitular = -1;
            int idPais = -1;
            String fechaExp = null;

            // 1️⃣ Buscar pasaporte en tabla principal
            try (PreparedStatement stmt = conn.prepareStatement(sqlSelectPasaporte)) {
                stmt.setString(1, codigoPasaporte);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        idPasaporte = rs.getLong("id_pasaporte");
                        codigo = rs.getString("codigo_pasaporte");
                        idTitular = rs.getInt("id_titular");
                        idPais = rs.getInt("id_pais");
                        fechaExp = rs.getString("fecha_de_expiracion");
                    }
                }
            }

            if (idPasaporte == -1) {
                return null;
            }

            try (PreparedStatement stmt = conn.prepareStatement(sqlSelectOrdinario)) {
                stmt.setLong(1, idPasaporte);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String razon = rs.getString("motivo_de_viaje");
                        PasaporteOrdinario po=(PasaporteOrdinario) cpo.crearPasaporte();
                        po.setRazonDeViaje(razon);
                        po.setId(codigo);
                        po.setTitular(idTitular);
                        po.setPais(idPais);
                        po.setFechaEx(fechaExp);
                        return po;
                    }
                }
            }
            try (PreparedStatement stmt = conn.prepareStatement(sqlSelectDiplomatico)) {
                stmt.setLong(1, idPasaporte);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        String mision = rs.getString("mision");
                        PasaporteDiplomatico pd=(PasaporteDiplomatico) cpd.crearPasaporte();
                        pd.setMision(mision);
                        pd.setId(codigo);
                        pd.setTitular(idTitular);
                        pd.setPais(idPais);
                        pd.setFechaEx(fechaExp);
                        return pd;
                    }
                }
            }
        } catch (Exception e) {
            if(flag){
                flag=false;
                return seleccionar(codigoPasaporte);
            }
            e.printStackTrace();
            return null;
        }
        return null;
    }
    @Override
    public List<Pasaporte> seleccionarTodos() {
        List<Pasaporte> pasaportes = new ArrayList<>();

        String sqlPasaporte = "SELECT * FROM pasaporte";
        String sqlOrdinario = "SELECT motivo_de_viaje FROM pasaporte_ordinario WHERE id_pasaporte = ?";
        String sqlDiplomatico = "SELECT mision FROM pasaporte_diplomatico WHERE id_pasaporte = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmtPasaporte = conn.prepareStatement(sqlPasaporte);
             ResultSet rs = stmtPasaporte.executeQuery()) {

            while (rs.next()) {
                long idPasaporte = rs.getLong("id_pasaporte");
                String codigo = rs.getString("codigo_pasaporte");
                int idTitular = rs.getInt("id_titular");
                int idPais = rs.getInt("id_pais");
                String fechaExp = rs.getString("fecha_de_expiracion");
                int f=-1;
                try (PreparedStatement stmtOrd = conn.prepareStatement(sqlOrdinario)) {
                    stmtOrd.setLong(1, idPasaporte);
                    try (ResultSet rsOrd = stmtOrd.executeQuery()) {
                        if (rsOrd.next()) {
                            f=0;
                            String razon = rsOrd.getString("motivo_de_viaje");
                            PasaporteOrdinario po=(PasaporteOrdinario) cpo.crearPasaporte();
                            po.setRazonDeViaje(razon);
                            po.setId(codigo);
                            po.setTitular(idTitular);
                            po.setPais(idPais);
                            po.setFechaEx(fechaExp);
                            pasaportes.add(po);
                        }
                    }
                }
                if (f == -1) {
                    try (PreparedStatement stmtDip = conn.prepareStatement(sqlDiplomatico)) {
                        stmtDip.setLong(1, idPasaporte);
                        try (ResultSet rsDip = stmtDip.executeQuery()) {
                            if (rsDip.next()) {
                                String mision = rsDip.getString("mision");
                                PasaporteDiplomatico pd=(PasaporteDiplomatico) cpd.crearPasaporte();
                                pd.setMision(mision);
                                pd.setId(codigo);
                                pd.setTitular(idTitular);
                                pd.setPais(idPais);
                                pd.setFechaEx(fechaExp);
                                pasaportes.add(pd);
                            }
                        }
                    }
                }
            }

        } catch (SQLException e) {
            if(flag){
                flag=false;
                return seleccionarTodos();
            }
            e.printStackTrace();
        }

        return pasaportes;
    }


    @Override
    public List<Pasaporte> seleccionarConCaracter(char ch) {
        List<Pasaporte> pasaportes = new ArrayList<>();

        String sqlPasaporte = "SELECT * FROM pasaporte WHERE codigo_pasaporte LIKE ?";
        String sqlOrdinario = "SELECT motivo_de_viaje FROM pasaporte_ordinario WHERE id_pasaporte = ?";
        String sqlDiplomatico = "SELECT mision FROM pasaporte_diplomatico WHERE id_pasaporte = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement stmtPasaporte = conn.prepareStatement(sqlPasaporte)) {

            stmtPasaporte.setString(1, "%" + ch + "%");

            try (ResultSet rs = stmtPasaporte.executeQuery()) {
                while (rs.next()) {
                    long idPasaporte = rs.getLong("id_pasaporte");
                    String codigo = rs.getString("codigo_pasaporte");
                    int idTitular = rs.getInt("id_titular");
                    int idPais = rs.getInt("id_pais");
                    String fechaExp = rs.getString("fecha_de_expiracion");

                    int f=-1;
                    try (PreparedStatement stmtOrd = conn.prepareStatement(sqlOrdinario)) {
                        stmtOrd.setLong(1, idPasaporte);
                        try (ResultSet rsOrd = stmtOrd.executeQuery()) {
                            if (rsOrd.next()) {
                                f=0;
                                String razon = rsOrd.getString("motivo_de_viaje");
                                PasaporteOrdinario po=(PasaporteOrdinario) cpo.crearPasaporte();
                                po.setRazonDeViaje(razon);
                                po.setId(codigo);
                                po.setTitular(idTitular);
                                po.setPais(idPais);
                                po.setFechaEx(fechaExp);
                                pasaportes.add(po);
                            }
                        }
                    }
                    if (f == -1) {
                        try (PreparedStatement stmtDip = conn.prepareStatement(sqlDiplomatico)) {
                            stmtDip.setLong(1, idPasaporte);
                            try (ResultSet rsDip = stmtDip.executeQuery()) {
                                if (rsDip.next()) {
                                    String mision = rsDip.getString("mision");
                                    PasaporteDiplomatico pd=(PasaporteDiplomatico) cpd.crearPasaporte();
                                    pd.setMision(mision);
                                    pd.setId(codigo);
                                    pd.setTitular(idTitular);
                                    pd.setPais(idPais);
                                    pd.setFechaEx(fechaExp);
                                    pasaportes.add(pd);
                                }
                            }
                        }
                    }
                }
            }

        } catch (SQLException e) {
            if(flag){
                flag=false;
                return seleccionarConCaracter(ch);
            }
            e.printStackTrace();
        }

        return pasaportes;
    }


}
