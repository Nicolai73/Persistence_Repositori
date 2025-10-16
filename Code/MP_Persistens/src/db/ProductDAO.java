package db;

import model.Orderline;

public interface ProductDAO {

	int getProductQuantityByName(String name) throws DataAccessException;
	
	Orderline createProductUnits(String name, int requestedQty) throws DataAccessException;
	
}
