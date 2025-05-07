// author: Bautista Montes
package Interfaz;
import javax.swing.JFrame;
import Logica.AdminJardin;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        AdminJardin admin = new AdminJardin();
        Principal p = new Principal();
        
        JFrame f = new JFrame ("Jardín Sol");
        f.add(p);
        f.setSize(425, 380);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        admin.initDatos();
    }
    
}