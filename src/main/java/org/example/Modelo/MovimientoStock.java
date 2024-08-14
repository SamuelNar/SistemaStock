package org.example.Modelo;

import java.time.LocalDateTime;

public class MovimientoStock {
    private Long id;
    private Producto producto;
    private int cantidad;
    private String tipo;
    private LocalDateTime fecha;
    private String comentario;

    public MovimientoStock(Long id, Producto producto, int cantidad, String tipo, LocalDateTime fecha, String comentario) {
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getComentario() {
        return comentario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
