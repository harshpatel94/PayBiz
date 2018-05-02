package model.dto;


/**
 * @author hpatel111
 * implementation DTO Customer especially for TableView element of javafx for displaying data.
 */

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class Customer {
	
	private final SimpleStringProperty customer_id;
	private final SimpleStringProperty customer_name;
	private final SimpleStringProperty customer_address;
	private final SimpleStringProperty customer_email_id;
	private final SimpleStringProperty vendor_type;
	private final SimpleFloatProperty customer_balance;
	
	
	
	
	public Customer(String customer_id, String customer_name, String customer_address, String customer_email_id,
			String vendor_type, float customer_balance) {
		super();
		this.customer_id = new SimpleStringProperty(customer_id);
		this.customer_name = new SimpleStringProperty(customer_name);
		this.customer_address = new SimpleStringProperty(customer_address);
		this.customer_email_id = new SimpleStringProperty(customer_email_id);
		this.vendor_type = new SimpleStringProperty(vendor_type);
		this.customer_balance = new SimpleFloatProperty(customer_balance);
	}
	
	public String getCustomer_id() {
		return customer_id.get();
	}
	public String getCustomer_name() {
		return customer_name.get();
	}
	public String getCustomer_address() {
		return customer_address.get();
	}
	public String getCustomer_email_id() {
		return customer_email_id.get();
	}
	public String getVendor_type() {
		return vendor_type.get();
	}
	public float getCustomer_balance() {
		return customer_balance.get();
	}

	@Override
	public String toString() {
		return "Customer [customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_address="
				+ customer_address + ", customer_email_id=" + customer_email_id + ", vendor_type=" + vendor_type
				+ ", customer_balance=" + customer_balance + "]";
	}
	
	
	
	
	
	
	

}
