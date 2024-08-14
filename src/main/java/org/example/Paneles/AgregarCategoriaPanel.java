package org.example.Paneles;

import org.example.Dao.CategoriaDao;
import org.example.Dao.ProveedorDao;
import org.example.Modelo.Categoria;
import org.example.Modelo.Proveedor;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AgregarCategoriaPanel extends JPanel {
    private JTextField txtNombre;
    private JComboBox<Categoria> cmbCategoria;
    private AgregarProductoPanel agregarProductoPanel;
    public AgregarCategoriaPanel(AgregarProductoPanel agregarProductoPanel) {
        this.agregarProductoPanel = agregarProductoPanel;
        setLayout(new GridLayout(2, 2, 5, 5));

        JLabel lblNombre = new JLabel("Nombre: ");
        txtNombre = new JTextField();
        add(lblNombre);
        add(txtNombre);

        JButton btnAgregar = new JButton("Agregar Categoria");
        add(btnAgregar);
        btnAgregar.addActionListener(e -> {
            try {
                agregarCategoria();
                cargarCategoria();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void agregarCategoria(){
        String nombre = txtNombre.getText();
        if (nombre.isEmpty()){
            JOptionPane.showMessageDialog(this,"El nombre no puede estar vacío");
        }
        CategoriaDao categoriaDao = new CategoriaDao();
        categoriaDao.agregarCategoria(new Categoria(0L, nombre));
        JOptionPane.showMessageDialog(this, "Categoria agregada exitosamente");
        limpiarCampos();
    }

    public void limpiarCampos(){
        txtNombre.setText("");
    }

    private void cargarCategoria(){
        CategoriaDao categoriaDao = new CategoriaDao();
        java.util.List<Categoria> categorias = categoriaDao.obtenerTodasCategorias();
        for (Categoria categoria : categorias) {
            cmbCategoria.addItem(categoria);
        }
    }

}
