package Entidades.Login;

import Entidades.Persona.Persona;

public class Usuario {
    private String userId;
    private String password;
    private String username;
    private String email;
    private Persona persona;

    public Usuario(String userId, String password, String username, String email, Persona persona) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.persona = persona;
    }

    public Usuario() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void ImprimirInformacion() {
        System.out.println("Usuario: " + userId);
        System.out.println("Email: " + email);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", persona=" + persona +
                '}';
    }
}
