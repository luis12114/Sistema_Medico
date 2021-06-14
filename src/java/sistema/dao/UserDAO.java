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

public class UserDAO {

    private Connection con = null;

    public UserDAO() {
        con = Database.getConnection();
    }

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
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setPicture(rs.getString("imagen_user"));
                
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
                user.setId(rs.getInt("id_user"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setPicture(rs.getString("imagen_user"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Metodo para actualizar usuarios
    public void updateUser(String nombre, User user) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE user SET password=?,name=?,email=? WHERE name= ?;");
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, nombre);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Metodo para eliminar usuario
    public void deleteUser(String name) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM user WHERE name= ?;");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Meotodo para eliminar archivos
    public void deleteItems(String name) {
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
  /*-------------------------------TERMINA VALIDACIONES--------------------------------*/
   
 
  /*-------------------------------INICIA VALIDACIONES-----------------------------------*/
   public boolean validarregistro1(String Usuario) {
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT id_user FROM user WHERE name=?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, Usuario);//convertir a String el parametro Usuario
            rs = pstm.executeQuery();//ejecutar el query 
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }  
  
   public boolean validaemail2(String Usuario) {
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT id_user FROM user WHERE email=?";
            pstm = con.prepareStatement(query);
            pstm.setString(1, Usuario);//convertir a String el parametro Usuario
            rs = pstm.executeQuery();//ejecutar el query 
            if (rs.next()) {
                return false;
            } else {
                return true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }  
  /*-------------------------------TERMINA VALIDACIONES--------------------------------*/

  
  
 
  

}
