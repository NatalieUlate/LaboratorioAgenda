/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class Event {
    
     private int event_id;
    private Contact contact;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private String description;
    private String location;

    public Event() {
    }
    /*
      Constructor para crear un evento con todos sus datos.
      @param event_id El ID único del evento.
      @param contact El objeto Contact al que está vinculado el evento.
      @param eventDate La fecha del evento.
      @param eventTime La hora del evento.
      @param description Una descripción de lo que ocurrirá.
      @param location El lugar del evento.
     */
    public Event(int event_id, Contact contact, LocalDate eventDate, LocalTime eventTime, String description, String location) {
        this.event_id = event_id;
        this.contact = contact;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.description = description;
        this.location = location;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public LocalTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(LocalTime eventTime) {
        this.eventTime = eventTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
}
