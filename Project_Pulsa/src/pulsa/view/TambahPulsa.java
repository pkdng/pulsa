package pulsa.view;

import javax.swing.*;
import java.awt.event.*;
import pulsa.MyException;
        
import pulsa.controller.HargaController;

public class TambahPulsa extends JFrame implements ActionListener{
    private JLabel loperator, lnominal, lharga;
    private JTextField foperator, fnominal, fharga;
    private JButton btnKembali, btnReset, btnTambah;
    
    HargaController hc = new HargaController();
    
    public TambahPulsa(){
        setTitle("Tambah Data Pulsa");
        setSize(275,265);
        
        loperator = new JLabel("Operator :");
        lnominal  = new JLabel("Nominal  :");
        lharga    = new JLabel("Harga    :");
        foperator = new JTextField();
        fnominal  = new JTextField();
        fharga    = new JTextField();
        
        btnKembali = new JButton("Kembali");
        btnReset   = new JButton("Reset");
        btnTambah  = new JButton("Tambah");
        
        setLayout(null);
        add(loperator);
        add(foperator);
        add(lnominal);
        add(fnominal);
        add(lharga);
        add(fharga);
        add(btnReset);
        add(btnKembali);
        add(btnTambah);
        
        loperator.setBounds(15, 15, 100, 30);
        foperator.setBounds(115, 15, 130, 30);
        lnominal.setBounds(15, 55, 100, 30);
        fnominal.setBounds(115, 55, 130, 30);
        lharga.setBounds(15, 95, 100, 30);
        fharga.setBounds(115, 95, 130, 30);
        btnReset.setBounds(15, 140, 110, 30);
        btnReset.addActionListener(this);
        btnTambah.setBounds(135, 140, 110, 30);
        btnTambah.addActionListener(this);
        btnKembali.setBounds(15, 180, 230, 30);
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
        else if(e.getSource()==btnTambah){
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
                        operator, fnominal.getText(), fharga.getText()
                    };
                    hc.create(data);
                    hc.readHarga();
                    dispose();
                }catch(NumberFormatException num){
                    setMessage("Nominal dan Harga harus bilangan");
                } catch (MyException ex) {
                    setMessage(ex.getMessage());
                }
            }
        }
        else if(e.getSource()==btnKembali){
            hc.readHarga();
            dispose();
        }
    }
    
    private void setMessage(String text) {
        JOptionPane.showMessageDialog(null, text, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}
