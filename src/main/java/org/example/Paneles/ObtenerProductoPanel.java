package org.example.Paneles;

import org.example.Dao.ProductoDao;
import org.example.Modelo.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ObtenerProductoPanel extends JPanel {

    private JTextField txtId;
    private JTextField txtNombre;
    private JTextArea txtResultado;

    public ObtenerProductoPanel() {
        setLayout(new GridLayout(4, 2, 5, 5));

        // Campo para búsqueda por ID
        JLabel lblId = new JLabel("ID (solo para buscar):");
        txtId = new JTextField();
        add(lblId);
        add(txtId);

        // Campo para búsqueda por Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        add(lblNombre);
        add(txtNombre);

        JButton btnObtener = new JButton("Obtener Producto");
        add(btnObtener);

        txtResultado = new JTextArea(3, 20);
        txtResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        add(new JLabel("Resultado:"));
        add(scrollPane);

        // ActionListener para el botón Obtener
        btnObtener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                obtenerProducto();
            }
        });
    }

    private void obtenerProducto() {
        String idStr = txtId.getText();
        String nombre = txtNombre.getText();

        if (idStr.isEmpty() && nombre.isEmpty()) {
            txtResultado.setText("Por favor, ingrese un ID o un nombre para buscar un producto.");
            return;
        }

        ProductoDao productoDAO = new ProductoDao();
        Producto producto = null;

        if (!idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            producto = productoDAO.obtenerProducto(id);
        } else {
            producto = productoDAO.obtenerProductoPorNombre(nombre);
        }

        if (producto != null) {
            txtResultado.setText("Producto encontrado:\n" +
                    "ID: " + producto.getId() + "\n" +
                    "Cantidad: " + producto.getCantidad() + "\n" +
                    "Nombre: " + producto.getNombre() + "\n" +
                    "Precio: " + producto.getPrecio());
        } else {
            txtResultado.setText("No se encontró un producto con los datos proporcionados.");
        }
    }
}
