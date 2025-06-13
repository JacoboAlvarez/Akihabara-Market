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

public class ProductTableModel extends AbstractTableModel {
    private List<ProductoOtaku> products;
    private List<ClienteOtaku> clientes;
    private String[] columnNames = {"ID", "Nombre", "Categoria", "Precio", "Stock"};

    public ProductTableModel() {
        products = new ArrayList<>();
    }

    public void loadData(Connection connection) {
        products.clear();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM productos")) {

            while (rs.next()) {
                ProductoOtaku product = new ProductoOtaku(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("categoria"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fireTableDataChanged();
    }
    
    public void loadData2(Connection connection, int id) {
        products.clear();
        try {
            String query = "SELECT * FROM productos WHERE id = ?";
            PreparedStatement pst = connection.prepareStatement(query);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ProductoOtaku product = new ProductoOtaku(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("categoria"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        fireTableDataChanged();
    }
    

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductoOtaku product = products.get(rowIndex);
        switch (columnIndex) {
            case 0: return product.getId();
            case 1: return product.getNombre();
            case 2: return product.getCategoria();
            case 3: return product.getPrecio();
            case 4: return product.getStock();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public String getColumnName2(int column) {
        return columnNames[column];
    }
}