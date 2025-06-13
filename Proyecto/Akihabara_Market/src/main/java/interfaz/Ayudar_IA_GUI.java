package interfaz;

import javax.swing.JPanel;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;

import dao.LlmService;
import model.ProductoOtaku;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.Font;

public class Ayudar_IA_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private Interfaz_Principal_GUI principal;
	private JTextField textField;
	private LlmService ia;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public Ayudar_IA_GUI(Interfaz_Principal_GUI Interfaz_Principal_GUI, LlmService ia) {
		this.ia = ia;
		this.principal = Interfaz_Principal_GUI;
		setLayout(null);

		JButton boton_volver = new JButton("Volver");
		boton_volver.setBounds(33, 24, 124, 23);
		add(boton_volver);

		JButton boton_enviar = new JButton("Enviar");
		boton_enviar.setBounds(800, 383, 106, 35);
		add(boton_enviar);

		textField = new JTextField();
		textField.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		textField.setBounds(101, 377, 645, 47);
		add(textField);
		textField.setColumns(10);

		textArea = new JTextArea();
		textArea.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 13));
		textArea.setBounds(91, 79, 713, 284);
		textArea.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK, 2));
		textArea.setEditable(false); // No se puede editar

		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(91, 79, 713, 284);
		add(scrollPane);

		String explicacion = "--------FUNCIONES--------- \n"
				+ "1-Generar Descripcion de Producto [Primero pon la id del Producto en el Chat] \n"
				+ "2-Sugerir una categoria para un Producto [Primero pon el nombre del producto en el chat] \n";

		textArea.setText(explicacion);
		JButton boton_generar_descripcion = new JButton("Generar");
		boton_generar_descripcion.setBounds(886, 54, 106, 29);
		add(boton_generar_descripcion);

		JButton boton_sugerir_categoria = new JButton("Generar");
		boton_sugerir_categoria.setBounds(886, 128, 106, 35);
		add(boton_sugerir_categoria);

		JLabel lblNewLabel = new JLabel("Generar Descripcion:");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		lblNewLabel.setBounds(886, 24, 137, 18);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Sugerir Categoria: ");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		lblNewLabel_1.setBounds(886, 94, 124, 23);
		add(lblNewLabel_1);

		boton_volver.addActionListener(e -> {
			principal.mostrarPanel("menu");
			textArea.setText("");
			textArea.setText(explicacion);
			textField.setText("");
		});

		boton_generar_descripcion.addActionListener(e -> {
			try {
				generarDescripcionProducto();
			} catch (Throwable e1) {
				e1.printStackTrace();
			}
		});

		boton_sugerir_categoria.addActionListener(e -> {
			sugerirCategoria();
		});

		boton_enviar.addActionListener(e -> {
			enviarPregunta();
		});
	}

	private void enviarPregunta() {
		String input = textField.getText().trim();
		if (input.isEmpty()) {
			textArea.append("[Sistema] La pregunta no puede estar vacía\n");
			return;
		}

		try {
			String respuesta = ia.enviarPregunta(input);
			textArea.append("Tú: " + input + "\n");
			textArea.append("IA: " + respuesta + "\n");
		} catch (IOException e) {
			textArea.append("ERROR en servicio IA: " + e.getMessage() + "\n");
		} catch (Exception e) {
			textArea.append("ERROR inesperado: " + e.getMessage() + "\n");
		}
		textField.setText("");
	}

	private void generarDescripcionProducto() throws Throwable {
		String input = textField.getText().trim();
		if (input.isEmpty()) {
			textArea.append("[Sistema] El nombre del producto no puede estar vacío\n");
			return;
		}

		try {
			int id = Integer.parseInt(input);
			Interfaz_Consola main = new Interfaz_Consola();
			ProductoOtaku producto = main.idCreador(id);
			if(producto == null) {
				textArea.append(" \n No existe un producto con la ID proporcionada \n");
			}else {
				textArea.append("Generando descripción para: " + producto.getNombre() + "\n");
				String descripcion = ia.generarDescripcionProducto(producto);
				textArea.append("\n--- DESCRIPCIÓN GENERADA ---\n");
				textArea.append(descripcion + "\n");
				textArea.append("----------------------------\n");	
			}
		} catch (Exception e) {
			textArea.append("Error generando descripción: " + e.getMessage() + "\n");
		}
	}

	private void sugerirCategoria() {
		String input = textField.getText().trim();
		if (input.isEmpty()) {
			textArea.append("[Sistema] El nombre del producto no puede estar vacío\n");
			return;
		}

		try {
			textArea.append("\n Sugeriendo categoría para: " + input + "\n");
			String categoria = ia.sugerirCategoria(input);
			textArea.append("\n--- CATEGORÍA SUGERIDA ---\n");
			textArea.append(categoria + "\n");
			textArea.append("----------------------------\n");
		} catch (Exception e) {
			textArea.append("Error sugeriendo categoría: " + e.getMessage() + "\n");
		}
	}

}