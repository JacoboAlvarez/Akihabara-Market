package model;

public class ProductoOtaku {

	private int id;
	private String nombre;
	private String categoria;
	private double precio;
	private int stock;
	
	public ProductoOtaku(String nombre, String categoria, double precio, int stock) {
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}
	
	//CONSTRUCTOR PARA LA FUNCION DE MOSTRAR TODOS
	
	public ProductoOtaku(int id,String nombre, String categoria, double precio, int stock) {
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.stock = stock;
	}
	

	public ProductoOtaku() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	
	
}
