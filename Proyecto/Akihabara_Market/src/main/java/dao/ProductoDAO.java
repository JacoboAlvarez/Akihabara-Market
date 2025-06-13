package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import interfaz.Interfaz_Consola;
import model.ProductoOtaku;

public class ProductoDAO {
	Interfaz_Consola interfaz = new Interfaz_Consola();
	/**
	 * @author Jacobo Alvarez Delgado
	 * @version 1.0
	 **/

	// Funcion para agregar un nuevo producto a la base de datos
	public void agregarProducto(ProductoOtaku nuevo) {
		try {
			Connection con1 = BDconnection.getConexion();
			String query = "INSERT INTO productos (nombre, categoria, precio, stock) VALUES (?, ?, ?, ?)";
			PreparedStatement pst = con1.prepareStatement(query);

			pst.setString(1, nuevo.getNombre());
			pst.setString(2, nuevo.getCategoria());
			pst.setDouble(3, nuevo.getPrecio());
			pst.setInt(4, nuevo.getStock());

			pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Funcion para Obtener los Datos de un Producto mediante su ID

	public ProductoOtaku MostrarProductoporID(int nbusca) {
		ProductoOtaku producto = null;
		try {
			Connection con1 = BDconnection.getConexion();
			String query = "SELECT * FROM productos WHERE id = ?";
			PreparedStatement pst = con1.prepareStatement(query);

			pst.setInt(1, nbusca);

			ResultSet ejecucion = pst.executeQuery();

			while (ejecucion.next()) {
				// Recuperamos los datos del ResultSet
				int id = ejecucion.getInt("id");
				if(id == nbusca) {
					String nombre = ejecucion.getString("nombre");
					String categoria = ejecucion.getString("categoria");
					double precio = ejecucion.getDouble("precio");
					int stock = ejecucion.getInt("stock");

					// Crear el objeto ProductoOtaku
					producto = new ProductoOtaku(id,nombre, categoria, precio, stock);
				}else {
					System.out.println("La id que has puesto no corresponde a ningun producto");
					break;
				}
		
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return producto;

	}

	// Ver todos los productos

	public List<ProductoOtaku> MostrarTodo() {
		List<ProductoOtaku> lista = new ArrayList<ProductoOtaku>();
		try {
			Connection con1 = BDconnection.getConexion();
			String query = "SELECT * FROM productos";
			PreparedStatement pst = con1.prepareStatement(query);

			ResultSet ejecucion = pst.executeQuery();

			while (ejecucion.next()) {
				// Recuperamos los datos del ResultSet
				int id = ejecucion.getInt("id");
				String nombre = ejecucion.getString("nombre");
				String categoria = ejecucion.getString("categoria");
				double precio = ejecucion.getDouble("precio");
				int stock = ejecucion.getInt("stock");

				ProductoOtaku padd = new ProductoOtaku(id,nombre, categoria, precio, stock);
				lista.add(padd);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return lista;

	}
	
	// Funcion para actualizar un producto, le pondremos un select para que podamos
	// elegir tambien el que queremos cambiar

	public boolean actualizarProducto(ProductoOtaku producto) {
		boolean cambio = false;
		try {
			Connection con1 = BDconnection.getConexion();
			//SUBMENU DE SELECCION
			System.out.println("¿Qué quieres cambiar?:");
			System.out.println("1 - Nombre");
			System.out.println("2 - Categoría");
			System.out.println("3 - Precio");
			System.out.println("4 - Stock");
			System.out.println("5 - Salir");
			int eleccion = interfaz.eligeopcion();

			String query = "";
			PreparedStatement pst = null;

			switch (eleccion) {
			case 1: //NOMBRE
				System.out.print("Introduce el nuevo nombre: ");
				String nombreNuevo = interfaz.pedirString();
				query = "UPDATE productos SET nombre = ? WHERE id = ?";
				pst = con1.prepareStatement(query);
				pst.setString(1, nombreNuevo);
				pst.setInt(2, producto.getId());
				producto.setNombre(nombreNuevo);
				break;
			case 2: //CATEGORIA
				System.out.print("Introduce la nueva categoría: ");
				String categoriaNueva = interfaz.pedirString();
				query = "UPDATE productos SET categoria = ? WHERE id = ?";
				pst = con1.prepareStatement(query);
				pst.setString(1, categoriaNueva);
				pst.setInt(2, producto.getId());
				producto.setCategoria(categoriaNueva);
				break;
			case 3: //PRECIO
				System.out.print("Introduce el nuevo precio: ");
				// NO PUEDES HACERLO CON . tienes que usar las , para que pille bien el double
				double precioNuevo = interfaz.pedirDouble();
				if (precioNuevo < 0) {
					System.out.println("El precio no puede ser menor que 0 ");
					con1.close();
					return false;
				}
				query = "UPDATE productos SET precio = ? WHERE id = ?";
				pst = con1.prepareStatement(query);
				pst.setDouble(1, precioNuevo);
				pst.setInt(2, producto.getId());
				producto.setPrecio(precioNuevo);
				break;
			case 4: //STOCK
				System.out.print("Introduce el nuevo stock: ");
				int stockNuevo = interfaz.pedirIntStock();
				int nuevostock = 0;

				if (stockNuevo >= 0) {
					nuevostock = stockNuevo;
				} else {
					nuevostock = producto.getStock() + stockNuevo;
				}
				if (stockNuevo < 0) {
					System.out.println("No puedes tener una cantidad de Stock negativa");
				} else {
					query = "UPDATE productos SET stock = ? WHERE id = ?";
					pst = con1.prepareStatement(query);
					pst.setInt(1, nuevostock);
					pst.setInt(2, producto.getId());
					producto.setStock(nuevostock);
					break;
				}

			case 5: //SALIR
				System.out.println("Saliendo sin cambios...");
				return false;
			default:
				System.out.println("Opción no válida");
				return false;
			}

			//FUNCIONALIDADES FINALES
			if (pst != null) {
			    int filasAfectadas = pst.executeUpdate();
			    if (filasAfectadas > 0) {
			        System.out.println("Actualización exitosa. Filas afectadas: " + filasAfectadas);
			        cambio = true;
			    } else {
			        System.out.println("No se encontró el cliente o no hubo cambios o (los datos eran iguales).");
			    }
			    pst.close();
			}

			con1.close();

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace(); 
		}
		return cambio;
	}

	// Eliminar un producto

	public boolean eliminarProductos(int id) {
		try {
			Connection con1 = BDconnection.getConexion();
			String query = "DELETE FROM productos WHERE id = ?";
			PreparedStatement pst = con1.prepareStatement(query);

			pst.setInt(1, id);
			int filasAfectadas = pst.executeUpdate();

			return filasAfectadas > 0; // Devuelve true si se eliminó al menos un producto
		} catch (Exception e) {
			System.out.println("Error al eliminar producto: " + e.getMessage());
			return false;
		}
	}

}
