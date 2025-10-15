package db;

import java.util.List;

import model.Customer;

public interface CustomerDAO {
	
	Customer findCustomer(String email);

	Customer insert(Customer customer) throws DataAccessException;

	Customer findByEmail(String email) throws DataAccessException;

	List<Customer> findAll() throws DataAccessException;
	

}
