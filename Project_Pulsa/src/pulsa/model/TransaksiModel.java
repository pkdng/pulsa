package pulsa.model;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;

import pulsa.controller.TransaksiController;
import pulsa.controller.UserController;
import pulsa.controller.UserSession;
import pulsa.database.Koneksi;

public class TransaksiModel extends HargaModel{
    private Connection connection;
    private Statement statement;
    private ResultSet result;
    
    //deklarasi list array untuk menampung data yang akan ditampilkan dalam pilihan combo box
    List<UserController> op = new ArrayList<>();
    List<UserController> nom = new ArrayList<>();
    
    public TransaksiModel(){
        Koneksi konek = new Koneksi();
        connection = konek.Koneksi();
    }

    public void hitungHarga(String[] data){ //mengambil nilai harga dari tabel harga
        try{
            String query = "SELECT `id_harga`,`harga` FROM `harga` WHERE operator = '"+data[1]+"' AND nominal = '"+data[2]+"'";
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            if(result.next()){
                TransaksiController.setIDHarga(result.getString("id_harga"));
                TransaksiController.setHarga(result.getString("harga"));
            }else{
                TransaksiController.setIDHarga(null);
                TransaksiController.setHarga(null);
            }
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage());
        }
    }
    
    public void createTrx(String[] data){ //memasukkan data pembelian ke tabel transaksi
        try{
            String query = "INSERT INTO transaksi(id_user, id_harga, no_hp, tgl_transaksi, status) VALUES ('"+data[0]+"','"+data[1]+"','"+data[2]+"','"+data[3]+"','BELUM TERKIRIM')";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Pembelian berhasil", "", JOptionPane.INFORMATION_MESSAGE);
            statement.close();
            connection.close();
        }catch(SQLException se){
            JOptionPane.showMessageDialog(null, se.getMessage());
        }
    }
    
    public String[][] readRwyt(){ //riwayat pembelian tiap member
        try{
            int row = 0;
            String query = "SELECT t.no_hp, h.operator, h.nominal, h.harga, t.tgl_transaksi, t.status FROM transaksi t INNER JOIN harga h ON t.id_harga=h.id_harga INNER JOIN user u ON t.id_user = u.id_user WHERE u.id_user='"+UserSession.getId()+"'";
            int numRows = numRows(query);
            if(numRows==0){
                return null;
            }
            String[][] data = new String[numRows][6];
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while(result.next()){
                data[row][0] = result.getString("no_hp");
                data[row][1] = result.getString("operator");
                data[row][2] = result.getString("nominal");
                data[row][3] = result.getString("harga");
                data[row][4] = result.getString("tgl_transaksi");
                data[row][5] = result.getString("status");
                row++;
            }
            return data;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public String[][] readTrx(){ //membaca seluruh data tabel transaksi
        try{
            int row = 0;
            String query = "SELECT * FROM transaksi";
            int numRows = numRows(query);
            if(numRows==0){
                return null;
            }
            String[][] data = new String[numRows][6];
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while(result.next()){
                data[row][0] = result.getString("id_transaksi");
                data[row][1] = result.getString("id_user");
                data[row][2] = result.getString("id_harga");
                data[row][3] = result.getString("no_hp");
                data[row][4] = result.getString("tgl_transaksi");
                data[row][5] = result.getString("status");
                row++;
            }
            return data;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public String[] readTrx(String tdata){ //untuk menampilkan detail salah satu data dari tabel transaksi
        try{
            TransaksiController.setID(tdata);
            String[] data = new String[8];
            statement = connection.createStatement();
            String query = "SELECT u.id_user, t.no_hp, h.operator, h.nominal, h.harga, t.tgl_transaksi, t.status FROM transaksi t INNER JOIN harga h ON t.id_harga=h.id_harga INNER JOIN user u ON t.id_user = u.id_user WHERE t.id_transaksi='"+tdata+"'";
            result = statement.executeQuery(query);
            while(result.next()){
                data[0] = result.getString("id_user");
                data[1] = result.getString("no_hp");
                data[2] = result.getString("operator");
                data[3] = result.getString("nominal");
                data[4] = result.getString("harga");
                data[5] = result.getString("tgl_transaksi");
                data[6] = result.getString("status");
            }
            return data;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public void update(String id){ //update status transaksi menjadi 'TERKIRIM'
        try{
            String query = "UPDATE transaksi SET status = 'TERKIRIM' WHERE id_transaksi = '"+id+"'";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Pulsa Berhasil Terkirim");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public List<UserController> getAllOperator(){ //set operator apa saja yang tersedia berdasarkan database
        try{
            String query = "SELECT DISTINCT operator FROM harga";
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while(result.next()){
                UserController uc = new UserController();
                uc.setOperator(result.getString(1));
                op.add(uc);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return op;
    }
    
    public List<UserController> getAllNominal(){ //set nominal berapa saja yang tersedia berdasarkan database
        try{
            String query = "SELECT DISTINCT nominal FROM harga ORDER BY nominal ASC";
            statement = connection.createStatement();
            result = statement.executeQuery(query);
            while(result.next()){
                UserController uc = new UserController();
                uc.setNominal(result.getString(1));
                nom.add(uc);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return nom;
    }
}
