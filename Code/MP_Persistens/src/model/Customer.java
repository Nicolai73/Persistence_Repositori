package model;

public class Customer {
	
	private String name;
	private String address;
	private int zipcode;
	private String city;
	private String phoneNO;
	private int type;
	private String email;
	
	
	public Customer(String name, String address, int zipcode, String city, String phoneNO, int type, String email) {
		
		this.name = name;
		this.address = address;
		this.zipcode = zipcode;
		this.city = city;
		this.phoneNO = phoneNO;
		this.type = type;
		this.email = email;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getZipcode() {
		return zipcode;
	}


	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getPhoneNO() {
		return phoneNO;
	}


	public void setPhoneNO(String phoneNO) {
		this.phoneNO = phoneNO;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	

	
	
}
