package Login;

import Persona.Persona;

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
