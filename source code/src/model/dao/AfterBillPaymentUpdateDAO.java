package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import constants.DBconstants;

import model.dto.AfterBillPaymentUpdateDTO;
import model.dto.AfterBillPaymentUpdateResponse;

/**
 * @author hpatel111
 * this class contain methods which will update the database
 * changes against any payment made
 */
public class AfterBillPaymentUpdateDAO {
	
	/**
	 * @param AfterBillPaymentUpdateDTO
	 * @return update the balance in the database against the customer who is
	 *         making the payment
	 */
	
	public AfterBillPaymentUpdateResponse updatePayment(AfterBillPaymentUpdateDTO updateReqDTO){
		AfterBillPaymentUpdateResponse updateResDTO = new AfterBillPaymentUpdateResponse(false);
		
		Connection conn = Connector.createConnection();
		if(conn != null){
			try{
				
				int customerId = updateReqDTO.getCustomer_id();
				float customerBalance = 0;
				String tp = updateReqDTO.getAmount_pay();
				float topay = Float.parseFloat(tp);
				float pending = updateReqDTO.getPending_amount();
				float new_balance = (pending) - (topay);
				
				String st1 = "select customer_balance from fp.customer_h where customer_id="+customerId+";";
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery(st1);
				if(rs.next())
				{
					customerBalance = rs.getFloat(1);
					System.out.println("fetching customer balance");
				}
				float db_balance = customerBalance - topay;
				
				if(new_balance >=0 && db_balance >=0){
					PreparedStatement st2 = conn
							.prepareStatement(DBconstants.UPDATE_CUSTOMER_PAY);
					st2.setFloat(1, db_balance);
					st2.setString(2, updateReqDTO.getCard_no());

					int b = st2.executeUpdate();

					if (b != 0) {
						updateResDTO.setStatus(true);
					}

				}
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			updateResDTO.setStatus(false);
		}
		return updateResDTO;
}
}
