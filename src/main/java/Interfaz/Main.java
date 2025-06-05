package Interfaz;
import javax.swing.JFrame;
import Logica.Administracion;

public class Main {
    public static void main(String[] args){
        Administracion admin = new Administracion();
        InterfazUsuario p = new InterfazUsuario();

        JFrame f = new JFrame ("Jardín Sol");
        f.add(p);
        f.setSize(900, 550);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        admin.initDatos();
    }

}
