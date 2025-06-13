package interfaz;

import java.awt.Font;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.BDconnection;

public class Mostrar_Clientes_GUI extends JPanel {

	private static final long serialVersionUID = 1L;
	    private Interfaz_Principal_GUI principal;
	    private JTable table;
	    private ClienteTableModel tableModel;
	    private JTextField textField;
	/**
	 * Create the panel.
	 */
	public Mostrar_Clientes_GUI(Interfaz_Principal_GUI GUI) {
    	this.principal = GUI;
        setBounds(100, 100, 1050, 490);
        setLayout(null);

        JButton botonvolver = new JButton("Volver");
        botonvolver.setBounds(38, 29, 89, 23);
        add(botonvolver);

        JLabel lblNewLabel = new JLabel("MOSTRAR CLIENTES");
        lblNewLabel.setFont(new Font("Yu Gothic", Font.BOLD, 17));
        lblNewLabel.setBounds(395, 27, 220, 25);
        add(lblNewLabel);

        tableModel = new ClienteTableModel();
        table = new JTable(tableModel);
        table.setBounds(38, 80, 974, 350);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(38, 80, 974, 350);
        add(scrollPane);
        
        JButton boton_actualizar = new JButton("Actualizar");
        boton_actualizar.setBounds(186, 29, 116, 23);
        add(boton_actualizar);
        
        boton_actualizar.addActionListener(e->{
        	try (Connection connection = BDconnection.getConexion()) {
                tableModel.loadData(connection);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        });
        
        JButton boton_buscar = new JButton("Buscar");
        boton_buscar.setBounds(923, 29, 89, 23);
        add(boton_buscar);
        
        textField = new JTextField();
        textField.setBounds(827, 30, 86, 20);
        add(textField);
        textField.setColumns(10);
        
        boton_buscar.addActionListener(e->{
            try (Connection connection = BDconnection.getConexion()) {
            	int id =Integer.parseInt(textField.getText()); 
                tableModel.loadData2(connection,id);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        });
        
 
        
        JLabel lblNewLabel_1 = new JLabel("Buscar por ID:");
        lblNewLabel_1.setBounds(742, 30, 76, 20);
        add(lblNewLabel_1);

        botonvolver.addActionListener(e ->{
            principal.mostrarPanel("menu");
        });

        // Cargar datos de la base de datos
        try (Connection connection = BDconnection.getConexion()) {
            tableModel.loadData(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
			
	}
