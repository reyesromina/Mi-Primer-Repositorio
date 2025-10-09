package Persona.UseCase;

import Persona.Output.MostrarLista;
import Persona.input.BuscarPersona;
import Persona.input.OrdenarPersona;
import org.Persona;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConsultarPersonasUseCase implements BuscarPersona, OrdenarPersona {

    private MostrarLista Listado;

    public ConsultarPersonasUseCase(MostrarLista Listado) {
        this.Listado=Listado;
    }

    //array
    @Override
    public ArrayList<Persona> BuscarPersonaXNombre(String nombre,ArrayList<Persona> Personas) {
        ArrayList<Persona> aux;

        Predicate<Persona> pred=s->s.getNombre().compareTo(nombre)==0;
        aux=Personas.stream().filter(pred).collect(Collectors.toCollection(ArrayList::new));

        return aux;

    }

    //agregar una excepcion que avise en caso de que la lista se encuentre vacia luego de realizar
    //el filtrado/busqueda

    //Podria tambien cambiarlo e implementar streams con sorted para el ordenamiento, pero como
    //solo voy a realizar un tipo de ordenamiento no tan "dinamico" no utilizaria comparator,
    //talver un comparator que se implemente mediante un .sort(?) vemoss
    @Override
    public ArrayList<Persona> OrdearPersonas(ArrayList<Persona> personas) {
        ArrayList<Persona> aux=new ArrayList<>();

        return  personas.stream()
                .sorted(Comparator.comparingInt(Persona::getEdad))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //utilizo un metodo auxiliar para unir tanto los metodos con el mock, aca podria implementar
    //alguna excepcion o "regla de negocio"
    public void implementacion(String nombre,ArrayList<Persona> personas){
        ArrayList<Persona> filtrado= BuscarPersonaXNombre(nombre,personas);
        ArrayList<Persona> OrdenFinal=OrdearPersonas(filtrado);

        Listado.mostrarLIstaPersonas(OrdenFinal);

    }

}
