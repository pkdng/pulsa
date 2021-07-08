package pulsa.view;

import java.sql.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import pulsa.MyException;

import pulsa.controller.TransaksiController;
import pulsa.controller.UserSession;

public class BeliPulsa extends JFrame implements ActionListener{
    private static final SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    
    String operator, nominal;
    String id_user  = UserSession.getId();
    
    private JLabel lOperator, lNominal, lNhp;
    private JTextField fNhp, fOperator, fNominal;
    private JButton bBayar, bKembali;

    public BeliPulsa(){
        setTitle("Beli Pulsa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(335,295);
             
        lOperator = new JLabel("Operator");
        lNominal  = new JLabel("Nominal");
        lNhp      = new JLabel("No HP");
        fNhp      = new JTextField();
        fOperator = new JTextField();
        fNominal  = new JTextField();
        bBayar    = new JButton("Bayar");
        bKembali  = new JButton("Kembali");
        
        setLayout(null);
        add(lOperator);
        add(fOperator);
        add(lNominal);
        add(fNominal);
        add(lNhp);
        add(fNhp);
        add(bBayar);
        add(bKembali);
        
        lOperator.setBounds(30, 30, 100, 30);
        fOperator.setBounds(110, 30, 180, 30);
        lNominal.setBounds(30, 70, 100, 30);
        fNominal.setBounds(110, 70, 180, 30);
        lNhp.setBounds(30, 110, 100, 30);
        fNhp.setBounds(110, 110, 180, 30);
        bBayar.setBounds(30, 155, 260, 30);
        bBayar.addActionListener(this);
        bKembali.setBounds(30, 195, 260, 30);
        bKembali.addActionListener(this);
        
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bBayar){
            try{
                operator = fOperator.getText().toUpperCase();
                nominal  = fNominal.getText();
                
                int cekNominal = Integer.parseInt(nominal);
                
                if(cekNominal <= 0){
                    throw new MyException("Bilangan harus lebih dari 0");
                }
                String[] data = {
                    id_user, operator, nominal
                };
                TransaksiController tc = new TransaksiController();
                tc.addTrx(data);

                if(TransaksiController.getIDHarga() != null){OptionDialog();}
                else{
                    JOptionPane.showMessageDialog(null, "Pembelian GAGAL, pulsa tidak tersedia", "", JOptionPane.WARNING_MESSAGE);
                }
            }catch(NumberFormatException num){
                setMessage("Nominal harus bilangan");
            } catch (MyException ex) {
                setMessage(ex.getMessage());
            }
        }
        if(e.getSource()==bKembali){
            dispose();
            new MenuUser();
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
            dispose();
            new MenuUser();
        }else{
            JOptionPane.showMessageDialog(null, "Pembelian dibatalkan", "", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void setMessage(String text) {
        JOptionPane.showMessageDialog(null, text, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}
