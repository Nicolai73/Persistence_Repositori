package db;

import model.Orderline;
import model.Product;

import java.sql.*;

public class DBOrderline implements OrderlineDAO {
    private static final String PS_INSERT =
        "INSERT INTO OrderlineItem (Quantity, SaleOrderID, ProductID) VALUES (?, ?, ?)";

    private final int orderId;
    private final PreparedStatement insertPS;

    public DBOrderline(int orderId) throws DataAccessException {
        this.orderId = orderId;
        try {
            Connection con = DBConnection.getInstance().getConnection();
            insertPS = con.prepareStatement(PS_INSERT, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new DataAccessException("Prepare insert orderline failed", e);
        }
    }

    @Override
    public Orderline addProductToOrderline(Product product, int quantity) throws DataAccessException {
        try {
            // IMPORTANT: ProductID is [Product].ID in your schema
            int productId = product.getId(); // make sure Product has getId()

            insertPS.setInt(1, quantity);
            insertPS.setInt(2, orderId);
            insertPS.setInt(3, productId);
            insertPS.executeUpdate();

            int newId = -1;
            try (ResultSet rs = insertPS.getGeneratedKeys()) {
                if (rs.next()) newId = rs.getInt(1);
            }
            return new Orderline(newId, quantity, productId);
        } catch (SQLException e) {
            throw new DataAccessException("addProductToOrderline failed", e);
        }
    }
}
