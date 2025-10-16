package controller;
import java.sql.SQLException;

import db.*;
import model.Customer;

public class CustomerCtrl {

	private CustomerDAO customerDao;
	
	public CustomerCtrl() throws DataAccessException, SQLException {
		customerDao = new DBCustomer();
	}
	
	public Customer findCustomerByEmail(String email) {
		Customer customer = customerDao.findCustomer(email);
		return customer;
	}
}

