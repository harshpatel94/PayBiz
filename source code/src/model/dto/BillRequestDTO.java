package model.dto;

/**
 * @author hpatel111
 *DTO class that contains the attributes of bill payment 
 */
public class BillRequestDTO {

	private String cusid;
	private String venType;
	private String venName;
	/**
	 * default constructor
	 */
	public BillRequestDTO() {
		super();
	}
	/**
	 * @param cusid
	 * @param venType
	 * @param venName
	 * @param amountCustomer
	 * parameterized constructor
	 */
	public BillRequestDTO(String cusid, String venType, String venName) {
		super();
		this.cusid = cusid;
		this.venType = venType;
		this.venName = venName;
	}
	/**
	 * @return
	 * get customer id
	 */
	public String getCusid() {
		return cusid;
	}
	/**
	 * @param cusid
	 * set customer id
	 */
	public void setCusid(String cusid) {
		this.cusid = cusid;
	}
	/**
	 * @return
	 * get vendor type
	 */
	public String getVenType() {
		return venType;
	}
	/**
	 * @param venType
	 * set vendor type
	 */
	public void setVenType(String venType) {
		this.venType = venType;
	}
	/**
	 * @return
	 * get vendor name
	 */
	public String getVenName() {
		return venName;
	}
	/**
	 * @param venName
	 * set vendor name
	 */
	public void setVenName(String venName) {
		this.venName = venName;
	}
	
	/**
	 * @return
	 * get customer amount
	 */
	
	
	
}
