/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.Group;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class GroupDAO {
    
        /*
      Obtiene una lista de todos los grupos de la base de datos, ordenados por nombre.
      Este método es fundamental para poblar el JComboBox en la interfaz de usuario.
      @return Una lista de objetos Group.
      @throws SQLException Si ocurre un error de base de datos.
    */
    public List<Group> getAllGroups() throws SQLException {
        List<Group> groups = new ArrayList<>();
        String sql = "SELECT * FROM contact_groups ORDER BY group_name";

        try (Connection conn = ConexionBD.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Group group = new Group();
                group.setGroupId(rs.getInt("group_id"));
                group.setGroupName(rs.getString("group_name"));
                groups.add(group);
            }
        }
        return groups;
    }
    /*
      Añade un nuevo grupo a la base de datos.
      @param group El objeto Group a guardar (solo se usa el nombre).
      @throws SQLException Si ocurre un error de base de datos.
    */
    public void addGroup(Group group) throws SQLException {
        String sql = "INSERT INTO contact_groups (group_name) VALUES (?)";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, group.getGroupName());
            pstmt.executeUpdate();
        }
    }
    /*
      Actualiza el nombre de un grupo existente en la base de datos.
      @param group El objeto Group con el ID del grupo a actualizar y su nuevo nombre.
      @throws SQLException Si ocurre un error de base de datos.
    */
    public void updateGroup(Group group) throws SQLException {
        String sql = "UPDATE contact_groups SET group_name = ? WHERE group_id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, group.getGroupName());
            pstmt.setInt(2, group.getGroupId());
            pstmt.executeUpdate();
        }
    }

    /*
      Elimina un grupo de la base de datos usando su ID.
      @param groupId El ID del grupo a eliminar.
      @throws SQLException Si ocurre un error de base de datos.
    */
    public void deleteGroup(int groupId) throws SQLException {
        String sql = "DELETE FROM contact_groups WHERE group_id = ?";

        try (Connection conn = ConexionBD.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, groupId);
            pstmt.executeUpdate();
        }
    }
    
}
