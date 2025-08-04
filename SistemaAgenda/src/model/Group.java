/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Natalie Ulate Rojas
 */
public class Group {
    
    private int groupId;
    private String groupName;

    public Group() {
    }
    /*
      Constructor para crear un grupo con sus datos.
      @param groupId El ID único del grupo.
      @param groupName El nombre del grupo (ej. "Familia", "Trabajo").
     */
    public Group(int groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
   /*
      Sobrescribimos este método para definir que dos objetos Group son iguales
      si sus IDs son idénticos. Esto es CRUCIAL para que el JComboBox pueda
      encontrar y seleccionar el ítem correcto automáticamente.
      @param obj El objeto a comparar.
      @return true si los grupos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Group)) {
            return false;
        }
        Group otroGrupo = (Group) obj;
        return this.groupId == otroGrupo.getGroupId();
    }
    /*
      Es una buena práctica sobreescribir hashCode cuando se sobreescribe equals
      para asegurar el funcionamiento correcto en colecciones de datos.
      @return El código hash basado en el groupId.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(this.groupId);
    }
    /*
      Sobrescribimos toString para que, cuando un objeto Group se muestre en un
      JComboBox, se vea su nombre (groupName) en lugar de la referencia al
      objeto en memoria (ej. "model.Group@1f2a3b4").
      @return El nombre del grupo.
     */
    @Override
    public String toString() {
        return groupName;
    }

    
}
