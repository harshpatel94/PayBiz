package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bo.RegCustomerBO;
import constants.DBconstants;
import model.dto.CustomerRegistrationDTO;
import model.dto.CustomerResponseDTO;
import exceptions.BpsDataBaseException;

/**
 * @author hpatel111 class which contain methods that updated customer data into
 *         database
 */
public class CustomerUpdationDAO {
	/**
	 * @param customerRegistrstionDTO
	 * @return get all the information from database against the selected
	 *         customer
	 * @throws BpsDataBaseException
	 */

	public CustomerRegistrationDTO customerSelectionDAO(
			CustomerRegistrationDTO customerRegistrstionDTO)
			throws BpsDataBaseException {
		CustomerRegistrationDTO customerRegistrstionDTO1 = new CustomerRegistrationDTO();

		Connection conn = Connector.createConnection();
		if(conn!=null){
			try {
				
				String custId = customerRegistrstionDTO
						.getCustomerId();
				System.out.println("Customer ID"+custId);
		

				System.out.println("Reach to selection");
				String countryId;
				

				PreparedStatement st1 = conn
						.prepareStatement(DBconstants.SELECT_CUSTOMER);
				//st1.setInt(1, customerId);
				st1.setString(1, custId);	
				ResultSet rs = st1.executeQuery();

				if (rs.next()) {
					
					customerRegistrstionDTO1.setCustomerId(rs.getString(1));
					customerRegistrstionDTO1.setCustomerName(rs.getString(3));
					customerRegistrstionDTO1.setAddress(rs.getString(4));
					customerRegistrstionDTO1.setContactNumber(rs.getString(5));
					countryId = rs.getString(6);
					String password = rs.getString(2);

					customerRegistrstionDTO1.setMailid(rs.getString(7));
					customerRegistrstionDTO1.setidentificationDocument(rs
							.getString(8));
					customerRegistrstionDTO1.setDocDetNum(rs.getString(9));
					customerRegistrstionDTO1.setTypeOfVendor(rs.getString(11));
					customerRegistrstionDTO1.setCardNumber(rs.getString(12));
					customerRegistrstionDTO1.setBalance(rs.getFloat(1));

					PreparedStatement st2 = conn
							.prepareStatement(DBconstants.COUNTRY_STATE);
					st2.setString(1, countryId);

					ResultSet rs2 = st2.executeQuery();
					rs2.next();
					customerRegistrstionDTO1.setCountry(rs2.getString(1));
					customerRegistrstionDTO1.setState(rs2.getString(2));
					conn.close();
				} else {

					return null;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}else{
			return null;
		}
		
		return customerRegistrstionDTO1;
	}
	
//	public Customer customer(CustomerRegistrationDTO customerRegistrationDTO) throws BpsDataBaseException{
//		
//		try{
//			Connection conn = Connector.createConnection();
//			String custId = customerRegistrationDTO.getCustomerId();
//			System.out.println("Customer ID"+custId);
//			PreparedStatement st1 = conn
//					.prepareStatement(DBconstants.SELECT_CUSTOMER);
//			//st1.setInt(1, customerId);
//			st1.setString(1, custId);	
//			ResultSet rs = st1.executeQuery();
//		}
//		return null;
//		
//	}

	/**
	 * @param customerRegistrstionDTO
	 * @return updates the customer data into the database
	 * @throws BpsDataBaseException
	 */
	/*
	 * For Updating the data
	 */
	public CustomerResponseDTO customerUpdateDAO(
			CustomerRegistrationDTO customerRegistrstionDTO)
			throws BpsDataBaseException {

		CustomerResponseDTO custRegisRespDto = new CustomerResponseDTO(false);

		/**
		 * @author hpatel111
		 */

		Connection conn = Connector.createConnection();
		if(conn!=null){
			try {
				RegCustomerBO rcb = new RegCustomerBO();
				int m = rcb.customerDocVerification(
						customerRegistrstionDTO.getidentificationDocument(),
						customerRegistrstionDTO.getDocDetNum());

				if (m == 0) {
					return custRegisRespDto;
				}
				System.out.println("Customer Id ->>"+customerRegistrstionDTO.getCustomerId());
				int customerId = Integer.parseInt(customerRegistrstionDTO
						.getCustomerId());
				String countryId;

				PreparedStatement st2 = conn
						.prepareStatement(DBconstants.COUNTRY_ID);
				st2.setString(1, customerRegistrstionDTO.getCountry());
				st2.setString(2, customerRegistrstionDTO.getState());

				ResultSet rs2 = st2.executeQuery();
				rs2.next();
				countryId = rs2.getString(1);
				System.out.println("This is country id" + countryId);

				System.out.println(countryId + "something");

				System.out.println("Before Sql");

				PreparedStatement st3 = conn
						.prepareStatement(DBconstants.UPDATE_CUSTOMER);

				st3.setString(1, customerRegistrstionDTO.getCustomerName());
				st3.setString(2, customerRegistrstionDTO.getAddress());
				st3.setString(3, customerRegistrstionDTO.getContactNumber());
				st3.setString(4, countryId);
				st3.setString(5, customerRegistrstionDTO.getMailid());
				st3.setString(6,
						customerRegistrstionDTO.getidentificationDocument());
				st3.setString(7, customerRegistrstionDTO.getDocDetNum());
				st3.setString(8, customerRegistrstionDTO.getTypeOfVendor());
				st3.setString(9, customerRegistrstionDTO.getCardNumber());
				st3.setFloat(10, customerRegistrstionDTO.getBalance());
				st3.setInt(11, customerId);

				boolean bl = st3.execute();

				System.out.println("After Sql");
				if (bl == false) {
					System.out.println("the sql is working");
					custRegisRespDto.setStatus(true);
				}

				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			custRegisRespDto.setStatus(false);
		}
		
		return custRegisRespDto;
	}
	
	
	/**
	 * @param customerRegistrstionDTO
	 * @return deletes the customer data from the database
	 * @throws BpsDataBaseException
	 */
	/*
	 * For Updating the data
	 */
	public CustomerResponseDTO customerDeletionDAO(
			CustomerRegistrationDTO customerRegistrstionDTO)
			throws BpsDataBaseException {

		CustomerResponseDTO custRegisRespDto = new CustomerResponseDTO(false);

		/**
		 * @author hpatel111
		 */

		Connection conn = Connector.createConnection();
		if(conn!=null){
			try {
				System.out.println("Customer Id ->>"+customerRegistrstionDTO.getCustomerId());
				String customerId = customerRegistrstionDTO
						.getCustomerId();

				PreparedStatement st2 = conn
						.prepareStatement(DBconstants.DELETE_CUSTOMER);
				st2.setString(1, customerId);
				boolean bl = st2.execute();
				System.out.println("After Sql");
				if (bl == false) {
					System.out.println("the sql is working");
					custRegisRespDto.setStatus(true);
				}

				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			custRegisRespDto.setStatus(false);
		}
				return custRegisRespDto;
	}


}
