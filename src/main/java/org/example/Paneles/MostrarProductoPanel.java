package org.example.Paneles;

import org.example.Dao.ProductoDao;
import org.example.Modelo.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MostrarProductoPanel extends JPanel {
    private JTextArea txtResultado;

    public MostrarProductoPanel() {
        setLayout(new BorderLayout());

        JButton btnMostrarTodos = new JButton("Mostrar Todos los Productos");
        add(btnMostrarTodos, BorderLayout.NORTH);

        txtResultado = new JTextArea(20, 50);
        txtResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        add(scrollPane, BorderLayout.CENTER);

        btnMostrarTodos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTodosLosProductos();
            }
        });
    }

    private void mostrarTodosLosProductos() {
        ProductoDao productoDAO = new ProductoDao();
        List<Producto> productos = productoDAO.MostrarTodosLosProductos();

        if (productos.isEmpty()) {
            txtResultado.setText("No se encontraron productos.");
        } else {
            StringBuilder resultado = new StringBuilder("Lista de Productos:\n");
            for (Producto producto : productos) {
                resultado.append("ID: ").append(producto.getId()).append("\n")
                        .append("Cantidad: ").append(producto.getCantidad()).append("\n")
                        .append("Nombre: ").append(producto.getNombre()).append("\n")
                        .append("Precio: ").append(producto.getPrecio()).append("\n\n");
            }
            txtResultado.setText(resultado.toString());
        }
    }
}
