package AcessoDatos.Interfaces;

public interface IGenerica<TEntidad> {
    boolean Crear(TEntidad entidad);
    boolean Editar(TEntidad entidad);
    boolean Eliminar(String id);
    TEntidad Buscar(String id);
    TEntidad[] BuscarTodo();
}
