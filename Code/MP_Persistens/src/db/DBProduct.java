package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;

public class DBProduct {

	// HVIS IKKE GJORT: IMPLEMENTERE "Quantity" I SQL SCRIPT FOR Product:

	private static final String PS_SELECT = "SELECT * FROM Product WHERE Name = ?";

	PreparedStatement pstmt;

	DBProduct() {

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

	public void createProductUnits(String name, int requestedQty) throws DataAccessException {

		int availableQty = getProductQuantityByName(name);

		if (availableQty < requestedQty) {
			throw new DataAccessException(
					"Not enough stock available. Requested: " + requestedQty + ", Available: " + availableQty, null);
		}

		try (Connection connection = DBConnection.getInstance().getConnection();
				PreparedStatement pstmt = connection
						.prepareStatement("UPDATE Product SET Quantity = Quantity - ? WHERE Name = ?")) {

			pstmt.setString(2, name);
			pstmt.setInt(1, requestedQty);

			int affectedRows = pstmt.executeUpdate();

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				buildObject(rs);
			}

			if (affectedRows == 0) {
				throw new DataAccessException("Failed to update product quantity.", null);
			}

		} catch (SQLException e) {
			throw new DataAccessException("Error updating product quantity", e);
		}

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
}
