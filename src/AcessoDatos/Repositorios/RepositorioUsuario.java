package AcessoDatos.Repositorios;

import AcessoDatos.Interfaces.IGenerica;

import Entidades.Login.Usuario;

public class RepositorioUsuario implements IGenerica<Usuario> {

    public boolean Crear(Usuario entidad) {
        throw new UnsupportedOperationException("Unimplemented method 'Crear'");
    }

    public boolean Editar(Usuario entidad) {
        throw new UnsupportedOperationException("Unimplemented method 'Editar'");
    }

    public boolean Eliminar(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'Eliminar'");
    }

    public Usuario Buscar(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'Buscar'");
    }

    public Usuario[] BuscarTodo() {
        throw new UnsupportedOperationException("Unimplemented method 'BuscarTodo'");
    }
    
}
