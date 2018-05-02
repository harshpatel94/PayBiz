package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.RegCustomerBO;
import model.dto.CustomerResponseDTO;
import model.dto.CustomerRegistrationDTO;
import exceptions.BpsDataBaseException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import constants.*;

/**
 * @author hpatel111 class which contain methods for storing customer information
 *         into database
 * 
 */
public class CustomerRegistrationDAO {

	/**
	 * @param customerRegistrstionDTO
	 * @return it stores the customer details into database
	 * @throws BpsDataBaseException
	 * 
	 */
	public CustomerResponseDTO customerRegisDAO(
			CustomerRegistrationDTO customerRegistrationDTO)
			throws BpsDataBaseException {

		CustomerResponseDTO custRegisRespDto = new CustomerResponseDTO(false);

		/**
		 * @author hpatel111
		 */
		Connection conn = Connector.createConnection();
		if(conn!=null){
			try {
				String customerName = customerRegistrationDTO.getCustomerName();
				String password = customerRegistrationDTO.getPassword();
				String country = customerRegistrationDTO.getCountry();
				String state = customerRegistrationDTO.getState();
				String identificationDocument = customerRegistrationDTO.getidentificationDocument();
				String docDetNum = customerRegistrationDTO.getDocDetNum();

				RegCustomerBO rcb = new RegCustomerBO();
				int m = rcb.customerDocVerification(identificationDocument,
						docDetNum);
				int n = rcb.checkname(customerName);

				if (m == 0 || n == 1) {
					return custRegisRespDto;
				}

				String startDate = customerRegistrationDTO.getDate();

				String cardNumber = customerRegistrationDTO.getCardNumber();
				if (cardNumber.length() != 16) {
					return custRegisRespDto;
				}
				Float balance = customerRegistrationDTO.getBalance();
				if (balance < 0) {
					return custRegisRespDto;
				}
				int customerId = rcb.customerIdgeneration();

				String countryId;

				PreparedStatement st2 = conn
						.prepareStatement(DBconstants.COUNTRY_ID);
				st2.setString(1, country);
				st2.setString(2, state);
				System.out.println(st2);	
				
				ResultSet rs2 = st2.executeQuery();
				rs2.next();
				countryId = rs2.getString(1);

				PreparedStatement st4 = conn.prepareStatement(DBconstants.LOGIN_LAST_ID);
				ResultSet rs4 = st4.executeQuery();
				rs4.next();
				int login_h = rs4.getInt(1);
				System.out.println(st4);
				
				PreparedStatement st3 = conn
						.prepareStatement(DBconstants.REGISTER_CUSTOMER);
				st3.setInt(1, customerId);
				st3.setString(2, customerRegistrationDTO.getPassword());
				st3.setString(3, customerRegistrationDTO.getCustomerName());
				st3.setString(4, customerRegistrationDTO.getAddress());
				st3.setString(5, customerRegistrationDTO.getContactNumber());
				st3.setString(6, countryId);
				st3.setString(7, customerRegistrationDTO.getMailid());
				st3.setString(8,customerRegistrationDTO.getidentificationDocument());
				st3.setString(9, customerRegistrationDTO.getDocDetNum());
				st3.setString(10, startDate);
				st3.setString(11, customerRegistrationDTO.getTypeOfVendor());
				st3.setString(12, customerRegistrationDTO.getCardNumber());
				st3.setFloat(13, customerRegistrationDTO.getBalance());
				
				System.out.println(st3);
				
				

				PreparedStatement st5 = conn.prepareStatement(DBconstants.INSERT_LOGIN);
				st5.setInt(1, (login_h+1));
				st5.setString(2, Integer.toString(customerId));
				st5.setString(3, password);
				st5.setString(4, "C");
				
				System.out.println(st5);
				boolean bl1 = st5.execute();
				if(bl1 == false){
					boolean bl = st3.execute();

					if (bl == false) {
						custRegisRespDto.setCustomer_id(Integer.toString(customerId));
						custRegisRespDto.setStatus(true);
					}
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
	
	
	public CustomerResponseDTO customerFeedBack(
			CustomerRegistrationDTO customerRegistrationDTO)
			throws BpsDataBaseException{
		
		CustomerResponseDTO custResDTO = new CustomerResponseDTO(false);

		/**
		 * @author hpatel111
		 */
		Connection conn = Connector.createConnection();
		if(conn!=null){
			try{
				PreparedStatement ps = conn.prepareStatement(DBconstants.INSERT_FEEDBACK);
				ps.setString(1, customerRegistrationDTO.getCustomerId());
				ps.setString(2, customerRegistrationDTO.getFeedback());
				
				boolean bl = ps.execute();
				if(bl == false){
					custResDTO.setStatus(true);
				}
				conn.close();
				ps.close();
			} catch (Exception e) {
				custResDTO.setStatus(false);
				e.printStackTrace();
			}			
		}else{
			custResDTO.setStatus(false);
		}
		return custResDTO;
	}
	
	public ObservableList<String> fillVendor() throws SQLException{
		ObservableList<String> vendorTypeData = FXCollections.observableArrayList();
		String q = "select distinct(vendor_type) from fp.amount_db;";
		System.out.println(q);
		Connection conn = Connector.createConnection();
		if(conn!=null){
			PreparedStatement ps = conn.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("vendor_type"));
				vendorTypeData.add(rs.getString("vendor_type"));
			}
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Reason : No connection established with system.");
			alert.showAndWait();
		}
		conn.close();
		return vendorTypeData;
	}
	
	public ObservableList<String> fillCountry() throws SQLException{
		ObservableList<String> countryData = FXCollections.observableArrayList();
		String q = "select distinct(country) from fp.country_db;";
		Connection conn = Connector.createConnection();
		
		if(conn!=null){
			PreparedStatement ps = conn.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("country"));
				countryData.add(rs.getString("country"));
			}
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Reason : No connection established with system.");
			alert.showAndWait();
		}
		conn.close();
		return countryData;
	}
	
	public ObservableList<String> fillState(String country) throws SQLException{
		ObservableList<String> stateData = FXCollections.observableArrayList();
		String q = "select distinct(state) from fp.country_db where country='"+country+"';";
		System.out.println(q);
		Connection conn = Connector.createConnection();
		if(conn!=null){
			PreparedStatement ps = conn.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("state"));
				stateData.add(rs.getString("state"));
			}
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Reason : No connection established with system.");
			alert.showAndWait();
		}
		conn.close();
		return stateData;
	}
	
	public ObservableList<String> fillVendorName(String country) throws SQLException{
		ObservableList<String> stateData = FXCollections.observableArrayList();
		String q = "select distinct(vendor_name) from fp.company_db where vendor_type='"+country+"';";
		System.out.println(q);
		Connection conn = Connector.createConnection();
		if(conn!=null){
			PreparedStatement ps = conn.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getString("vendor_name"));
				stateData.add(rs.getString("vendor_name"));
			}
		}else{
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Error:");
			alert.setContentText("Reason : No connection established with system.");
			alert.showAndWait();
		}
		
		conn.close();
		return stateData;
	}

}
