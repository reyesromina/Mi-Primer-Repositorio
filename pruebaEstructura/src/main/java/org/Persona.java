package org;

import Persona.exceptios.PersonaException;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Persona {
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private float altura;
    private float peso;

    private Persona(String nombre, String apellido, LocalDate fechaNacimiento,String dni, float altura, float peso) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.dni = dni;
        this.altura = altura;
        this.peso = peso;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Persona persona = (Persona) o;
        return Objects.equals(dni, persona.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    public static Persona create(String nombre, String apellido, String dni, LocalDate fechaNacimiento, float altura, float peso){

        //aplicar reglas de negocio/normas para los atributsos
        if (Period.between(fechaNacimiento,LocalDate.now()).getYears()< 18){
            throw new PersonaException("La persona debe ser mayor de edad");
        }

        if(nombre.isEmpty()){
            throw new PersonaException("El nombre no puede estar vacio");
        }


        return new Persona(nombre,apellido,fechaNacimiento,dni,altura,peso);
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getDni(){ return this.dni;}

    public int getEdad(){

        return Period.between(this.fechaNacimiento,LocalDate.now()).getYears();
    }

    @Override
    public String toString() {

        return nombre+" "+
                apellido+" "
                +"(DNI "+dni+"), "
                +"Edad: "+this.getEdad()+ " años, "
                +"mide: "+altura+"m, "
                +"peso: "+peso+"kg.";
    }
}

