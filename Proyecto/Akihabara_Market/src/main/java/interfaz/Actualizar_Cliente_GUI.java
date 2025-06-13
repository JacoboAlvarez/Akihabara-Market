package interfaz;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import dao.ClienteDAO;
import dao.ProductoDAO;
import model.ClienteOtaku;
import model.ProductoOtaku;

public class Actualizar_Cliente_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private Interfaz_Principal_GUI principal;
	private JTextField textField;
	/**
	 * Create the panel.
	 */
	public Actualizar_Cliente_GUI(Interfaz_Principal_GUI Interfaz_Principal_GUI) {
		this.principal = Interfaz_Principal_GUI ;
		setLayout(null);
		
		JButton boton_volver = new JButton("Volver");
		boton_volver.setBounds(46, 23, 89, 23);
		add(boton_volver);
		
		JLabel lblNewLabel = new JLabel("ACTUALIZAR CLIENTEES");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 27));
		lblNewLabel.setBounds(348, 23, 378, 43);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dime la ID del Cliente: ");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 13));
		lblNewLabel_1.setBounds(425, 172, 176, 23);
		add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(425, 206, 176, 37);
		add(textField);
		textField.setColumns(10);
		
		JButton boton_buscar = new JButton("BUSCAR");
		boton_buscar.setBounds(425, 286, 176, 43);
		add(boton_buscar);
		
		JLabel aviso = new JLabel("");
		aviso.setBounds(91, 196, 267, 59);
		add(aviso);
		
		boton_volver.addActionListener(e->{
			principal.mostrarPanel("menu");
		});
		
		boton_buscar.addActionListener(e -> {
			try {
				ClienteDAO main = new ClienteDAO();
				int id = Integer.parseInt(textField.getText());
				ClienteOtaku cliente = main.MostrarClienteporID(id);
				if (cliente == null) {
					aviso.setText("No existe un cliente con esa ID");
				} else {
					// Pasar el cliente al nuevo panel usando el método con datos
					principal.mostrarPanelConDatos2("actualizar_clientes2", cliente);
				}
			} catch (NumberFormatException ex) {
				aviso.setText("Por favor, introduce un número válido para la ID");
			}
		});
	}
}
