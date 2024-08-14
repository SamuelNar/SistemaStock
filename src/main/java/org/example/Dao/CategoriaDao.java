package org.example.Dao;

import org.example.Modelo.Categoria;
import org.example.Utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    public void agregarCategoria (Categoria categoria){
        String query = "INSERT INTO categorias (nombre) VALUES (?)";
        try (
                Connection conn = DatabaseConnection.getConnection();
                PreparedStatement pstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ) {
            pstm.setString(1, categoria.getNombre());
            int affectedRows = pstm.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("La creacion de categoria fallo");
            }
            try(ResultSet generatedKey = pstm.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    categoria.setId(generatedKey.getLong(1));
                } else {
                    throw new SQLException("No se pudo obtener el ID de la categoria");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Categoria> obtenerTodasCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String query = "SELECT * FROM categorias";
        try (
                Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)
        ) {
            while (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getLong("id"),
                        rs.getString("nombre")
                );
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
}
