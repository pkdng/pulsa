package pulsa.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pulsa.controller.UserController;

public class Daftar extends JFrame implements ActionListener{    
    private JLabel lnama, lusername, lpassword;
    private JTextField fnama, fusername;
    private JPasswordField fpassword;
    private JButton bdaftar, bkembali;
    private JCheckBox clihat;
    
    public Daftar(){
        setTitle("Daftar Akun");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(335,250);
        
        lnama      = new JLabel("Nama");
        lusername  = new JLabel("Username");
        lpassword  = new JLabel("Password");
        fnama      = new JTextField();
        fusername  = new JTextField();
        fpassword  = new JPasswordField();
        bdaftar    = new JButton("Daftar");
        bkembali   = new JButton("Kembali");
        clihat     = new JCheckBox("Lihat");
        
        setLayout(null);
        add(lnama);
        add(fnama);
        add(lusername);
        add(fusername);
        add(lpassword);
        add(fpassword);
        add(clihat);
        add(bdaftar);
        add(bkembali);
        
        lnama.setBounds(30, 30, 100, 30);
        fnama.setBounds(110, 30, 180, 30);
        lusername.setBounds(30, 70, 100, 30);
        fusername.setBounds(110, 70, 180, 30);
        lpassword.setBounds(30, 110, 100, 30);
        fpassword.setBounds(110, 110, 125, 30);
        clihat.setBounds(240, 110, 60, 30);
        clihat.addActionListener(this);
        bdaftar.setBounds(110, 150, 90, 30);
        bdaftar.addActionListener(this);
        bkembali.setBounds(210, 150, 80, 30);
        bkembali.addActionListener(this);
        
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true); 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==clihat){
            JCheckBox c = (JCheckBox) e.getSource();
            fpassword.setEchoChar(c.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
            if (c.isSelected()) {
                clihat.setText("Tutup");
            } else {
                clihat.setText("Lihat");
            }
        }
        else if(e.getSource()==bdaftar){
            String pass = String.valueOf(fpassword.getPassword());
            if(fnama.getText().equals("")||fusername.getText().equals("")||pass.equals("")){
                JOptionPane.showMessageDialog(null, "Data tidak boleh kosong", "Peringatan", JOptionPane.WARNING_MESSAGE);
            }
            else{
                String[] data = {
                    fnama.getText(), fusername.getText(), pass
                };
                UserController user = new UserController();
                user.createUser(data);
                new Login();
                dispose();
            }
        }
        else if(e.getSource()==bkembali){
            new Login();
            dispose();
        }
    }
}
