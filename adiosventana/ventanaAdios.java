package adiosventana;
import javax.swing.JFrame;

public class ventanaAdios extends JFrame{
    public ventanaAdios () {
        super("ventana hola");
        setLocation(50, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 200);
        setVisible(true);
    }
}