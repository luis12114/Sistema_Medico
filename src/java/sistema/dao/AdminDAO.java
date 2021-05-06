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

    // Metodo para listar usuarios
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT *FROM user";
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                user.setPicture(rs.getString("imagen_user"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }
    
    // Metodo para añadir usuarios
    public void updateUser(String nombre,User user) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE user SET password=?,name=? WHERE name= ?;");
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3,nombre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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

    //Meotodo para eliminar archivos
    public void deleteitems(String name) {
        final String RUTA = "C:\\Users\\52777\\Documents\\NetBeansProjects\\SistemaMedico\\web\\images\\" + name;
        try {
            File archivo = new File(RUTA);

            if (archivo.delete()) {
                System.out.println("El archivo fue eliminado satisfactoriamente.");
            } else {
                System.out.println("No se ha podido borrar el archivo.");
            }

        } catch (Exception e) {
            System.err.println("Error -> " + e.getMessage());
        }
    }

}
