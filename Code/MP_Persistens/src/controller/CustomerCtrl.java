package controller;
import db.*;
import model.Customer;

public class CustomerCtrl {

	private CustomerDAO customerDao;
	
	public CustomerCtrl() {
		customerDao = new DBCustomer();
	}
	
	public Customer findCustomerByEmail(String email) {
		Customer customer = customerDao.findCustomer(email);
		return customer;
	}
}

