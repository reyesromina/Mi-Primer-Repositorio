import Persona.exceptios.PersonaException;
import org.Persona;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class testPersona {
    @Test
    public void create_AtributosCorrectos_Success() {
        Persona nuevaPersona = Persona.create("Luciano",
                "De Cecco",
                "1234567",
                LocalDate.of(1988,6,2),
                194.0f,
                89.0f);

        Assertions.assertNotNull(nuevaPersona);
        Assertions.assertEquals("De Cecco", nuevaPersona.getApellido());

    }

    @Test
    public void pruebaException(){
//Prueba el funcionamiento de la excepcion que avisa si la persona ingresada es menor de edad
        assertThrows(PersonaException.class, ()->Persona.create("Luciano",
                "De Cecco",
                "1234567",
                LocalDate.of(2020,6,2),
                194.0f,
                89.0f));
    }
    @Test
    public void pruebaException2(){
//Prueba el funcionamiento de la excepcion que determina si el campo "nombre" esta vacio
        assertThrows(PersonaException.class, ()->{Persona.create("",
                "De Cecco",
                "1234567",
                LocalDate.of(1988,6,2),
                194.0f,
                89.0f);});
    }
    @Test
    public void pruebtoString(){
        Persona nuevaPersona = Persona.create("Luciano",
                "De Cecco",
                "1234567",
                LocalDate.of(1988,6,2),
                194.0f,
                89.0f);

        Assertions.assertEquals("Luciano De Cecco (DNI 1234567), Edad: 37 años, mide: 194.0m, peso: 89.0kg.", nuevaPersona.toString());

    }
}
