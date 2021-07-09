package pulsa.controller;

import java.math.BigInteger;
import java.security.*;
import javax.swing.*;

import pulsa.model.UserModel;

public class UserController {
    String op, nom;
    
    public void setOperator(String so){op = so;}
    public void setNominal(String sn){nom = sn;}
    public String getOperator(){return op;}
    public String getNominal(){return nom;}
    
    public void createUser(String[] data){
        UserModel user = new UserModel();
        user.createUser(data);
    }
    
    public void loginUser(String[] data){
        UserModel user = new UserModel();
        user.loginUser(data);
    }
    
    public static String getMd5(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) { hashtext = "0" + hashtext; }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal Ditampilkan" + e);
            throw new RuntimeException(e);
        }
    }
}
