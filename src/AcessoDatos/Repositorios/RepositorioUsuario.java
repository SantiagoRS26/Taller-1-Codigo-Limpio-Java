package AcessoDatos.Repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import AcessoDatos.DBContext.DBContext;
import AcessoDatos.Interfaces.RepositorioGenerico;

import Entidades.Login.Usuario;
import Entidades.Persona.Persona;

public class RepositorioUsuario implements RepositorioGenerico<Usuario> {
    private final Connection conexion;
    private static final RepositorioGenerico<Persona> _repositorioPersona = new RepositorioPersona();

    public RepositorioUsuario() {
        this.conexion = new DBContext().getConexion();
    }

    public boolean Crear(Usuario entidad) {
        try {
            conexion.setAutoCommit(false);
            try (PreparedStatement stmt = conexion.prepareStatement(
                    "INSERT INTO Usuario (userId, password, username, email, personaCedula) VALUES (?, ?, ?, ?, ?)")) {
                stmt.setString(1, entidad.getUserId());
                stmt.setString(2, entidad.getPassword());
                stmt.setString(3, entidad.getUsername());
                stmt.setString(4, entidad.getEmail());
                stmt.setString(5, entidad.getPersona().getCedula());

                int filasAfectadas = stmt.executeUpdate();

                conexion.commit();

                return filasAfectadas > 0;
            } catch (SQLException e) {
                conexion.rollback();
                e.printStackTrace();
                throw new RuntimeException("Error al crear el usuario");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al establecer la transacción");
        }
    }

    public boolean Editar(Usuario entidad) {
        try {
            conexion.setAutoCommit(false);
            try (PreparedStatement stmt = conexion.prepareStatement(
                    "UPDATE Usuario SET password=?, username=?, email=?, personaCedula=? WHERE userId=?")) {
                stmt.setString(1, entidad.getPassword());
                stmt.setString(2, entidad.getUsername());
                stmt.setString(3, entidad.getEmail());
                stmt.setString(4, entidad.getPersona().getCedula());
                stmt.setString(5, entidad.getUserId());

                int filasAfectadas = stmt.executeUpdate();

                conexion.commit();

                return filasAfectadas > 0;
            } catch (SQLException e) {
                conexion.rollback();
                e.printStackTrace();
                throw new RuntimeException("Error al editar el usuario");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al establecer la transacción");
        }
    }

    public boolean Eliminar(String id) {
        try {
            conexion.setAutoCommit(false);
            try (PreparedStatement stmt = conexion.prepareStatement("DELETE FROM Usuario WHERE userId=?")) {
                stmt.setString(1, id);

                int filasAfectadas = stmt.executeUpdate();

                conexion.commit();

                return filasAfectadas > 0;
            } catch (SQLException e) {
                conexion.rollback();
                e.printStackTrace();
                throw new RuntimeException("Error al eliminar el usuario");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al establecer la transacción");
        }
    }

    public Usuario Buscar(String id) {
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM Usuario WHERE userId=?")) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Persona persona = _repositorioPersona.Buscar(rs.getString("personaCedula"));

                Usuario usuario = new Usuario(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("username"),
                        rs.getString("email"),
                        persona);
                return usuario;
            } else {
                return null; // No se encontró ningún usuario con el ID proporcionado.
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar el usuario por ID");
        }
    }

    public List<Usuario> BuscarTodo() {
        try (PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM Usuario")) {
            ResultSet rs = stmt.executeQuery();
            List<Usuario> usuarios = new ArrayList<>();

            while (rs.next()) {
                Persona persona = _repositorioPersona.Buscar(rs.getString("personaCedula"));

                Usuario usuario = new Usuario(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("username"),
                        rs.getString("email"),
                        persona);
                usuarios.add(usuario);
            }

            return usuarios;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al buscar todos los usuarios");
        }
    }
}
