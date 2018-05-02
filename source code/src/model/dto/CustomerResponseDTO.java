package model.dto;

/**
 * @author hpatel111
 *DTO response object for customer registration
 */
public class CustomerResponseDTO {
		
	private boolean status;
	private String customer_id;

	/**
	 * default constructor
	 */
	public CustomerResponseDTO() {
		super();
	}

	/**
	 * @param status
	 * parameterized constructor
	 */
	public CustomerResponseDTO(boolean status) {
		super();
		this.status = status;
	}

	/**
	 * @return
	 * status information
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 * get status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * @return
	 * customer_id information
	 */
	public String getCustomer_id() {
		return customer_id;
	}

	/**
	 * @param customer_id
	 * set customerId
	 */
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	
	
}
