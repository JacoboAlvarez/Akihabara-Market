package principal;

import dao.ClienteDAO;
import dao.ProductoDAO;
import interfaz.Interfaz_Consola;
import model.ProductoOtaku;
import vista.Vista_Funciones;

public class Main {

	public static void main(String[] args) {
		Interfaz_Consola main = new Interfaz_Consola();
		Vista_Funciones vista = new Vista_Funciones();
		ProductoDAO PDA = new ProductoDAO();
		ClienteDAO CDA = new ClienteDAO();
		boolean activador = true;
		while (activador) {
			main.mostrarmenu();
			int seleccion = main.eligeopcion();
			switch (seleccion) {
			// AGREGAR PRODUCTO [ FUNCIONA ]
			case 1:
				PDA.agregarProducto(main.crearProductoOtaku());
				break;
			// CONSULTAR UN PRODUCTO ESPECIFICO POR ID
			case 2:
				vista.mostrar_mostrarProductosID(PDA.MostrarProductoporID(main.pedirid()));
				break;
			// VER TODOS LOS PRODUCTOS DEL INVENTARIO
			case 3:
				vista.mostrar_mostrarTodos(PDA.MostrarTodo());
				break;
			// ACTUALIZAR INFORMACION DE UN PRODUCTO
			case 4:
				PDA.actualizarProducto(main.idCreador(main.pedirid()));
				break;
			// ELIMINAR UN PRODUCTO DEL INVENTARIO
			case 5:
				vista.mostrar_EliminarProducto(PDA.eliminarProductos(main.pedirid()));
				break;
			// UTILIZAR ASISTENTE IA [FUNCIONA]
			case 6:
				main.preguntarAlaIA();
				break;
			// CLIENTES
			case 7:
				main.mostrarmenuClientes();
				int selector2 = main.eligeopcion2();
				switch (selector2) {
				//AGREGAR CLIENTE
				case 1:
				CDA.agregarCliente(main.crearClienteOtaku());
				break;
				//VER CLIENTE POR ID
				case 2:
				vista.mostrar_mostrarClientesID(CDA.MostrarClienteporID(main.pedirid_cliente()));	
				break;
				//MOSTRAR TODOS LOS CLIENTES
				case 3:
				vista.mostrar_mostrarTodosClientes(CDA.MostrarTodo());
				break;
				case 4:
				CDA.actualizarCliente(main.idClienteCreador(main.pedirid_cliente()));	
				break;	
				case 5:
				vista.mostrar_EliminarCliente(CDA.eliminarCliente(main.pedirid_cliente()));
				break;	
				case 6:
					
				break;
				case 7:
				}
			break;
			//SALIR
			case 8:
				vista.saliendo();
				activador = false;
				break;
			// MENSAJE POR DEFECTO
			default:
				System.out.println("!!!!!!!!!!!!!!!");
				System.out.println("");

			} // switch

		} // while activador
	}// main
}
