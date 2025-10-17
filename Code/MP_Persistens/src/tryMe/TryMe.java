package tryMe;
import java.sql.SQLException;


import db.*;

public class TryMe {
	
	public static void main(String[] args) throws SQLException, DataAccessException {
		
		/*
		OrderDAO orderDB = new OrderDB();
		
		Product wirelessMouse = new Product(100,"1600", "Wireless Mouse", BigDecimal.valueOf(25));
		Product laptopStand = new Product(102,"4600", "Laptop Stand", BigDecimal.valueOf(10));	
		
		Order o = new Order(12347);
		
		
		OrderLine ol1 = new OrderLine(8, wirelessMouse);
		OrderLine ol2 = new OrderLine(6, laptopStand);		
		o.addOrderLine(ol1);
		o.addOrderLine(ol2);	
		
		orderDB.insert(o);
		
		System.out.println("Done");
			*/
		
		db.DBConnection.getInstance();
		
	}

}