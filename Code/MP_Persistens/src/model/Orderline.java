package model;

import java.util.ArrayList;

public class Orderline {
    private int id;
    private int quantity;
    private int productId;
    private ArrayList<Product> products = new ArrayList<>();

    public Orderline() {}

    public Orderline(int id, int quantity, int productId) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
    }

    public Orderline(int id, int quantity, int productId, ArrayList<Product> products) {
        this(id, quantity, productId);
        if (products != null) this.products = products;
    }

    public int getId() { 
    	return id;
    	}
    public void setId(int id) { 
    	this.id = id; 
    	}

    public int getQuantity() { 
    	return quantity; 
    	}
    public void setQuantity(int quantity) { 
    	this.quantity = quantity; 
    	}

    public int getProductId() { 
    	return productId; 
    	}
    public void setProductId(int productId) { 
    	this.productId = productId; 
    	}

    public ArrayList<Product> getProducts() { 
    	return products; 
    	}
    public void setProducts(ArrayList<Product> products) { 
    	this.products = products; 
    	}
}
