package sistema.dao;

import sistema.util.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sistema.model.User;

public class AdminDAO {

    private Connection con = null;

    // Metodo que genera la conexion
    public AdminDAO() {
        con = Database.getConnection();
    }

    // Metodo que devuelve false o true si el  usuario existe en la BD
    public boolean login(String Usuario, String Password)
    {
        try
        {
           PreparedStatement pstm = null; 
           ResultSet rs = null;
           String query = "SELECT ID_User FROM user WHERE name = ? AND password=?";
           pstm = con.prepareStatement(query);
           pstm.setString(1, Usuario);//convertir a String el parametro Usuario
           pstm.setString(2, Password);//convertir a String el parametro Password
           rs = pstm.executeQuery();//ejecutar el query 
           if(rs.next())
           {
               return true;
           }else{
               return false;
           }
        }catch(Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
    }

}
