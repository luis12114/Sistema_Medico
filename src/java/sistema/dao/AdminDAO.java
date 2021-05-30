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
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO user (name,password,imagen_user,ID_Rol) VALUES (?,?,?,?);");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getPicture());
            preparedStatement.setInt(4, user.getId_role());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  /*-------------------------------TERMINA REGISTRO DE USURIOS --------------------------*/

    
  /*-------------------------------INICIA ROLES---------------------------------------------*/
    // Metodo para listar todos los roles
    public List<Roles> getAllRoles() {
        List<Roles> roles = new ArrayList<Roles>();
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT *FROM roles";
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Roles role = new Roles();
                role.setId_rol(rs.getInt("ID_Rol"));
                role.setName_role(rs.getString("Name_Rol"));
                role.setPermissions(rs.getString("Permissions"));
                role.setUrl(rs.getString("url"));
                role.setId_permisos(rs.getInt("ID_permisos"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }
    
    // Metodo para obtener los datos de un rol
    public Roles getRoles(int id) {
        Roles r = new Roles();
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT *FROM roles WHERE ID_Rol= ?";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
               r.setId_rol(rs.getInt("ID_Rol"));
               r.setName_role(rs.getString("Name_Rol"));
               r.setPermissions(rs.getNString("Permissions"));
               r.setUrl(rs.getString("url"));
               r.setId_permisos(rs.getInt("ID_permisos"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return r;
    }
    
    // Metodo para añadir usuarios
    public void addRole(Roles role) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO roles (Name_Rol,Permissions,url) VALUES (?,?,?);");
            preparedStatement.setString(1,role.getName_role());
            preparedStatement.setString(2,role.getPermissions());
            preparedStatement.setString(3,role.getUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Metodo para actualizar roles
    public void updateRoles(int id, Roles role) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE roles SET ID_Rol=?,Name_Rol=?,Permissions=?,url=? WHERE ID_Rol=?;");
            preparedStatement.setInt(1,role.getId_rol());
            preparedStatement.setString(2,role.getName_role());
            preparedStatement.setString(3,role.getPermissions());
            preparedStatement.setString(4,role.getUrl());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Metodo para eliminar roles
    public void deleteRoles(int id) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM roles WHERE ID_Rol= ?;");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
   /*-------------------------------TERMINA ROLES---------------------------------------------*/
}
