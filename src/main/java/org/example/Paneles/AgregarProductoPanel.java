package org.example.Paneles;

import org.example.Dao.ProductoDao;
import org.example.Modelo.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class AgregarProductoPanel extends JPanel {
    private JTextField txtCantidad;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextArea txtResultado;

    public AgregarProductoPanel() {
        setLayout(new GridLayout(6, 2, 5, 5));

        // Campos para agregar productos
        JLabel lblCantidad = new JLabel("Cantidad:");
        txtCantidad = new JTextField();
        add(lblCantidad);
        add(txtCantidad);

        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        add(lblNombre);
        add(txtNombre);

        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField();
        add(lblPrecio);
        add(txtPrecio);

        JButton btnAgregar = new JButton("Agregar Producto");
        add(btnAgregar);

        txtResultado = new JTextArea(3, 20);
        txtResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        add(new JLabel("Resultado:"));
        add(scrollPane);

        // ActionListener para el botón Agregar
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });
    }

    private void agregarProducto() {
        String cantidadStr = txtCantidad.getText();
        String nombre = txtNombre.getText();
        String precioStr = txtPrecio.getText();

        if (cantidadStr.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
            txtResultado.setText("Por favor, complete todos los campos para agregar un producto.");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
        } catch (NumberFormatException e) {
            txtResultado.setText("Por favor, ingrese un número válido para la cantidad.");
            return;
        }

        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            txtResultado.setText("Por favor, ingrese un número válido para el precio.");
            return;
        }

        ProductoDao productoDAO = new ProductoDao();
        Producto producto = new Producto(0, cantidad, nombre, precio);
        productoDAO.agregarProducto(producto);

        txtResultado.setText("Producto agregado exitosamente.");

    }
}
