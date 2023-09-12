package Login;

import java.time.LocalDateTime;

public class UsuarioRegistrado {
    private LocalDateTime fechaRegistro;
    private String nivelAcceso;
    private String usuarioRegistradoId;
    private Usuario usuario;
    private Plataforma plataforma;

    public Usuario getUsuario() {
        return usuario;
    }

    public UsuarioRegistrado(LocalDateTime fechaRegistro, String nivelAcceso, Usuario usuario, Plataforma plataforma, String usuarioRegistradoId) {
        this.fechaRegistro = fechaRegistro;
        this.nivelAcceso = nivelAcceso;
        this.usuario = usuario;
        this.plataforma = plataforma;
        this.usuarioRegistradoId = usuarioRegistradoId;
    }

    public void RealizarActividadEnPlataforma() {
        System.out.println("El usuario " + usuario.getUsername() + " está realizando una actividad en la plataforma " + plataforma.getNombre());
    }

    @Override
    public String toString() {
        return "UsuarioRegistrado{" +
                "fechaRegistro=" + fechaRegistro +
                ", nivelAcceso='" + nivelAcceso + '\'' +
                ", usuarioRegistradoId='" + usuarioRegistradoId + '\'' +
                ", usuario=" + usuario +
                ", plataforma=" + plataforma +
                '}';
    }
}
