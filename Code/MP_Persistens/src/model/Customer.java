package model;

public class Customer {
	
	private int id;
	private String Fname;
	private String Lname;
	private String address;
	private int zipcode;
	private String city;
	private String phoneNO;
	private int type;
	private String email;
	
	
	public Customer(int id, String Fname, String Lname, String address, String phoneNO, String email, int type, int zipcode) {
		
		this.id = id;
		this.Fname = Fname;
		this.Lname = Lname;
		this.address = address;
		this.zipcode = zipcode;
		this.phoneNO = phoneNO;
		this.type = type;
		this.email = email;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	
	public String getFname() {
		return Fname;
	}


	public void setFname(String fname) {
		Fname = fname;
	}


	public String getLname() {
		return Lname;
	}


	public void setLname(String lname) {
		Lname = lname;
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

	
	public String toReadableString() {
	    return "ID: " + id + "\n" +
	           "First Name: " + Fname + "\n" +
	           "Last Name: " + Lname + "\n" +
	           "Address: " + address + "\n" +
	           "Zip Code: " + zipcode + "\n" +
	           "City: " + city + "\n" +
	           "Phone Number: " + phoneNO + "\n" +
	           "Type: " + type + "\n" +
	           "Email: " + email;
	}

	
	
}
