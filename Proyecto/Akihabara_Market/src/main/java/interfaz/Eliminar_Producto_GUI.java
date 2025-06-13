package interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import dao.ProductoDAO;
import model.ProductoOtaku;

import javax.swing.JTextArea;
import java.awt.Button;
import java.awt.Color;

public class Eliminar_Producto_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private Interfaz_Principal_GUI principal;
	/**
	 * Create the panel.
	 */
	public Eliminar_Producto_GUI(Interfaz_Principal_GUI interfaz_Principal_GUI) {
		setLayout(null);
		this.principal = interfaz_Principal_GUI;
		
		JLabel lblNewLabel = new JLabel("       ELIMINAR PRODUCTO");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 29));
		lblNewLabel.setBounds(294, 10, 426, 63);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dime la id: ");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_1.setBounds(395, 113, 92, 24);
		add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(497, 110, 114, 24);
		add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		textArea.setBounds(382, 268, 276, 79);
		textArea.setEditable(false); // No se puede editar
		textArea.setFocusable(false); // No se puede seleccionar
		add(textArea);
		
		JLabel lblNewLabel_2 = new JLabel("Este producto: ");
		lblNewLabel_2.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_2.setBounds(460, 239, 114, 24);
		add(lblNewLabel_2);
		
		Button boton_buscar = new Button("Buscar");
		boton_buscar.setBackground(new Color(192, 192, 192));
		boton_buscar.setBounds(441, 168, 110, 31);
		add(boton_buscar);
		
		Button boton_eliminar = new Button("ELIMINAR");
		boton_eliminar.setBackground(new Color(192, 192, 192));
		boton_eliminar.setBounds(441, 363, 140, 31);
		add(boton_eliminar);
		
		Button boton_volver = new Button("Volver");
		boton_volver.setBackground(new Color(192, 192, 192));
		boton_volver.setBounds(10, 10, 86, 31);
		add(boton_volver);
		

		boton_volver.addActionListener(e ->{
			principal.mostrarPanel("menu");
		});
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_3.setBounds(44, 213, 238, 41);
		lblNewLabel_3.setFocusable(false);
		add(lblNewLabel_3);
		
		boton_buscar.addActionListener(e->{
			ProductoDAO main = new ProductoDAO();
			int id = Integer.parseInt(textField.getText()); 
			ProductoOtaku producto = main.MostrarProductoporID(id);
			if(producto == null) {
				lblNewLabel_3.setText("No existe un producto con esa ID");
				textArea.setText("");
			}else {
				lblNewLabel_3.setText("");
				textArea.setText("Nombre: " + producto.getNombre());	
			}
		});

		
		boton_eliminar.addActionListener(e->{
			ProductoDAO main = new ProductoDAO();
			int id = Integer.parseInt(textField.getText());
			main.eliminarProductos(id);
			textArea.setText("");
			lblNewLabel_3.setText("Se ha eliminado correctamente");
		});
		

	}
}
