package org.example.Paneles;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductoPanel extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelContenedor;

    public ProductoPanel(){
        setTitle("Gestión de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el layout principal y el panel de contenedor
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);

        // Crear instancias de los paneles
        AgregarProductoPanel agregarProductoPanel = new AgregarProductoPanel();
        AgregarCategoriaPanel agregarCategoriaPanel = new AgregarCategoriaPanel();
        ObtenerProductoPanel obtenerProductoPanel = new ObtenerProductoPanel();
        EliminarProductoPanel eliminarProductoPanel = new EliminarProductoPanel();
        MostrarProductoPanel mostrarProductoPanel = new MostrarProductoPanel();
        AgregarProveedorPanel agregarProveedorPanel = new AgregarProveedorPanel();
        // Añadir paneles al contenedor
        panelContenedor.add(agregarProductoPanel, "AgregarProducto");
        panelContenedor.add(obtenerProductoPanel, "ObtenerProducto");
        panelContenedor.add(eliminarProductoPanel,"EliminarProducto");
        panelContenedor.add(mostrarProductoPanel,"MostrarProductos");
        panelContenedor.add(agregarCategoriaPanel,"agregarCategoria");
        panelContenedor.add(agregarProveedorPanel,"agregarProveedor");
        // Crear el panel de navegación
        JPanel panelNavegacion = new JPanel();
        JButton btnAgregar = new JButton("Agregar Producto");
        JButton btnObtener = new JButton("Obtener Producto");
        JButton btnEliminar= new JButton("Eliminar Producto");
        JButton btnMostrar = new JButton("Mostrar Productos");
        JButton btnAgregarC = new JButton("Agregar Categoría");
        JButton btnAgregarP = new JButton("Agregar Proveedor");
        panelNavegacion.add(btnAgregar);
        panelNavegacion.add(btnObtener);
        panelNavegacion.add(btnEliminar);
        panelNavegacion.add(btnMostrar);
        panelNavegacion.add(btnAgregarC);
        panelNavegacion.add(btnAgregarP);
        // Acción para cambiar al panel de agregar producto
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "AgregarProducto");
            }
        });

        // Acción para cambiar al panel de obtener producto
        btnObtener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor, "ObtenerProducto");
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor,"EliminarProducto");
            }
        });

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor,"MostrarProductos");
            }
        });

        btnAgregarC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor,"agregarCategoria");
            }
        });

        btnAgregarP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(panelContenedor,"agregarProveedor");
            }
        });
        // Configurar la ventana principal
        add(panelNavegacion, BorderLayout.NORTH);
        add(panelContenedor, BorderLayout.CENTER);
    }
}
