package pulsa.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pulsa.controller.UserController;
import pulsa.controller.UserSession;

public class Login extends JFrame implements ActionListener{
    private JLabel lLogin, lUsername, lPassword;
    private JTextField fUsername;
    private JPasswordField fPassword;
    private JButton bLogin, bDaftar;
    private JCheckBox cLihat;
    
    public Login(){
        setTitle("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(335,220);
        
        lLogin    = new JLabel("LOGIN");
        lUsername = new JLabel("Username");
        lPassword = new JLabel("Password");
        fUsername = new JTextField();
        fPassword = new JPasswordField();
        bLogin    = new JButton("Login");
        bDaftar   = new JButton("Daftar");
        cLihat    = new JCheckBox("Lihat");
        
        setLayout(null);
        add(lLogin);
        add(lUsername);
        add(fUsername);
        add(lPassword);
        add(fPassword);
        add(cLihat);
        add(bLogin);
        add(bDaftar);
        
        lLogin.setBounds(145, 15, 100, 20);
        lUsername.setBounds(30, 50, 100, 30);
        fUsername.setBounds(110, 50, 180, 30);
        lPassword.setBounds(30, 90, 100, 30);
        fPassword.setBounds(110, 90, 125, 30);
        cLihat.setBounds(240, 90, 60, 30);
        cLihat.addActionListener(this);
        bLogin.setBounds(110, 130, 85, 30);
        bLogin.addActionListener(this);
        bDaftar.setBounds(205, 130, 85, 30);
        bDaftar.addActionListener(this);
                
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cLihat){
            JCheckBox c = (JCheckBox) e.getSource();
            fPassword.setEchoChar(c.isSelected() ? '\u0000' : (Character) UIManager.get("PasswordField.echoChar"));
            if (c.isSelected()) {
                cLihat.setText("Tutup");
            } else {
                cLihat.setText("Lihat");
            }
        }
        if(e.getSource()==bLogin){
            String pass = String.valueOf(fPassword.getPassword());
            if(fUsername.getText().equals("")){
                setMessage("Masukkan username");
            }
            if(pass.equals("")){
                setMessage("Masukkan password");
            }
            else{
                String data[] = {
                    fUsername.getText(), pass
                };
                UserController user = new UserController();
                user.loginUser(data);
                if(UserSession.getId() != null){
                    dispose();
                    new MenuUser();
                }
            }
        }
        if(e.getSource()==bDaftar){
            dispose();
            new Daftar();
        }
    }
    
    private void setMessage(String text) {
        JOptionPane.showMessageDialog(null, text, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }
}
