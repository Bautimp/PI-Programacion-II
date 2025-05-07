/*
 * @author Bautista M
 */
package Logica;
import Persistencia.ManejoArchivos;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;

public class AdminJardin{
    /*Atributos*/
    static ArrayList<Alumno> alumnos = new ArrayList();
    static ArrayList<Docente> docentes = new ArrayList();
    static ArrayList<NoDocente> nodocentes = new ArrayList();
    static ArrayList<String> datosPersonas = new ArrayList();
    
    /*Metodos*/
    public ArrayList muestraPersonas(String tipo){
        // ArrayList de arreglo de Strings, en donde en cada arreglo están los datos de una persona, sin importar su tipo
        ArrayList<String[]> personas = new ArrayList();
        
        // Almacenar cada persona en el ArrayList
        switch(tipo){
            case "Alumno":
                for(Alumno a:alumnos){
                    String per[] = {a.getNombre(), a.getApellido(), Integer.toString(a.getEdad()), a.getSala()};
                    personas.add(per);
                    
                }
                break;
            case "Docente":
                for(Docente a:docentes){
                    String per[] = {a.getNombre(), a.getApellido(), a.getDireccion(), a.getSala()};
                    personas.add(per);
                    
                }
                break;
            case "No docente":
                for(NoDocente a:nodocentes){
                    String per[] = {a.getNombre(), a.getApellido(), a.getDireccion(), a.getCategoria()};
                    personas.add(per);
                }
        }
        
        return personas;
    }
    
    
    public void AgregarAlumno(Alumno a) {
        alumnos.add(a);
    }
    public void AgregarDocente(Docente d){
        docentes.add(d);
    }
    public void AgregarNoDocente(NoDocente nd){
        nodocentes.add(nd);
    }
    
    public void initDatos() throws IOException{
        //DEFINO UNAS PERSONAS DE EJEMPLO
        ManejoArchivos archivos = new ManejoArchivos();
        datosPersonas = archivos.CargarDatos();
        
        //ELIMINA PERSONAS REPETIDAS
        for(int i=0;i<datosPersonas.size();i++){
            for(int j=0;j<datosPersonas.size();j++){
                if((i!=j) && ((datosPersonas.get(i).equals(datosPersonas.get(j))) || (datosPersonas.get(i).equals((datosPersonas.get(j))+"/n")))){
                    datosPersonas.remove(j);
                }
            }
        }
        
        String tipo, nomb, apel, edad, sala, dire, cate;
        
        for(String persona:datosPersonas){
            tipo=""; nomb="";apel="";edad="";sala="";dire="";cate="";
            tipo = persona.substring(persona.indexOf("tipo:")+5, persona.indexOf("/tipo"));
            nomb = persona.substring(persona.indexOf("nomb:")+5, persona.indexOf("/nomb"));
            apel = persona.substring(persona.indexOf("apel:")+5, persona.indexOf("/apel"));
            
            //Switch en donde agrego a cada persona en su arraylist segun su tipo
            switch(tipo){
                case "Alumno":
                    edad = persona.substring(persona.indexOf("edad:")+5, persona.indexOf("/edad"));
                    sala = persona.substring(persona.indexOf("sala:")+5, persona.indexOf("/sala"));
                    Alumno alumno_ejemplo = new Alumno(sala, Integer.parseInt(edad), nomb, apel);
                    AgregarAlumno(alumno_ejemplo);
                    break;
                case "Docente":
                    dire = persona.substring(persona.indexOf("dire:")+5, persona.indexOf("/dire"));
                    sala = persona.substring(persona.indexOf("sala:")+5, persona.indexOf("/sala"));
                    Docente docente_ejemplo = new Docente(nomb,  apel, dire, sala);
                    AgregarDocente(docente_ejemplo);
                    break;
                case "No docente":
                    dire = persona.substring(persona.indexOf("dire:")+5, persona.indexOf("/dire"));
                    cate = persona.substring(persona.indexOf("cate:")+5, persona.indexOf("/cate"));
                    NoDocente nodocente_ejemplo = new NoDocente(nomb,  apel, dire, cate);
                    AgregarNoDocente(nodocente_ejemplo);
            }
        }
        
        Collections.sort(alumnos, (Alumno a1, Alumno a2) -> (a1.getApellido().compareTo(a2.getApellido())));
        Collections.sort(docentes, (Docente d1, Docente d2) -> (d1.getApellido().compareTo(d2.getApellido())));
        Collections.sort( nodocentes, (NoDocente nd1, NoDocente nd2) -> (nd1.getApellido().compareTo(nd2.getApellido())));
        
    }
    
    public void ordenarApellido(String tipo){
        switch(tipo){
            case "Alumno":
                Collections.sort(alumnos, (Alumno a1, Alumno a2) -> (a1.getApellido().compareTo(a2.getApellido())));
                break;
            case "Docente":
                Collections.sort(docentes, (Docente d1, Docente d2) -> (d1.getApellido().compareTo(d2.getApellido())));
                break;
            case "No docente":
                Collections.sort( nodocentes, (NoDocente nd1, NoDocente nd2) -> (nd1.getApellido().compareTo(nd2.getApellido())));
        }
        
    }
    
    public void ordenarSala(String tipo){
        switch(tipo){
            case "Alumno":
                Collections.sort(alumnos, (Alumno a1, Alumno a2) -> (a1.getSala().compareTo(a2.getSala())));
                break;
            case "Docente":
                Collections.sort(docentes, (Docente d1, Docente d2) -> (d1.getSala().compareTo(d2.getSala())));
                break;
            case "No docente":
                Collections.sort( nodocentes, (NoDocente nd1, NoDocente nd2) -> (nd1.getCategoria().compareTo(nd2.getCategoria())));
        }
        
    }
    
    
    public void guardarPersona(String tipo, String nomb, String apel, String dat1, String dat2) throws IOException{
        switch(tipo){
            case "Alumno":
                Alumno alumno_ejemplo = new Alumno(dat1, Integer.parseInt(dat2), nomb, apel);
                AgregarAlumno(alumno_ejemplo);
                datosPersonas.add("tipo:Alumno/tipo;nomb:"+nomb+"/nomb;apel:"+apel+"/apel;edad:"+dat2+"/edad;sala:"+dat1+"/sala");
                break;
            case "Docente":
                Docente docente_ejemplo = new Docente(nomb,  apel, dat1, dat2);
                AgregarDocente(docente_ejemplo);
                datosPersonas.add("tipo:Docente/tipo;nomb:"+nomb+"/nomb;apel:"+apel+"/apel;dire:"+dat1+"/dire;sala:"+dat2+"/sala");
                break;
            case "No docente":
                NoDocente nodocente_ejemplo = new NoDocente(nomb,  apel, dat1, dat2);
                AgregarNoDocente(nodocente_ejemplo);
                datosPersonas.add("tipo:No docente/tipo;nomb:"+nomb+"/nomb;apel:"+apel+"/apel;dire:"+dat1+"/dire;cate:"+dat2+"/cate");
        }
        
        //Paso el arraylist con los datos
        ManejoArchivos archivos = new ManejoArchivos();
        archivos.guardarDatos(datosPersonas);
        datosPersonas.clear();
        alumnos.clear();
        docentes.clear();
        nodocentes.clear();
        initDatos();
    }
}