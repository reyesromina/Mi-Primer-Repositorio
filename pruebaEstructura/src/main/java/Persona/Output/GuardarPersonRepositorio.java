package Persona.Output;

import org.Persona;

public interface GuardarPersonRepositorio {

    boolean guardarPersona(Persona persona);
    boolean existePersona(String dni);
}
