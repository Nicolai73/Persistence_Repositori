package db;

import model.SaleOrder;
import model.Customer;
import model.Orderline;
import model.Product;

import java.sql.*;
import java.util.List;

public class DBSaleOrder implements SaleOrderDAO {
    private static final String PS_INSERT = "INSERT INTO SaleOrder VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String PS_SELECT_BY_ID = "SELECT OrderNumber FROM SaleOrder WHERE OrderNumber = ?";
    private static final String PS_INSERT_LINE = "INSERT INTO OrderlineItem VALUES (?, ?, ?)";

    private PreparedStatement insertPS;
    private PreparedStatement selectByIdPS;
    private PreparedStatement insertLinePS;

    public DBSaleOrder() throws SQLException, DataAccessException {
        initPreparedStatements();
    }

    private void initPreparedStatements() throws SQLException, DataAccessException {
        Connection con = DBConnection.getInstance().getConnection();
        insertPS    = con.prepareStatement(PS_INSERT, Statement.RETURN_GENERATED_KEYS);
        selectByIdPS = con.prepareStatement(PS_SELECT_BY_ID);
        insertLinePS = con.prepareStatement(PS_INSERT_LINE, Statement.RETURN_GENERATED_KEYS);
    }

    @Override
    public SaleOrder findById(int orderNumber) throws DataAccessException {
    	SaleOrder saleOrder = null;
        try {
            try {
				selectByIdPS.setInt(1, orderNumber);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            try (ResultSet rs = selectByIdPS.executeQuery()) {
                if (!rs.next()) {

               saleOrder = buildObject(rs);
            }
        } catch (SQLException e) {
            throw new DataAccessException("findById failed", e);
        }
    } finally {
    	
    }
		return saleOrder;
    }
    
    
    @Override
    public int insert(SaleOrder saleOrder) throws DataAccessException {
        Connection con = DBConnection.getInstance().getConnection();
        boolean oldAutoCommit;
        try {
            oldAutoCommit = con.getAutoCommit();
        } catch (SQLException e) {
            throw new DataAccessException("Could not read autocommit", e);
        }

        try {
            con.setAutoCommit(false);

            insertPS.setDate(1, saleOrder.getDate());
            insertPS.setInt(2, saleOrder.getAmount());
            insertPS.setString(3, saleOrder.getDeliveryStatus());

            if (saleOrder.getDeliveryDate() == null) {
                insertPS.setNull(4, Types.DATE);
            } else {
                insertPS.setDate(4, saleOrder.getDeliveryDate());
            }

            insertPS.setInt(5, saleOrder.getCustomerID());
            insertPS.setInt(6, saleOrder.getDiscountID());
            insertPS.setInt(7, saleOrder.getInvoiceID());
            insertPS.setInt(8, saleOrder.getFreightID());

            insertPS.executeUpdate();

            int newOrderNumber;
            try (ResultSet keys = insertPS.getGeneratedKeys()) {
                if (!keys.next()) {
                    throw new DataAccessException("Insert SaleOrder: no generated key returned", null);
                }
                newOrderNumber = keys.getInt(1);
            }
            saleOrder.setOrderNumber(newOrderNumber);

            // Lines
            List<Orderline> lines = saleOrder.getOrderlines();
            if (lines != null) {
                for (Orderline ol : lines) {
                    insertLinePS.setInt(1, ol.getQuantity());
                    insertLinePS.setInt(2, newOrderNumber);
                    insertLinePS.setInt(3, ol.getProductId()); // IMPORTANT: matches [Product].ID
                    insertLinePS.executeUpdate();

                    try (ResultSet lkeys = insertLinePS.getGeneratedKeys()) {
                        if (lkeys.next()) {
                            ol.setId(lkeys.getInt(1));
                        }
                    }
                }
            }

            con.commit();
            return newOrderNumber;
        } catch (SQLException e) {
            try { con.rollback(); } catch (SQLException ignored) {}
            throw new DataAccessException("Insert SaleOrder failed", e);
        } finally {
            try { con.setAutoCommit(oldAutoCommit); } catch (SQLException ignored) {}
        }
    }

	@Override
	public List<SaleOrder> findAll() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Orderline> findAllOrderLines() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> findAllCustomers() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SaleOrder> findByCustomerId(int customerId) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}
	

	private SaleOrder buildObject(ResultSet rs) throws SQLException {
		int orderNumber = rs.getInt("OrderNumber");
		Date date = rs.getDate("Date");
		int amount = rs.getInt("Amount");
		String deliveryStatus = rs.getString("DeliveryStatus");
		Date deliveryDate = rs.getDate("DeliveryDate");
		int customerID = rs.getInt("CustomerID");
		int discountID = rs.getInt("DiscountID");
		int invoiceID = rs.getInt("InvoiceID");
		int freightID = rs.getInt("FreightID");

		return new SaleOrder(date, amount, deliveryStatus, deliveryDate, customerID, discountID, invoiceID, freightID, orderNumber);

	}
}
