package interfaz;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.TextArea;

import javax.swing.JTextField;

import dao.ClienteDAO;
import model.ClienteOtaku;

public class Eliminar_Cliente_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	private Interfaz_Principal_GUI principal;
	private JTextField campo_id;
	/**
	 * Create the panel.
	 */
	public Eliminar_Cliente_GUI(Interfaz_Principal_GUI Interfaz_Principal_GUI) {
		setLayout(null);
		this.principal = Interfaz_Principal_GUI;
		
		JButton boton_volver = new JButton("Volver");
		boton_volver.setBounds(39, 29, 129, 32);
		add(boton_volver);
		
		JLabel lblNewLabel = new JLabel("ELIMINAR CLIENTE");
		lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 27));
		lblNewLabel.setBounds(383, 19, 272, 57);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Dime la ID del Cliente: ");
		lblNewLabel_1.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_1.setBounds(405, 87, 179, 38);
		add(lblNewLabel_1);
		
		campo_id = new JTextField();
		campo_id.setBounds(405, 146, 179, 25);
		add(campo_id);
		campo_id.setColumns(10);
		
		JButton boton_buscar = new JButton("Buscar");
		boton_buscar.setBounds(452, 182, 89, 23);
		add(boton_buscar);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setFont(new Font("Yu Gothic", Font.BOLD, 15));
		lblNewLabel_2.setBounds(405, 268, 212, 57);
		add(lblNewLabel_2);
		
		JButton boton_eliminar = new JButton("ELIMINAR");
		boton_eliminar.setBounds(428, 367, 138, 38);
		add(boton_eliminar);
		
		JLabel aviso = new JLabel("");
		aviso.setBounds(75, 209, 200, 32);
		add(aviso);
		
		boton_volver.addActionListener(e->{
			principal.mostrarPanel("menu");
		});
		
		boton_buscar.addActionListener(e->{
			ClienteDAO main = new ClienteDAO();
			int id = Integer.parseInt(campo_id.getText()); 
			ClienteOtaku cliente = main.MostrarClienteporID(id);
			if(cliente == null) {
				aviso.setText("No existe un cliente con esa ID");
				campo_id.setText("");
			}else {
				aviso.setText("");
				lblNewLabel_2.setText("Cliente: \n " + cliente.getNombre());
			}
		});
		
		boton_eliminar.addActionListener(e->{
			ClienteDAO main = new ClienteDAO();
			int id = Integer.parseInt(campo_id.getText()); 
			main.eliminarCliente(id);
			campo_id.setText("");
			aviso.setText("Se ha eliminado correctamente");
		});
	}

}
