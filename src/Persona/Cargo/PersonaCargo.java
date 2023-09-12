package Persona.Cargo;

import java.time.LocalDate;

import Persona.Persona;

public class PersonaCargo {
    private String idPersonaCargo;
    private LocalDate fechaContratacion;
    private Persona persona;
    private Cargo cargo;

    public PersonaCargo(String idPersonaCargo, LocalDate fechaContratacion, Persona persona, Cargo cargo) {
        this.idPersonaCargo = idPersonaCargo;
        this.fechaContratacion = fechaContratacion;
        this.persona = persona;
        this.cargo = cargo;
    }

    @Override
    public String toString() {
        return "PersonaCargo{" +
                "idPersonaCargo='" + idPersonaCargo + '\'' +
                ", fechaContratacion=" + fechaContratacion +
                ", persona=" + persona +
                ", cargo=" + cargo +
                '}';
    }
}
