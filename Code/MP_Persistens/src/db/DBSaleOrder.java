package db;

import model.*;
import java.sql.*;

public class DBSaleOrder implements SaleOrderDAO {
	
	private static final String PS_INSERT = "insert into SaleOrder values (?,?,?,?,?,?,?,?)";
	private static final String PS_SELECT_BY_ORDERNUMBER = "SELECT * FROM SaleOrder WHERE OrderNumber = ?";
	private static final String PS_SELECT = "SELECT* FROM SaleOrder";
	
	private static final String PS_INSERT_ORDERLINE = "insert into OrderlineItem VALUES (?,?,?)";
	private static final String PS_SELECT_BY_ID = "SELECT * FROM OrderlineItem WHERE ID = ?";
	private static final String PS_SELECT_FROM_ALL_ORDERLINE = "SELECT* FROM OrderlineItem";
	
	private PreparedStatement insertPS;
	private PreparedStatement selectByOrderNumberPS;
	private PreparedStatement selectPS;
	
	private PreparedStatement insertOlPS;
	private PreparedStatement selectByIDPS;
	private PreparedStatement selectAllOrderlinePS;
	
	
	public DBSaleOrder() throws DataAccessException, SQLException {
		initPreparedStatement();
	}
	
	private void initPreparedStatement()throws DataAccessException, SQLException {
		Connection connection = DBConnection.getInstance().getConnection();
		
		try {
			insertPS = connection.prepareStatement(PS_INSERT, Statement.RETURN_GENERATED_KEYS);
			selectByOrderNumberPS = connection.prepareStatement(PS_SELECT_BY_ORDERNUMBER);
			selectPS = connection.prepareStatement(PS_SELECT);
			
		} catch (Exception e) {
			throw new DataAccessException(null, e);
		}
			
	}
			
			@Override
			public SaleOrder insert(SaleOrder saleOrder) throws DataAccessException {
				int orderNumber = 0;
				try {
					insertPS.setDate(1, SaleOrder.getDate());
					insertPS.setInt(2, SaleOrder.getAmount());
					insertPS.setString(3, SaleOrder.getDeliveryStatus());
					insertPS.setDate(4, SaleOrder.getDeliveryDate());
					insertPS.setInt(5, SaleOrder.getCustomerID());
					insertPS.setInt(6, SaleOrder.getDiscountID());
					insertPS.setInt(7, SaleOrder.getInvoiceID());
					insertPS.setInt(8, SaleOrder.getFreightID());
					
					insertPS.executeUpdate();
					
					ResultSet rs = insertPS.getGeneratedKeys();
					if(rs.next()) {
						orderNumber = rs.getInt(1);
					}
				} catch (Exception e) {
					throw new DataAccessException(null, e);
				}
				saleOrder.setOrderNumber (orderNumber);
				return saleOrder;
			} 
			

	

}
