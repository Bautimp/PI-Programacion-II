/*
 * @author Bautista M
 */
package Logica;

public class Alumno extends Persona{
    private String sala;
    private int edad;

    public Alumno(String sala, int edad, String nombre, String apellido) {
        super(nombre, apellido, "Alumno");
        this.sala=sala;
        this.edad = edad;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    
}
