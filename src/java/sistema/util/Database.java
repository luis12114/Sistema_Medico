package sistema.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    //Métodos: función que crea una conexión a la bd
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsp_project? useTimezone "+ "= true & serverTimezone = UTC", "root", "");
            return con;
        } catch (Exception ex) {
            System.out.println("Database.getConnection() Error -->" + ex.getMessage());
            return null;
        }
    }
    
    //función para cerrar la conexión a la bd
    public static void close(Connection con) {
       try {
         con.close();
        }
       catch(Exception ex) {
           ex.getMessage();
        }
    }
}
