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
    public AdminDAO()
    {
        con = Database.getConnection();
    }
    
    
    
    // Metodo que devuelve false o true si el  usuario existe en la BD
    
}
