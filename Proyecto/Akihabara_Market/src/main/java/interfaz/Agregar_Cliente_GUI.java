package interfaz;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import dao.ClienteDAO;
import model.ClienteOtaku;

public class Agregar_Cliente_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private Interfaz_Principal_GUI principal;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	/**
	 * Create the panel.
	 */
	public Agregar_Cliente_GUI(Interfaz_Principal_GUI Interfaz_Principal_GUI) {
		setLayout(null);
		
		this.principal = Interfaz_Principal_GUI;
		
		JButton boton_volver = new JButton("Volver");
		boton_volver.setBounds(36, 26, 125, 33);
		add(boton_volver);
		
		JLabel lblNewLabel = new JLabel("AGREGAR CLIENTE");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 26));
		lblNewLabel.setBounds(401, 14, 268, 82);
		add(lblNewLabel);
		
		JButton boton_agregar = new JButton("Agregar");
		boton_agregar.setBounds(220, 409, 110, 29);
		add(boton_agregar);
		
		JButton boton_limpiar = new JButton("Limpiar");
		boton_limpiar.setBounds(713, 412, 110, 26);
		add(boton_limpiar);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1.setBounds(401, 107, 89, 38);
		add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(401, 152, 158, 33);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Correo: ");
		lblNewLabel_2.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_2.setBounds(401, 196, 89, 21);
		add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(401, 228, 158, 33);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Telefono: ");
		lblNewLabel_3.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_3.setBounds(401, 272, 110, 29);
		add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(401, 318, 158, 33);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel aviso = new JLabel("");
		aviso.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		aviso.setBounds(77, 237, 224, 33);
		add(aviso);
		
		boton_volver.addActionListener(e->{
			principal.mostrarPanel("menu");
		});
		
		boton_agregar.addActionListener(e->{
			String nombre = textField.getText();
			String correo = textField_1.getText();
			String telefono = textField_2.getText();
			
			ClienteDAO maincliente = new ClienteDAO();
			ClienteOtaku nuevo = new ClienteOtaku(nombre,correo,telefono);		
			maincliente.agregarCliente(nuevo);	
			
			aviso.setText("Se ha agregado correctamente");
			
		});
		
		boton_limpiar.addActionListener(e->{
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			

		});
	}
}
