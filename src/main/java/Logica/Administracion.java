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

    public Persona compararPersona(String[] datosPersona){
        for (Persona persona : personas) {
            if ((persona.getTipo()).equals(datosPersona[0]) &&
                (persona.getNombre()).equals(datosPersona[1]) &&
                (persona.getApellido()).equals(datosPersona[2]) &&
                (persona.getDato1()).equals(datosPersona[3]) &&
                (persona.getDato2()).equals(datosPersona[4])){

                return persona;
            }
        }
        return null;
    }

    public void eliminarPersona(Persona persona) {
        personas.remove(persona);
        guardarDatos();
    }

    public void eliminarDuplicados() {
        for (int i = 0; i < personas.size(); i++) {
            for (int j = i + 1; j < personas.size(); j++) {
                if ((personas.get(i).getTipo()).equals(personas.get(j).getTipo()) &&
                    (personas.get(i).getNombre()).equals(personas.get(j).getNombre()) &&
                    (personas.get(i).getApellido()).equals(personas.get(j).getApellido()) &&
                    (personas.get(i).getDato1()).equals(personas.get(j).getDato1()) &&
                    (personas.get(i).getDato2()).equals(personas.get(j).getDato2())){

                    eliminarPersona(personas.get(j));
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
                Collections.sort(personas, (p1, p2) -> p1.getDato2().compareTo(p2.getDato2()));
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
                    case "No Docente":
                        personas.add(new NoDocente(nombre, apellido, dato1, dato2));
                        break;
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
