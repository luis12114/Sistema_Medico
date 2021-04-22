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

    public User getUser(String nombre) {
        User u = new User();
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT *FROM user WHERE name= ?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, nombre);
            rs = pstm.executeQuery();
            while(rs.next()){
                u.setName(rs.getString("name"));
                u.setPicture(rs.getString("imagen_user"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }

    // Metodo para añadir usuarios
    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO user (name,password,imagen_user) VALUES (?,?,?);");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPicture());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
}
