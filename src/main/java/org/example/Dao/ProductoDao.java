package org.example.Dao;

import org.example.Modelo.Categoria;
import org.example.Modelo.Producto;
import org.example.Modelo.Proveedor;
import org.example.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {

    public void agregarProducto (Producto producto){
        String query = "INSERT INTO productos (nombre,descripcion,cantidad,precio,categoria_id,proveedor_id) VALUES (?,?,?,?,?,?)";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);)
        {
            pstm.setString(1,producto.getNombre());
            pstm.setString(2,producto.getDescripcion());
            pstm.setInt(3,producto.getCantidad());
            pstm.setDouble(4,producto.getPrecio());
            pstm.setLong(5,producto.getCategoria().getId());
            pstm.setLong(6,producto.getProveedor().getId());
            int arrowsAffected = pstm.executeUpdate();
            if(arrowsAffected ==0){
                throw  new SQLException("La creacion del producto fallo");
            }
            try(ResultSet generatedKey = pstm.getGeneratedKeys()){
                if(generatedKey.next()){
                    producto.setId(generatedKey.getLong(1));
                }else{
                    throw new SQLException("No se pudo obtener el ID del producto");
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Producto obtenerProducto(int id) {
        String query = "SELECT p.*, c.nombre as categoria_nombre, pr.nombre as proveedor_nombre, pr.contacto as proveedor_contacto " +
                "FROM productos p " +
                "LEFT JOIN categorias c ON p.categoria_id = c.id " +
                "LEFT JOIN proveedores pr ON p.proveedor_id = pr.id " +
                "WHERE p.id = ?";
        Producto producto = null;
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(query)
        ) {
            pstm.setInt(1, id);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = new Categoria(
                            rs.getLong("categoria_id"),
                            rs.getString("categoria_nombre")
                    );

                    Proveedor proveedor = new Proveedor(
                            rs.getLong("proveedor_id"),
                            rs.getString("proveedor_nombre"),
                            rs.getString("proveedor_contacto")
                    );

                    producto = new Producto(
                                                rs.getLong("id"),
                                                rs.getString("nombre"),
                                                rs.getString("descripcion"),
                                                rs.getDouble("precio"),
                                                rs.getInt("cantidad"),
                            proveedor,
                            categoria
                                        );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    public Producto obtenerProductoPorNombre(String nombre) {
        String query = "SELECT p.*, c.nombre as categoria_nombre, pr.nombre as proveedor_nombre, pr.contacto as proveedor_contacto " +
                "FROM productos p " +
                "LEFT JOIN categorias c ON p.categoria_id = c.id " +
                "LEFT JOIN proveedores pr ON p.proveedor_id = pr.id " +
                "WHERE p.nombre = ?";
        Producto producto = null;
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(query)
        ) {
            pstm.setString(1, nombre);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = new Categoria(
                            rs.getLong("categoria_id"),
                            rs.getString("categoria_nombre")
                    );

                    Proveedor proveedor = new Proveedor(
                            rs.getLong("proveedor_id"),
                            rs.getString("proveedor_nombre"),
                            rs.getString("proveedor_contacto")
                    );

                    producto = new Producto(
                            rs.getLong("id"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio"),
                            rs.getInt("cantidad"),
                            proveedor,
                            categoria
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    public void EliminarProductoPorId(int id){
        String query = "DELETE FROM productos WHERE id = ?";
        Producto producto = null;
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(query))
        {
            pstm.setInt(1,id);
            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void EliminarProductoPorNombre(String nombre){
        String query ="DELETE FROM productos WHERE nombre = ?";
        Producto producto = null;
        try(Connection conn = DatabaseConnection.getConnection();
            PreparedStatement pstm = conn.prepareStatement(query)
        ) {
            pstm.setString(1,nombre);
            pstm.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Producto>  MostrarTodosLosProductos(){
        String query = "SELECT * FROM productos";
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
