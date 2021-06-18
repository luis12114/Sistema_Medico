package sistema.dao;

import java.sql.Connection;
import sistema.model.*;
import sistema.util.*;
import java.sql.*;

public class reporteDAO {
    private Connection conn;
    //un metodo constructor no regresa ningun tipo de dato e inicializa el objeto 
    public reporteDAO()
    {
        conn = Database.getConnection();  
    }//fin del constructor 
}
