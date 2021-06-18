package sistema.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sistema.model.Category;
import sistema.model.User;
import sistema.util.Database;

public class CategoryDAO {
    private Connection connection;
    public CategoryDAO()
    {
        connection=Database.getConnection(); 
    }
    
    // Metodo para obtener el usuario logeado
    public User getUser(String nombre) {
        User u = new User();
        try {
            PreparedStatement pstm = null;
            ResultSet rs = null;
            String query = "SELECT * FROM user JOIN roles WHERE user.ID_Rol=roles.ID_Rol AND user.name=?";
            pstm = connection.prepareStatement(query);
            pstm.setString(1, nombre);
            rs = pstm.executeQuery();
            while (rs.next()) {
                u.setName(rs.getString("name"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setPicture(rs.getString("imagen_user"));
                u.setId_role(rs.getInt("user.ID_Rol"));
                u.setName_rol(rs.getString("roles.Name_Rol"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return u;
    }
    
    public List<Category> getAllCategories() throws SQLException {
        List<Category> listCategory = new ArrayList<Category>();
        try{
                String sql = "SELECT * FROM paciente where estatus = 'activo' ORDER BY name";
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);             
                while (result.next()) 
                {
                    int id = result.getInt("id_paciente");
                    String name = result.getString("name");
                    String fec = result.getString("fecha");
                    Category category = new Category(id, name,fec);                     
                    listCategory.add(category);
                } 
        }catch(SQLException ex){
            ex.printStackTrace();
            throw ex;
        }
    return listCategory;
    }
}
