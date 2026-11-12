package com.mycompany.supermercado;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaSuperMercado extends JFrame {

    // ---------- DATOS ----------
    private Producto[] productos = new Producto[10];
    private Caja[] cajas = new Caja[3];
    private Carrito carritoActual = new Carrito();

    // ---------- COMPONENTES DE LA VISTA ----------
    private DefaultTableModel modeloProductos;
    private DefaultTableModel modeloCajas;
    private JTable tablaProductos;
    private JTable tablaCajas;
    private JComboBox<String> comboCaja;
    private JLabel lblCarrito;
    private JTextArea txtCobro;

    public VentanaSuperMercado() {
        precargarProductos();
        crearCajas();
        construirVentana();
        refrescarProductos();
        refrescarCajas();
    }

    // ---------- DATOS INICIALES ----------

    private void precargarProductos() {
        productos[0] = new Producto(1, "Leche", "Leche entera 1L", 25.0, 50);
        productos[1] = new Producto(2, "Pan", "Pan de caja", 40.0, 30);
        productos[2] = new Producto(3, "Huevo", "Cartón de 12", 55.0, 40);
        productos[3] = new Producto(4, "Arroz", "Bolsa 1kg", 30.0, 60);
        productos[4] = new Producto(5, "Frijol", "Bolsa 1kg", 35.0, 60);
        productos[5] = new Producto(6, "Aceite", "Botella 1L", 45.0, 25);
        productos[6] = new Producto(7, "Azúcar", "Bolsa 1kg", 28.0, 45);
        productos[7] = new Producto(8, "Café", "Frasco 200g", 90.0, 20);
        productos[8] = new Producto(9, "Jabón", "Barra", 15.0, 70);
        productos[9] = new Producto(10, "Refresco", "Botella 2L", 32.0, 80);
    }

    private void crearCajas() {
        cajas[0] = new Caja(1, "Ana");
        cajas[1] = new Caja(2, "Luis");
        cajas[2] = new Caja(3, "María");
    }

    // ---------- VISTA ----------

    private void construirVentana() {
        setTitle("SuperMercado - Pilas y Colas");
        setSize(800, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

        // --- Tabla de productos (arriba) ---
        String[] colProductos = {"Código", "Nombre", "Descripción", "Precio", "Existencias"};
        modeloProductos = new DefaultTableModel(colProductos, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaProductos = new JTable(modeloProductos);
        JScrollPane scrollProductos = new JScrollPane(tablaProductos);
        scrollProductos.setBorder(BorderFactory.createTitledBorder("Productos"));
        scrollProductos.setPreferredSize(new Dimension(780, 220));
        add(scrollProductos, BorderLayout.NORTH);

        // --- Controles (centro) ---
        JPanel panelControles = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelControles.setBorder(BorderFactory.createTitledBorder("Carrito y caja"));

        JButton btnAgregar = new JButton("Agregar producto al carrito");
        lblCarrito = new JLabel("Productos en el carrito: 0");
        comboCaja = new JComboBox<>(new String[]{"Caja 1", "Caja 2", "Caja 3"});
        JButton btnFormar = new JButton("Formar carrito (encolar)");
        JButton btnCobrar = new JButton("Cobrar (desencolar)");

        panelControles.add(btnAgregar);
        panelControles.add(lblCarrito);
        panelControles.add(comboCaja);
        panelControles.add(btnFormar);
        panelControles.add(btnCobrar);

        txtCobro = new JTextArea();
        txtCobro.setEditable(false);
        JScrollPane scrollCobro = new JScrollPane(txtCobro);
        scrollCobro.setBorder(BorderFactory.createTitledBorder("Cobro"));

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.add(panelControles, BorderLayout.NORTH);
        panelCentro.add(scrollCobro, BorderLayout.CENTER);
        add(panelCentro, BorderLayout.CENTER);

        // --- Tabla de cajas (abajo) ---
        String[] colCajas = {"Caja", "Cajero", "Carritos formados", "Recaudación"};
        modeloCajas = new DefaultTableModel(colCajas, 0) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        tablaCajas = new JTable(modeloCajas);
        JScrollPane scrollCajas = new JScrollPane(tablaCajas);
        scrollCajas.setBorder(BorderFactory.createTitledBorder("Cajas"));
        scrollCajas.setPreferredSize(new Dimension(780, 120));
        add(scrollCajas, BorderLayout.SOUTH);

        // --- Eventos de los botones ---
        btnAgregar.addActionListener(e -> agregarProducto());
        btnFormar.addActionListener(e -> formarCarrito());
        btnCobrar.addActionListener(e -> cobrar());
    }

    // ---------- REFRESCAR TABLAS ----------

    private void refrescarProductos() {
        modeloProductos.setRowCount(0);
        for (Producto p : productos) {
            modeloProductos.addRow(new Object[]{
                p.getCodigo(), p.getNombre(), p.getDescripcion(),
                p.getPrecio(), p.getExistencias()
            });
        }
    }

    private void refrescarCajas() {
        modeloCajas.setRowCount(0);
        for (Caja c : cajas) {
            modeloCajas.addRow(new Object[]{
                c.getNumero(), c.getNombreCajero(),
                c.cuantosCarritos(), c.getRecaudacion()
            });
        }
    }

    // ---------- ACCIONES ----------

    // Agrega un producto a la PILA del carrito actual (push)
    private void agregarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto de la tabla");
            return;
        }

        Producto p = productos[fila];
        if (p.getExistencias() <= 0) {
            JOptionPane.showMessageDialog(this, "Sin existencias");
            return;
        }

        carritoActual.agregarProducto(p);
        p.setExistencias(p.getExistencias() - 1);
        lblCarrito.setText("Productos en el carrito: " + carritoActual.contarProductos());
        refrescarProductos();
        tablaProductos.setRowSelectionInterval(fila, fila);
    }

    // Agrega el carrito a la COLA de la caja elegida (encolar)
    private void formarCarrito() {
        if (carritoActual.estaVacio()) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío");
            return;
        }

        int caja = comboCaja.getSelectedIndex();
        cajas[caja].formarCarrito(carritoActual);

        carritoActual = new Carrito();
        lblCarrito.setText("Productos en el carrito: 0");
        refrescarCajas();
    }

    // Elimina el primer carrito de la COLA de la caja elegida (desencolar)
    private void cobrar() {
        Caja caja = cajas[comboCaja.getSelectedIndex()];

        StringBuilder detalle = new StringBuilder();
        double total = caja.cobrar(detalle);

        if (total < 0) {
            JOptionPane.showMessageDialog(this, "No hay carritos en esta caja");
            return;
        }

        txtCobro.setText("Caja " + caja.getNumero() + " - Cajero: " + caja.getNombreCajero() + "\n"
                + "Productos cobrados (de arriba hacia abajo):\n"
                + detalle
                + "Total cobrado: $" + total + "\n"
                + "Recaudación acumulada: $" + caja.getRecaudacion());
        refrescarCajas();
    }
}