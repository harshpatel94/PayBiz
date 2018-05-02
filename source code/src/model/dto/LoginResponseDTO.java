package model.dto;


/**
 * @author hpatel111
 *DTO object for the login response
 */
public class LoginResponseDTO {
	private boolean status;

	/**
	 * default constructor
	 */
	public LoginResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 * parameterized constructor
	 */
	public LoginResponseDTO(boolean status) {
		super();
		this.status = status;
	}

	/**
	 * @return
	 * get the status of the login
	 */
	public boolean isStatus() {
		return status;
	}

	/**
	 * @param status
	 * set the status of the login
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	

}

