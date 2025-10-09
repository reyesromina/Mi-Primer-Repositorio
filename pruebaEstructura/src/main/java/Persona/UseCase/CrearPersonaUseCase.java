package Persona.UseCase;

import Persona.Output.GuardarPersonRepositorio;
import Persona.exceptios.PersonaException;
import Persona.exceptios.RepositorioException;
import Persona.input.CrearPersonaInput;
import org.Persona;

import java.time.LocalDate;

public class CrearPersonaUseCase implements CrearPersonaInput {

    private GuardarPersonRepositorio repositorio;

    public CrearPersonaUseCase(GuardarPersonRepositorio repositorio) {
        this.repositorio=repositorio;
    }


    @Override
    public Boolean crearPersona(String nombre, String apellido, String dni,
                                LocalDate fechaNacimiento, float altura, float peso) {

        if(this.repositorio.existePersona(dni)){
            throw new PersonaException("Persona ya existe");
        }
        try{
            Persona persona = Persona.create(nombre,apellido,dni,fechaNacimiento,altura,peso);

            if(!this.repositorio.guardarPersona(persona)){
                throw new RepositorioException("algo salio mal");
            }

            return true;
        }catch (PersonaException e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
