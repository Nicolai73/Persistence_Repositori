package model;

public class Product {
	
	private int productNumber;
	private String name;
	private int minStock;
	private int sku;
	private String description;
	private String brand;
	private int type;
	
	  public Product() {
	    }

	    // Parameterized constructor
	    public Product(int productNumber, String name, int minStock, int sku, String description, String brand, int type) {
	        this.productNumber = productNumber;
	        this.name = name;
	        this.minStock = minStock;
	        this.sku = sku;
	        this.description = description;
	        this.brand = brand;
	        this.type = type;
	     
	    }
	
	public int getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(int productNumber) {
		this.productNumber = productNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMinStock() {
		return minStock;
	}
	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}
	public int getSku() {
		return sku;
	}
	public void setSku(int sku) {
		this.sku = sku;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	

}
