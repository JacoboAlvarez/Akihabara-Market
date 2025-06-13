package interfaz;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JTextField;

import dao.BDconnection;
import dao.ClienteDAO;
import model.ClienteOtaku;

public class Actualizar_PanelSelector_Clientes extends JPanel {

	private static final long serialVersionUID = 1L;
	private Interfaz_Principal_GUI principal;
	private JTextField campo_nombre;
	private JTextField campo_correo;
	private JTextField campo_telefono;
	private ClienteOtaku cliente;

	public void SetCliente(ClienteOtaku cliente) {
	    this.cliente = cliente;
	    if (cliente != null) {
	        try {
	            campo_nombre.setText(cliente.getNombre() != null ? cliente.getNombre() : "");
	            campo_correo.setText(cliente.getEmail() != null ? cliente.getEmail() : "");
	            campo_telefono.setText(cliente.getTelefono() != null ? String.valueOf(cliente.getTelefono()) : "");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    } else {
	        // Limpiar los campos si el cliente es null
	        campo_nombre.setText("");
	        campo_correo.setText("");
	        campo_telefono.setText("");
	    }
	}


	/**
	 * Create the panel.
	 */
	public Actualizar_PanelSelector_Clientes(Interfaz_Principal_GUI Interfaz_Principal_GUI) {
		this.principal = Interfaz_Principal_GUI;
		setLayout(null);

		JButton boton_salir = new JButton("Salir");
		boton_salir.setFont(new Font("Tahoma", Font.PLAIN, 11));
		boton_salir.setBounds(31, 24, 95, 23);
		add(boton_salir);

		JButton boton_volver = new JButton("Volver");
		boton_volver.setBounds(158, 24, 105, 23);
		add(boton_volver);

		JLabel lblNewLabel = new JLabel("ACTUALIZAR DATOS CLIENTE");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 27));
		lblNewLabel.setBounds(378, 20, 437, 47);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre: ");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_1.setBounds(407, 106, 165, 24);
		add(lblNewLabel_1);

		campo_nombre = new JTextField();
		campo_nombre.setBounds(407, 139, 198, 29);
		add(campo_nombre);
		campo_nombre.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Correo: ");
		lblNewLabel_1_1.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(407, 188, 165, 24);
		add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("Telefono:");
		lblNewLabel_1_2.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(407, 270, 165, 24);
		add(lblNewLabel_1_2);

		campo_correo = new JTextField();
		campo_correo.setColumns(10);
		campo_correo.setBounds(407, 223, 198, 29);
		add(campo_correo);

		campo_telefono = new JTextField();
		campo_telefono.setColumns(10);
		campo_telefono.setBounds(407, 305, 198, 29);
		add(campo_telefono);

		JButton boton_actualizar = new JButton("Actualizar");
		boton_actualizar.setBounds(436, 377, 151, 47);
		add(boton_actualizar);

		JLabel aviso = new JLabel("");
		aviso.setBounds(57, 210, 276, 52);
		add(aviso);

		boton_actualizar.addActionListener(e -> {
			Connection con1;
			try {
				con1 = BDconnection.getConexion();
				String nombrecambiar = campo_nombre.getText();
				String correocambiar = campo_correo.getText();
				String telefonocambiar = (campo_telefono.getText());
				PreparedStatement pst = null;

				String query1 = "UPDATE clientes SET nombre = ? , email = ?, telefono = ? WHERE id = ?";
				pst = con1.prepareStatement(query1);
				pst.setString(1, nombrecambiar);
				pst.setString(2, correocambiar);
				pst.setString(3, telefonocambiar);
				pst.setInt(4, cliente.getId());

				int filasafectadas = pst.executeUpdate();

				if (filasafectadas > 0) {
					aviso.setText("Se ha actualizado correctamente");
				} else {
					aviso.setText("No se ha podido actualizar ningún atributo");
				}

			} catch (Exception e1) {
				e1.printStackTrace();
				aviso.setText("Error al actualizar: " + e1.getMessage());
			}
		});

		boton_volver.addActionListener(e -> {
			principal.mostrarPanel("actualizar_clientes");
		});

		boton_salir.addActionListener(e -> {
			principal.mostrarPanel("menu");
		});
	}
}