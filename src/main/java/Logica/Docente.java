/*
 * @author Bautista M
 */
package Logica;

public class Docente extends Empleado{
    private String sala;

    public Docente(String nombre, String apellido, String direccion, String sala) {
        super(nombre, apellido, direccion, "Docente");
        this.sala=sala;
    }
    
    public String getSala(){
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }
    
}
