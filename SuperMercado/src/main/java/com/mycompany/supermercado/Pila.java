package com.mycompany.supermercado;

public class Pila {

    private class Nodo {
        Producto dato;
        Nodo siguiente;

        Nodo(Producto dato) {
            this.dato = dato;
        }
    }

    private Nodo cima;
    private int contador;

    public Pila() {
        cima = null;
        contador = 0;
    }

    // PUSH: agregar un producto arriba de la pila
    public void push(Producto p) {
        Nodo nuevo = new Nodo(p);
        nuevo.siguiente = cima;
        cima = nuevo;
        contador++;
    }

    // POP: sacar el producto de arriba
    public Producto pop() {
        if (estaVacia()) {
            return null;
        }
        Producto p = cima.dato;
        cima = cima.siguiente;
        contador--;
        return p;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    // Contar número de productos
    public int contar() {
        return contador;
    }
}