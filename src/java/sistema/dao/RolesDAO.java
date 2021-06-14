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

import sistema.model.Roles;
import sistema.model.User;

public class RolesDAO {
    private Connection con = null;
    
    // Metodo que genera la conexion
    public RolesDAO() {
        con = Database.getConnection();
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
    
  /*-------------------------------INICIA VALIDACIONES-----------------------------------*/
    public boolean validarregistro(String buscar) {
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT ID_Rol  FROM roles WHERE Name_Rol=?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, buscar);//convertir a String el parametro Usuario
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
