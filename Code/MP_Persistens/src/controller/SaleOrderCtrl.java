package controller;
import model.*;
import db.*;

public class SaleOrderCtrl {
	private Customer customer;
	private SaleOrder saleOrder;
	private Product product;
	public boolean ok;
	

	public SaleOrderCtrl() {
		
	}
	
	public Customer findCustomer(String email) {
		return customer;
		
	}
	
	public Product findProduct(int ID) {
		return product;
	}
	
	public SaleOrder findSaleOrder (int OrderNumber) {
		return saleOrder;
	}
}

