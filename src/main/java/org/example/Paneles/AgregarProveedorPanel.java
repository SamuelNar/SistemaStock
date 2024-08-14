package org.example.Paneles;

import org.example.Dao.ProveedorDao;
import org.example.Modelo.Proveedor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AgregarProveedorPanel extends JPanel {
    private JTextField txtNombre;
    private JTextField txtContacto;
    private JComboBox<Proveedor> cmbProveedor;
    public AgregarProveedorPanel() {
        setLayout(new GridLayout(2, 2, 5, 5));
        JLabel lblNombre = new JLabel("Nombre");
        add(lblNombre);
        txtNombre = new JTextField();
        add(txtNombre);

        JLabel lblContacto = new JLabel("Contacto");
        add(lblContacto);
        txtContacto = new JTextField();
        add(txtContacto);

        JButton btnAgregar = new JButton("Agregar Proveedor");
        add(btnAgregar);
        btnAgregar.addActionListener(e -> {
            try {
                agregarProveedor();
                cargarProveedores();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void agregarProveedor() throws Exception {
        String nombre = txtNombre.getText();
        String contacto = txtContacto.getText();

        if (nombre.isEmpty() || contacto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos");
            return;
        }
        ProveedorDao proveedorDao = new ProveedorDao();
        proveedorDao.agregarProveedor(new Proveedor(0L, nombre, contacto));
        JOptionPane.showMessageDialog(this, "Proveedoredor agregado");
        limpiarCampos();
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtContacto.setText("");
    }

    private void cargarProveedores(){
        ProveedorDao proveedorDao = new ProveedorDao();
        List<Proveedor> proveedores = proveedorDao.obtenerTodosProveedores();
        for (Proveedor proveedor : proveedores) {
            cmbProveedor.addItem(proveedor);
        }
    }
}
