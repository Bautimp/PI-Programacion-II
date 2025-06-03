package Logica;

public abstract class Persona {
    /*Atributos*/
    private String nombre;
    private String apellido;
    private String tipo;
    private String dato1; // Puede ser edad (Alumno) o direccion (Empleado)
    private String dato2; // Puede ser sala (Alumno, Docente) o categoria (NoDocente)
    /*Métodos*/
    public Persona(){}
    public Persona(String nombre, String apellido, String tipo, String dato1, String dato2) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.tipo = tipo;
        this.dato1 = dato1;
        this.dato2 = dato2;
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
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDato1() {
        return dato1;
    }
    public void setDato1(String dato1) {
        this.dato1 = dato1;
    }
    public String getDato2() {
        return dato2;
    }
    public void setDato2(String dato2) {
        this.dato2 = dato2;
    }
}
