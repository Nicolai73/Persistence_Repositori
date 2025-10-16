// src/db/SaleOrderDAO.java
package db;

import java.util.List;
import model.Customer;
import model.SaleOrder;
import model.Orderline;

public interface SaleOrderDAO {
    SaleOrder insert(SaleOrder saleOrder) throws DataAccessException;
    SaleOrder findById(int orderNumber) throws DataAccessException;

    List<SaleOrder> findAll() throws DataAccessException;
    
    List<SaleOrder> findByCustomerId(int customerId) throws DataAccessException;
    
    List<Orderline> findAllOrderLines() throws DataAccessException;
    
    List<Customer> findAllCustomers() throws DataAccessException;
}
