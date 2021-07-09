package pulsa.database;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {
    private Connection connection;
    public Connection Koneksi(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/pulsa", "root", "");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return  connection;
    }
}
