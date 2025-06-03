package Logica;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import Persistencia.Archivos;

public class Administracion {
    /*Atributos*/
    static ArrayList<Persona> personas = new ArrayList<>();
    /*Métodos*/
    public ArrayList<Persona> getPersonas(String tipo) {
        ArrayList<Persona> tipoPersona = new ArrayList<>();

        for(Persona persona : personas){
            if(persona.getTipo().equals(tipo)){
                tipoPersona.add(persona);
            }
        }
        return tipoPersona;
    }

    public void agregarPersona(Persona persona) {
        personas.add(persona);
        guardarDatos();
    }

    public String[] personaToString(Persona persona) {
        String[] datosPersona = new String[5];
        datosPersona[0] = persona.getTipo();
        datosPersona[1] = persona.getNombre();
        datosPersona[2] = persona.getApellido();
        datosPersona[3] = persona.getDato1();
        datosPersona[4] = persona.getDato2();
        return datosPersona;
    }

    public void eliminarPersona(String[] datosPersona) {
        for(int i = 0; i < personas.size(); i++){
            if((personas.get(i).getTipo().equals(datosPersona[0])) && (personas.get(i).getNombre().equals(datosPersona[1])) && (personas.get(i).getApellido().equals(datosPersona[2])) && (personas.get(i).getDato1().equals(datosPersona[3])) && (personas.get(i).getDato2().equals(datosPersona[4]))) {
                personas.remove(personas.get(i));
            }
        }
        guardarDatos();
    }

    public void eliminarDuplicados() {
        for (int i = 0; i < personas.size(); i++) {
            for (int j = 0; j < personas.size(); j++) {
                if ((i != j) && (personas.get(i).getTipo().equals(personas.get(j).getTipo())) && (personas.get(i).getNombre().equals(personas.get(j).getNombre())) && (personas.get(i).getApellido().equals(personas.get(j).getApellido())) && (personas.get(i).getDato1().equals(personas.get(j).getDato1())) && (personas.get(i).getDato2().equals(personas.get(j).getDato2()))) {
                    String[] eliminarPersona = personaToString(personas.get(j));
                    eliminarPersona(eliminarPersona);
                    j--;
                }
            }
        }
    }

    @SuppressWarnings("Java8ListSort")
    public void ordenarPersonas(int atributo) {
        switch (atributo) {
            case 0: // Ordenar por Apellido
                Collections.sort(personas, (p1, p2) -> p1.getApellido().compareTo(p2.getApellido()));
                break;
            case 1: // Ordenar por Sala (Alumno, Docente) o Categoria (NoDocente)
                Collections.sort(personas, (p1, p2) -> p1.getDato1().compareTo(p2.getDato1()));
                break;
        }
    }

    public void initDatos() {
        Archivos archivos = new Archivos();
        ArrayList<String> stringPersonas = archivos.cargarArchivo();

        if (stringPersonas == null || stringPersonas.isEmpty()) {
            System.out.println("No se encontraron datos para cargar.");
        }else{
            for (String stringPersona : stringPersonas) {
                String[] datos = stringPersona.split("\t");
                String nombre, apellido, tipo, dato1, dato2;
                tipo = datos[0];
                nombre = datos[1];
                apellido = datos[2];
                dato1 = datos[3];
                dato2 = datos[4];

                switch (tipo) {
                    case "Alumno":
                        personas.add(new Alumno(nombre, apellido, dato1, dato2));
                        break;
                    case "Docente":
                        personas.add(new Docente(nombre, apellido, dato1, dato2));
                        break;
                    case "No docente":
                        personas.add(new NoDocente(nombre, apellido, dato1, dato2));
                        break;
                    default:
                        System.out.println("No se pudo agregar a la persona: " + nombre + " " + apellido + ". Tipo no reconocido.");
                }
            }
            eliminarDuplicados();
            ordenarPersonas(0);
        }
    }

    public void guardarDatos() {
        Archivos archivos = new Archivos();
        ArrayList<String> stringPersonas = new ArrayList<>();
        eliminarDuplicados();

        for (Persona persona : personas) {
            String datosPersona = persona.getTipo() + "\t" + persona.getNombre() + "\t" + persona.getApellido() + "\t" + persona.getDato1() + "\t" + persona.getDato2();
            System.out.println(datosPersona);
            stringPersonas.add(datosPersona);
        }

        try {
            archivos.guardarArchivo(stringPersonas);
            personas.clear();
            initDatos();

        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }
}
