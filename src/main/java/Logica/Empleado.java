/*
 * @author Bautista M
 */
package Logica;

public class Empleado extends Persona{
    private String direccion;

    public Empleado(String nombre, String apellido, String direccion, String es_un) {
        super(nombre, apellido, es_un);
        this.direccion = direccion;
    }

    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
