/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package controller;
import dao.ErrorLogDAO; 
import model.Contact;
import model.Group;
import service.AgendaService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Natalie Ulate Rojas
 */
public class ContactosController {
    
    private final JTable tblTablaContactos;
    private final DefaultTableModel modeloTabla;
    private final JTextField txtId;
    private final JTextField txtNombre;
    private final JTextField txtApellido;
    private final JTextField txtTelefono;
    private final JTextField txtEmail;
    private final JComboBox<Group> cbmGrupo;
    private final JButton btnNuevo;
    private final JButton btnGuardar;
    private final JButton btnEliminar;

    // --- Referencia a la Capa de Servicio ---
    private final AgendaService agendaService;
    /*
      Constructor de la clase. Recibe todos los componentes de la vista con los
      que necesita interactuar.
     
      @param tblTablaContactos La tabla donde se muestran los contactos.
      @param txtId Campo de texto (oculto) para el ID del contacto.
      @param txtNombre Campo de texto para el nombre.
      @param txtApellido Campo de texto para el apellido.
      @param txtTelefono Campo de texto para el teléfono.
      @param txtEmail Campo de texto para el email.
      @param cbmGrupo ComboBox para seleccionar el grupo.
      @param btnNuevo Botón para limpiar el formulario.
      @param btnGuardar Botón para guardar o actualizar un contacto.
      @param btnEliminar Botón para eliminar un contacto.
    */
    public ContactosController(JTable tblTablaContactos, JTextField txtId, JTextField txtNombre, JTextField txtApellido, JTextField txtTelefono, JTextField txtEmail, JComboBox<Group> cbmGrupo, JButton btnNuevo, JButton btnGuardar, JButton btnEliminar) {
        
        this.tblTablaContactos = tblTablaContactos;
        this.modeloTabla = (DefaultTableModel) tblTablaContactos.getModel();
        this.txtId = txtId;
        this.txtNombre = txtNombre;
        this.txtApellido = txtApellido;
        this.txtTelefono = txtTelefono;
        this.txtEmail = txtEmail;
        this.cbmGrupo = cbmGrupo;
        this.btnNuevo = btnNuevo;
        this.btnGuardar = btnGuardar;
        this.btnEliminar = btnEliminar;
        
        this.agendaService = new AgendaService();
    }
    /*
      Método principal que se llama desde la Vista para activar el controlador.
      Asigna todos los listeners a los componentes y realiza la carga inicial de datos.
    */
    public void initController() {

        btnNuevo.addActionListener(e -> limpiarCampos());
        btnGuardar.addActionListener(e -> guardarContacto());
        btnEliminar.addActionListener(e -> eliminarContacto());
        tblTablaContactos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tblTablaContactos.getSelectedRow() != -1) {
                seleccionarContacto();
            }
        });
        
        cargarGrupos();
        cargarContactos();
    }
    /*
      Pide los contactos al servicio y los carga en la JTable.
    */
    private void cargarContactos() {
        modeloTabla.setRowCount(0);
        List<Contact> contactos = agendaService.getAllContacts();
        for (Contact c : contactos) {
            Object[] fila = {c.getContact_id(), c.getFirstName(), c.getLastName(), c.getPhoneNumbers(), c.getEmails(), c.getGroup()};
            modeloTabla.addRow(fila);
        }
    }
    /*
      Pide los grupos al servicio y los carga en el JComboBox.
    */
    private void cargarGrupos() {
        List<Group> grupos = agendaService.getAllGroups();
        DefaultComboBoxModel<Group> modeloCombo = new DefaultComboBoxModel<>();
        modeloCombo.addElement(null);
        grupos.forEach(modeloCombo::addElement);
        cbmGrupo.setModel(modeloCombo);
    }
    /*
      Lógica para guardar un contacto. Decide si es una inserción nueva o una
      actualización basándose en si el campo ID tiene un valor.
    */
    private void guardarContacto() {
        if (txtNombre.getText().isEmpty() || txtApellido.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nombre y Apellido son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Contact contacto = new Contact();
        contacto.setFirstName(txtNombre.getText());
        contacto.setLastName(txtApellido.getText());
        contacto.setPhoneNumbers(txtTelefono.getText());
        contacto.setEmails(txtEmail.getText());
        contacto.setGroup((Group) cbmGrupo.getSelectedItem());

        if (!txtId.getText().isEmpty()) {
            contacto.setContact_id(Integer.parseInt(txtId.getText()));
        }

        agendaService.saveContact(contacto);
        JOptionPane.showMessageDialog(null, "Contacto guardado exitosamente.");
        cargarContactos();
        limpiarCampos();
    }
    /*
      Lógica para eliminar un contacto seleccionado.
    */
    private void eliminarContacto() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un contacto para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int id = Integer.parseInt(txtId.getText());
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Está seguro?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        
        if (respuesta == JOptionPane.YES_OPTION) {
            agendaService.deleteContact(id);
            cargarContactos();
            limpiarCampos();
        }
    }
    /*
      Se activa al seleccionar una fila de la tabla. Toma los datos del modelo
      y los muestra en los campos del formulario.
    */
    private void seleccionarContacto() {
        int filaSeleccionada = tblTablaContactos.getSelectedRow();
        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        String nombre = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
        String apellido = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
        String telefono = (String) modeloTabla.getValueAt(filaSeleccionada, 3);
        String email = (String) modeloTabla.getValueAt(filaSeleccionada, 4);
        Group grupo = (Group) modeloTabla.getValueAt(filaSeleccionada, 5);

        txtId.setText(String.valueOf(id));
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        txtTelefono.setText(telefono);
        txtEmail.setText(email);
        cbmGrupo.setSelectedItem(grupo);
    }
    /*
      Limpia todos los campos del formulario y quita la selección de la tabla.
    */
    private void limpiarCampos() {
        txtId.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        cbmGrupo.setSelectedItem(null);
        tblTablaContactos.clearSelection();
    }
    
}
