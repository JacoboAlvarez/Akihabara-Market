package interfaz;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JTextField;

import dao.BDconnection;
import model.ProductoOtaku;

public class Actualizar_PanelSelector_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private Interfaz_Principal_GUI principal;
	private JTextField campo_nombre;
	private JTextField campo_categoria;
	private JTextField campo_precio;
	private JTextField campo_stock;

	private ProductoOtaku producto;

	/**
	 * Create the panel.
	 */

	public void setProducto(ProductoOtaku datos) {
		this.producto = datos;
		campo_nombre.setText(producto.getNombre());
		campo_categoria.setText(producto.getCategoria());
		campo_precio.setText(String.valueOf(producto.getPrecio()));
		campo_stock.setText(String.valueOf(producto.getStock()));
		
		
	}

	public Actualizar_PanelSelector_GUI(Interfaz_Principal_GUI Interfaz_Principal_GUI) {

		this.principal = Interfaz_Principal_GUI;
		setLayout(null);

		JButton boton_salir = new JButton("Salir");
		boton_salir.setBounds(42, 28, 89, 23);
		add(boton_salir);

		boton_salir.addActionListener(e -> {
			principal.mostrarPanel("menu");
		});

		JButton boton_volver = new JButton("Volver");
		boton_volver.setBounds(162, 28, 106, 23);
		add(boton_volver);

		JLabel lblNewLabel = new JLabel("ACTUALIZAR DATOS");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 31));
		lblNewLabel.setBounds(342, 24, 352, 51);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(374, 90, 106, 32);
		add(lblNewLabel_1);

		campo_nombre = new JTextField();
		campo_nombre.setBounds(374, 120, 217, 23);
		add(campo_nombre);
		campo_nombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Categoria: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(374, 154, 89, 23);
		add(lblNewLabel_2);

		campo_categoria = new JTextField();
		campo_categoria.setBounds(374, 188, 217, 23);
		add(campo_categoria);
		campo_categoria.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Precio: ");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(374, 222, 77, 23);
		add(lblNewLabel_3);

		campo_precio = new JTextField();
		campo_precio.setBounds(374, 254, 167, 24);
		add(campo_precio);
		campo_precio.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Stock: ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(378, 289, 78, 18);
		add(lblNewLabel_4);

		campo_stock = new JTextField();
		campo_stock.setBounds(374, 316, 167, 29);
		add(campo_stock);
		campo_stock.setColumns(10);
		
		JButton boton_actualizar = new JButton("Actualizar");
		boton_actualizar.setFont(new Font("Yu Gothic", Font.BOLD, 17));
		boton_actualizar.setBounds(374, 380, 236, 51);
		add(boton_actualizar);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_5.setBounds(42, 222, 266, 38);
		add(lblNewLabel_5);

		boton_volver.addActionListener(e -> {
			principal.mostrarPanel("actualizar1");
		});
		
		boton_actualizar.addActionListener(e->{
			Connection con1;
			try {
				con1 = BDconnection.getConexion();
				String nombrecambiar = campo_nombre.getText();
				String categoriacambiar = campo_categoria.getText();
				double preciocambiar = Double.parseDouble(campo_precio.getText());
				int stockcambiar = Integer.parseInt(campo_stock.getText());
				PreparedStatement pst = null;
				
				
				String query1 = "UPDATE productos SET nombre = ? , categoria = ?, precio = ?, stock = ?   WHERE id = ?";
				pst = con1.prepareStatement(query1);
				pst.setString(1,nombrecambiar);
				pst.setString(2, categoriacambiar);
				pst.setDouble(3, preciocambiar);
				pst.setInt(4, stockcambiar);
				pst.setInt(5, producto.getId());
				
				int filasafectadas = pst.executeUpdate();
				
				if(filasafectadas < 0) {
					lblNewLabel_5.setText("No se ha podido actualizar ningun atributo");
				}else {
					lblNewLabel_5.setText("Se ha actualizado correctamente");
				}
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		});
	}

}
