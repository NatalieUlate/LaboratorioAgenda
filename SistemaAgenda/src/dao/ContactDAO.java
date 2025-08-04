/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Contact;
import model.Group;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class ContactDAO {
    
    /*
      Añade un nuevo contacto a la base de datos.
      @param contact El objeto Contact con todos los datos a insertar.
      @throws SQLException Si ocurre un error durante la inserción en la BD.
     */
     public void addContact(Contact contact) throws SQLException {
        String sql = "INSERT INTO contacts (first_name, last_name, phone_numbers, emails, address, tags, group_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, contact.getFirstName());
            pstmt.setString(2, contact.getLastName());
            pstmt.setString(3, contact.getPhoneNumbers());
            pstmt.setString(4, contact.getEmails());
            pstmt.setString(5, contact.getAddress());
            pstmt.setString(6, contact.getTags());
            
            if (contact.getGroup() != null) {
                pstmt.setInt(7, contact.getGroup().getGroupId());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }
            
            pstmt.executeUpdate();
        }
    }
    /*
      Actualiza un contacto existente en la base de datos.
      @param contact El objeto Contact con los datos actualizados. El ID del contacto es crucial.
      @throws SQLException Si ocurre un error durante la actualización.
    */
    public void updateContact(Contact contact) throws SQLException {
        String sql = "UPDATE contacts SET first_name = ?, last_name = ?, phone_numbers = ?, emails = ?, address = ?, tags = ?, group_id = ? WHERE contact_id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, contact.getFirstName());
            pstmt.setString(2, contact.getLastName());
            pstmt.setString(3, contact.getPhoneNumbers());
            pstmt.setString(4, contact.getEmails());
            pstmt.setString(5, contact.getAddress());
            pstmt.setString(6, contact.getTags());

            if (contact.getGroup() != null) {
                pstmt.setInt(7, contact.getGroup().getGroupId());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }
            
            pstmt.setInt(8, contact.getContact_id());
            
            pstmt.executeUpdate();
        }
    }
    /*
      Elimina un contacto de la base de datos usando su ID.
      @param contactId El ID del contacto a eliminar.
      @throws SQLException Si ocurre un error durante la eliminación.
    */
    public void deleteContact(int contactId) throws SQLException {
        String sql = "DELETE FROM contacts WHERE contact_id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, contactId);
            pstmt.executeUpdate();
        }
    }
    /*
      Obtiene una lista con todos los contactos de la base de datos.
      @return Una lista de objetos Contact.
      @throws SQLException Si ocurre un error al consultar la BD.
    */
    public List<Contact> getAllContacts() throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        // Usamos un LEFT JOIN para obtener también el nombre del grupo en la misma consulta
        // y así evitar hacer una consulta adicional por cada contacto.
        String sql = "SELECT c.*, g.group_name FROM contacts c LEFT JOIN contact_groups g ON c.group_id = g.group_id ORDER BY c.first_name, c.last_name";

        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // Recorremos cada fila del resultado de la consulta.
            while (rs.next()) {
                // Por cada fila, creamos un objeto Contact y lo llenamos con los datos.
                Contact contact = new Contact();
                contact.setContact_id(rs.getInt("contact_id"));
                contact.setFirstName(rs.getString("first_name"));
                contact.setLastName(rs.getString("last_name"));
                contact.setPhoneNumbers(rs.getString("phone_numbers"));
                contact.setEmails(rs.getString("emails"));
                contact.setAddress(rs.getString("address"));
                contact.setTags(rs.getString("tags"));
                // Si el group_id no es nulo, creamos el objeto Group asociado.
                if (rs.getObject("group_id") != null) {
                    Group group = new Group();
                    group.setGroupId(rs.getInt("group_id"));
                    group.setGroupName(rs.getString("group_name"));
                    contact.setGroup(group);
                }
                
                contacts.add(contact);
            }
        }
        return contacts;
    }
    
}
