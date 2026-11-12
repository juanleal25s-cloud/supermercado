/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supermercado;

/**
 *
 * @author dorad
 */
public class Producto {

    private int codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    private int existencias;

    public Producto(int codigo, String nombre, String descripcion, double precio, int existencias) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.existencias = existencias;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public double getPrecio() { return precio; }
    public int getExistencias() { return existencias; }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }
}

