package db;

import model.Product;
import model.Orderline;

public interface OrderlineDAO {

    /**
     * Adds a product to the current order line in the database.
     * @param product the Product to add
     * @param quantity the quantity of the product
     * @return the created OrderLine object
     * @throws DataAccessException if a database access error occurs
     */
    Orderline addProductToOrderline(Product product, int quantity) throws DataAccessException;
}
