package model.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import constants.DBconstants;
import model.dto.LoginRequestDTO;
import model.dto.LoginResponseDTO;

/**
 * @author hpatel111
 * class which contain methods for authenticating user while
 * logging in
 */
public class LoginDAO {

	
	
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Statement st = null;
		
	/**
	 * @param loginReqDTO
	 * @return check whether the given user exist or not
	 */
	public LoginResponseDTO login(LoginRequestDTO loginReqDTO) {
		
		/**
		 * @author hpatel111
		 */
		LoginResponseDTO loginResponseDTO = new LoginResponseDTO(false);
		Connection conn = Connector.createConnection();
		if(conn!=null){
			try {
				String userId = loginReqDTO.getUserId();
				String password = loginReqDTO.getPassword();
				String priviledge = loginReqDTO.getPriviledge();

				PreparedStatement st2 = conn.prepareStatement(DBconstants.LOGIN);
				st2.setString(1, userId);
				st2.setString(2, password);
				st2.setString(3, priviledge);
				
				System.out.println(st2);

				ResultSet rs = st2.executeQuery();
				if (rs.next()) {
					String userId1 = rs.getString(2);
					String password1 = rs.getString(3);
					String priviledge1 = rs.getString(4);
					
					System.out.println(priviledge1);

					if (userId.equals(userId1) && password.equals(password1) && priviledge.equals(priviledge1)) {
						loginResponseDTO.setStatus(true);
					}

				}
				rs.close();

				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 5
		}else{
			loginResponseDTO.setStatus(false);
		}
				return loginResponseDTO;
	}

}
