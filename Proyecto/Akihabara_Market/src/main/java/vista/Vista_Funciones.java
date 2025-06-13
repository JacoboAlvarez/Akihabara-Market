package vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.ClienteOtaku;
import model.ProductoOtaku;

/**
 * @author Jacobo Alvarez Delgado
 * @version 1.0
 **/
public class Vista_Funciones {

	// BUSCAR PRODUCTO POR ID
	public void mostrar_mostrarProductosID(ProductoOtaku p) {

		// PARTE SUPERIOR DE LA VENTANA
		System.out.printf("%-5s %-25s %-15s %-10s %-10s%n", "ID", "Nombre", "Categoría", "Precio", "Stock");
		System.out.println("--------------------------------------------------------------------------");

		// Imprimir cada fila con formato
		System.out.printf("%-5d %-25s %-15s %-10.2f %-10d%n", p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(),
				p.getStock());
		System.out.println("");
	}

	// MOSTRAR TODOS LOS PRODUCTOS
	public void mostrar_mostrarTodos(List<ProductoOtaku> lista) {

		// PARTE SUPERIOR DE LA VENTANA
		System.out.printf("%-5s %-25s %-15s %-10s %-10s%n", "ID", "Nombre", "Categoría", "Precio", "Stock");
		System.out.println("--------------------------------------------------------------------------");

		for (ProductoOtaku p : lista) {
			int id = p.getId();
			String nombre = p.getNombre();
			String categoria = p.getCategoria();
			double precio = p.getPrecio();
			int stock = p.getStock();

			// Imprimir cada fila con formato
			System.out.printf("%-5d %-25s %-15s %-10.2f %-10d%n", id, nombre, categoria, precio, stock);
			System.out.println("");
		}
	}

	// MOSTRAR TODOS LOS CLIENTES
	public void mostrar_mostrarTodosClientes(List<ClienteOtaku> lista) {

		// PARTE SUPERIOR DE LA VENTANA
		System.out.printf("%-5s %-15s %-25s %-10s %-20s%n", "ID", "Nombre", "Correo", "Telefono", "Fecha");
		System.out.println("--------------------------------------------------------------------------");

		for (ClienteOtaku c : lista) {
			int id = c.getId();
			String nombre = c.getNombre();
			String correo = c.getEmail();
			String telefono = c.getTelefono();
			LocalDate fecha = c.getFecha();

			// Imprimir cada fila con formato
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String fechaFormateada = fecha.format(formatter);

			System.out.printf("%-5d %-25s %-15s %-10s %-10s%n", id, nombre, correo, telefono, fechaFormateada);
			System.out.println("");
		}
	}

	// BUSCAR PRODUCTO POR ID
	public void mostrar_mostrarClientesID(ClienteOtaku c) {

		// PARTE SUPERIOR DE LA VENTANA
		System.out.printf("%-5s %-15s %-25s %-10s %-20s%n", "ID", "Nombre", "Correo", "Telefono", "Fecha");
		System.out.println("--------------------------------------------------------------------------");

		// Imprimir cada fila con formato
		LocalDate fecha = c.getFecha();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String fechaFormateada = fecha.format(formatter);

		System.out.printf("%-5d %-15s %-25s %-10s %-20s%n", c.getId(), c.getNombre(), c.getEmail(), c.getTelefono(),
				fechaFormateada);
		System.out.println();
	}

	// ELIMINAR UN PRODUCTO
	public void mostrar_EliminarProducto(boolean a) {
		if (a) {
			System.out.println("Se ha eliminado correctamente el Producto");
			for (int i = 0; i < 3; i++) {
				try {
					Thread.sleep(500); // espera 500 milisegundos (0.5 segundos)
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt(); // en caso de interrupción
				}
				System.out.print(".");
			}
			System.out.println("");
		} else {
			System.out.println("No se ha podido eliminar el registro");
		}
	}
	
	//ELIMINAR UN CLIENTE
	public void mostrar_EliminarCliente(boolean a) {
		if (a) {
			System.out.println("Se ha eliminado correctamente el Cliente");
			for (int i = 0; i < 3; i++) {
				try {
					Thread.sleep(500); // espera 500 milisegundos (0.5 segundos)
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt(); // en caso de interrupción
				}
				System.out.print(".");
			}
			System.out.println("");
		} else {
			System.out.println("No se ha podido eliminar el cliente");
		}
	}

	// ANIMACION DE SALIENDO...
	public void saliendo() {
		System.out.println("Saliendo");
		for (int i = 0; i < 3; i++) {
			try {
				Thread.sleep(500); // espera 500 milisegundos (0.5 segundos)
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // en caso de interrupción
			}
			System.out.print(".");
		}
	}

}
