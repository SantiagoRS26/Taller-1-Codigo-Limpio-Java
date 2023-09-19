package AcessoDatos.Interfaces;

import java.util.List;

public interface RepositorioGenerico<TEntidad> {
    boolean Crear(TEntidad entidad);

    boolean Editar(TEntidad entidad);

    boolean Eliminar(String id);

    TEntidad Buscar(String id);

    List<TEntidad> BuscarTodo();
}