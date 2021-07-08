package pulsa.controller;

import javax.swing.JOptionPane;

import pulsa.model.HargaModel;
import pulsa.view.DataPulsa;
import pulsa.view.DetailPulsa;

public class HargaController {
    HargaModel hm = new HargaModel();
    
    public void create(String[] data){hm.create(data);}
    
    public void readHarga(){
        String[][] data = hm.readHarga();
        if(data==null){
            JOptionPane.showMessageDialog(null, "Data kosong", "", JOptionPane.WARNING_MESSAGE);
        }else{
            new DataPulsa(data);
        }
    }
    
    public void readHarga(String data){new DetailPulsa(hm.readHarga(data));}
    
    public void update(String[] data){hm.update(data);}
    
    public void delete(String id){hm.delete(id);}
}
