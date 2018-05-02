package model.dto;


/**
 * 
 * DTO for taking log in parameters
 *
 */
public class LoginRequestDTO {
	private String userId;
	private String password;
	private String priviledge;
	/**
	 * default constructor
	 */
	public LoginRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param userId
	 * @param password
	 * perematarized constructor
	 */
	public LoginRequestDTO(String userId, String password,String priviledge) {
		super();
		this.userId = userId;
		this.password = password;
		this.priviledge = priviledge;
	}
	/**
	 * @return
	 * get user id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * @param userId
	 * set user id of the admin
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * @return
	 * get password of the user
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password
	 * set password of the admin
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return priviledge
	 * get priviledge 
	 */
	public String getPriviledge() {
		return priviledge;
	
	}
	/**
	 * @param priviledge
	 * set priviledge
	 */
	public void setPriviledge(String priviledge) {
		this.priviledge = priviledge;
	}
	
	
}
