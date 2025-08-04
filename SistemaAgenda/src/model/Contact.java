/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


/**
 *
 * @author Natalie Ulate Rojas
 */
public class Contact {
    
    private int contact_id;
    private String firstName;
    private String lastName;
    private String phoneNumbers;
    private String emails;
    private String address;
    private String tags;
    private Group group;
    
    /*
      Constructor por defecto. Permite crear una instancia vacía.
    */
    public Contact() {
    }
   /*
      Constructor parametrizado para crear un objeto Contact con todos sus datos.
     
     @param contact_id El ID único del contacto.
     @param firstName El nombre del contacto.
     @param lastName El apellido del contacto.
     @param phoneNumbers El número o números de teléfono.
     @param emails El correo o correos electrónicos.
     @param address La dirección física.
     @param tags Etiquetas para clasificar al contacto.
     @param group El objeto Group al que pertenece.
     */
    public Contact(int contact_id, String firstName, String lastName, String phoneNumbers, String emails, String address, String tags, Group group) {
        this.contact_id = contact_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumbers = phoneNumbers;
        this.emails = emails;
        this.address = address;
        this.tags = tags;
        this.group = group;
    }
    // --- Getters y Setters ---
    /* A continuación se encuentran los métodos para acceder y modificar
     los atributos privados de la clase, manteniendo el encapsulamiento.*/
    public int getContact_id() {
        return contact_id;
    }

    public void setContact_id(int contact_id) {
        this.contact_id = contact_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    
}
