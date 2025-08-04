
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.ContactDAO;
import dao.EventDAO;
import dao.GroupDAO;
import dao.ErrorLogDAO;
import model.Contact;
import model.Event;
import model.Group;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Natalie Ulate Rojas
 */
public class AgendaService {
    
    // El servicio "depende de" las clases DAO para poder interactuar con la base de datos.
    // Se declaran como 'final' porque se inicializan una vez y no cambiarán.
    private final ContactDAO contactDAO;
    private final EventDAO eventDAO;
    private final GroupDAO groupDAO;
    private final ErrorLogDAO errorLogDAO;
    /*
      Constructor de la clase.
      Al crear una instancia de AgendaService, se inicializan todos los objetos DAO
      que se necesitarán para realizar las operaciones.
     */
    public AgendaService() {
        this.contactDAO = new ContactDAO();
        this.eventDAO = new EventDAO();
        this.groupDAO = new GroupDAO();
        this.errorLogDAO = new ErrorLogDAO();
    }
    /*
      Pide al DAO la lista completa de contactos.
      Si ocurre un error de base de datos, lo registra y devuelve una lista vacía
      para evitar que la aplicación se detenga 
      @return Una lista de objetos Contact. Si hay un error, la lista estará vacía.
     */
    public List<Contact> getAllContacts() {
        try {
            return contactDAO.getAllContacts();
        } catch (SQLException e) {
            System.err.println("¡ERROR AL OBTENER CONTACTOS! La excepción es:");
            e.printStackTrace();
            errorLogDAO.logError(e.getMessage(), "AgendaService.getAllContacts");
            return Collections.emptyList();
        }
    }
   /*
      Guarda un contacto. Decide si es un nuevo registro (INSERT) o una
      actualización (UPDATE) basándose en el ID del contacto.
     
      @param contact El objeto Contact a guardar.
    */
    public void saveContact(Contact contact) {
        try {

            if (contact.getContact_id() == 0) {
                contactDAO.addContact(contact);
            } else {
                contactDAO.updateContact(contact);
            }
        } catch (SQLException e) {
            errorLogDAO.logError(e.getMessage(), "AgendaService.saveContact");
        }
    }
    /*
      Pide al DAO que elimine un contacto por su ID.
     
      @param contactId El ID del contacto a eliminar.
    */
    public void deleteContact(int contactId) {
        try {
            contactDAO.deleteContact(contactId);
        } catch (SQLException e) {
            errorLogDAO.logError(e.getMessage(), "AgendaService.deleteContact");
        }
    }
    /*
      Pide al DAO la lista de eventos futuros.
     
      @return Una lista de objetos Event. Si hay un error, la lista estará vacía.
    */
    public List<Event> getUpcomingEvents() {
        try {
            return eventDAO.getUpcomingEvents();
        } catch (SQLException e) {
            errorLogDAO.logError(e.getMessage(), "AgendaService.getUpcomingEvents");
            return Collections.emptyList();
        }
    }
    /*
      Pide al DAO que guarde un nuevo evento.
     
     * @param event El objeto Event a guardar.
    */
    public void saveEvent(Event event) {
        try {
            eventDAO.addEvent(event);
        } catch (SQLException e) {
            errorLogDAO.logError(e.getMessage(), "AgendaService.saveEvent");
        }
    }
    /*
      Pide al DAO la lista completa de grupos.
     
      @return Una lista de objetos Group. Si hay un error, la lista estará vacía.
    */
    public List<Group> getAllGroups() {
        try {
            return groupDAO.getAllGroups();
        } catch (SQLException e) {
            errorLogDAO.logError(e.getMessage(), "AgendaService.getAllGroups");
            return Collections.emptyList();
        }
    }
    
}
