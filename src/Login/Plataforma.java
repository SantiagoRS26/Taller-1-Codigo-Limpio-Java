package Login;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import DBContext.DBContext;

public class Plataforma {
    private String nombre;
    private String plataformaId;
    private String tipo;
    private int usuariosConectados;

    public Plataforma(String nombre, String tipo, int usuariosConectados, String plataformaId) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.usuariosConectados = usuariosConectados;
        this.plataformaId = plataformaId;
    }

    public Plataforma() {
    }

    public String getNombre() {
        return nombre;
    }

    public String generarInformeEstado() {
        StringBuilder informe = new StringBuilder();
        informe.append("Estado de la Plataforma:\n");
        informe.append("Nombre: ").append(nombre).append("\n");
        informe.append("Tipo: ").append(tipo).append("\n");
        informe.append("Usuarios conectados: ").append(usuariosConectados).append("\n");
        informe.append("ID de plataforma: ").append(plataformaId).append("\n");
        return informe.toString();
    }

    public void listarPlataformas() {
        try (Connection conexion = new DBContext().getConexion()) {
            CallableStatement declaracion = conexion.prepareCall("{call listar_plataformas()}");
            ResultSet resultado = declaracion.executeQuery();
    
            while (resultado.next()) {
                String plataformaId = resultado.getString("plataformaId");
                String nombre = resultado.getString("nombre");
                String tipo = resultado.getString("tipo");
                int usuariosConectados = resultado.getInt("usuariosConectados");
    
                System.out.println("Plataforma ID: " + plataformaId);
                System.out.println("Nombre: " + nombre);
                System.out.println("Tipo: " + tipo);
                System.out.println("Usuarios Conectados: " + usuariosConectados);
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void enviarNotificacion(String mensaje) {
        System.out.println("Enviando notificación: " + mensaje);
    }

    @Override
    public String toString() {
        return "Plataforma{" +
                "nombre='" + nombre + '\'' +
                ", plataformaId='" + plataformaId + '\'' +
                ", tipo='" + tipo + '\'' +
                ", usuariosConectados=" + usuariosConectados +
                '}';
    }
}