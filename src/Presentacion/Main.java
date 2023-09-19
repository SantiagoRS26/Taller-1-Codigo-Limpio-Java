package Presentacion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import AcessoDatos.Interfaces.RepositorioGenerico;
import AcessoDatos.Repositorios.*;
import Entidades.Login.*;
import Entidades.Persona.*;
import Entidades.Persona.Cargo.*;
import Entidades.Persona.TipoPersona.*;
import Entidades.Servicio.*;

public class Main {
    private static final RepositorioGenerico<Persona> _repositorioPersona = new RepositorioPersona();
    private static final RepositorioGenerico<Usuario> _repositorioUsuario = new RepositorioUsuario();
    private static final RepositorioGenerico<Plataforma> _repositorioPlataforma = new RepositorioPlataforma();

    public static void main(String[] args) throws Exception {
    }

    private static void CrearInstancias() {
        // Crear instancias

        Persona persona = new Persona("1234567890", "Ana López", "Femenino", 25, "Calle 456, Ciudad");

        Cargo cargo1 = new Cargo("C001", "Gerente de Recursos Humanos", "Encargado de la gestión de personal");
        Cargo cargo2 = new Cargo("C002", "Analista de Reclutamiento", "Encargado de procesos de selección");

        Empleado empleado = new Empleado("Juan Pérez", "Masculino", 30, "1234567890", "Calle 123, Ciudad", "EC001",
                "321 4314131");

        Estudiante estudiante = new Estudiante("María García", "Femenino", 20, "0987654321", "Av. Principal, Ciudad",
                "EC002", "Ingeniería de Sistemas", 8.5f, 3);

        Docente docente = new Docente("Carlos Rodríguez", "Masculino", 35, "2345678901", "Av. Central, Ciudad", "EC003",
                "555-5678", "Programación Avanzada", "Facultad de Ingeniería");

        PersonaCargo pcEmpleado = new PersonaCargo("PC001", LocalDate.now(), persona, cargo1);
        PersonaCargo pcEstudiante = new PersonaCargo("PC002", LocalDate.now(), persona, cargo2);
        PersonaCargo pcDocente = new PersonaCargo("PC003", LocalDate.now(), persona, cargo1);

        Servicio servicio1 = new Servicio("Reclutamiento de personal", "Servicio de búsqueda y selección de talento",
                500.0);
        Servicio servicio2 = new Servicio("Capacitación en liderazgo", "Programa de formación para líderes", 300.0);

        DetalleVentaServicio detalleVenta = new DetalleVentaServicio("DV001", 5, servicio1);

        VentaServicio venta = new VentaServicio("V001", LocalDateTime.now(), persona);
        venta.agregarDetalle(detalleVenta);

        Facturacion factura = new Facturacion(LocalDateTime.now(), "F001");
        factura.AgregarVenta(venta);

        System.out.println("\nTotal factura: " + factura.GenerarFactura());

        Usuario usuario = new Usuario("userID123", "contraseña123", "usuario123", "usuario123@example.com", persona);

        Plataforma plataforma = new Plataforma("Plataforma de Recursos Humanos", "Recursos Humanos", 100, "PLAT001");

        UsuarioRegistrado usuarioRegistrado = new UsuarioRegistrado(LocalDateTime.now(), "Nivel de Acceso 1", usuario,
                plataforma, "UR001");

        LoginUsuario loginUsuario = new LoginUsuario("LoginID001", usuarioRegistrado, LocalDateTime.now());

        System.out.println("\nInformación del Usuario:");
        usuario.ImprimirInformacion();

        System.out.println("\nRealizando actividad en la Plataforma:");
        usuarioRegistrado.RealizarActividadEnPlataforma();

        System.out.println("\nInforme del Estado de la Plataforma:");
        String informeEstadoPlataforma = plataforma.generarInformeEstado();
        System.out.println(informeEstadoPlataforma);

        System.out.println("\nListado de Plataformas:");
        plataforma.listarPlataformas();

        System.out.println("\nEnviando notificación desde la Plataforma:");
        plataforma.enviarNotificacion("¡Bienvenidos a la Plataforma de Recursos Humanos!");

        System.out.println("\nIntento de inicio de sesión:");
        loginUsuario.RealizarLogin("juanperez35", "claveSegura1");
    }
}