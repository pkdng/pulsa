package pulsa.view;

import java.sql.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;

import pulsa.controller.TransaksiController;
import pulsa.controller.UserController;
import pulsa.controller.UserSession;
import pulsa.model.TransaksiModel;

public class BeliPulsa extends JFrame implements ActionListener{
    private static final SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    
    String operator, nominal;
    String id_user  = UserSession.getId();
    
    private JLabel lOperator, lNominal, lNhp;
    private JTextField fNhp;
    private JComboBox<String> cOperator, cNominal;
    private JButton bBayar, bKembali;

    public BeliPulsa(){
        setTitle("Beli Pulsa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(335,295);
             
        lOperator = new JLabel("Operator");
        lNominal  = new JLabel("Nominal");
        lNhp      = new JLabel("No HP");
        fNhp      = new JTextField();
        cOperator = new JComboBox<>();
        cNominal  = new JComboBox<>();
        bBayar    = new JButton("Bayar");
        bKembali  = new JButton("Kembali");
        
        setLayout(null);
        add(lOperator);
        add(cOperator);
        add(lNominal);
        add(cNominal);
        add(lNhp);
        add(fNhp);
        add(bBayar);
        add(bKembali);
        
        lOperator.setBounds(30, 30, 100, 30);
        cOperator.setBounds(110, 30, 180, 30);
        lNominal.setBounds(30, 70, 100, 30);
        cNominal.setBounds(110, 70, 180, 30);
        lNhp.setBounds(30, 110, 100, 30);
        fNhp.setBounds(110, 110, 180, 30);
        bBayar.setBounds(30, 155, 260, 30);
        bBayar.addActionListener(this);
        bKembali.setBounds(30, 195, 260, 30);
        bKembali.addActionListener(this);
        
        Operator();
        Nominal();
        
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bBayar){
            if(fNhp.getText().equals("")){
                JOptionPane.showMessageDialog(null, "No HP tidak boleh kosong");
            }else{
                operator = cOperator.getSelectedItem().toString();
                nominal  = cNominal.getSelectedItem().toString();
                String[] data = {
                    id_user, operator, nominal
                };
                TransaksiController tc = new TransaksiController();
                tc.addTrx(data);

                if(TransaksiController.getIDHarga() != null){OptionDialog();}
                else{
                    JOptionPane.showMessageDialog(null, "Pembelian GAGAL, pulsa tidak tersedia", "", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        else if(e.getSource()==bKembali){
            new MenuUser();
            dispose();
        }
    }
    
    public void Operator(){
        cOperator.removeAllItems();
        TransaksiModel tm = new TransaksiModel();
        List<UserController> op = tm.getAllOperator();
        for(UserController uop : op){
            cOperator.addItem(uop.getOperator());
        }
    }
    
    public void Nominal(){
        cNominal.removeAllItems();
        TransaksiModel tm = new TransaksiModel();
        List<UserController> op = tm.getAllNominal();
        for(UserController uop : op){
            cNominal.addItem(uop.getNominal());
        }
    }
    
    private void OptionDialog() {
        int jawab = JOptionPane.showOptionDialog(this, 
                        "Total yang harus dibayar = Rp."+TransaksiController.getHarga(), 
                        "Konfirmasi", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(jawab == JOptionPane.YES_OPTION){
            String tgl      = (String) time.format(timestamp);
            String nhp      = fNhp.getText();
            
            String[] data = {
                id_user, TransaksiController.getIDHarga(), nhp, tgl
            };
            TransaksiController t = new TransaksiController();
            t.createTrx(data);
            new MenuUser();            
            dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Pembelian dibatalkan", "", JOptionPane.WARNING_MESSAGE);
        }
    }
}
