package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.ProductoDAO;
import model.ProductoOtaku;

import java.awt.Font;

public class Agregar_Producto_GUI extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	private Interfaz_Principal_GUI principal;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	
	
	//HABER PEDAZO DE BOBO QUE NO SE TE OLVIDE QUE TINES QUE INICIALIZAR EL PRINCIPAL OSINO CORTATE LAS VENAS PORQUE NO VA A TIRAR
	public Agregar_Producto_GUI(Interfaz_Principal_GUI interfaz_Principal_GUI) {
		setLayout(null);
		this.principal = interfaz_Principal_GUI;
		JButton btnNewButton = new JButton("Volver");
		btnNewButton.setBounds(10, 11, 89, 23);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Nombre de Producto:");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		lblNewLabel.setBounds(431, 99, 145, 23);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(431, 119, 189, 30);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Categoria:");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		lblNewLabel_1.setBounds(431, 164, 102, 23);
		add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(431, 184, 188, 29);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		lblNewLabel_2.setBounds(431, 229, 89, 23);
		add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(431, 251, 145, 30);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Stock:");
		lblNewLabel_3.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		lblNewLabel_3.setBounds(431, 293, 46, 14);
		add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(431, 318, 145, 30);
		add(textField_3);
		textField_3.setColumns(10);
		
		JButton boton_agregarProducto = new JButton("Agregar");
		boton_agregarProducto.setBounds(236, 400, 89, 23);
		add(boton_agregarProducto);
		
		JButton btnNewButton_2 = new JButton("Limpiar");
		btnNewButton_2.setBounds(716, 400, 89, 23);
		add(btnNewButton_2);
		
		JLabel lblNewLabel_4 = new JLabel("  AGREGAR PRODUCTO");
		lblNewLabel_4.setFont(new Font("Yu Gothic Medium", Font.BOLD, 24));
		lblNewLabel_4.setBounds(362, 21, 304, 46);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_5.setBounds(65, 192, 249, 47);
		add(lblNewLabel_5);

		btnNewButton.addActionListener(e ->{
		principal.mostrarPanel("menu");
		}); 
		
		
		
		
		
		boton_agregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Recuperar datos del formulario
				String nombre = textField.getText();
				String categoria = textField_1.getText();
				double precio = Double.parseDouble(textField_2.getText());
				int stock = Integer.parseInt(textField_3.getText());

				// Crear instancia de ProductoOtaku
				ProductoOtaku nuevoProducto = new ProductoOtaku(nombre, categoria, precio, stock);

				// Llamar al método agregarProducto
				ProductoDAO productoDAO = new ProductoDAO();
				productoDAO.agregarProducto(nuevoProducto);
				
				lblNewLabel_5.setText("Se ha añadido correctamente");
				
				
				// Limpiar los campos de texto
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				
			}
		});

		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Limpiar los campos de texto
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				
				lblNewLabel_5.setText("Formulario Vaciado");
			}
		});
	}
}
