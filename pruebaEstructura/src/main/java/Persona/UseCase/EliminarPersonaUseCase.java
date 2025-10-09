package Persona.UseCase;

import Persona.Output.BuscarPersonaRepositorio;
import Persona.exceptios.PersonaException;
import Persona.input.EliminarPersonaInput;
import org.Persona;

public class EliminarPersonaUseCase implements EliminarPersonaInput {

    private BuscarPersonaRepositorio repositorio;

    public EliminarPersonaUseCase(BuscarPersonaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public boolean eliminarPersona(String DNI) {

    Persona persona=this.repositorio.buscarPersona(DNI);

        if (persona == null) {
            return false;
        }


    if(persona.getDni().compareTo(DNI)==0){
        boolean resultadoEliminacion=this.repositorio.quitarPersona(persona);

        if(!resultadoEliminacion){
            throw new PersonaException("Persona no eliminada");
        }


        return resultadoEliminacion;
    }

        return false;
    }
}
