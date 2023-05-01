package funciones;

/*
@majugatti
 */

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author joako
 */
public class conexion {


    Connection conectar = null;
    
    String driver = "com.mysql.cj.jdbc.Driver";

    String usuario = "root";

    String contraseña = "Maju0302";

    String bd = "alumno";

    String ip = "localhost";

    String puerto = "3306";

    String ruta = "jdbc:mysql://localhost:3306/alumno?autoReconnect=true&useSSL=false";
    
    Statement st = null;
    
    ResultSet rs = null;
    
    public Connection estableceConexion(){


        try {

            Class.forName(driver);

            conectar = DriverManager.getConnection(ruta, usuario, contraseña);

            JOptionPane.showMessageDialog(null, "Se conecto correctamente a la BD");
            
            conectar.close();
            


        } catch (HeadlessException | ClassNotFoundException | SQLException e) {

            JOptionPane.showMessageDialog(null, "NO se conecto correctamente a la BD" + e);

        }

        return conectar;
        
        
    }//de public connection estableceConexion()
    
    
    
}//de la clase conexion
