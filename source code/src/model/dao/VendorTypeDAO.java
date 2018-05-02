package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import constants.DBconstants;
import exceptions.BpsDataBaseException;
import model.dto.VendorTypeDTO;

/**
 * @author hpatel111 this class contains methods to insert new type of vendor data
 */
public class VendorTypeDAO {
	
	/**
	 * @param venTypeDTO
	 * @return stores the vendorType and amount details into the database
	 * @throws BpsDataBaseException
	 */
	
	public boolean insertVendorInfo(VendorTypeDTO venDTO) throws BpsDataBaseException{
		
		Connection conn = Connector.createConnection();
		if(conn!=null){
			try{
				
				PreparedStatement ps = conn.prepareStatement(DBconstants.INSERT_VENDOR_TYPE);
				ps.setString(1, venDTO.getVendorType());
				ps.setInt(2, Integer.parseInt(venDTO.getVendorAmount()));
				
				boolean b = ps.execute();
				if(b == false){
					System.out.println("Reached 1");
					venDTO.setStatus(true);
				}
				conn.close();
			}catch(SQLException e){
				System.out.println("Inside Exception");
				venDTO.setStatus(false);
				return venDTO.isStatus();
			}catch(Exception e){
				System.out.println("Inside Exception");
				venDTO.setStatus(false);
				return venDTO.isStatus();
			}
		}else{
			venDTO.setStatus(true);
			return venDTO.isStatus();
		}
			return venDTO.isStatus();
	}

}
