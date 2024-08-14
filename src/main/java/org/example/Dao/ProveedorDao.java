package org.example.Dao;

import org.example.Modelo.Proveedor;
import org.example.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDao {
    public void agregarProveedor(Proveedor proveedor) {
        String query = "INSERT INTO proveedores (nombre, contacto) VALUES (?, ?)";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            pstm.setString(1, proveedor.getNombre());
            pstm.setString(2, proveedor.getContacto());

            int affectedRows = pstm.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La creación del proveedor falló, no se insertaron filas.");
            }

            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    proveedor.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("La creación del proveedor falló, no se obtuvo el ID.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Proveedor> obtenerTodosProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        String query = "SELECT * FROM proveedores";
        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                Proveedor proveedor = new Proveedor(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getString("contacto")
                );
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proveedores;
    }
}
