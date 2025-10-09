package Persona.Output;

import org.Persona;

public interface BuscarPersonaRepositorio {
    Persona buscarPersona(String DNI);
    boolean quitarPersona(Persona persona);
}
