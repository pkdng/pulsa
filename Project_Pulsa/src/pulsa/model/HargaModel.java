package pulsa.model;

import java.sql.*;
import javax.swing.JOptionPane;

import pulsa.database.Koneksi;

public class HargaModel{
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    
    public HargaModel(){
        Koneksi konek = new Koneksi();
        connection = konek.Koneksi();
    }

    public void create(String[] data){ //input data harga pulsa
        boolean cek=true;
        String[][] dataH = readHarga();
        String q = "SELECT * FROM harga";
        for(int i=0; i<numRows(q); i++){ //cek apakah data sudah ada?
            if(dataH[i][1].equals(data[0]) && dataH[i][2].equals(data[1])){
                JOptionPane.showMessageDialog(null, "Data Sudah Ada!!", "GAGAL", JOptionPane.WARNING_MESSAGE);
                cek=false;
                break;
            }
        }
        if(cek){ //kalo blm ada, input ke db
            try{
                String query = "INSERT INTO harga(operator,nominal,harga)VALUES('"+data[0]+"','"+data[1]+"','"+data[2]+"')";
                statement = connection.createStatement();
                statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Data berhasil disimpan", "Pesan", JOptionPane.INFORMATION_MESSAGE);
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e.getMessage());            
            }
        }
    }
    
    public String[][] readHarga(){ //membaca seluruh data tabel harga
        try{
            int row = 0;
            String query = "SELECT * FROM harga ORDER BY operator, nominal";
            int numRows = numRows(query);
            if(numRows==0){
                return null;
            }
            String[][] data = new String[numRows][4];
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while(result.next()){
                data[row][0] = result.getString("id_harga");
                data[row][1] = result.getString("operator");
                data[row][2] = result.getString("nominal");
                data[row][3] = result.getString("harga");
                row++;
            }
            return data;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public String[] readHarga(String hdata){ //membaca salah satu data tabel harga
        try{
            String[] data = new String[4];
            statement = connection.createStatement();
            String query = "SELECT * FROM harga WHERE id_harga = '"+hdata+"'";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                data[0] = rs.getString("id_harga");
                data[1] = rs.getString("operator");
                data[2] = rs.getString("nominal");
                data[3] = rs.getString("harga");
            }
            return data;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public void update(String[] data){ //update data harga pulsa
        boolean cek=true;
        String[][] dataH = readHarga();
        String q = "SELECT * FROM harga";
        for(int i=0; i<numRows(q); i++){ //cek apakah data sudah ada?
            if(dataH[i][1].equals(data[1]) && dataH[i][2].equals(data[2]) && dataH[i][3].equals(data[3])){
                JOptionPane.showMessageDialog(null, "Data Sudah Ada!!", "GAGAL", JOptionPane.WARNING_MESSAGE);
                cek=false;
                break;
            }
        }
        if(cek){//kalau belum ada datanya, data akan diupdate
            System.out.println("ada");
            try{
                String query = "UPDATE harga SET operator = '"+data[1]+"', nominal = '"+data[2]+"', harga = '"+data[3]+"' WHERE id_harga = '"+data[0]+"'";
                statement = connection.createStatement();
                statement.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Update Berhasil");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
    
    public void delete(String id){ //menghapus salah satu data pada tabel harga
        try {
            String query = " DELETE FROM harga WHERE id_harga = '"+id+"'";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Hapus Berhasil");
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Jangan Dihapus");
        }
    }
        
    public int numRows(String query) { //menghitung jumlah baris suatu tabel
        int jmlData = 0;
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                jmlData++;
            }
            return jmlData;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        }
    }
}
