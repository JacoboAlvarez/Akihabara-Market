package interfaz;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.ClienteOtaku;
import model.ProductoOtaku;

public class ClienteTableModel extends AbstractTableModel {
	private List<ClienteOtaku> clientes;
	private String[] columnNames = { "ID", "Nombre", "Correo", "Telefono", "Fecha Registro" };

	public ClienteTableModel() {
		clientes = new ArrayList<>();
	}

	public void loadData(Connection connection) {
		clientes.clear();
		try (Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")) {

			while (rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String email = rs.getString("email");
				String telefono = rs.getString("telefono");
				LocalDate fecha = rs.getDate("fecha_registro").toLocalDate();

				ClienteOtaku cliente = new ClienteOtaku(id, nombre, email, telefono, fecha);
				clientes.add(cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		fireTableDataChanged();
	}

	public void loadData2(Connection connection, int id) {
	    clientes.clear();
	    try {
	        String query = "SELECT * FROM clientes WHERE id = ?";
	        PreparedStatement pst = connection.prepareStatement(query);

	        pst.setInt(1, id);

	        ResultSet rs = pst.executeQuery();

	        while (rs.next()) {
	            int id2 = rs.getInt("id");
	            String nombre = rs.getString("nombre");
	            String email = rs.getString("email");
	            String telefono = rs.getString("telefono");
	            LocalDate fecha = rs.getDate("fecha_registro").toLocalDate();

	            ClienteOtaku cliente = new ClienteOtaku(id2, nombre, email, telefono, fecha);
	            clientes.add(cliente);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    fireTableDataChanged();
	}

	@Override
	public int getRowCount() {
		return clientes.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ClienteOtaku cliente = clientes.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return cliente.getId();
		case 1:
			return cliente.getNombre();
		case 2:
			return cliente.getEmail();
		case 3:
			return cliente.getTelefono();
		case 4:
			return cliente.getFecha();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
}