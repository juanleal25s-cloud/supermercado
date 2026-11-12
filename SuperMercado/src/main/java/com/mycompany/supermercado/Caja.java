/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supermercado;

/**
 *
 * @author dorad
 */
public class Caja {

    private int numero;
    private String nombreCajero;
    private double recaudacion;
    private Cola carritos;

    public Caja(int numero, String nombreCajero) {
        this.numero = numero;
        this.nombreCajero = nombreCajero;
        this.recaudacion = 0;
        this.carritos = new Cola();
    }

    public int getNumero() { return numero; }
    public String getNombreCajero() { return nombreCajero; }
    public double getRecaudacion() { return recaudacion; }

    // Formar el carrito en esta caja (encolar)
    public void formarCarrito(Carrito c) {
        carritos.encolar(c);
    }

    // Cobrar: atiende al primer carrito y saca sus productos de arriba hacia abajo.
    // Guarda el detalle en el StringBuilder para mostrarlo en la ventana.
    // Devuelve -1 si no hay carritos en la cola.
    public double cobrar(StringBuilder detalle) {
        Carrito c = carritos.desencolar();
        if (c == null) {
            return -1;
        }

        double total = 0;
        while (!c.estaVacio()) {
            Producto p = c.sacarProducto();
            detalle.append(p.getNombre()).append(" - $").append(p.getPrecio()).append("\n");
            total += p.getPrecio();
        }
        recaudacion += total;
        return total;
    }

    // Saber cuántos carritos hay formados en esta caja
    public int cuantosCarritos() {
        return carritos.contar();
    }

}
