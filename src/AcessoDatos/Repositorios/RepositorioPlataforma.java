package AcessoDatos.Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AcessoDatos.DBContext.DBContext;
import AcessoDatos.Interfaces.RepositorioGenerico;
import Entidades.Login.Plataforma;

public class RepositorioPlataforma implements RepositorioGenerico<Plataforma> {
    private final Connection conexion;

    public RepositorioPlataforma() {
        this.conexion = new DBContext().getConexion();
    }

    public boolean Crear(Plataforma entidad) {
        try {
            conexion.setAutoCommit(false);
            try (PreparedStatement stmt = conexion.prepareStatement(
                    "INSERT INTO Plataforma (plataformaId, nombre, tipo, usuariosConectados) VALUES (?, ?, ?, ?)")) {
                stmt.setString(1, entidad.getPlataformaId());
                stmt.setString(2, entidad.getNombre());
                stmt.setString(3, entidad.getTipo());
                stmt.setInt(4, entidad.getUsuariosConectados());

                int filasAfectadas = stmt.executeUpdate();

                conexion.commit();

                return filasAfectadas > 0;

            } catch (SQLException e) {
                conexion.rollback();
                e.printStackTrace();
                throw new RuntimeException("Error al crear la plataforma");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al establecer la transaccion");
        }
    }

    public boolean Editar(Plataforma entidad) {
        try {
            conexion.setAutoCommit(false);
            try (PreparedStatement stmt = conexion.prepareStatement(
                    "UPDATE Plataforma SET nombre=?, tipo=?, usuariosConectados=? WHERE plataformaId=?")) {
                stmt.setString(1, entidad.getNombre());
                stmt.setString(2, entidad.getTipo());
                stmt.setInt(3, entidad.getUsuariosConectados());
                stmt.setString(4, entidad.getPlataformaId());

                int filasAfectadas = stmt.executeUpdate();

                conexion.commit();

                return filasAfectadas > 0;
            } catch (SQLException e) {
                conexion.rollback();
                e.printStackTrace();
                throw new RuntimeException("Error al editar la plataforma");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al establecer la transacción");
        }
    }

    public boolean Eliminar(String id) {
        try {
            conexion.setAutoCommit(false);
            try (PreparedStatement stmt = conexion.prepareStatement("DELETE FROM Plataforma WHERE plataformaId=?")) {
                stmt.setString(1, id);

                int filasAfectadas = stmt.executeUpdate();

                conexion.commit();

                return filasAfectadas > 0;
            } catch (SQLException e) {
                conexion.rollback();
                e.printStackTrace();
                throw new RuntimeException("Error al eliminar la plataforma");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al establecer la transacción");
        }
    }

    public Plataforma Buscar(String id) {
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM Plataforma WHERE plataformaId=?")) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Plataforma plataforma = new Plataforma(
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getInt("usuariosConectados"),
                        rs.getString("plataformaId"));
                return plataforma;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar la plataforma por ID");
        }
    }

    public List<Plataforma> BuscarTodo() {
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM Plataforma")) {
            ResultSet rs = stmt.executeQuery();
            List<Plataforma> plataformas = new ArrayList<>();

            while (rs.next()) {
                Plataforma plataforma = new Plataforma(
                        rs.getString("nombre"),
                        rs.getString("tipo"),
                        rs.getInt("usuariosConectados"),
                        rs.getString("plataformaId"));
                plataformas.add(plataforma);
            }

            return plataformas;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar todas las plataformas");
        }
    }

}
