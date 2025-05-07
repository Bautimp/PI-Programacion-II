/*
 * @author Bautista M
 */
package Logica;

public class Persona {
    private String nombre;
    private String apellido;
    private String es_un;
    
   public Persona(){
       this.nombre = "Indefinido";
       this.apellido = "Indefinido";
       this.es_un = "Indefinido";
   }
    public Persona(String nombre, String apellido, String es_un) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.es_un = es_un;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getEs_un() {
        return es_un;
    }
    public void setEs_un(String es_un) {
        this.es_un = es_un;
    }
    
}
