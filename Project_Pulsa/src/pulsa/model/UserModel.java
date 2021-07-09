package pulsa.model;

import java.sql.*;
import javax.swing.JOptionPane;

import pulsa.controller.UserController;
import pulsa.controller.UserSession;
import pulsa.database.Koneksi;

public class UserModel {
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    
    public UserModel(){
        Koneksi konek = new Koneksi();
        connection = konek.Koneksi();
    }
    
    public void createUser(String[] data){
        try{
            String pw = UserController.getMd5(data[2]);
            String query = "INSERT INTO user(roleID,nama,username,password)VALUES('2','"+data[0]+"','"+data[1]+"','"+pw+"')";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Akun Berhasil Dibuat", "Pesan", JOptionPane.INFORMATION_MESSAGE);            
            statement.close();
            connection.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Gagal Membuat Akun", "Peringatan", JOptionPane.WARNING_MESSAGE);            
        }
    }
    
    public void loginUser(String[] data){
        try{
            String pass = UserController.getMd5(data[1]);
            String query = "SELECT * FROM user WHERE username='"+data[0]+"' AND password='"+pass+"'";
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            if(result.next()){
                String id = result.getString("id_user");
                UserSession.setId(id);
                String nama = result.getString("nama");
                UserSession.setNama(nama);
                String role = result.getString("roleID");
                UserSession.setRole(role);
            }else{
                JOptionPane.showMessageDialog(null, "Username atau Password salah!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Gagal Login", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
}
