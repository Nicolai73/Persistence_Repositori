package model;

import java.sql.Date;
import java.util.*;
import model.*
;public class SaleOrder {
	
	private List<Orderline> orderlines = new ArrayList<>();
	
	private static Date date;
	private static int amount;
	private static String deliveryStatus;
	private static Date deliveryDate;
	private static int customerID;
	private static int discountID;
	private static int invoiceID;
	private static int freightID;
	
	
	public SaleOrder(Date date, int amount, String deliveryStatus, Date deliveryDate, int customerID, int discountID,
			int invoiceID, int freightID) {
		this.date = date;
		this.amount = amount;
		this.deliveryStatus = deliveryStatus;
		this.deliveryDate = deliveryDate;
		this.customerID = customerID;
		this.discountID = discountID;
		this.invoiceID = invoiceID;
		this.freightID = freightID;
	}


	public static Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public static int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public static String getDeliveryStatus() {
		return deliveryStatus;
	}


	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}


	public static Date getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	public static int getCustomerID() {
		return customerID;
	}


	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}


	public static int getDiscountID() {
		return discountID;
	}


	public void setDiscountID(int discountID) {
		this.discountID = discountID;
	}


	public static int getInvoiceID() {
		return invoiceID;
	}


	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}


	public static int getFreightID() {
		return freightID;
	}


	public void setFreightID(int freightID) {
		this.freightID = freightID;
	}
	
	public List<Orderline> getOrderlines() {
		return orderlines;
	}
	
	public void addOrderline(Orderline orderline) {
		orderlines.add(orderline);
	}


	public void setOrderNumber(int orderNumber) {
		// TODO Auto-generated method stub
		
	}

	dsdsd



}
