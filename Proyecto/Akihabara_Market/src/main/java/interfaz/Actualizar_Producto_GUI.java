package interfaz;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import dao.ProductoDAO;
import model.ProductoOtaku;

public class Actualizar_Producto_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private Interfaz_Principal_GUI principal;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Actualizar_Producto_GUI(Interfaz_Principal_GUI Interfaz_Principal_GUI) {
		setLayout(null);
		
		this.principal = Interfaz_Principal_GUI;
		
		JLabel lblNewLabel = new JLabel("Actualizar Producto");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 26));
		lblNewLabel.setBounds(370, 31, 265, 47);
		add(lblNewLabel);
		
		JButton boton_volver = new JButton("Volver");
		boton_volver.setBounds(10, 23, 89, 23);
		add(boton_volver);
		
		JLabel lblNewLabel_1 = new JLabel("Dime la ID del producto a cambiar: ");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 18));
		lblNewLabel_1.setBounds(341, 126, 322, 41);
		add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(397, 188, 186, 41);
		add(textField);
		textField.setColumns(10);
		
		JButton boton_busqueda = new JButton("Buscar");
		boton_busqueda.setBounds(447, 257, 89, 23);
		add(boton_busqueda);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_2.setBounds(393, 349, 242, 63);
		add(lblNewLabel_2);
		
		boton_volver.addActionListener(e ->{
			principal.mostrarPanel("menu");
		});
		
		boton_busqueda.addActionListener(e -> {
		    ProductoDAO main = new ProductoDAO();
		    int id = Integer.parseInt(textField.getText());
		    ProductoOtaku producto = main.MostrarProductoporID(id);
		    if (producto == null) {
		        lblNewLabel_2.setText("No existe un producto con esa ID");
		    } else {
		        // Pasar el producto al nuevo panel usando el método con datos
		        principal.mostrarPanelConDatos("actualizar2", producto);
		    }
		});

		
	}

}
