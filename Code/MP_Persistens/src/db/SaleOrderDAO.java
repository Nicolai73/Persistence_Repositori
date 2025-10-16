package db;

import model.SaleOrder;

public interface SaleOrderDAO {

	SaleOrder insert(SaleOrder saleOrder) throws DataAccessException;

}
