package sistema.dao;

import sistema.util.Database;

import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


import sistema.model.User;
import sistema.model.Citas;
import sistema.model.Medico;

public class CitaDAO {
   private Connection con = null;
   
   public CitaDAO() {
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
                u.setEmail(rs.getString("email"));
                u.setPicture(rs.getString("imagen_user"));
                u.setPassword(rs.getString("password"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }
    
    
    //Metodo para listar todas las citas
    public List<Citas> getAllCitas(String name) {
        List<Citas> citas= new ArrayList<Citas>();
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT *FROM paciente WHERE name=? AND estatus='activo'";
            pstm = con.prepareStatement(query);
            pstm.setString(1,name);
            rs = pstm.executeQuery();
            while (rs.next()) {
               Citas cita = new Citas();
               cita.setId(rs.getInt("id_paciente"));
               cita.setName(rs.getString("name"));
               cita.setDoctor(rs.getString("doctor"));
               cita.setFecha(rs.getString("fecha"));
               cita.setHora(rs.getString("hora"));
               cita.setTel(rs.getString("tel"));
               cita.setCorreo(rs.getString("correo"));
               cita.setGenero(rs.getString("genero"));
               cita.setMotivo(rs.getString("motivo"));
               cita.setSintomas(rs.getString("sintomas"));
               cita.setEstatus(rs.getString("estatus"));
               citas.add(cita);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citas;
    }
    
    
    // Metodo para añadir citas
    public void addCitas(Citas cita) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO paciente(name,doctor,fecha,hora,tel,correo,genero,motivo,sintomas,estatus) VALUES(?,?,?,?,?,?,?,?,?,?);");
            preparedStatement.setString(1,cita.getName());
            preparedStatement.setString(2,cita.getDoctor());
            preparedStatement.setString(3, cita.getFecha());
            preparedStatement.setString(4,cita.getHora());
            preparedStatement.setString(5,cita.getTel());
            preparedStatement.setString(6,cita.getCorreo());
            preparedStatement.setString(7,cita.getGenero());
            preparedStatement.setString(8,cita.getMotivo());
            preparedStatement.setString(9,cita.getSintomas());
            preparedStatement.setString(10,cita.getEstatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Metodo para obtener los datos de una cita
    public Citas getCita(int id) {
        Citas c = new Citas();
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT *FROM paciente WHERE id_paciente=?";
            pstm = con.prepareStatement(query);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            while (rs.next()) {
                c.setId(rs.getInt("id_paciente"));
                c.setName(rs.getString("name"));
                c.setDoctor(rs.getString("doctor"));
                c.setFecha(rs.getString("fecha"));
                c.setHora(rs.getString("hora"));
                c.setTel(rs.getString("tel"));
                c.setCorreo(rs.getString("correo"));
                c.setGenero(rs.getString("genero"));
                c.setMotivo(rs.getString("motivo"));
                c.setSintomas(rs.getString("sintomas"));
            }  
        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return c;
    }
    
    // Metodo para actualizar cita
    public void updateCita(int id, Citas cita) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE paciente SET name=?,doctor=?,fecha=?,hora=?,tel=?,correo=?,genero=?,motivo=?,sintomas=?  WHERE id_paciente=?;");
            preparedStatement.setString(1,cita.getName());
            preparedStatement.setString(2,cita.getDoctor());
            preparedStatement.setString(3, cita.getFecha());
            preparedStatement.setString(4,cita.getHora());
            preparedStatement.setString(5,cita.getTel());
            preparedStatement.setString(6,cita.getCorreo());
            preparedStatement.setString(7,cita.getGenero());
            preparedStatement.setString(8,cita.getMotivo());
            preparedStatement.setString(9,cita.getSintomas());
            preparedStatement.setInt(10,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Metodo para cancelar cita
    public void cancelarCita(int id) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("UPDATE paciente SET estatus='cancelado'  WHERE id_paciente=?;");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        } 
    }
    
    //Metodo para eliminar citas
    public void deleteCita(int id){
        try {
            PreparedStatement preparedStatement = con.prepareStatement("DELETE  FROM  paciente WHERE id_paciente=?;");
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
            String query = "SELECT id_paciente FROM paciente WHERE hora=?";
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
  
  /*-------------------------------TERMINA VALIDACIONES--------------------------------*/ 
}
