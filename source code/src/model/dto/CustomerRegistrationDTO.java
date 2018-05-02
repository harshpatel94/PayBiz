package model.dto;

/**
 * @author hpatel111
 *DTO object for customer
 */
public class CustomerRegistrationDTO {
	private String customerId ;
	private String password;
	private String customerName;
	private String address;
	private String contactNumber;
	private String country;
	private String state;
	private String mailid;
	private String identificationDocument;
	private String docDetNum;
	private String date;
	private String typeOfVendor;
	private String cardNumber;
	private Float balance;
	private String feedback;
	
	/**
	 * default constructor
	 */
	public CustomerRegistrationDTO() {
		super();
	}

	/**
	 * @param customerName
	 * @param address
	 * @param contactNumber2
	 * @param country
	 * @param state
	 * @param mailid
	 * @param identificationDocument
	 * @param docDetNum
	 * @param date2
	 * @param typeOfVendor
	 * @param cardNumber
	 * @param balance
	 * parameterized constructor
	 */
	public CustomerRegistrationDTO(String customerName,String password,String address,String contactNumber2,
			String country, String state, String mailid,String identificationDocument,
			String docDetNum, String date2, String typeOfVendor,
			String cardNumber,Float balance) {
		super();
		this.customerName = customerName;
		this.password = password;
		this.address = address;
		this.contactNumber = contactNumber2;
		this.country = country;
		this.state = state;
		this.mailid = mailid;
		this.identificationDocument = identificationDocument;
		this.docDetNum = docDetNum;
		this.date = date2;
		this.typeOfVendor = typeOfVendor;
		this.cardNumber = cardNumber;
		this.balance = balance;
	}
	
	/**
	 * @param customerId
	 * @param password
	 * @param customerName
	 * @param address
	 * @param contactNumber
	 * @param country
	 * @param state
	 * @param mailid
	 * @param identificationDocument
	 * @param docDetNum
	 * @param date
	 * @param typeOfVendor
	 * @param cardNumber
	 * @param balance
	 * parameterized constructor which will add customer id along with all the other details
	 */
	public CustomerRegistrationDTO(String customerId,String password,String customerName, String address,
			String contactNumber, String country, String state, String mailid,
			String identificationDocument, String docDetNum, String date,
			String typeOfVendor, String cardNumber, Float balance
			) {
		super();
		this.customerId = customerId;
		this.password = password;
		this.customerName = customerName;
		this.address = address;
		this.contactNumber = contactNumber;
		this.country = country;
		this.state = state;
		this.mailid = mailid;
		this.identificationDocument = identificationDocument;
		this.docDetNum = docDetNum;
		this.date = date;
		this.typeOfVendor = typeOfVendor;
		this.cardNumber = cardNumber;
		this.balance = balance;
		
	}
	
	
	
	public CustomerRegistrationDTO(String country) {
		super();
		this.country = country;
	}

	/**
	 * @param customerId
	 * @param customerName
	 * @param address
	 * @param contactNumber3
	 * @param country
	 * @param state
	 * @param mailid
	 * @param identificationDocument
	 * @param docDetNum
	 * @param typeOfVendor
	 * @param cardNumber
	 * @param balance
	 * parameterized constructor
	 */
	public CustomerRegistrationDTO(String customerId,String customerName,String address,String contactNumber3,
			String country, String state, String mailid,String identificationDocument,
			String docDetNum, String typeOfVendor,String cardNumber,
			Float balance) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.address = address;
		this.contactNumber = contactNumber3;
		this.country = country;
		this.state = state;
		this.mailid = mailid;
		this.identificationDocument = identificationDocument;
		this.docDetNum = docDetNum;
		this.typeOfVendor = typeOfVendor;
		this.cardNumber = cardNumber;
		this.balance = balance;
	}
	
	
	
	/**
	 * @param customerId
	 * @param feedback
	 */
	public CustomerRegistrationDTO(String customerId, String feedback) {
		super();
		this.customerId = customerId;
		this.feedback = feedback;
	}

	/**
	 * @return the feedback
	 */
	public String getFeedback() {
		return feedback;
	}

	/**
	 * @param feedback the feedback to set
	 */
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	/**
	 * @return
	 * get the customer id
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId
	 * set the customer id
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * @return
	 * get the customer password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 * set the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return
	 * get the document
	 */
	public String getIdentificationDocument() {
		return identificationDocument;
	}

	/**
	 * @param document
	 * set the document
	 */
	public void setIdentificationDocument(String identificationDocument) {
		this.identificationDocument = identificationDocument;
	}

	/**
	 * @return
	 * get customer name
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName
	 * set customer name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	/**
	 * @return
	 * get customer address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address
	 * set customer address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * @return
	 * get customer contact number
	 */
	public String getContactNumber() {
		return contactNumber;
	}

	/**
	 * @param contactNumber
	 * set customer contact number
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	/**
	 * @return
	 * get country of the customer
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 * set country name
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return
	 * get state name
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 * set state name
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * @return
	 * get email id of the customer
	 */
	public String getMailid() {
		return mailid;
	}

	/**
	 * @param mailid
	 * set email id of the customer
	 */
	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	
	/**
	 * @return
	 * get identification document of the customer
	 */
	public String getidentificationDocument() {
		return identificationDocument;
	}

	/**
	 * @param identificationDocument
	 * set customer identification document
	 */
	public void setidentificationDocument(String identificationDocument) {
		this.identificationDocument = identificationDocument;
	}
	
	
	/**
	 * @return
	 * get document details number
	 */
	public String getDocDetNum() {
		return docDetNum;
	}

	/**
	 * @param docDetNum
	 * set document details number
	 */
	public void setDocDetNum(String docDetNum) {
		this.docDetNum = docDetNum;
	}

	

	

	/**
	 * @return
	 * get date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 * set date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return
	 * get type of vendor
	 */
	public String getTypeOfVendor() {
		return typeOfVendor;
	}

	/**
	 * @param typeOfVendor
	 * set type of vendor
	 */
	public void setTypeOfVendor(String typeOfVendor) {
		this.typeOfVendor = typeOfVendor;
	}

	/**
	 * @return
	 * get card number of the customer
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
	 * get the balance of the customer
	 */
	public Float getBalance() {
		return balance;
	}

	/**
	 * @param balance
	 * set the balance of the customer
	 */
	public void setBalance(Float balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "CustomerRegistrationDTO [customerId=" + customerId + ", password=" + password + ", customerName="
				+ customerName + ", address=" + address + ", contactNumber=" + contactNumber + ", country=" + country
				+ ", state=" + state + ", mailid=" + mailid + ", identificationDocument=" + identificationDocument
				+ ", docDetNum=" + docDetNum + ", date=" + date + ", typeOfVendor=" + typeOfVendor + ", cardNumber="
				+ cardNumber + ", balance=" + balance + "]";
	}
	
	
}
