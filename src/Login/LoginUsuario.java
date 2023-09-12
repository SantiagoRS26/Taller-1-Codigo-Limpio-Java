package Login;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import DBContext.DBContext;

public class LoginUsuario {
    private String loginUsuarioId;
    private LocalDateTime fechaLogin;
    private UsuarioRegistrado usuarioRegistrado;

    public LoginUsuario() {
    }

    public LoginUsuario(String loginUsuarioId, UsuarioRegistrado usuarioRegistrado, LocalDateTime fechaLogin) {
        this.loginUsuarioId = loginUsuarioId;
        this.usuarioRegistrado = usuarioRegistrado;
        this.fechaLogin = fechaLogin;
    }

    public void RealizarLogin(String login, String password) {
        try (Connection conexion = new DBContext().getConexion()) {
            String sql = "{CALL obtener_usuario_por_credenciales(?, ?)}";
            try (CallableStatement statement = conexion.prepareCall(sql)) {
                statement.setString(1, login);
                statement.setString(2, password);
    
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String userId = resultSet.getString("userId");
                        String email = resultSet.getString("email");
                        String username = resultSet.getString("username");
                        String cedula = resultSet.getString("cedula_persona");
                        String nombre = resultSet.getString("nombre_persona");
                        String genero = resultSet.getString("genero_persona");
                        int edad = resultSet.getInt("edad_persona");
                        String direccion = resultSet.getString("direccion_persona");
    
                        System.out.println("Usuario ID: " + userId);
                        System.out.println("Email: " + email);
                        System.out.println("Username: " + username);
                        System.out.println("Cedula: " + cedula);
                        System.out.println("Nombre: " + nombre);
                        System.out.println("Genero: " + genero);
                        System.out.println("Edad: " + edad);
                        System.out.println("Direccion: " + direccion);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al realizar el inicio de sesión: " + e.getMessage());
        }
    }
    

    @Override
    public String toString() {
        return "LoginUsuario{" +
                "loginUsuarioId='" + loginUsuarioId + '\'' +
                ", fechaLogin=" + fechaLogin +
                ", usuarioRegistrado=" + usuarioRegistrado +
                '}';
    }
}