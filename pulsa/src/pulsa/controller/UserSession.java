package pulsa.controller;

public class UserSession {
    static String id_user, nama, role;
    
    public static void setId(String idu){id_user = idu;}
    public static void setNama(String n){nama = n;}
    public static void setRole(String r){role = r;}
    
    public static String getId(){return id_user;}
    public static String getNama(){return nama;}
    public static String getRole(){return role;}
}
