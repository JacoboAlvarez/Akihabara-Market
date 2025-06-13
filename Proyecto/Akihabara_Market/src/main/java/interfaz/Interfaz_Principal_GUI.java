package interfaz;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.LlmService;
import model.ClienteOtaku;
import model.ProductoOtaku;

public class Interfaz_Principal_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	// CardLayout y panel contenedor
	private static CardLayout cardLayout;
	private static JPanel panelContenedor;
	
	
	private Opciones_Panel opciones;
	private Agregar_Producto_GUI agregarProducto;
	private Eliminar_Producto_GUI eliminarProducto;
	private MostrarProductos mostrar;
	private Actualizar_Producto_GUI actualizar1;
	private Actualizar_PanelSelector_GUI actualizar2;
	private Ayudar_IA_GUI ayudar_ia;
	private LlmService ia;
	private Agregar_Cliente_GUI agregarcliente;
	private Eliminar_Cliente_GUI eliminarcliente;
	private Mostrar_Clientes_GUI mostrarclientes;
	private Actualizar_Cliente_GUI actualizarclientes;
	private Actualizar_PanelSelector_Clientes actualizarclientes2;
	
	/**
	 * Launch the application.
	 */
	
	//1050x490
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz_Principal_GUI frame = new Interfaz_Principal_GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	public Interfaz_Principal_GUI() throws Exception {
		setTitle("Akihabara Market");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 490);

		getContentPane().setLayout(new BorderLayout());

		cardLayout = new CardLayout();
		panelContenedor = new JPanel(cardLayout);
		getContentPane().add(panelContenedor, BorderLayout.CENTER);

		//GUARDAMOS LOS PANELES EN VARIABLES PARA PODER LLAMARLOS LUEGO
		opciones = new Opciones_Panel(this);
	    agregarProducto = new Agregar_Producto_GUI(this);
	    eliminarProducto = new Eliminar_Producto_GUI(this);
	    mostrar = new MostrarProductos(this);
	    actualizar1 = new Actualizar_Producto_GUI(this);
	    actualizar2 = new Actualizar_PanelSelector_GUI(this);
	    ia = new LlmService();
	    ayudar_ia = new Ayudar_IA_GUI(this,ia);
	    agregarcliente = new Agregar_Cliente_GUI(this);
	    eliminarcliente = new Eliminar_Cliente_GUI(this);
	    mostrarclientes = new Mostrar_Clientes_GUI(this);
	    actualizarclientes = new Actualizar_Cliente_GUI(this);
	    actualizarclientes2 = new Actualizar_PanelSelector_Clientes(this);
	    
		panelContenedor.add(opciones, "menu");
		panelContenedor.add(agregarProducto, "agregarProducto");
		panelContenedor.add(eliminarProducto, "eliminarProducto");
		panelContenedor.add(mostrar,"mostrar");
		panelContenedor.add(actualizar1,"actualizar1");
		panelContenedor.add(actualizar2,"actualizar2");
		panelContenedor.add(ayudar_ia,"ayudar_ia");
		panelContenedor.add(agregarcliente,"agregar_cliente");
		panelContenedor.add(eliminarcliente,"eliminar_cliente");
		panelContenedor.add(mostrarclientes,"mostrar_clientes");
		panelContenedor.add(actualizarclientes,"actualizar_clientes");
		panelContenedor.add(actualizarclientes2,"actualizar_clientes2");
		
		cardLayout.show(panelContenedor, "menu");
	}

	// Método  para permitir a los paneles cambiar la vista
	public void mostrarPanel(String nombrePanel) {
		try {
			cardLayout.show(panelContenedor, nombrePanel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mostrarPanelConDatos(String nombrePanel, Object datos) {
	    try {
	        cardLayout.show(panelContenedor, nombrePanel);
	        if (nombrePanel.equals("actualizar2")) {
	        	actualizar2.setProducto((ProductoOtaku) datos);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public void mostrarPanelConDatos2(String nombrePanel, Object datos) {
	    try {
	        if (nombrePanel.equals("actualizar_clientes2")) {
	            if (datos instanceof ClienteOtaku) {
	                actualizarclientes2.SetCliente((ClienteOtaku) datos);
	                cardLayout.show(panelContenedor, nombrePanel);
	            } else {
	                System.out.println("El objeto pasado no es un ClienteOtaku");
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}

