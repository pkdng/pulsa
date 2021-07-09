package pulsa.view;

import java.awt.event.*;
import javax.swing.*;

import pulsa.controller.TransaksiController;

public class KirimPulsa extends JFrame implements ActionListener{
    private JLabel lIDUser, lnhp, loperator, lnominal, lharga, ltgl, lstatus;
    private JTextField fIDUser, fnhp, foperator, fnominal, fharga, ftgl, fstatus;
    private JButton btnKembali, btnKirim;
    private String id;
    
    public KirimPulsa(String[] data){
        id = TransaksiController.getID();
        setTitle(id);
        
        lIDUser   = new JLabel("ID User");
        lnhp      = new JLabel("No HP");
        loperator = new JLabel("Operator");
        lnominal  = new JLabel("Nominal");
        lharga    = new JLabel("Harga");
        ltgl      = new JLabel("Tgl Beli");
        lstatus   = new JLabel("STATUS");
        fIDUser   = new JTextField(data[0]);
        fnhp      = new JTextField(data[1]);
        foperator = new JTextField(data[2]);
        fnominal  = new JTextField(data[3]);
        fharga    = new JTextField(data[4]);
        ftgl      = new JTextField(data[5]);
        fstatus   = new JTextField(data[6]);
        btnKembali = new JButton("Kembali");
        btnKirim   = new JButton("Kirim Pulsa");
        
        setLayout(null);
        if(data[6].equals("BELUM TERKIRIM")){
            setSize(275,425);
            add(btnKirim);
            add(btnKembali);
            btnKirim.setBounds(15, 300, 230, 30);
            btnKirim.addActionListener(this);
            btnKembali.setBounds(15, 340, 230, 30);
            btnKembali.addActionListener(this);
        }else{
            setSize(275,385);
            add(btnKembali);
            btnKembali.setBounds(15, 300, 230, 30);
            btnKembali.addActionListener(this);
        }
        add(lIDUser);
        add(fIDUser);
        add(lnhp);
        add(fnhp);
        add(loperator);
        add(foperator);
        add(lnominal);
        add(fnominal);
        add(lharga);
        add(fharga);
        add(ltgl);
        add(ftgl);
        add(lstatus);
        add(fstatus);
        
        lIDUser.setBounds(15, 15, 100, 30);
        fIDUser.setBounds(100, 15, 145, 30);
        fIDUser.setEditable(false);
        lnhp.setBounds(15, 60, 100, 30);
        fnhp.setBounds(100, 60, 145, 30);
        fnhp.setEditable(false);
        loperator.setBounds(15, 100, 100, 30);
        foperator.setBounds(100, 100, 145, 30);
        foperator.setEditable(false);
        lnominal.setBounds(15, 140, 100, 30);
        fnominal.setBounds(100, 140, 145, 30);
        fnominal.setEditable(false);
        lharga.setBounds(15, 180, 100, 30);
        fharga.setBounds(100, 180, 145, 30);
        fharga.setEditable(false);
        ltgl.setBounds(15, 220, 100, 30);
        ftgl.setBounds(100, 220, 145, 30);
        ftgl.setEditable(false);
        lstatus.setBounds(15, 260, 100, 30);
        fstatus.setBounds(100, 260, 145, 30);
        fstatus.setEditable(false);
                       
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnKirim){
            TransaksiController t = new TransaksiController();
            t.update(id);
            t.readTrx();
            dispose();
        }
        else if(e.getSource()==btnKembali){
           TransaksiController tp = new TransaksiController();
           tp.readTrx();
           dispose();
        }
    }
}
