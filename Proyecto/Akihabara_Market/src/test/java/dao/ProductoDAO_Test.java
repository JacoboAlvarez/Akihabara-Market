package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import interfaz.Interfaz_Consola;
import model.ProductoOtaku;

class ProductoDAO_Test {
	ProductoDAO dao;

	@BeforeEach
	void setUp() {
		dao = new ProductoDAO();
	}

	@Test
	void testAgregarProducto() {
		ProductoOtaku nuevo = new ProductoOtaku("Poster Bakugan", "Poster", 4.99, 10);
		dao.agregarProducto(nuevo);

		// Intentamos recuperar un producto similar por nombre
		List<ProductoOtaku> lista = dao.MostrarTodo();
		boolean encontrado = lista.stream().anyMatch(p -> p.getNombre().equals("Poster Bakugan"));
		assertTrue(encontrado, "El producto debería haberse insertado y encontrado.");
	}

	@Test
	void testMostrarProductoporID() {
		// Suponemos que hay un producto con ID 1
		ProductoOtaku producto = dao.MostrarProductoporID(1);
		assertNotNull(producto, "Debe devolver un producto con ID 1 si existe.");
		assertEquals(1, producto.getId(), "El ID del producto recuperado debe ser 1.");
	}

	@Test
	void testMostrarTodo() {
		List<ProductoOtaku> lista = dao.MostrarTodo();
		assertNotNull(lista, "La lista no debe ser nula.");
		assertTrue(lista.size() >= 0, "Puede haber 0 o más productos.");
	}

	@Test
	void testActualizarProducto() {
		Interfaz_Consola main = new Interfaz_Consola();
		ProductoOtaku producto = main.idCreador(main.pedirid());
		if (producto != null) {
			String nombreAntiguo = producto.getNombre();
			producto.setNombre("Nombre Actualizado Test");
			boolean actualizado = dao.actualizarProducto(producto);

			assertTrue(actualizado, "El producto debería haberse actualizado.");
			assertEquals("Nombre Actualizado Test", producto.getNombre());

			// Dejar el producto como estaba (opcional)
			producto.setNombre(nombreAntiguo);
		} else {
			fail("No hay producto con ID 1 para probar la actualización.");
		}
	}

	@Test
	void testEliminarProductos() {
		// Primero agregamos un producto
		ProductoOtaku producto = new ProductoOtaku("Producto temporal", "Test", 1.0, 1);
		dao.agregarProducto(producto);

		// Luego obtenemos su ID
		List<ProductoOtaku> lista = dao.MostrarTodo();
		ProductoOtaku ultimo = lista.get(lista.size() - 1); // suponemos que es el último
		int idAEliminar = ultimo.getId();

		// Ahora lo eliminamos
		boolean eliminado = dao.eliminarProductos(idAEliminar);
		assertTrue(eliminado, "El producto debería haberse eliminado.");
	}
}
