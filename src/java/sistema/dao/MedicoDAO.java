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

import sistema.model.Medico;

public class MedicoDAO {

    private Connection con = null;

    public MedicoDAO() {
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

   
    //Metodo para listar todos los doctores
    public List<Medico> getAllMedicos() {
        List<Medico> medicos = new ArrayList<Medico>();
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT *FROM medico";
            pstm = con.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getInt("id_medico"));
                medico.setArea(rs.getString("area"));
                medico.setNombre(rs.getString("nombre"));
                medico.setAp_pat(rs.getString("apell_pat"));
                medico.setAp_mat(rs.getString("apell_mat"));
                medico.setDireccion(rs.getString("direccion"));
                medico.setEmail(rs.getString("email"));
                medico.setTel(rs.getString("tel"));
                medico.setHora_inc(rs.getString("hora_inic"));
                medico.setHora_fin(rs.getString("hora_fin"));
                medicos.add(medico);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicos;
    }
    
     // Metodo para añadir doctores
    public void addMedico(Medico medico) {
        try {
            PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO medico (area,nombre,apell_pat,apell_mat,direccion,email,tel,hora_inic,hora_fin) VALUES (?,?,?,?,?,?,?,?,?);");
            preparedStatement.setString(1,medico.getArea());
            preparedStatement.setString(2,medico.getNombre());
            preparedStatement.setString(3,medico.getAp_pat());
            preparedStatement.setString(4,medico.getAp_mat());
            preparedStatement.setString(5,medico.getDireccion());
            preparedStatement.setString(6,medico.getEmail());
            preparedStatement.setString(7,medico.getTel());
            preparedStatement.setString(8,medico.getHora_inc());
            preparedStatement.setString(9,medico.getHora_fin());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
