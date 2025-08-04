/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;
import model.Event;
import service.AgendaService;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 *
  Controlador para la lógica de la pestaña de "Agenda de Eventos".
  Su responsabilidad es obtener y mostrar la lista de eventos próximos.
 * @author Natalie Ulate Rojas
 */
public class EventosController {
    
     // --- Referencias a los Componentes de la Vista ---
    private final JTable tblTablaEventos;
    private final DefaultTableModel modeloTabla;
     // --- Referencia a la Capa de Servicio ---
    private final AgendaService agendaService;

    /*
      Método de inicio que se llama desde la Vista para cargar los datos.
    */
    public EventosController(JTable tblTablaEventos) {
        this.tblTablaEventos = tblTablaEventos;
        this.modeloTabla = (DefaultTableModel) tblTablaEventos.getModel();
        this.agendaService = new AgendaService();
    }

    public void initController() {
        cargarEventos();
    }

    /*
      Obtiene la lista de eventos próximos del servicio y la carga en la JTable.
    */
    private void cargarEventos() {

        modeloTabla.setRowCount(0);

        List<Event> eventos = agendaService.getUpcomingEvents();

        for (Event e : eventos) {
            Object[] fila = new Object[]{
                e.getEventDate(),
                e.getEventTime(),
                e.getDescription(),
                e.getLocation(),
                e.getContact().getFirstName() + " " + e.getContact().getLastName()
            };
            modeloTabla.addRow(fila);
        }
    }
    
}
