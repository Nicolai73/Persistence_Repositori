package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Orderline;
import model.Product;

public class DBProduct implements ProductDAO {

	// HVIS IKKE GJORT: IMPLEMENTERE "Quantity" I SQL SCRIPT FOR Product:

	PreparedStatement pstmt;

	public DBProduct() {

	}

	
	
	
	
	public int getProductQuantityByName(String name) throws DataAccessException {
		int quantity = -1;

		try (Connection connection = DBConnection.getInstance().getConnection();
				PreparedStatement pstmt = connection.prepareStatement("SELECT Quantity FROM Product WHERE Name = ?")) {

			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				quantity = rs.getInt("Quantity");
			}
		} catch (SQLException e) {
			throw new DataAccessException("Error fetching quantity", e);
		}

		return quantity;
	}

	
	
	
	
	public Orderline createProductUnits(String name, int requestedQty) throws DataAccessException {
	   
		Orderline orderline = new Orderline(requestedQty, requestedQty, requestedQty, null);
		
		int availableQty = getProductQuantityByName(name);

	    if (availableQty < requestedQty) {
	        throw new DataAccessException(
	            "Not enough stock available. Requested: " + requestedQty + ", Available: " + availableQty, null);
	    }

	    try (Connection connection = DBConnection.getInstance().getConnection()) {

	        
	        Product baseProduct = null;
	        try (PreparedStatement selectStmt = connection.prepareStatement(
	                "SELECT * FROM Product WHERE Name = ?")) {

	            selectStmt.setString(1, name);
	            ResultSet rs = selectStmt.executeQuery();

	            if (rs.next()) {
	                baseProduct = buildObject(rs);
	            } else {
	                throw new DataAccessException("Product not found: " + name, null);
	            }
	        }

	        
	        try (PreparedStatement updateStmt = connection.prepareStatement(
	                "UPDATE Product SET Quantity = Quantity - ? WHERE Name = ?")) {

	            updateStmt.setInt(1, requestedQty);
	            updateStmt.setString(2, name);

	            int affectedRows = updateStmt.executeUpdate();
	            if (affectedRows == 0) {
	                throw new DataAccessException("Failed to update product quantity.", null);
	            }
	        }

	     
	        for (int i = 0; i < requestedQty; i++) {
	            orderline.getProducts().add(baseProduct); 
	        }

	    } catch (SQLException e) {
	        throw new DataAccessException("Error updating product quantity or creating units", e);
	    }

	    return orderline;
	    
	}

	
	
	
	
	private Product buildObject(ResultSet rs) throws SQLException {
		int productNumber = rs.getInt("ProductNumber");
		String name = rs.getString("Name");
		int minStock = rs.getInt("MinStock");
		int sku = rs.getInt("SKU");
		String description = rs.getString("Description");
		String brand = rs.getString("Brand");
		int type = rs.getInt("Type");

		return new Product(productNumber, name, minStock, sku, description, brand, type);
	}





	@Override
	public String getProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
}
