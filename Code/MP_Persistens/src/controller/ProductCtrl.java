package controller;

import db.DataAccessException;
import db.ProductDAO;
import model.Orderline;

public class ProductCtrl {

	private ProductDAO PDAO;
	
	ProductCtrl() {
	
	}
	
	public int findQuantityByName(String name) throws DataAccessException {
		System.out.println(PDAO.getProductQuantityByName(name));
		return PDAO.getProductQuantityByName(name);
	}
	
	public Orderline createProducts(String name, int quantity) throws DataAccessException {
		System.out.println(PDAO.createProductUnits(name, quantity));
		return PDAO.createProductUnits(name, quantity);
	}
	
}
