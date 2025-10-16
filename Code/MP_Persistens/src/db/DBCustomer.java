package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class DBCustomer implements CustomerDAO {

	private static final String PS_INSERT = "insert into Customer values (?,?,?,?,?,?,?)";
	private static final String PS_SELECT_BY_EMAIL = "SELECT * FROM Customer WHERE email = ?";
	private static final String PS_SELECT = "SELECT* FROM Customer";
	private PreparedStatement insertPS;
	private PreparedStatement selectByEmailPS;
	private PreparedStatement selectPS;
	
	
	public DBCustomer() throws DataAccessException, SQLException {
		initPreparedStatement();
	}
	
	private void initPreparedStatement()throws DataAccessException, SQLException {
		Connection connection = DBConnection.getInstance().getConnection();
		
		try {
			insertPS = connection.prepareStatement(PS_INSERT, Statement.RETURN_GENERATED_KEYS);
			selectByEmailPS = connection.prepareStatement(PS_SELECT_BY_EMAIL);
			selectPS = connection.prepareStatement(PS_SELECT);
			
			
		} catch (Exception e) {
			throw new DataAccessException(null, e);
		}
	}

	@Override
	public Customer insert(Customer customer) throws DataAccessException {
		String email = null;
		try {
			insertPS.setString(1, customer.getName());
			insertPS.setString(2, customer.getAddress());
			insertPS.setInt(3, customer.getZipcode());
			insertPS.setString(4, customer.getCity());
			insertPS.setString(5, customer.getPhoneNO());
			insertPS.setInt(6, customer.getType());
			insertPS.setString(7, customer.getEmail());
			
			insertPS.executeUpdate();
			
			ResultSet rs = insertPS.getGeneratedKeys();
			if(rs.next()) {
				email = rs.getString(1);
			}
		} catch (Exception e) {
			throw new DataAccessException(null, e);
		}
		customer.setEmail(email);
		return customer;
	}
	
	@Override
	public Customer findByEmail(String email) throws DataAccessException{
		Customer customer = null;
		ResultSet rs;
		try {
			selectByEmailPS.setString(1, email);
			
		} catch (Exception e) {
			throw new DataAccessException(null, e);
		}
		try {
			rs = selectByEmailPS.executeQuery();
			
		} catch (Exception e) {
			throw new DataAccessException(null, e);		}
		try {
			if(rs.next()) {
				customer = buildObject(rs);
			}
			
		} catch (Exception e) {
			throw new DataAccessException(null, e);
		}
		return customer;
	}

	
	
	@Override
	public List<Customer> findAll() throws DataAccessException {
		List<Customer> listCustomer = new ArrayList<>();
		try {
			ResultSet rs = selectPS.executeQuery();
					while(rs.next()) {
						Customer customer = buildObject(rs);
						listCustomer.add(customer);
					}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listCustomer;
	}
	

	private Customer buildObject(ResultSet rs) throws SQLException {
		String name = rs.getString(1);
		String address = rs.getString(2);
		int zipcode = rs.getInt(3);
		String city = rs.getString(4);
		String phoneNo = rs.getString(5);
		int type = rs.getInt(6);
		String email = rs.getString(7);
		
		return new Customer(name, address, zipcode, city, phoneNo, type, email);
	}

	@Override
	public Customer findCustomer(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
