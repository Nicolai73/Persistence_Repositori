package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Orderline;
import model.Product;

public class DBOrderline implements OrderlineDAO {

    private static final String PS_INSERT = 
        "INSERT INTO OrderLine (orderId, productId, quantity, unitPrice) VALUES (?, ?, ?, ?)";

    private PreparedStatement insertPS;
    private int currentOrderId; // the active order to which we’re adding products

    public DBOrderline(int orderId) throws DataAccessException {
        this.currentOrderId = orderId;
        initPreparedStatement();
    }

    private void initPreparedStatement() throws DataAccessException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            insertPS = connection.prepareStatement(PS_INSERT, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new DataAccessException("Error initializing prepared statement for OrderLine", e);
        }
    }

    @Override
    public Orderline addProductToOrderline(Product product, int quantity) throws DataAccessException {
        int generatedId = -1;
        Orderline orderLine = null;

        try {
            insertPS.setInt(1, currentOrderId);
            insertPS.setString(2, product.getName());
            insertPS.setInt(3, quantity);
            

            insertPS.executeUpdate();

            ResultSet rs = insertPS.getGeneratedKeys();
            if (rs.next()) {
                generatedId = rs.getInt(1);
            }
            orderLine = new Orderline(generatedId, currentOrderId, product.getName(), quantity);

        } catch (SQLException e) {
            throw new DataAccessException("Error adding product to OrderLine", e);
        }

        return orderLine;
    }
}
