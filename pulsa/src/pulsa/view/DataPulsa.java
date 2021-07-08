package pulsa.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import pulsa.controller.HargaController;

public class DataPulsa extends JFrame implements ActionListener{
    private JButton btnTambah, btnKembali;
    private JTable tableData;
    private String selectedData;
    
    public DataPulsa(String[][] data){
        try{
            selectedData = data[0][0];
            final String[] tableTitle = {"Id","Operator","Nominal","Harga"};
            
            setTitle("Data Pulsa");
            setSize(500,300);
            
            btnKembali= new JButton("Kembali");
            btnTambah = new JButton("Tambah Data");
            tableData = new JTable(data, tableTitle);
            
            JScrollPane s = new JScrollPane(tableData);
            this.getContentPane().add(BorderLayout.CENTER, s);
            
            this.getContentPane().add(BorderLayout.PAGE_START, btnTambah);
            this.getContentPane().add(BorderLayout.PAGE_END, btnKembali);
            btnTambah.addActionListener(this);
            btnKembali.addActionListener(this);
            btnKembali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            tableData.setCursor((Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)));
            tableData.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event) {
                    dispose();
                    selectedData = tableData.getValueAt(tableData.getSelectedRow(), 0).toString();
                    HargaController hc = new HargaController();
                    hc.readHarga(selectedData);
                }
            });
            
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnTambah){
            new TambahPulsa();
            dispose();
        }
        else if(e.getSource()==btnKembali){
            new MenuUser();
            dispose();
        }
    }
}
