package AcessoDatos.Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import AcessoDatos.DBContext.DBContext;
import AcessoDatos.Interfaces.RepositorioGenerico;
import Entidades.Persona.Persona;

public class RepositorioPersona implements RepositorioGenerico<Persona> {

    private final Connection conexion;

    public RepositorioPersona() {
        conexion = new DBContext().getConexion();
    }

    public boolean Crear(Persona entidad) {
        try {
            conexion.setAutoCommit(false);
            try (PreparedStatement stmt = conexion.prepareStatement(
                    "INSERT INTO Persona (cedula, nombre, genero, edad, direccion) VALUES (?, ?, ?, ?, ?)")) {
                stmt.setString(1, entidad.getCedula());
                stmt.setString(2, entidad.getNombre());
                stmt.setString(3, entidad.getGenero());
                stmt.setInt(4, entidad.getEdad());
                stmt.setString(5, entidad.getDireccion());

                int filasAfectadas = stmt.executeUpdate();

                conexion.commit();

                return filasAfectadas > 0;
            } catch (SQLException e) {
                conexion.rollback();
                e.printStackTrace();
                throw new RuntimeException("Error al crear la persona");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al establecer la transacción");
        }
    }

    public boolean Editar(Persona entidad) {
        try {
            conexion.setAutoCommit(false);
            try (PreparedStatement stmt = conexion.prepareStatement(
                    "UPDATE Persona SET nombre=?, genero=?, edad=?, direccion=? WHERE cedula=?")) {
                stmt.setString(1, entidad.getNombre());
                stmt.setString(2, entidad.getGenero());
                stmt.setInt(3, entidad.getEdad());
                stmt.setString(4, entidad.getDireccion());
                stmt.setString(5, entidad.getCedula());

                int filasAfectadas = stmt.executeUpdate();

                conexion.commit();

                return filasAfectadas > 0;
            } catch (SQLException e) {
                conexion.rollback();
                e.printStackTrace();
                throw new RuntimeException("Error al editar la persona");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al establecer la transacción");
        }
    }

    public boolean Eliminar(String cedula) {
        try {
            conexion.setAutoCommit(false);
            try (PreparedStatement stmt = conexion.prepareStatement("DELETE FROM Persona WHERE cedula=?")) {
                stmt.setString(1, cedula);

                int filasAfectadas = stmt.executeUpdate();

                conexion.commit();

                return filasAfectadas > 0;
            } catch (SQLException e) {
                conexion.rollback();
                e.printStackTrace();
                throw new RuntimeException("Error al eliminar la persona");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al establecer la transacción");
        }
    }

    public Persona Buscar(String cedula) {
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM Persona WHERE cedula=?")) {
            stmt.setString(1, cedula);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Persona persona = new Persona(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("genero"),
                        rs.getInt("edad"),
                        rs.getString("direccion"));
                return persona;
            } else {
                return null; // No se encontró ninguna persona con la cédula proporcionada.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar la persona por cédula");
        }
    }

    public List<Persona> BuscarTodo() {
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM Persona")) {
            ResultSet rs = stmt.executeQuery();
            List<Persona> personas = new ArrayList<>();

            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getString("cedula"),
                        rs.getString("nombre"),
                        rs.getString("genero"),
                        rs.getInt("edad"),
                        rs.getString("direccion"));
                personas.add(persona);
            }

            return personas;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar todas las personas");
        }
    }
}
