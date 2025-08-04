/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class ConexionBD {
    
    
    // -- DATOS DE CONEXIÓN --
    // Es importante mantener estos datos aquí para poder cambiarlos fácilmente.
    private static final String URL = "jdbc:postgresql://localhost:5432/agenda";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    /*
      Establece y devuelve una conexión con la base de datos.
      Este es el método estándar que usarán todas las clases DAO.
     
      @return un objeto Connection listo para ser usado.
      @throws SQLException si ocurre un error al intentar conectar con la BD.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    /*
      Sobrecarga del método getConnection que permite controlar el modo 'auto-commit'.
      Útil para manejar transacciones manualmente (ej. si varias operaciones deben
      tener éxito o fracasar juntas).
     
      @param autoCommit false para iniciar una transacción manual, true para el modo normal.
      @return una conexión con el modo auto-commit configurado.
      @throws SQLException si ocurre un error al conectar.
     */
    public static Connection getConnection(boolean autoCommit) throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        connection.setAutoCommit(autoCommit);
        return connection;
    }
    
}
