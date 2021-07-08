package pulsa.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import pulsa.controller.HargaController;
import pulsa.controller.TransaksiController;
import pulsa.controller.UserSession;

public class MenuUser extends JFrame implements ActionListener{
    private JLabel ljudul;
    private JButton btnBeli, btnKirim, btnPulsa, btnRiwayat, btnKeluar;
    
    public MenuUser(){
        if(UserSession.getId()!=null && UserSession.getRole().equals("2")){
            MenuMember();
        }else if(UserSession.getId()!=null && UserSession.getRole().equals("1")){
            MenuAdmin();
        }else{
            JOptionPane.showMessageDialog(null, "Login Duluu..", "", JOptionPane.WARNING_MESSAGE);
            new Login();
            dispose();
        }
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void MenuMember(){
        setTitle("Menu Member");
        setSize(295,190);
        
        ljudul     = new JLabel("Selamat Datang, "+UserSession.getNama());
        btnBeli    = new JButton("Beli Pulsa");
        btnRiwayat = new JButton("Riwayat");
        btnKeluar  = new JButton("Logout");
        
        setLayout(null);
        add(ljudul);
        add(btnBeli);
        add(btnRiwayat);
        add(btnKeluar);
        
        ljudul.setBounds(30, 10, 200, 30);
        btnBeli.setBounds(30, 50, 100, 30);
        btnBeli.addActionListener(this);
        btnRiwayat.setBounds(150, 50, 100, 30);
        btnRiwayat.addActionListener(this);
        btnKeluar.setBounds(30, 100, 220, 30);
        btnKeluar.addActionListener(this);
    }
    
    private void MenuAdmin(){
        setTitle("Menu Admin");
        setSize(295,220);
        
        ljudul     = new JLabel("Hi, "+UserSession.getNama());
        btnPulsa    = new JButton("Update Pulsa");
        btnKirim = new JButton("Kirim Pulsa");
        btnKeluar  = new JButton("Logout");
        
        setLayout(null);
        add(ljudul);
        add(btnPulsa);
        add(btnKirim);
        add(btnKeluar);
        
        ljudul.setBounds(30, 10, 200, 30);
        btnPulsa.setBounds(30, 50, 220, 30);
        btnPulsa.addActionListener(this);
        btnKirim.setBounds(30, 90, 220, 30);
        btnKirim.addActionListener(this);
        btnKeluar.setBounds(30, 130, 220, 30);
        btnKeluar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==btnBeli){
           new BeliPulsa();
           dispose();
       }else if(e.getSource()==btnRiwayat){
           TransaksiController ru = new TransaksiController();
           ru.readRiwayat();
           dispose();
       }else if(e.getSource()==btnKeluar){
           //JOptionPane.showMessageDialog(null, "Anda telah keluar!", "", JOptionPane.INFORMATION_MESSAGE);
           UserSession.setId(null);
           UserSession.setRole(null);
           new Login();
           dispose();
       }else if(e.getSource()==btnPulsa){
           HargaController hr = new HargaController();
           hr.readHarga();
           dispose();
       }else if(e.getSource()==btnKirim){
           TransaksiController tp = new TransaksiController();
           tp.readTrx();
           dispose();
       }
    }
}
