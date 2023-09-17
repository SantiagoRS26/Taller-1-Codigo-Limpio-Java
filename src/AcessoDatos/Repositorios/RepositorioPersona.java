package AcessoDatos.Repositorios;

import java.sql.CallableStatement;
import java.sql.Connection;

import AcessoDatos.DBContext.DBContext;
import AcessoDatos.Interfaces.IGenerica;
import Entidades.Persona.Persona;

public class RepositorioPersona implements IGenerica<Persona> {

    private final Connection conexion;

    public RepositorioPersona() {
        conexion = new DBContext().getConexion();
    }

    public boolean Crear(Persona entidad) {
        boolean exito = false;

        try (CallableStatement stmt = conexion.prepareCall("{ ? = call sp_InsertarPersona(?, ?, ?, ?, ?) }")) {

            stmt.registerOutParameter(1, java.sql.Types.BOOLEAN);
            stmt.setString(2, entidad.getCedula());
            stmt.setString(3, entidad.getNombre());
            stmt.setString(4, entidad.getGenero());
            stmt.setInt(5, entidad.getEdad());
            stmt.setString(6, entidad.getDireccion());
            stmt.execute();

            exito = stmt.getBoolean(1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al crear la persona");
        }

        return exito;
    }

    public boolean Editar(Persona entidad) {
        throw new UnsupportedOperationException("Unimplemented method 'Editar'");
    }

    public boolean Eliminar(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'Eliminar'");
    }

    public Persona Buscar(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'Buscar'");
    }

    public Persona[] BuscarTodo() {
        throw new UnsupportedOperationException("Unimplemented method 'BuscarTodo'");
    }

}
