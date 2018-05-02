package model.dto;

/**
 * @author hpatel111
 *update request DTO for bill payment
 */
public class AfterBillPaymentUpdateDTO {
	private int customer_id;
	private float pending_amount;
	private String card_no;
	private String amount_pay;
	private String card_validity;
	private String cvv;
	/**
	 * default constructor
	 */
	public AfterBillPaymentUpdateDTO() {
		super();
	}
	/**
	 * @param customer_id 
	 * @param pending_amount
	 * @param card_no
	 * @param amount_pay
	 * @param card_validity
	 * @param cvv
	 * parameterized constructor
	 */
	public AfterBillPaymentUpdateDTO(int customer_id, float pending_amount, String card_no,
			String amount_pay, String card_validity, String cvv) {
		super();
		this.customer_id=customer_id;
		this.pending_amount = pending_amount;
		this.card_no = card_no;
		this.amount_pay = amount_pay;
		this.card_validity = card_validity;
		this.cvv = cvv;
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
	/**
	 * @return
	 * get pending amount of the customer
	 */
	public float getPending_amount() {
		return pending_amount;
	}
	/**
	 * @param pending_amount
	 * set pending amount of the customer
	 */
	public void setPending_amount(float pending_amount) {
		this.pending_amount = pending_amount;
	}
	/**
	 * @return
	 * get card number of the customer
	 */
	public String getCard_no() {
		return card_no;
	}
	/**
	 * @param card_no
	 * set card number of the customer
	 */
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	/**
	 * @return
	 * get the payable amount 
	 */
	public String getAmount_pay() {
		return amount_pay;
	}
	/**
	 * @param amount_pay
	 * set the payable amount
	 */
	public void setAmount_pay(String amount_pay) {
		this.amount_pay = amount_pay;
	}
	/**
	 * @return
	 * get the validity of the card
	 */
	public String getCard_validity() {
		return card_validity;
	}
	/**
	 * @param card_validity
	 * set the validity of the card
	 */
	public void setCard_validity(String card_validity) {
		this.card_validity = card_validity;
	}
	/**
	 * @return
	 * get the cvv of the card of the customer
	 */
	public String getCvv() {
		return cvv;
	}
	/**
	 * @param cvv
	 * set the cvv of the card of the customer
	 */
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
}