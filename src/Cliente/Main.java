package Cliente;

import Login.LoginUsuario;
import Login.Plataforma;
import Persona.Persona;
import Persona.TipoPersona.Empleado;

public class Main {
    public static void main(String[] args) throws Exception {
        LoginUsuario userLogin = new LoginUsuario();

        Persona persona = new Persona("Juan Perez", "Masculino", 25, "123456789", "Calle 123");
        Empleado empleado = new Empleado("", null, 20, null, null, null, null);

        Plataforma plataforma = new Plataforma("Plataforma de prueba", "Prueba", 2, "123456789");

        System.out.println("\n");
        plataforma.listarPlataformas();
        System.out.println("\n");
        userLogin.RealizarLogin("juan.perez@example.com", "claveSegura1");
    }
}