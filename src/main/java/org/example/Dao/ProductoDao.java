package org.example.Dao;

import org.example.Modelo.Producto;
import org.example.Utils.DatabaseConnection;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDao {

    public void agregarProducto (Producto producto){
        String query = "INSERT INTO producto (nombre,cantidad,precio) VALUES (?,?,?)";
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(query);)
        {
            pstm.setString(1,producto.getNombre());
            pstm.setInt(2,producto.getCantidad());
            pstm.setDouble(3,producto.getPrecio());
            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Producto obtenerProducto(int id){
        String query = "SELECT * FROM producto WHERE id=?";
        Producto producto = null;
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(query))
        {
            pstm.setInt(1,id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                producto = new Producto(
                        rs.getInt("id"),
                        rs.getInt("cantidad"),
                        rs.getString("nombre"),
                        rs.getDouble("precio")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return producto;
    }

    public Producto obtenerProductoPorNombre(String nombre){
        String query = "SELECT * FROM producto WHERE nombre=?";
        Producto producto = null;
        try(
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(query))
        {
            pstm.setString(1,nombre);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()){
                producto = new Producto(
                        rs.getInt("id"),
                        rs.getInt("cantidad"),
                        rs.getString("nombre"),
                        rs.getDouble("precio")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return producto;
    }

    public void EliminarProductoPorId(int id){
        String query = "DELETE FROM producto WHERE id = ?";
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
        String query ="DELETE FROM producto WHERE nombre = ?";
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
        String query = "SELECT * FROM producto";
        List<Producto> productos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstm = conn.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id"),
                        rs.getInt("cantidad"),
                        rs.getString("nombre"),
                        rs.getDouble("precio")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}
