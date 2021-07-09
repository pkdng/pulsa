package pulsa.view;

import java.awt.event.*;
import javax.swing.*;

import pulsa.MyException;
import pulsa.controller.HargaController;

public class DetailPulsa extends JFrame implements ActionListener{
    private JLabel lID, loperator, lnominal, lharga;
    private JTextField fID, foperator, fnominal, fharga;
    private JButton btnKembali, btnReset, btnUpdate, btnHapus;
    private String id;
    
    HargaController hc = new HargaController();
    
    public DetailPulsa(String[] data){
        this.id = data[0];
        setTitle(id);
        setSize(275,310);
        
        lID       = new JLabel("ID Harga :");
        loperator = new JLabel("Operator :");
        lnominal  = new JLabel("Nominal  :");
        lharga    = new JLabel("Harga    :");
        fID       = new JTextField(id);
        foperator = new JTextField(data[1]);
        fnominal  = new JTextField(data[2]);
        fharga    = new JTextField(data[3]);
        
        btnKembali = new JButton("Kembali");
        btnReset   = new JButton("Reset");
        btnUpdate  = new JButton("Update");
        btnHapus   = new JButton("Hapus");
        
        setLayout(null);
        add(lID);
        add(fID);
        add(loperator);
        add(foperator);
        add(lnominal);
        add(fnominal);
        add(lharga);
        add(fharga);
        add(btnReset);
        add(btnKembali);
        add(btnUpdate);
        add(btnHapus);
        
        lID.setBounds(15, 15, 100, 30);
        fID.setBounds(115, 15, 130, 30);
        fID.setEditable(false);
        loperator.setBounds(15, 60, 100, 30);
        foperator.setBounds(115, 60, 130, 30);
        lnominal.setBounds(15, 100, 100, 30);
        fnominal.setBounds(115, 100, 130, 30);
        lharga.setBounds(15, 140, 100, 30);
        fharga.setBounds(115, 140, 130, 30);
        btnReset.setBounds(15, 185, 70, 30);
        btnReset.addActionListener(this);
        btnUpdate.setBounds(90, 185, 80, 30);
        btnUpdate.addActionListener(this);
        btnHapus.setBounds(175, 185, 70, 30);
        btnHapus.addActionListener(this);
        btnKembali.setBounds(15, 225, 230, 30);
        btnKembali.addActionListener(this);
        
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnReset){
            foperator.setText("");
            fnominal.setText("");
            fharga.setText("");
        }
        else if(e.getSource()==btnUpdate){
            if(foperator.getText().equals("")||fnominal.getText().equals("")||fharga.getText().equals("")){
                setMessage("Semua data harus diisi");
            }else{
                try{
                    int cekHarga = Integer.parseInt(fharga.getText());
                    int cekNominal = Integer.parseInt(fnominal.getText());
                    
                    if(cekHarga <= 0 || cekNominal <= 0){
                        throw new MyException("Bilangan harus lebih dari 0");
                    }
                    String operator = foperator.getText().toUpperCase();
                    String[] data = {
                        id, operator, fnominal.getText(), fharga.getText()
                    };
                    hc.update(data);
                    hc.readHarga();
                    dispose();
                }catch(NumberFormatException num){
                    setMessage("Nominal dan Harga harus bilangan");
                } catch (MyException ex) {
                    setMessage(ex.getMessage());
                }
            }
        }
        else if(e.getSource()==btnHapus){
            OptionDialog();
        }
        else if(e.getSource()==btnKembali){
            hc.readHarga();
            dispose();
        }
    }
    
    private void OptionDialog() {
        int jawab = JOptionPane.showOptionDialog(this, 
                        "Yakin Ingin Menghapus?", 
                        "Hapus", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(jawab == JOptionPane.YES_OPTION){
            hc.delete(id);
            hc.readHarga();
            dispose();
        }
    }
    
    private void setMessage(String text) {
        JOptionPane.showMessageDialog(null, text, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}
