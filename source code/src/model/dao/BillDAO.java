package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import constants.DBconstants;
import model.dto.BillRequestDTO;
import model.dto.BillResponseDTO;

/**
 * @author hpatel111 
 * BillDAO class is used to update billing details into database
 */
public class BillDAO {
	
	/**
	 * @param billReqDTO
	 * @return this method fetching all the details of the selected customer and
	 *         presenting it
	 */
	
	public BillResponseDTO billpayment(BillRequestDTO billReqDTO) {
		BillResponseDTO billResDTO = new BillResponseDTO(0, null, false,0,0);

		Connection conn = Connector.createConnection();
		if(conn!=null){
			try {
				String cusid = billReqDTO.getCusid();
				int custid = Integer.parseInt(cusid);
				String venType = billReqDTO.getVenType();
				billResDTO.setCustomer_id(custid);
							
				
				String s4 = "select customer_balance from fp.customer_h where customer_id='"+custid+"';";
				System.out.println(s4);
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery(s4);
				if(rs2.next()){
					billResDTO.setCustomerAmount(rs2.getFloat(1));
				}
				
				PreparedStatement st2 = conn
						.prepareStatement(DBconstants.FETCH_CUSTOMER_PAY);
				st2.setInt(1, custid);
				st2.setString(2, venType);
				
				System.out.println(st2);

				ResultSet rs1 = st2.executeQuery();
				

				while (rs1.next()) {
					//billResDTO.setPendingAmount(rs1.getFloat(1));
					billResDTO.setCardNumber(rs1.getString(1));
					billResDTO.setStatus(true);
				}
				
				String st1="select vendor_amount from fp.amount_db where vendor_type='"+venType+"';";
				System.out.println(st1);
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(st1);
				if(rs.next())
				{
					billResDTO.setPendingAmount(rs.getFloat(1));
				}
				rs.close();
				rs1.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		
		}else{
			billResDTO.setStatus(false);
		}
		
		return billResDTO;
	}
}

