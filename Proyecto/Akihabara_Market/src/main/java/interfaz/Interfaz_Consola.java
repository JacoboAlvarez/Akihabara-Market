package interfaz;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;
import dao.BDconnection;
import dao.LlmService;
import model.ClienteOtaku;
import model.ProductoOtaku;
import vista.Vista_Funciones;

public class Interfaz_Consola {
	private static final Scanner scan = new Scanner(System.in);
	
	// MOSTRAR POR PANTALLA
	public void mostrarmenu() {
		System.out.println("1. Añadir nuevo producto al Inventario");
		System.out.println("2. Consulta un producto especifico por su ID");
		System.out.println("3. Ver todos los productos del Inventario ");
		System.out.println("4. Actualizar la informacion de un producto existente");
		System.out.println("5. Eliminar un producto del inventario");
		System.out.println("6. Utilizar Asistente IA");
		System.out.println("7. Clientes");
		System.out.println("8. Salir");
	}
	
	public void mostrarmenuClientes() {
		System.out.println("1. Añadir nuevo Cliente ");
		System.out.println("2. Consulta un Cliente especifico por su ID");
		System.out.println("3. Ver todos los Clientes ");
		System.out.println("4. Actualizar la informacion de un cliente existente");
		System.out.println("5. Eliminar un Cliente del inventario");
		System.out.println("6. Salir");
	}

