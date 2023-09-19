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

        /* Descomentar la opcion a probar, la base de datos esta en la nube, entonces se puede ejecutar desde cualquier maquina */
        /* Puede tomar un tiempo ya que la base de datos fue creada en una pagina gratuita */


        probarBuscarTodoUsuarios();
        //probarCrearUsuario();
        //probarEditarUsuario();
        //probarEliminarUsuario();
        //probarBuscarUsuario();
        //CrearInstancias();
    }

    private static void probarCrearUsuario() {
        // Crea un objeto Usuario
        Usuario usuario = new Usuario("usuario1", "password", "username", "email", new Persona("cedula", "nombre", "genero", 30, "direccion"));

        // Llama al método Crear del RepositorioUsuario
        boolean resultado = _repositorioUsuario.Crear(usuario);

        // Verifica si se creó correctamente
        if (resultado) {
            System.out.println("Usuario creado con éxito.");
        } else {
            System.out.println("Error al crear el usuario.");
        }
    }

    private static void probarEditarUsuario() {
        // Supongamos que tienes un usuario con ID "usuario1" en la base de datos.
        Usuario usuario = _repositorioUsuario.Buscar("usuario1");

        if (usuario != null) {
            // Modifica algún campo del usuario
            usuario.setUsername("nuevoUsername");

            // Llama al método Editar del RepositorioUsuario
            boolean resultado = _repositorioUsuario.Editar(usuario);

            // Verifica si se editó correctamente
            if (resultado) {
                System.out.println("Usuario editado con éxito.");
            } else {
                System.out.println("Error al editar el usuario.");
            }
        } else {
            System.out.println("Usuario no encontrado para editar.");
        }
    }

    private static void probarEliminarUsuario() {
        // Supongamos que tienes un usuario con ID "usuario1" en la base de datos.
        Usuario usuario = _repositorioUsuario.Buscar("usuario1");

        if (usuario != null) {
            // Llama al método Eliminar del RepositorioUsuario
            boolean resultado = _repositorioUsuario.Eliminar("usuario1");

            // Verifica si se eliminó correctamente
            if (resultado) {
                System.out.println("Usuario eliminado con éxito.");
            } else {
                System.out.println("Error al eliminar el usuario.");
            }
        } else {
            System.out.println("Usuario no encontrado para eliminar.");
        }
    }

    private static void probarBuscarUsuario() {
        // Supongamos que tienes un usuario con ID "usuario1" en la base de datos.
        Usuario usuario = _repositorioUsuario.Buscar("usuario1");

        if (usuario != null) {
            System.out.println("Usuario encontrado:");
            System.out.println("ID: " + usuario.getUserId());
            System.out.println("Nombre: " + usuario.getUsername());
            System.out.println("Email: " + usuario.getEmail());
            // Agrega más campos según tu clase Usuario
        } else {
            System.out.println("Usuario no encontrado.");
        }
    }

    private static void probarBuscarTodoUsuarios() {
        List<Usuario> usuarios = _repositorioUsuario.BuscarTodo();
    
        if (!usuarios.isEmpty()) {
            System.out.println("Usuarios encontrados:");
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getUserId());
                System.out.println("Nombre de Usuario: " + usuario.getUsername());
                System.out.println("Email: " + usuario.getEmail());
                
                // Imprime la información de la persona asociada al usuario
                System.out.println("Información de la Persona Asociada:");
                System.out.println("Cédula: " + usuario.getPersona().getCedula());
                System.out.println("Nombre: " + usuario.getPersona().getNombre());
                System.out.println("Género: " + usuario.getPersona().getGenero());
                System.out.println("Edad: " + usuario.getPersona().getEdad());
                System.out.println("Dirección: " + usuario.getPersona().getDireccion());
                
                System.out.println("-----------------------------------");
            }
        } else {
            System.out.println("No se encontraron usuarios.");
        }
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