package org.example.Modelo;

public class Proveedor {
    private Long id;
    private String nombre;
    private String Contacto;

    public Proveedor(Long id, String nombre, String contacto) {
        this.id = id;
        this.nombre = nombre;
        Contacto = contacto;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContacto() {
        return Contacto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContacto(String contacto) {
        Contacto = contacto;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
