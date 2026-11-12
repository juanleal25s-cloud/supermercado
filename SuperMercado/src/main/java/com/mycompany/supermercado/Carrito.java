package com.mycompany.supermercado;

public class Carrito {

    private final Pila productos;

    public Carrito() {
        productos = new Pila();
    }

    public void agregarProducto(Producto p) {
        productos.push(p);
    }

    public Producto sacarProducto() {
        return productos.pop();
    }

    public int contarProductos() {
        return productos.contar();
    }

    public boolean estaVacio() {
        return productos.estaVacia();
    }
}