package sistema.dao;

import sistema.util.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import sistema.model.User;

import sistema.model.Roles;

public class AdminDAO {

    private Connection con = null;

    
    // Metodo que genera la conexion
    public AdminDAO() {
        con = Database.getConnection();
    }
    
  /*-------------------------------INICIA LOGIN-----------------------------------------*/
    // Metodo Login
    public boolean login(String Usuario, String Password) {
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT id_user FROM user WHERE name = ? AND password=?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, Usuario);//convertir a String el parametro Usuario
            pstm.setString(2, Password);//convertir a String el parametro Password
            rs = pstm.executeQuery();//ejecutar el query 
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    //Metodo que devuelve la url del Rol
    public String getURLMenu(String usuario) {
        String url = "";
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT url FROM roles as r INNER JOIN user as u ON u.ID_Rol = r.ID_Rol WHERE u.name = ?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, usuario);
            rs = pstm.executeQuery();
            if (rs.next()) {
                url = rs.getString("url");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return url;
    }
 
    // Metodo para obtener el usuario logeado
    public User getUser(String nombre) {
        User u = new User();
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT *FROM user WHERE name= ?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, nombre);
            rs = pstm.executeQuery();
            while (rs.next()) {
                u.setName(rs.getString("name"));
                u.setPicture(rs.getString("imagen_user"));
                u.setPassword(rs.getString("password"));
                u.setId_role(rs.getInt("ID_Rol"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }
  /*-------------------------------TERMINA LOGIN------------------------------------------*/
       
    
  /*-------------------------------INICIA REGISTRO DE USURIOS----------------------------*/
    // Metodo para añadir usuarios
    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO user (name,email,password,imagen_user,ID_Rol) VALUES (?,?,?,?,?);");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getPicture());
            preparedStatement.setInt(5, user.getId_role());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  /*-------------------------------TERMINA REGISTRO DE USURIOS --------------------------*/
    

  /*-------------------------------INICIA VALIDACIONES-----------------------------------*/
   public boolean validarregistro(String Usuario) {
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT id_user FROM user WHERE name=?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, Usuario);//convertir a String el parametro Usuario
            rs = pstm.executeQuery();//ejecutar el query 
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }  
  
   public boolean validaemail(String Usuario) {
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT id_user FROM user WHERE email=?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, Usuario);//convertir a String el parametro Usuario
            rs = pstm.executeQuery();//ejecutar el query 
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }  
  /*-------------------------------TERMINA VALIDACIONES---------------------------------*/
  
  
}
