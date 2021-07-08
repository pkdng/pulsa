package pulsa.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import pulsa.controller.HargaController;
import pulsa.controller.TransaksiController;

public class Transaksi extends JFrame implements ActionListener{
    private JButton btnKembali;
    private JTable tableData;
    private String selectedData;
    
    public Transaksi(String[][] data){
        try{
            selectedData = data[0][0];
            final String[] tableTitle = {"Id Transaksi","Id Member","Id Harga","No HP","Tgl Transaksi","STATUS"};
            
            setTitle("Data Penjualan");
            setSize(900,300);
            
            btnKembali= new JButton("Kembali");
            tableData = new JTable(data, tableTitle);
            
            JScrollPane s = new JScrollPane(tableData);
            this.getContentPane().add(BorderLayout.CENTER, s);
            
            this.getContentPane().add(BorderLayout.SOUTH, btnKembali);
            btnKembali.addActionListener(this);
            btnKembali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            tableData.setCursor((Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)));
            tableData.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event) {
                    dispose();
                    selectedData = tableData.getValueAt(tableData.getSelectedRow(), 0).toString();
                    TransaksiController tc = new TransaksiController();
                    tc.readTrx(selectedData);
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
        if(e.getSource()==btnKembali){
            new MenuUser();
            dispose();
        }
    }
}
