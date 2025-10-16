package db;

import model.SaleOrder;
import model.Orderline;

import java.sql.*;
import java.util.List;

public class DBSaleOrder implements SaleOrderDAO {

    // --- SQL (bracket [Date] because DATE is reserved) ---
    private static final String PS_INSERT = "INSERT INTO SaleOrder VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String PS_SELECT_BY_ORDERNUMBER ="SELECT OrderNumber, FROM SaleOrder WHERE OrderNumber = ?";
    private static final String PS_SELECT ="SELECT OrderNumber, FROM SaleOrder";
    private static final String PS_INSERT_ORDERLINE ="INSERT INTO OrderlineItem VALUES (?, ?, ?)";
    private static final String PS_SELECT_BY_ID ="SELECT ID, Quantity, SaleOrderID, ProductID FROM OrderlineItem WHERE ID = ?";
    private static final String PS_SELECT_FROM_ALL_ORDERLINE ="SELECT ID, Quantity, SaleOrderID, ProductID FROM OrderlineItem";

    private PreparedStatement insertPS;
    private PreparedStatement selectByOrderNumberPS;
    private PreparedStatement selectPS;

    private PreparedStatement insertOlPS;
    private PreparedStatement selectByIDPS;
    private PreparedStatement selectAllOrderlinePS;

    public DBSaleOrder() throws DataAccessException {
        initPreparedStatement();
    }

    private void initPreparedStatement() throws DataAccessException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            insertPS = connection.prepareStatement(PS_INSERT, Statement.RETURN_GENERATED_KEYS);
            setSelectByOrderNumberPS(connection.prepareStatement(PS_SELECT_BY_ORDERNUMBER));
            setSelectPS(connection.prepareStatement(PS_SELECT));

            insertOlPS = connection.prepareStatement(PS_INSERT_ORDERLINE, Statement.RETURN_GENERATED_KEYS);
            setSelectByIDPS(connection.prepareStatement(PS_SELECT_BY_ID));
            setSelectAllOrderlinePS(connection.prepareStatement(PS_SELECT_FROM_ALL_ORDERLINE));
        } catch (SQLException e) {
            throw new DataAccessException("Could not prepare statements", e);
        }
    }

    @Override
    public SaleOrder insert(SaleOrder saleOrder) throws DataAccessException {
        Connection connection = DBConnection.getInstance().getConnection();

        boolean oldAutoCommit;
        try {
            oldAutoCommit = connection.getAutoCommit();
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DataAccessException("Could not start transaction", e);
        }

        int orderNumber = 0;
        try {            
            java.sql.Date sqlDate = SaleOrder.getDate();
            insertPS.setDate(1, sqlDate);

            insertPS.setInt(2, SaleOrder.getAmount());
            insertPS.setString(3, SaleOrder.getDeliveryStatus());

            if (SaleOrder.getDeliveryDate() == null) {
                insertPS.setNull(4, Types.DATE);
            } else {
                insertPS.setDate(4, SaleOrder.getDeliveryDate());
            }

            insertPS.setInt(5, SaleOrder.getCustomerID());
            insertPS.setInt(6, SaleOrder.getDiscountID());
            insertPS.setInt(7, SaleOrder.getInvoiceID());
            insertPS.setInt(8, SaleOrder.getFreightID());

            insertPS.executeUpdate();

            try (ResultSet rs = insertPS.getGeneratedKeys()) {
                if (rs.next()) {
                    orderNumber = rs.getInt(1);
                } else {
                    throw new DataAccessException("Insert SaleOrder: no generated key returned", null);
                }
            }
            saleOrder.setOrderNumber(orderNumber);

            // --- insert lines ---
            List<Orderline> lines = saleOrder.getOrderlines();
            if (lines != null) {
                for (Orderline ol : lines) {
                    insertOlPS.setInt(1, ol.getQuantity());
                    insertOlPS.setInt(2, orderNumber);           // SaleOrderID FK
                    insertOlPS.setInt(3, ol.getProductId());
                    insertOlPS.executeUpdate();

                    try (ResultSet lineKeys = insertOlPS.getGeneratedKeys()) {
                        if (lineKeys.next()) {
                            ol.setId(lineKeys.getInt(1));
                        }
                    }
                }
            }

            connection.commit();
            connection.setAutoCommit(oldAutoCommit);
            return saleOrder;

        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ignored) {}
            throw new DataAccessException("Insert SaleOrder failed", e);
        } finally {
            try { connection.setAutoCommit(true); } catch (SQLException ignored) {}
        }
    }

	public PreparedStatement getSelectByOrderNumberPS() {
		return selectByOrderNumberPS;
	}

	public void setSelectByOrderNumberPS(PreparedStatement selectByOrderNumberPS) {
		this.selectByOrderNumberPS = selectByOrderNumberPS;
	}

	public PreparedStatement getSelectPS() {
		return selectPS;
	}

	public void setSelectPS(PreparedStatement selectPS) {
		this.selectPS = selectPS;
	}

	public PreparedStatement getSelectByIDPS() {
		return selectByIDPS;
	}

	public void setSelectByIDPS(PreparedStatement selectByIDPS) {
		this.selectByIDPS = selectByIDPS;
	}

	public PreparedStatement getSelectAllOrderlinePS() {
		return selectAllOrderlinePS;
	}

	public void setSelectAllOrderlinePS(PreparedStatement selectAllOrderlinePS) {
		this.selectAllOrderlinePS = selectAllOrderlinePS;
	}

}
