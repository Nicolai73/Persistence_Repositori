package model;

public class Clothing extends Product {

	private String size;
	private String colour;
	
	public Clothing() {
		super();
	}
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}

	
}
