/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.supermercado;

/**
 *
 * @author dorad
 */
public class Cola {
    
    private class Nodo {
        Carrito dato;
        Nodo siguiente;

        Nodo(Carrito dato) {
            this.dato = dato;
        }
    }

    private Nodo frente;  // el primero de la fila
    private Nodo fin;     // el último de la fila
    private int contador;

    public Cola() {
        frente = null;
        fin = null;
        contador = 0;
    }

    // Agregar a la cola = formar el carrito
    public void encolar(Carrito c) {
        Nodo nuevo = new Nodo(c);
        if (estaVacia()) {
            frente = nuevo;          // si estaba vacía, es el primero
        } else {
            fin.siguiente = nuevo;   // el último actual apunta al nuevo
        }
        fin = nuevo;                 // el nuevo es ahora el último
        contador++;
    }

    // Eliminar de la cola = cobrar
    public Carrito desencolar() {
        if (estaVacia()) {
            return null;
        }
        Carrito c = frente.dato;     // guardamos el primero
        frente = frente.siguiente;   // el segundo pasa a ser el primero
        if (frente == null) {
            fin = null;              // si ya no queda nadie, fin también es null
        }
        contador--;
        return c;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    // Saber cuántos carritos tengo formados
    public int contar() {
        return contador;
    }

}
