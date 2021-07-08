package pulsa.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Riwayat extends JFrame implements ActionListener{
    private JTable tableData;
    private JButton btnKembali;
    
    public Riwayat(String[][] data){
        try{
            final String[] tableTitle = {"No HP","Operator","Nominal","Harga","Tanggal Transaksi","Status"};
            
            setTitle("Riwayat");
            setSize(700,300);
            
            btnKembali= new JButton("Kembali");
            tableData = new JTable(data, tableTitle);
            
            JScrollPane s = new JScrollPane(tableData);
            this.getContentPane().add(BorderLayout.CENTER, s);
            
            this.getContentPane().add(BorderLayout.SOUTH, btnKembali);
            btnKembali.addActionListener(this);
            
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
            dispose();
            new MenuUser();
        }
    }
    
}