	// PEDIR DATOS
	public int pedirid() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Dime la ID del producto: ");
		int numero = scan.nextInt();
		if (numero < 0) {
			System.out.println("No puedes poner un numero menor que 0");
			scan.nextInt();
		}
		return numero;
	}
	
	public int pedirid_cliente() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Dime la ID del Cliente: ");
		int numero = scan.nextInt();
		if (numero < 0) {
			System.out.println("No puedes poner un numero menor que 0");
			scan.nextInt();
		}
		return numero;
	}
	
	public int pedirid_IA() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Dime la ID del producto: ");
		int numero = scan.nextInt();
		if (numero < 0) {
			System.out.println("No puedes poner un numero menor que 0");
			scan.nextInt();
		}
		return numero;
	}

	public int eligeopcion() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Cual es tu Opción: ");
		int numero = scan.nextInt();
		if (numero < 0 || numero >= 9) {
			System.out.println("No puedes poner un numero mayor que 8");
		}
		return numero;
	}
	
	public int eligeopcion2() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Cual es tu Opción: ");
		int numero = scan.nextInt();
		if (numero < 0 || numero >= 9) {
			System.out.println("No puedes poner un numero mayor que 6");
		}
		return numero;
	}

	public String pedirString() {
		Scanner scan = new Scanner(System.in);
		String linea = scan.nextLine();
		if (!(linea instanceof String)) {
			System.out.println("No se puede poner otro tipo de dato");
			scan.nextLine();
		}
		return linea;
	}

	public Double pedirDouble() {
		Scanner scan = new Scanner(System.in);
		Double numero = scan.nextDouble();
		if (!(numero instanceof Double) && numero < 0) {
			System.out.println("No se puede poner otro tipo de dato ni tampoco numeros negativos");
			scan.nextDouble();
		}
		return numero;
	}

	public int pedirIntStock() {
		Scanner scan = new Scanner(System.in);
		int numero = scan.nextInt();
		return numero;
	}

	// AUTOCREADOR DE OBJETOS DE TIPO OTAKU

	public ProductoOtaku crearProductoOtaku() {
		// PEDIMOS LOS DATOS PARA CREAR UN OBJETO QUE PASAREMOS A LA FUNCION PARA QUE LO
		// AGREGE A LA BASE DE DATOS
		System.out.println("Dime el nombre del Producto:");
		String nombre = pedirString();
		System.out.println("Dime la Categoria:");
		String categoria = pedirString();
		System.out.println("Dime el Precio: ");
		double precio = pedirDouble();
		System.out.println("Dime el Stock: ");
		int stock = pedirIntStock();
		ProductoOtaku añadible = new ProductoOtaku(nombre, categoria, precio, stock);
		return añadible;
	}

	// GENERADO DE PRODUCTO MEDIANTE LA ID

	public ProductoOtaku idCreador(int id) {
		ProductoOtaku producto = null;
		try {
			Connection con1 = BDconnection.getConexion();
			String query = "SELECT * FROM productos WHERE id = ?";
			PreparedStatement pst = con1.prepareStatement(query);

			pst.setInt(1, id);

			ResultSet ejecucion = pst.executeQuery();

			while (ejecucion.next()) {
				// Recuperamos los datos del ResultSet
				int id2 = ejecucion.getInt("id");
				String nombre = ejecucion.getString("nombre");
				String categoria = ejecucion.getString("categoria");
				double precio = ejecucion.getDouble("precio");
				int stock = ejecucion.getInt("stock");

				// Crear el objeto ProductoOtaku
				producto = new ProductoOtaku(id2, nombre, categoria, precio, stock);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return producto;
	}

	// HACER UNA PREGUNTA A LA IA

	//FUNCIONES AGREGADAS A LA IA
	public void mostrarOpcionesIA() {
		System.out.println("--------FUNCIONES IMPLEMENTADAS---------");
		System.out.println("1-Generar Descripcion de Producto [Generar Descripcion]");
		System.out.println("2-Sugerir una categoria para un Producto [Sugerir Categoria]");
		System.out.println("3-Salir de la conversación [Salir]");
	}
	
	//PRINCIPAL IA
	public void preguntarAlaIA() {
		Vista_Funciones vf = new Vista_Funciones();
		LlmService ia = new LlmService();
		mostrarOpcionesIA();

		try {
			while (true) {
				System.out.print("Tú: ");
				String input = scan.nextLine().trim();

				if (input.equalsIgnoreCase("salir")) {
					vf.saliendo();
					System.out.println();
					break;
				}

				if (input.equalsIgnoreCase("generar descripcion")) {
					generarDescripcionProducto(ia);
					continue;
				}

				if (input.equalsIgnoreCase("sugerir categoria")) {
					sugerirCategoria(ia);
					continue;
				}


				if (input.isEmpty()) {
					System.out.println("[Sistema] La pregunta no puede estar vacía");
					continue;
				}

				try {
					String respuesta = ia.enviarPregunta(input);
					System.out.println("IA: " + respuesta);
				} catch (IOException e) {
					System.out.println("ERROR en servicio IA: " + e.getMessage());
				} catch (Exception e) {
					System.out.println("ERROR inesperado: " + e.getMessage());
				}
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// ¡NO cerrar el scanner aquiiiiiiiiii
		}
	}

	private void generarDescripcionProducto(LlmService ia) throws Throwable {
		try {
			// Obtener producto (reemplaza esto con tu lógica real)
			ProductoOtaku producto = idCreador(pedirid_IA());

			if (producto != null) {
				System.out.println("Generando descripción para: " + producto.getNombre());
				String descripcion = ia.generarDescripcionProducto(producto);
				System.out.println("\n--- DESCRIPCIÓN GENERADA ---");
				System.out.println(descripcion);
				System.out.println("----------------------------\n");
			} else {
				System.out.println("Error: Producto con ID " + producto.getId() + " no encontrado");
			}
		} catch (NumberFormatException e) {
			System.out.println("Error: Debes introducir un número válido para el ID");
		} catch (Exception e) {
			System.out.println("Error generando descripción: " + e.getMessage());
		}
	}
	
	@SuppressWarnings("unused")
	private void sugerirCategoria(LlmService ia) {
	    try {
	    	System.out.println("Dime el nombre del producto: ");
	        String nombre = pedirString();
	        
	        if (nombre != null) {
	            System.out.println("Sugeriendo categoria para: " + nombre);
	            String categoria = ia.sugerirCategoria(nombre);
	            System.out.println("\n--- CATEGORIA SUGERIDA ---");
	            System.out.println(categoria);
	            System.out.println("----------------------------\n");
	        } else {
	            // Usamos 'id' en vez de 'producto.getId()' (evita NullPointerException)
	            System.out.println("Error: Producto con nombre " + nombre + " no encontrado");
	        }
	    } catch(IOException e) {
	        System.out.println("Error: Debes introducir un número válido para el ID");
	    } catch(Exception e) {  // Opcional: Capturar otras excepciones
	        System.out.println("Error inesperado: " + e.getMessage());
	    }
	}

	
	
	public String hacerPregunta() {
		return scan.nextLine();
	}
	
	//APARTADOS PARA LOS CLIENTES
	
	
	public LocalDate pedirFecha() {
		Scanner scan = new Scanner(System.in);
		LocalDate numero = LocalDate.parse(scan.nextLine()) ;
		return numero;
	}
	public ClienteOtaku crearClienteOtaku() {
		// PEDIMOS LOS DATOS PARA CREAR UN OBJETO QUE PASAREMOS A LA FUNCION PARA QUE LO
		// AGREGE A LA BASE DE DATOS
		System.out.println("Dime el nombre del Cliente:");
		String nombre = pedirString();
		System.out.println("Dime el Correo:");
		String email = pedirString();
		System.out.println("Dime el Telefono: ");
		String telefono = pedirString();

		ClienteOtaku añadible = new ClienteOtaku(nombre, email, telefono);
		return añadible;
	}
	
	public ClienteOtaku idClienteCreador(int id) {
		ClienteOtaku cliente = null;
		try {
			Connection con1 = BDconnection.getConexion();
			String query = "SELECT * FROM clientes WHERE id = ?";
			PreparedStatement pst = con1.prepareStatement(query);

			pst.setInt(1, id);

			ResultSet ejecucion = pst.executeQuery();

			while (ejecucion.next()) {
				// Recuperamos los datos del ResultSet
				String nombre = ejecucion.getString("nombre");
				String correo = ejecucion.getString("email");
				String telefono = ejecucion.getString("telefono");
				LocalDate fecha = ejecucion.getDate("fecha_registro").toLocalDate();

				// Crear el objeto ProductoOtaku
				cliente = new ClienteOtaku(id,nombre, correo, telefono,fecha);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return cliente;
	}
	
}
