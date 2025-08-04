/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.ErrorLog;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class ErrorLogDAO {
    
     /*
     Inserta un registro de error en la base de datos.
      Este método es especial porque maneja sus propias excepciones para no causar
      un fallo en cascada (un error al loguear otro error).
     
      @param errorMessage El mensaje de la excepción que fue capturada.
      @param methodName El nombre del método donde se originó el error.
    */
    public void logError(String errorMessage, String methodName) {
        String sql = "INSERT INTO error_logs (error_message, method_name) VALUES (?, ?)";

        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, errorMessage);
            pstmt.setString(2, methodName);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("--- FALLO CRÍTICO DEL SISTEMA DE LOGS ---");
            System.err.println("No se pudo registrar el siguiente error en la base de datos:");
            System.err.println("Método Origen: " + methodName);
            System.err.println("Mensaje: " + errorMessage);
            e.printStackTrace();
        }
    }
    /*
      Obtiene todos los registros de error de la base de datos, ordenados del
      más reciente al más antiguo. Podría ser útil para una futura vista de administrador.
     
      @return Una lista de objetos ErrorLog.
      @throws SQLException Si ocurre un error al leer de la base de datos.
    */    

    public List<ErrorLog> getAllLogs() throws SQLException {
        List<ErrorLog> logs = new ArrayList<>();
        String sql = "SELECT * FROM error_logs ORDER BY log_timestamp DESC";

        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ErrorLog log = new ErrorLog();
                log.setLog_id(rs.getInt("log_id"));
                log.setLogTimestamp(rs.getTimestamp("log_timestamp").toLocalDateTime());
                log.setErrorMessage(rs.getString("error_message"));
                log.setMethodName(rs.getString("method_name"));
                logs.add(log);
            }
        }
        return logs;
    }
    
}
