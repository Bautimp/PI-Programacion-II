package Persistencia;
import java.util.ArrayList;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;


public class Archivos {
    /*Atributos*/
    File archivo = new File("Datos.csv");
    /*Métodos*/
    public ArrayList<String> cargarArchivo() {
        try{
            if(archivo.createNewFile()){
                System.out.println("Archivo creado");
            }else{
                System.out.println("Ya existe el archivo");
            }
            FileReader leo = new FileReader(archivo);
            BufferedReader bLeo = new BufferedReader(leo);

            if (archivo.exists()){
                ArrayList<String> datosPersonas = new ArrayList<>();
                String s;

                while((s = bLeo.readLine())!=null){
                    datosPersonas.add(s);
                }
                return (datosPersonas);
            }
            leo.close();
            bLeo.close();
        } catch (IOException e) {
            System.out.println("Error al cargar el archivo");
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void guardarArchivo(ArrayList<String> stringPersonas) throws IOException {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
            String texto = "";

            for(String persona:stringPersonas){
                //noinspection StringConcatenationInLoop
                texto += persona + "\n";
            }

            writer.write(texto);
            writer.close();

        }catch (IOException e){
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}
