package model.dto;

/**
 * @author hpatel111
 *DTO response object for billpayment response
 */
public class BillResponseDTO {

	/**
	 * pending amount to be paid to vendor
	 */
	public float pendingAmount;
	/**
	 * card number of the customer
	 */
	public String cardNumber;
	/**
	 * status of the payment
	 */
	public boolean status;
	/**
	 * default constructor
	 */
	public int customer_id;
	
	public float customerAmount;
	
	/**
	 * 
	 */
	public BillResponseDTO() {
		super();
	}
	/**
	 * @param pendingAmount
	 * @param cardNumber
	 * @param status
	 * parameterized constructor
	 * @param customer_id 
	 * @param customerAmount
	 */
	public BillResponseDTO(float pendingAmount, String cardNumber,
			boolean status, int customer_id,float customerAmount) {
		super();
		this.pendingAmount = pendingAmount;
		this.cardNumber = cardNumber;
		this.status = status;
		this.customer_id=customer_id;
		this.customerAmount = customerAmount;
	}
	/**
	 * @return
	 * get pending amount to be paid to the vendor
	 */
	public float getPendingAmount() {
		return pendingAmount;
	}
	/**
	 * @param pendingAmount
	 * set pending amount of the vendor
	 */
	public void setPendingAmount(float pendingAmount) {
		this.pendingAmount = pendingAmount;
	}
	/**
	 * @return
	 * get the card number of the customer
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber
	 * set card number of the customer
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * @return
	 * get the status of the bill payment
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status
	 * set the status of the bill payment
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	/**
	 * @return 
	 * ..
	 */
	public int getCustomer_id() {
		return customer_id;
	}
	/**
	 * @param customer_id
	 */
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
	
	public float getCustomerAmount() {
		return customerAmount;
	}
	public void setCustomerAmount(float customerAmount) {
		this.customerAmount = customerAmount;
	}
	@Override
	public String toString() {
		return "BillResponseDTO [pendingAmount=" + pendingAmount + ", cardNumber=" + cardNumber + ", status=" + status
				+ ", customer_id=" + customer_id + ", customerAmount=" + customerAmount + "]";
	}
	
	
}
	
