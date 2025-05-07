/*
 * @author Bautista M
 */
package Logica;

public class NoDocente extends Empleado{
    private String categoria;

    public NoDocente(String nombre, String apellido, String direccion, String categoria) {
        super(nombre, apellido, direccion, "No docente");
        this.categoria = categoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
