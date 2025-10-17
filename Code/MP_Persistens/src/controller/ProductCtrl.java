package controller;

import db.DBProduct;
import db.DataAccessException;
import db.ProductDAO;
import model.Orderline;

public class ProductCtrl {

	private ProductDAO PDAO;
	
	ProductCtrl() {
	PDAO = new DBProduct();
	}
	
	public int findQuantityByName(String name) throws DataAccessException {
		System.out.println(PDAO.getProductQuantityByName(name));
		return PDAO.getProductQuantityByName(name);
	}
	
	public String findByName(String name) throws DataAccessException {
		System.out.println(PDAO.getProductByName(name));
		return PDAO.getProductByName(name);
	}
	
	public Orderline createProducts(String name, int quantity) throws DataAccessException {
		System.out.println(PDAO.createProductUnits(name, quantity));
		return PDAO.createProductUnits(name, quantity);
	}
	
}
