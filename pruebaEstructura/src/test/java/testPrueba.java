import Persona.Output.BuscarPersonaRepositorio;
import Persona.Output.GuardarPersonRepositorio;
import Persona.Output.MostrarLista;
import Persona.UseCase.ConsultarPersonasUseCase;
import Persona.UseCase.CrearPersonaUseCase;

import Persona.UseCase.EliminarPersonaUseCase;
import Persona.exceptios.PersonaException;
import Persona.exceptios.RepositorioException;
import Persona.input.CrearPersonaInput;
import Persona.input.EliminarPersonaInput;
import org.Persona;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class testPrueba {
@Mock
GuardarPersonRepositorio repositorio;

@Mock
BuscarPersonaRepositorio BuscarPerRepositorio;
//MostrarLista Listado;
//Test que valida el caso general sin caminos alternativos
@Test
public void CrearPersona_Success(){

//arrange
//Que se le puede asignar a una variable de tipo interfaz?→ cualquier clase que
// implemente la interfaz → permite mokito


    CrearPersonaInput uscase= new CrearPersonaUseCase(repositorio);

    when(repositorio.existePersona("1234567")).thenReturn(false);

    when(repositorio.guardarPersona(any(Persona.class))).thenReturn(true);


//Act
    Boolean result=uscase.crearPersona("Luciano","De Cecco",
            "1234567", LocalDate.of(1988,6,2),
            194.0f, 89.0f);
//Assert

    Assertions.assertTrue(result);

}
@Test
public void CrearPersona_UNSuccess_(){

//arrange
//Que se le puede asignar a una variable de tipo interfaz?→ cualquier clase que
// implemente la interfaz → permite mokito


        CrearPersonaInput uscase= new CrearPersonaUseCase(repositorio);

        when(repositorio.existePersona("12345678")).thenReturn(true);

    lenient().when(repositorio.guardarPersona(any(Persona.class))).thenReturn(false);


//Act
    //    Boolean result=uscase.crearPersona("Luciano","De Cecco",
     //           "123458", LocalDate.of(1988,6,2), 194.0f, 89.0f);
//Assert
    Assertions.assertThrows(PersonaException.class,()->{uscase.crearPersona("Luciano","De Cecco",
            "12345678", LocalDate.of(1988,6,2), 194.0f, 89.0f);});


}
@Test
public void CrearPersona_UNSuccess_2(){


    CrearPersonaInput uscase= new CrearPersonaUseCase(repositorio);

    when(repositorio.existePersona("12345678")).thenReturn(false);

    when(repositorio.guardarPersona(any(Persona.class))).thenReturn(false);


//Act

//Assert
        Assertions.assertThrows(RepositorioException.class,()->{uscase.crearPersona("Luciano","De Cecco",
                "12345678", LocalDate.of(1988,6,2), 194.0f, 89.0f);});


    }
    @Test
    void CrearPersona_AtributoInvalidos_Crear(){

        CrearPersonaInput uscase=new CrearPersonaUseCase(repositorio);

        verify(repositorio,never()).existePersona("1234567");

        //Boolean result=uscase.crearPersona("Luciano","De Cecco",
          //      "1234567", LocalDate.of(1988,6,2), 194.0f, 89.0f);

        Assertions.assertThrows(RepositorioException.class,()->{uscase.crearPersona("Luciano","De Cecco",
                "1234567", LocalDate.of(1988,6,2), 194.0f, 89.0f);});
    }
    @Test
    void ConsultarListaPersonas_Success(){
        MostrarLista Listado = mock(MostrarLista.class);
        ConsultarPersonasUseCase useCase=new ConsultarPersonasUseCase(Listado);

        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(Persona.create("Luciano","De Cecco","12345675",
                    LocalDate.of(1988,6,2),194.0f,89.0f));

        personas.add(Persona.create("Luciano","Vicentin","12345679",
                        LocalDate.of(2000,4,4),199.0f,84.0f));

        personas.add(Persona.create("Agustin","Losser","12345674",
                LocalDate.of(1997,10,12),198.0f,86.0f));

        //revisar el ordenamiento, porque le estoy pasando el parametro de edad?
        // Ejecutar el metodo
        useCase.implementacion("Luciano", personas);

        //completar esto
        // Verificar que se llamó a mostrarLista con las personas filtradas y ordenadas


       verify(Listado).mostrarLIstaPersonas(
                Arrays.asList(Persona.create("Luciano","Vicentin","12345679",
                        LocalDate.of(2000,4,4),199.0f,84.0f),Persona.create("Luciano","De Cecco","12345675",
                        LocalDate.of(1988,6,2),194.0f,89.0f))
        );

        Assertions.assertEquals("","");
    }
    //completar
    @Test
    void ConsultarListaPersonas_UnSuccess_ListaVacia(){
        MostrarLista Listado = mock(MostrarLista.class);
        ConsultarPersonasUseCase useCase=new ConsultarPersonasUseCase(Listado);

        ArrayList<Persona> personas = new ArrayList<>();
        personas.add(Persona.create("Luciano","De Cecco","12345675",
                LocalDate.of(1988,6,2),194.0f,89.0f));

        personas.add(Persona.create("Luciano","Vicentin","12345679",
                LocalDate.of(2000,4,4),199.0f,84.0f));

        personas.add(Persona.create("Agustin","Losser","12345674",
                LocalDate.of(1997,10,12),198.0f,86.0f));

        //revisar el ordenamiento, porque le estoy pasando el parametro de edad?
        // Ejecutar el metodo
        useCase.implementacion("Luciano", personas);

        //completar esto
        // Verificar que se llamó a mostrarLista con las personas filtradas y ordenadas



        verify(Listado).mostrarLIstaPersonas(
                Arrays.asList(Persona.create("Luciano","Vicentin","12345679",
                        LocalDate.of(2000,4,4),199.0f,84.0f),Persona.create("Luciano","De Cecco","12345675",
                        LocalDate.of(1988,6,2),194.0f,89.0f))
        );

        Assertions.assertEquals("","");
    }



    @Test
    public void EliminarPersona_Success(){


        EliminarPersonaInput uscase= new EliminarPersonaUseCase(BuscarPerRepositorio);

        Persona personaEliminar=Persona.create("Luciano","Vicentin","12345679",
                LocalDate.of(2000,4,4),199.0f,84.0f);

        when(BuscarPerRepositorio.buscarPersona("12345679")).thenReturn(personaEliminar);

        when(BuscarPerRepositorio.quitarPersona(any(Persona.class))).thenReturn(true);
//Act
        Boolean result=uscase.eliminarPersona("12345679");
//Assert
        Assertions.assertTrue(result);

    }
    @Test
    public void EliminarPersona_FalloBusqueda(){


        EliminarPersonaInput uscase= new EliminarPersonaUseCase(BuscarPerRepositorio);

        Persona personaEliminar=Persona.create("Luciano","Vicentin","12345679",
                LocalDate.of(2000,4,4),199.0f,84.0f);

        when(BuscarPerRepositorio.buscarPersona("12345679")).thenReturn(null);

      lenient().when(BuscarPerRepositorio.quitarPersona(any(Persona.class))).thenReturn(true);
//Act
        Boolean result=uscase.eliminarPersona("12345679");
//Assert
        Assertions.assertFalse(result);

    }
    @Test
    public void EliminarPersona_FalloEliminacion(){


        EliminarPersonaInput uscase= new EliminarPersonaUseCase(BuscarPerRepositorio);

        Persona personaEliminar=Persona.create("Luciano","Vicentin","12345679",
                LocalDate.of(2000,4,4),199.0f,84.0f);

        when(BuscarPerRepositorio.buscarPersona("12345679")).thenReturn(personaEliminar);


        when(BuscarPerRepositorio.quitarPersona(any(Persona.class))).thenReturn(false);
//Act
        Assertions.assertThrows(PersonaException.class,()->{
            uscase.eliminarPersona("12345679");
        });

    }

}
