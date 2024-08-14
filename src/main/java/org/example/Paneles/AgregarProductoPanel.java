package org.example.Paneles;

import org.example.Dao.CategoriaDao;
import org.example.Dao.ProductoDao;
import org.example.Dao.ProveedorDao;
import org.example.Modelo.Categoria;
import org.example.Modelo.Producto;
import org.example.Modelo.Proveedor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AgregarProductoPanel extends JPanel {
    private JTextField txtNombre;
    private JTextField txtDescripcion;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JTextArea txtResultado;
    private JComboBox<Proveedor> cmbProveedor;
    private JComboBox<Categoria> cmbCategoria;

    public AgregarProductoPanel() {
        ProductoDao productoDao = new ProductoDao();
        CategoriaDao categoriaDao = new CategoriaDao();
        ProveedorDao proveedorDao = new ProveedorDao();

        setLayout(new GridLayout(8, 2, 5, 5));

        // Campos para agregar productos
        add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Descripcion"));
        txtDescripcion = new JTextField();
        add(txtDescripcion);

        add(new JLabel("Cantidad"));
        txtCantidad = new JTextField();
        add(txtCantidad);

        JLabel lblPrecio = new JLabel("Precio:");
        txtPrecio = new JTextField();
        add(lblPrecio);
        add(txtPrecio);

        add(new JLabel("Proveedor"));
        cmbProveedor = new JComboBox<>();
        add(cmbProveedor);

        add(new JLabel("Categoria"));
        cmbCategoria = new JComboBox<>();
        add(cmbCategoria);

        JButton btnAgregar = new JButton("Agregar Producto");
        add(btnAgregar);

        txtResultado = new JTextArea(3, 20);
        txtResultado.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        add(new JLabel("Resultado:"));
        add(scrollPane);

        // ActionListener para el botón Agregar
        btnAgregar.addActionListener(e -> agregarProducto());
        cargarProveedores();
        cargarCategoria();
    }


    private void cargarCategoria(){
        CategoriaDao categoriaDao = new CategoriaDao();
        List<Categoria> categorias = categoriaDao.obtenerTodasCategorias();
        for (Categoria categoria : categorias) {
            cmbCategoria.addItem(categoria);
        }
    }

    private void cargarProveedores(){
        ProveedorDao proveedorDao = new ProveedorDao();
        List<Proveedor> proveedores = proveedorDao.obtenerTodosProveedores();
        for (Proveedor proveedor : proveedores) {
            cmbProveedor.addItem(proveedor);
        }
    }

    public void recargar(){
        cargarProveedores();
        cargarCategoria();
    }
    private void agregarProducto() {
        String nombre = txtNombre.getText();
        String descripcion = txtDescripcion.getText();
        String precioStr = txtPrecio.getText();
        String cantidadStr = txtCantidad.getText();
        Categoria categoria = (Categoria) cmbCategoria.getSelectedItem();
        Proveedor proveedor = (Proveedor) cmbProveedor.getSelectedItem();
        ProductoDao productoDao = new ProductoDao();
        if (nombre.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty() || categoria == null || proveedor == null) {
            txtResultado.setText("Por favor, complete todos los campos para agregar un producto.");
            return;
        }
        double precio;
        try {
            precio = Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            txtResultado.setText("Por favor, ingrese un número válido para el precio.");
            return;
        }
        int cantidad = Integer.parseInt(cantidadStr);
        Producto producto = new Producto(0L,nombre,descripcion, precio, cantidad, proveedor, categoria);
        productoDao.agregarProducto(producto);
        txtResultado.setText("Producto agregado exitosamente.");
        limpiarCampos();
    }

    public void limpiarCampos(){
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        cmbCategoria.setSelectedIndex(0);
        cmbProveedor.setSelectedIndex(0);
        txtResultado.setText("");
    }
}
