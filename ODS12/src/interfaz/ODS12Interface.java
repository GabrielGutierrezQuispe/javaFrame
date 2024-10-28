package interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import ods12.ODS12;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ODS12Interface extends JFrame {

    private JTextField txtNombre, txtApellido, txtCedula, txtTelefono, txtEmail, txtSolicitud, txtEstado;
    private JTable tableConsultas;
    private DefaultTableModel tableModel;
    private ODS12 ods12;
    private JButton btnConfirmarModificacion; // Botón para confirmar la modificación
    private int selectedId = -1; // Almacena el ID seleccionado para modificar

    public ODS12Interface() {
        ods12 = new ODS12(); // Instancia de la clase de operaciones CRUD en la base de datos
        initComponents();
        listarConsultas(); // Inicializar la tabla con datos de la base de datos
    }

    private void initComponents() {
        setTitle("Gestión de Consultas - ODS12");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Título de la sección
        JLabel lblTitulo = new JLabel("Agregar datos de consulta", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Verdana", Font.BOLD, 18));

        // Etiquetas y campos de texto
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblApellido = new JLabel("Apellido:");
        JLabel lblCedula = new JLabel("Cédula:");
        JLabel lblTelefono = new JLabel("Teléfono:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblSolicitud = new JLabel("Solicitud:");
        JLabel lblEstado = new JLabel("Estado:");

        txtNombre = new JTextField();
        txtApellido = new JTextField();
        txtCedula = new JTextField();
        txtTelefono = new JTextField();
        txtEmail = new JTextField();
        txtSolicitud = new JTextField();
        txtEstado = new JTextField();

        // Botones
        JButton btnRegistrar = new JButton("Registrar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnListar = new JButton("Listar");
        btnConfirmarModificacion = new JButton("Confirmar Modificación");
        btnConfirmarModificacion.setVisible(false); // Inicialmente oculto

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new GridLayout(8, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.add(lblNombre);
        inputPanel.add(txtNombre);
        inputPanel.add(lblApellido);
        inputPanel.add(txtApellido);
        inputPanel.add(lblCedula);
        inputPanel.add(txtCedula);
        inputPanel.add(lblTelefono);
        inputPanel.add(txtTelefono);
        inputPanel.add(lblEmail);
        inputPanel.add(txtEmail);
        inputPanel.add(lblSolicitud);
        inputPanel.add(txtSolicitud);
        inputPanel.add(lblEstado);
        inputPanel.add(txtEstado);

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnRegistrar);
        buttonPanel.add(btnEliminar);
        buttonPanel.add(btnModificar);
        buttonPanel.add(btnListar);
        buttonPanel.add(btnConfirmarModificacion);

        // Configuración de la tabla
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Apellido", "Cédula", "Teléfono", "Email", "Solicitud", "Estado"}, 0);
        tableConsultas = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableConsultas);

        // Layout principal
        setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(lblTitulo, BorderLayout.NORTH);
        leftPanel.add(inputPanel, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);

        // Acciones de los botones
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarConsulta();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarConsulta();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prepararModificacion();
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarConsultas();
            }
        });

        btnConfirmarModificacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmarModificacion();
            }
        });
    }

    private void registrarConsulta() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String cedula = txtCedula.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        String solicitud = txtSolicitud.getText();
        String estado = txtEstado.getText();

        boolean isInserted = ods12.insertarConsulta(nombre, apellido, cedula, email, solicitud, estado);
        if (isInserted) {
            JOptionPane.showMessageDialog(this, "Consulta insertada exitosamente.");
            listarConsultas();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al insertar la consulta.");
        }
    }

    private void eliminarConsulta() {
        int selectedRow = tableConsultas.getSelectedRow();
        if (selectedRow != -1) {
            int id = Integer.parseInt(tableConsultas.getValueAt(selectedRow, 0).toString());
            boolean isDeleted = ods12.eliminarConsulta(id);
            if (isDeleted) {
                JOptionPane.showMessageDialog(this, "Consulta eliminada exitosamente.");
                listarConsultas();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la consulta.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.");
        }
    }

    private void prepararModificacion() {
        int selectedRow = tableConsultas.getSelectedRow();
        if (selectedRow != -1) {
            selectedId = Integer.parseInt(tableConsultas.getValueAt(selectedRow, 0).toString());
            txtNombre.setText(tableConsultas.getValueAt(selectedRow, 1).toString());
            txtApellido.setText(tableConsultas.getValueAt(selectedRow, 2).toString());
            txtCedula.setText(tableConsultas.getValueAt(selectedRow, 3).toString());
            txtTelefono.setText(tableConsultas.getValueAt(selectedRow, 4).toString());
            txtEmail.setText(tableConsultas.getValueAt(selectedRow, 5).toString());
            txtSolicitud.setText(tableConsultas.getValueAt(selectedRow, 6).toString());
            txtEstado.setText(tableConsultas.getValueAt(selectedRow, 7).toString());
            
            btnConfirmarModificacion.setVisible(true); // Mostrar el botón de confirmación
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una fila para modificar.");
        }
    }

    private void confirmarModificacion() {
    String nombre = txtNombre.getText();
    String apellido = txtApellido.getText();
    String cedula = txtCedula.getText();
    String telefono = txtTelefono.getText();
    String email = txtEmail.getText();
    String solicitud = txtSolicitud.getText();
    String estado = txtEstado.getText();

    // Verificar que el campo 'number' no exceda los 9 caracteres
    if (cedula.length() > 9) {
        JOptionPane.showMessageDialog(this, "El campo 'Cédula' no puede tener más de 9 caracteres.");
        return; // Salir sin intentar actualizar si el valor es demasiado largo
    }

    boolean isUpdated = ods12.actualizarConsulta(selectedId, nombre, apellido, cedula, email, solicitud, estado);
    
    if (isUpdated) {
        JOptionPane.showMessageDialog(this, "Consulta modificada exitosamente.");
        listarConsultas();
        limpiarCampos(); // Limpia los campos después de confirmar la modificación
        btnConfirmarModificacion.setVisible(false); // Ocultar el botón de confirmación
        selectedId = -1; // Restablecer el ID seleccionado
    } else {
        JOptionPane.showMessageDialog(this, "Error al modificar la consulta.");
    }
}

    private void listarConsultas() {
        tableModel.setRowCount(0); // Limpiar la tabla
        List<String> consultas = ods12.obtenerConsulta();
        
        for (String consulta : consultas) {
            String[] data = consulta.split(", ");
            Object[] row = new Object[data.length];
            for (int i = 0; i < data.length; i++) {
                row[i] = data[i].split(": ")[1];
            }
            tableModel.addRow(row);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtApellido.setText("");
        txtCedula.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtSolicitud.setText("");
        txtEstado.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ODS12Interface().setVisible(true);
        });
    }
}
