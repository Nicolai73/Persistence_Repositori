package tryMe;

import java.sql.SQLException;

import db.*;
import model.*;

public class TryMe {

	public static void main(String[] args) throws SQLException, DataAccessException {

	//	DBConnection db = new DBConnection().getInstance();

		CustomerDAO cdao = new DBCustomer();
		SaleOrderDAO sodao = new DBSaleOrder();

		Customer customer = cdao.findByEmail("john.doe@email.com");
		
		int id = customer.getId();
		
		SaleOrder order = sodao.createOrder(id);
		System.out.println("new order with customer of id " + order.getCustomerID());
		System.out.println("Id " + order.getCustomerID() + " = " + customer.toReadableString());

	}
}