package com.movi.loginobjetoutputstream.modelo;

import android.net.Uri;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int dni;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;
    private String imagen;

    public Usuario() {
    }

    public Usuario(String apellido, String nombre, int dni, String email, String contrasena,String imagen) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.contrasena = contrasena;
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "apellido='" + apellido + '\'' +
                ", dni=" + dni +
                ", nombre='" + nombre + '\'' +
                ", correo='" + email + '\'' +
                ", contraseña='" + contrasena + '\'' +
                '}';
    }
}
