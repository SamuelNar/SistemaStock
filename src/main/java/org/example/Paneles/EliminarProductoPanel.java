package org.example.Paneles;

import org.example.Dao.ProductoDao;
import org.example.Modelo.Producto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EliminarProductoPanel extends JPanel {
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextArea txtResultado;

    public EliminarProductoPanel(){
        setLayout(new GridLayout(6, 2, 5, 5));
        JLabel lblId = new JLabel("ID (solo para buscar):");
        txtId = new JTextField();
        add(lblId);
        add(txtId);

        // Campo para búsqueda por Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        txtNombre = new JTextField();
        add(lblNombre);
        add(txtNombre);

        JButton btnEliminar = new JButton("Eliminar Producto");
        add(btnEliminar);
        txtResultado = new JTextArea(3, 20);
        txtResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        add(new JLabel("Resultado:"));
        add(scrollPane);

        // ActionListener para el botón Obtener
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });
    }

    public void eliminarProducto(){
        String idStr = txtId.getText();
        String nombre = txtNombre.getText();

        if (idStr.isEmpty() && nombre.isEmpty()) {
            txtResultado.setText("Por favor, ingrese un ID o un nombre para eliminar un producto.");
            return;
        }

        ProductoDao productoDAO = new ProductoDao();
        Producto producto = null;

        if (!idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            productoDAO.EliminarProductoPorId(id);
        } else {
            productoDAO.EliminarProductoPorNombre(nombre);
        }
        txtResultado.setText("Producto eliminado exitosamente.");
    }
}
