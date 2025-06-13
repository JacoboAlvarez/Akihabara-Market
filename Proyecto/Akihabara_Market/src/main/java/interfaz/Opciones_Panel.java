package interfaz;

import javax.swing.JPanel;
import java.awt.Button;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Opciones_Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */

	// Creamos una interfaz privada para el funcionamiento
	private Interfaz_Principal_GUI principal;
	private JButton boton_mostrar_clientes;

	// HABER PEDAZO DE BOBO QUE NO SE TE OLVIDE QUE TINES QUE INICIALIZAR EL
	// PRINCIPAL OSINO CORTATE LAS VENAS PORQUE NO VA A TIRAR

	public Opciones_Panel(Interfaz_Principal_GUI interfaz_Principal_GUI) {
		setLayout(null);
		this.principal = interfaz_Principal_GUI;

		JButton botonMostrar = new JButton("Mostrar Productos");
		botonMostrar.setBounds(122, 31, 169, 23);
		add(botonMostrar);

		botonMostrar.addActionListener(e -> {
			principal.mostrarPanel("mostrar");
		});

		JButton botonAgregar = new JButton("Agregar Productos");
		botonAgregar.setBounds(301, 31, 157, 23);
		add(botonAgregar);

		botonAgregar.addActionListener(e -> {
			principal.mostrarPanel("agregarProducto");
		});

		JButton boton_ir_eliminar = new JButton("Eliminar");
		boton_ir_eliminar.setBounds(610, 31, 136, 23);
		add(boton_ir_eliminar);

		boton_ir_eliminar.addActionListener(e -> {
			principal.mostrarPanel("eliminarProducto");
		});

		JButton boton_actualizar = new JButton("Actualizar");
		boton_actualizar.setBounds(468, 31, 132, 23);
		add(boton_actualizar);

		boton_actualizar.addActionListener(e -> {
			principal.mostrarPanel("actualizar1");
		});

		JButton boton_ia = new JButton("Ayudar I.A");
		boton_ia.setBounds(756, 31, 142, 23);
		add(boton_ia);
		
		boton_ia.addActionListener(e->{
			principal.mostrarPanel("ayudar_ia");
		});
		
		JButton boton_agregar_cliente = new JButton("Agregar Cliente");
		boton_agregar_cliente.setBounds(301, 65, 157, 23);
		add(boton_agregar_cliente);
		
		boton_agregar_cliente.addActionListener(e->{
			principal.mostrarPanel("agregar_cliente");
		});
		
		JButton boton_mostrar_clientes = new JButton("Mostrar Clientes");
		boton_mostrar_clientes.setBounds(122, 65, 169, 23);
		add(boton_mostrar_clientes);
		
		boton_mostrar_clientes.addActionListener(e->{
			principal.mostrarPanel("mostrar_clientes");
		});
		
		JButton boton_actualizar_cliente = new JButton("Actualizar Cliente");
		boton_actualizar_cliente.setBounds(468, 65, 132, 23);
		add(boton_actualizar_cliente);
		
		boton_actualizar_cliente.addActionListener(e->{
			principal.mostrarPanel("actualizar_clientes");
		});
		
		JButton boton_eliminar_cliente = new JButton("Eliminar Cliente");
		boton_eliminar_cliente.setBounds(610, 65, 136, 23);
		add(boton_eliminar_cliente);
		
		boton_eliminar_cliente.addActionListener(e->{
			principal.mostrarPanel("eliminar_cliente");
		});
		

		
		JLabel titulo = new JLabel("");
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("Imagenes/titulo.png").getImage().getScaledInstance(700, 400, Image.SCALE_DEFAULT));
		titulo.setIcon(imageIcon);
		titulo.setBounds(133, 132, 799, 312);
		add(titulo);
	}
}
