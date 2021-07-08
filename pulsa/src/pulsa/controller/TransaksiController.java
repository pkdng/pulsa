package pulsa.controller;

import javax.swing.JOptionPane;
import pulsa.model.TransaksiModel;
import pulsa.view.KirimPulsa;
import pulsa.view.MenuUser;
import pulsa.view.Riwayat;
import pulsa.view.Transaksi;

public class TransaksiController {
    static String idHarga, harga, idTrx;
    
    public static void setIDHarga(String id){idHarga = id;}
    public static void setHarga(String h){harga = h;}
    public static String getIDHarga(){return idHarga;}
    public static String getHarga(){return harga;}
    public static void setID(String trx){idTrx = trx;}
    public static String getID(){return idTrx;}
    
    TransaksiModel tm = new TransaksiModel();
    
    public void addTrx(String[] data){tm.hitungHarga(data);}
    
    public void createTrx(String[] data){tm.createTrx(data);}
    
    public void readRiwayat(){
        String[][] data = tm.readRwyt();
        if(data==null){
            JOptionPane.showMessageDialog(null, "Riwayat kosong", "", JOptionPane.WARNING_MESSAGE);
        }else{
            new Riwayat(data);
        }
    }
    
    public void readTrx(){
        String[][] data = tm.readTrx();
        if(data==null){
            JOptionPane.showMessageDialog(null, "Data kosong", "", JOptionPane.WARNING_MESSAGE);
        }else{
            new Transaksi(data);
        }
    }
    
    public void readTrx(String data){new KirimPulsa(tm.readTrx(data));}
    
    public void update(String id){tm.update(id);}//update status trx
}
