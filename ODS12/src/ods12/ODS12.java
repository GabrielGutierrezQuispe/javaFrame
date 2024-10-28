package ods12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Conection.conexion;

public class ODS12 {
    
    public List<String> obtenerConsulta() {
        List<String> consultations = new ArrayList<>();
        String sql = "SELECT * FROM consultation";
        try (Connection conn = conexion.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String lastname = rs.getString("lastname");
                String number = rs.getString("number");
                String email = rs.getString("email");
                String request = rs.getString("request");
                String dateConsult = rs.getString("dateConsult");
                String status = rs.getString("status");

                consultations.add("id: " + id + ", name: " + name + ", lastname: " + lastname + ", number: " + number + ", email: " + email + ", request: " + request + ", dateConsult: " + dateConsult + ", status: " + status);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener consultas: " + e.getMessage());
        }
        return consultations;
    }

    public boolean insertarConsulta(String name, String lastname, String number, String email, String request, String status) {
    String sql = "INSERT INTO consultation (name, lastname, number, email, request, status) VALUES (?, ?, ?, ?, ?, ?)";
    
    try (Connection conn = conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, name);
        stmt.setString(2, lastname);
        stmt.setString(3, number);
        stmt.setString(4, email);
        stmt.setString(5, request);
        stmt.setString(6, status);

        int filasInsertadas = stmt.executeUpdate();
        return filasInsertadas > 0;
        
    } catch (SQLException e) {
        System.out.println("Error al insertar consulta: " + e.getMessage());
        return false;
    }
}

    public boolean actualizarConsulta(int id, String name, String lastname, String number, String email, String request, String status) {
    String sql = "UPDATE consultation SET name = ?, lastname = ?, number = ?, email = ?, request = ?, status = ? WHERE id = ?";
    
    try (Connection conn = conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setString(1, name);
        stmt.setString(2, lastname);
        stmt.setString(3, number);
        stmt.setString(4, email);
        stmt.setString(5, request);
        stmt.setString(6, status);
        stmt.setInt(7, id);

        int filasActualizadas = stmt.executeUpdate();
        return filasActualizadas > 0;
        
    } catch (SQLException e) {
        System.out.println("Error al actualizar consulta: " + e.getMessage());
        return false;
    }
}

    public boolean eliminarConsulta(int id) {
    String sql = "DELETE FROM consultation WHERE id = ?";
    
    try (Connection conn = conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, id);
        int filasEliminadas = stmt.executeUpdate();
        
        return filasEliminadas > 0;
        
    } catch (SQLException e) {
        System.out.println("Error al eliminar consulta: " + e.getMessage());
        return false;
    }
}
}