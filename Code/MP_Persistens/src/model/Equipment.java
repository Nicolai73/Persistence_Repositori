package model;

public class Equipment extends Product{

	private int material;
	private String style;
	
	public Equipment() {
		super();
	}
	
	public int getMaterial() {
		return material;
	}

	public void setMaterial(int material) {
		this.material = material;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
	
	
}
