
package Conection;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private static final String url = "jdbc:mysql:// ods12.cz2a0bwvyxca.us-east-1.rds.amazonaws.com:3306/ods_12";
        private static final String user = "admin";
        private static final String password = "sjgods12";
        
    public static void main(String[] args) {
        String url = "jdbc:mysql:// ods12.cz2a0bwvyxca.us-east-1.rds.amazonaws.com:3306/ods_12";
        String user = "admin";
        String password = "sjgods12";
                
        try(Connection connection = DriverManager.getConnection(url, user, password)){
            System.out.println("Conexion exitosa a la base de datos");
        } catch(SQLException e){
            System.out.println("Error en la conexion: " + e.getMessage());}
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
