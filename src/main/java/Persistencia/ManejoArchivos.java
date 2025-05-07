/*
 * @author Bautista M
 */
package Persistencia;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class ManejoArchivos {
    /*Atributos*/
    ArrayList<String> datosPersonas = new ArrayList();
    File archivo = new File("Datos.txt");
    /*Metodos*/
    
    //Agregar todas las personas dentro del jardin
    public ArrayList CargarDatos() throws FileNotFoundException, IOException {
        //datosPersonas.add("tipo:Docente/tipo;nomb:Bautista/nomb;apel:Montes/apel;dire:Illia 2222/dire;sala:Sala 3/sala");
        
        String s;
        try{
            //Creacion de archivo si no existe
            if(archivo.createNewFile()){
                System.out.println("Archivo creado");
            }else{
                System.out.println("Ya existe el archivo");
            }
            
            //Lectura de datos
            FileReader leo = new FileReader(archivo);
            BufferedReader bLeo = new BufferedReader(leo);
            if (archivo.exists()){
                while((s = bLeo.readLine())!=null){
                    datosPersonas.add(s);
                }
            }
            leo.close();
            bLeo.close();
            
        }catch(IOException e) {
            System.out.println("An error occurred.");
        }

        return(datosPersonas);
        
    }
    
    public void guardarDatos(ArrayList datos) throws IOException{
        datosPersonas = datos;
        String texto="";
        BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
        for(String per:datosPersonas){
            texto = texto+per+"\n";
        }
        writer.write(texto);
        writer.close();
    }
}

