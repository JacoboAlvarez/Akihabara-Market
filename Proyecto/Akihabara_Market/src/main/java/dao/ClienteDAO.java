package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import interfaz.Interfaz_Consola;
import model.ClienteOtaku;
import model.ProductoOtaku;

public class ClienteDAO {
	Interfaz_Consola interfaz = new Interfaz_Consola();
	
	public void agregarCliente(ClienteOtaku nuevo) {
		try {
			Connection con1 = BDconnection.getConexion();
			String query = "INSERT INTO clientes (nombre, email, telefono) VALUES (?, ?, ?)";
			PreparedStatement pst = con1.prepareStatement(query);

			pst.setString(1, nuevo.getNombre());
			pst.setString(2, nuevo.getEmail());
			pst.setString(3, nuevo.getTelefono());

			pst.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	// Funcion para Obtener los Datos de un Cliente mediante su ID

	public ClienteOtaku MostrarClienteporID(int nbusca) {
		ClienteOtaku cliente = null;
		try {
			Connection con1 = BDconnection.getConexion();
			String query = "SELECT * FROM clientes WHERE id = ?";
			PreparedStatement pst = con1.prepareStatement(query);

			pst.setInt(1, nbusca);

			ResultSet ejecucion = pst.executeQuery();

			while (ejecucion.next()) {
				// Recuperamos los datos del ResultSet
				int id = ejecucion.getInt("id");
				if (id == nbusca) {
					String nombre = ejecucion.getString("nombre");
					String email = ejecucion.getString("email");
					String telefono = ejecucion.getString("telefono");
					LocalDate fecha = ejecucion.getDate("fecha_registro").toLocalDate();

					 cliente = new ClienteOtaku(id, nombre, email, telefono, fecha);
				} else {
					System.out.println("La id que has puesto no corresponde a ningun cliente");
					break;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return cliente;

	}

	// Ver todos los clientes

	public List<ClienteOtaku> MostrarTodo() {
		List<ClienteOtaku> lista = new ArrayList<ClienteOtaku>();
		try {
			Connection con1 = BDconnection.getConexion();
			String query = "SELECT * FROM clientes";
			PreparedStatement pst = con1.prepareStatement(query);

			ResultSet ejecucion = pst.executeQuery();

			while (ejecucion.next()) {
				// Recuperamos los datos del ResultSet
				int id = ejecucion.getInt("id");
				String nombre = ejecucion.getString("nombre");
				String email = ejecucion.getString("email");
				String telefono = ejecucion.getString("telefono");
				LocalDate fecha = ejecucion.getDate("fecha_registro").toLocalDate();

				ClienteOtaku Cadd = new ClienteOtaku(id, nombre, email, telefono, fecha);
				lista.add(Cadd);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return lista;
	}
	
	
	public boolean eliminarCliente(int id) {
		try {
			Connection con1 = BDconnection.getConexion();
			String query = "DELETE FROM clientes WHERE id = ?";
			PreparedStatement pst = con1.prepareStatement(query);

			pst.setInt(1, id);
			int filasAfectadas = pst.executeUpdate();

			return filasAfectadas > 0; // Devuelve true si se eliminó al menos un producto
		} catch (Exception e) {
			System.out.println("Error al eliminar producto: " + e.getMessage());
			return false;
		}
	}
	
	//Actualizar Cliente
	public boolean actualizarCliente(ClienteOtaku cliente) {
	    boolean cambio = false;
	    try {
	        Connection con1 = BDconnection.getConexion();
	        // SUBMENÚ DE SELECCIÓN
	        System.out.println("¿Qué quieres cambiar?:");
	        System.out.println("1 - Nombre");
	        System.out.println("2 - Email");
	        System.out.println("3 - Teléfono");
	        System.out.println("4 - Salir");
	        int eleccion = interfaz.eligeopcion();

	        String query = "";
	        PreparedStatement pst = null;
	        System.out.println("ID del cliente a actualizar: " + cliente.getId());
	        switch (eleccion) {
	            case 1: // NOMBRE
	                System.out.print("Introduce el nuevo nombre: ");
	                String nombreNuevo = interfaz.pedirString();
	                query = "UPDATE clientes SET nombre = ? WHERE id = ?";
	                pst = con1.prepareStatement(query);
	                pst.setString(1, nombreNuevo);
	                pst.setInt(2, cliente.getId());
	                cliente.setNombre(nombreNuevo);
	                break;

	            case 2: // EMAIL
	                System.out.print("Introduce el nuevo email: ");
	                String emailNuevo = interfaz.pedirString();
	                query = "UPDATE clientes SET email = ? WHERE id = ?";
	                pst = con1.prepareStatement(query);
	                pst.setString(1, emailNuevo);
	                pst.setInt(2, cliente.getId());
	                cliente.setEmail(emailNuevo);
	                break;

	            case 3: // TELÉFONO
	                System.out.print("Introduce el nuevo teléfono: ");
	                String telefonoNuevo = interfaz.pedirString();
	                query = "UPDATE clientes SET telefono = ? WHERE id = ?";
	                pst = con1.prepareStatement(query);
	                pst.setString(1, telefonoNuevo);
	                pst.setInt(2, cliente.getId());
	                cliente.setTelefono(telefonoNuevo);
	                break;

	            case 4: // SALIR
	                System.out.println("Saliendo sin cambios...");
	                con1.close();
	                return false;

	            default:
	                System.out.println("Opción no válida");
	                con1.close();
	                return false;
	        }

	        // EJECUTAR CONSULTA
	        if (pst != null) {
	            int filasAfectadas = pst.executeUpdate();
	            if (filasAfectadas > 0) {
	                System.out.println("Actualización exitosa. Filas afectadas: " + filasAfectadas);
	                System.out.println("");
	                cambio = true;
	            } else {
	                System.out.println("No se encontró el cliente.");
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

}
