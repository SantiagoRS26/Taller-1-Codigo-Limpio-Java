package AcessoDatos.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidades.Login.Usuario;

public class CrudUsuario {

    private Connection conexion;

    public CrudUsuario() {
        conexion = new DBContext().getConexion();
    }

    public void CrearUsuario(Usuario usuario) {
        String sql = "INSERT INTO Usuario(userId, password, username, email, personaCedula) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getUserId());
            statement.setString(2, usuario.getPassword());
            statement.setString(3, usuario.getUsername());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getPersonaCedula());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear el usuario");
        }
    }

    public Usuario LeerUsuarioPorId(String userId) {
        String sql = "SELECT * FROM Usuario WHERE userId = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setUserId(resultSet.getString("userId"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setUsername(resultSet.getString("username"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setPersonaCedula(resultSet.getString("personaCedula"));
                return usuario;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al leer el usuario por ID");
        }

        return null;
    }

    public void ActualizarUsuario(Usuario usuario) {
        String sql = "UPDATE Usuario SET password = ?, username = ?, email = ?, personaCedula = ? WHERE userId = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, usuario.getPassword());
            statement.setString(2, usuario.getUsername());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getPersonaCedula());
            statement.setString(5, usuario.getUserId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el usuario");
        }
    }

    public void EliminarUsuario(String userId) {
        String sql = "DELETE FROM Usuario WHERE userId = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar el usuario");
        }
    }

    public List<Usuario> ListarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM Usuario";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setUserId(resultSet.getString("userId"));
                usuario.setPassword(resultSet.getString("password"));
                usuario.setUsername(resultSet.getString("username"));
                usuario.setEmail(resultSet.getString("email"));
                //usuario.setPersonaCedula(resultSet.getString("personaCedula"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al listar los usuarios");
        }

        return usuarios;
    }
}
