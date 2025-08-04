/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Contact;
import model.Event;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class EventDAO {
    
    /*
      Añade un nuevo evento a la base de datos.
      @param event El objeto Event a guardar.
      @throws SQLException Si ocurre un error de base de datos.
    */
    public void addEvent(Event event) throws SQLException {
        String sql = "INSERT INTO events (contact_id, event_date, event_time, description, location) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, event.getContact().getContact_id());
            // Convertimos de java.time.LocalDate a java.sql.Date para la BD.
            pstmt.setDate(2, Date.valueOf(event.getEventDate()));
             // Convertimos de java.time.LocalTime a java.sql.Time para la BD.
            pstmt.setTime(3, Time.valueOf(event.getEventTime()));
            pstmt.setString(4, event.getDescription());
            pstmt.setString(5, event.getLocation());
            pstmt.executeUpdate();
        }
    }
    /*
      Actualiza un evento existente en la base de datos.
      @param event El objeto Event con los datos actualizados.
      @throws SQLException Si ocurre un error de base de datos.
    */
    public void updateEvent(Event event) throws SQLException {
        String sql = "UPDATE events SET contact_id = ?, event_date = ?, event_time = ?, description = ?, location = ? WHERE event_id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, event.getContact().getContact_id());
            pstmt.setDate(2, Date.valueOf(event.getEventDate()));
            pstmt.setTime(3, Time.valueOf(event.getEventTime()));
            pstmt.setString(4, event.getDescription());
            pstmt.setString(5, event.getLocation());
            pstmt.setInt(6, event.getEvent_id());
            pstmt.executeUpdate();
        }
    }
    /*
      Elimina un evento de la base de datos usando su ID.
      @param eventId El ID del evento a eliminar.
      @throws SQLException Si ocurre un error de base de datos.
    */
    public void deleteEvent(int eventId) throws SQLException {
        String sql = "DELETE FROM events WHERE event_id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, eventId);
            pstmt.executeUpdate();
        }
    }
    /*
      Obtiene una lista de todos los eventos futuros (desde la fecha actual en adelante).
      @return Una lista de objetos Event.
      @throws SQLException Si ocurre un error al consultar la BD.
    */
    public List<Event> getUpcomingEvents() throws SQLException {
        List<Event> events = new ArrayList<>();        
        String sql = "SELECT e.*, c.first_name, c.last_name " +
                     "FROM events e JOIN contacts c ON e.contact_id = c.contact_id " +
                     "WHERE e.event_date >= CURRENT_DATE " +
                     "ORDER BY e.event_date, e.event_time";

        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Event event = new Event();
                event.setEvent_id(rs.getInt("event_id"));
                event.setEventDate(rs.getDate("event_date").toLocalDate());
                event.setEventTime(rs.getTime("event_time").toLocalTime());
                event.setDescription(rs.getString("description"));
                event.setLocation(rs.getString("location"));
                Contact contact = new Contact();
                contact.setContact_id(rs.getInt("contact_id"));
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("last_name"));
                event.setContact(contact);

                events.add(event);
            }
        }
        return events;
    }
    
}
