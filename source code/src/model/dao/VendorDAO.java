package model.dao;

import exceptions.BpsDataBaseException;
import model.dto.VendorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bo.RegVendorBO;
import constants.DBconstants;


/**
 * @author hpatel111 this class contains methods to register , fetch and update the
 *         details of the vendor
 */
public class VendorDAO {

	/**
	 * @param venDto
	 * @return stores the vendor details into the database
	 * @throws BpsDataBaseException
	 */
	public boolean registerVendor(VendorDTO venDto) throws BpsDataBaseException{
		
		Connection conn = Connector.createConnection();
		if(conn!=null){
			try{
				PreparedStatement stGetCountryCode = conn
						.prepareStatement(DBconstants.COUNTRY_ID);

				stGetCountryCode.setString(1, venDto.getCountry());
				stGetCountryCode.setString(2, venDto.getState());
				
				System.out.println(stGetCountryCode);

				ResultSet countryCode = stGetCountryCode.executeQuery();
				
				
				countryCode.next();
				System.out.println(countryCode.getString(1));
				
				PreparedStatement st4 = conn.prepareStatement(DBconstants.LOGIN_LAST_ID);
				ResultSet rs4 = st4.executeQuery();
				rs4.next();
				int login_h = rs4.getInt(1);
				System.out.println(st4);
			
				PreparedStatement st6 = conn.prepareStatement(DBconstants.INSERT_COMPANY_DB);
				st6.setInt(1, Integer.parseInt(venDto.getCompanyRegNo()));
				st6.setString(2, venDto.getVendorName());
				st6.setString(3, venDto.getVendorType());
				
			   
				
				PreparedStatement stInsert = conn
						.prepareStatement(DBconstants.REGISTER_VENDOR);
				RegVendorBO rvb = new RegVendorBO();
				int n = rvb.checkname(venDto.getVendorName());
				if(n == 0){
					stInsert.setString(1, venDto.getVendorId());
					
					stInsert.setString(2, venDto.getPassword());

					stInsert.setInt(3, Integer.parseInt(venDto.getCompanyRegNo()));

					stInsert.setString(4, venDto.getAddress());

					stInsert.setString(5, venDto.getEmail());

					stInsert.setString(6, venDto.getContactNumber());

					stInsert.setString(7, venDto.getWebsite());

					stInsert.setString(8, venDto.getCertificateIssuedDate());

					stInsert.setString(9, venDto.getCertificateValidityDate());

					stInsert.setInt(10, venDto.getEmployeeCount());

					stInsert.setInt(11, venDto.getCustomerCount());

					stInsert.setInt(12, venDto.getYearOfEstablishment());

					stInsert.setString(13, venDto.getCertificate());

					stInsert.setString(14, countryCode.getString(1));

					PreparedStatement st7 = conn.prepareStatement(DBconstants.INSERT_LOGIN);
					st7.setInt(1, (login_h+1));
					st7.setString(2, venDto.getVendorId());
					st7.setString(3, venDto.getPassword());
					st7.setString(4, "V");
					
					boolean bl1 = st7.execute();
					if(bl1 == false){
						System.out.println("Reached..1");

							boolean b2 = st6.execute();
							if(b2 == false){
								boolean i = stInsert.execute();
								System.out.println("Reached..2");
								if(i == false){
									System.out.println("Reached..3");
									venDto.setStatus(true);
							}	

					}					
				}

				}

							conn.close();
			
			} catch (SQLException e) {
				System.out.println("insideSQLEXCEPTION");
				System.out.println(venDto.isStatus());
				throw new BpsDataBaseException(e.getMessage());
			}

			catch (Exception e) {
				System.out.println("insideEXCEPTION");
				System.out.println(venDto.isStatus());
				venDto.setStatus(false);
				return venDto.isStatus();
			}

		}else{
			venDto.setStatus(false);
			return venDto.isStatus();
		}
				return venDto.isStatus();
		
	}
	
	/**
	 * @param vendorDTO
	 * @return fetch the vendor data against the vendor id provided
	 * @throws BpsDataBaseException
	 */
	public boolean fetchData(VendorDTO vendorDTO)
			throws BpsDataBaseException {
		
		Connection conn = Connector.createConnection();
		if(conn!=null){
			try {

				String vendorID = vendorDTO.getVendorId();
				

				PreparedStatement st2 = conn
						.prepareStatement(DBconstants.SELECT_VENDOR);
				st2.setString(1, vendorID);

				ResultSet rs = st2.executeQuery();

				vendorDTO.setStatus(rs.next());
				if (vendorDTO.isStatus() == true) {

					vendorDTO.setCompanyRegNo(rs.getString(3));

					vendorDTO.setAddress(rs.getString(4));

					vendorDTO.setEmail((rs.getString(5)));

					vendorDTO.setContactNumber((rs.getString(6)));

					vendorDTO.setWebsite((rs.getString(7)));

					vendorDTO.setCertificateIssuedDate((rs.getString(8)));

					vendorDTO.setCertificateValidityDate((rs.getString(9)));

					vendorDTO.setEmployeeCount((rs.getInt(10)));

					vendorDTO.setCustomerCount((rs.getInt(11)));

					vendorDTO.setYearOfEstablishment((rs.getInt(12)));

					vendorDTO.setCertificate((rs.getString(13)));
				} else {
					vendorDTO.setStatus(false);
					return vendorDTO.isStatus();
				}

				String CountryCode = rs.getString(14);

				PreparedStatement stGetCountryInfo = conn
						.prepareStatement(DBconstants.COUNTRY_STATE);
				stGetCountryInfo.setString(1, CountryCode);
				ResultSet rs1 = stGetCountryInfo.executeQuery();
				rs1.next();
				vendorDTO.setCountry(rs1.getString(1));
				vendorDTO.setState(rs1.getString(2));

				PreparedStatement stGetvendorNameType = conn
						.prepareStatement(DBconstants.SELECT_VENDOR_NAME_TYPE);

				stGetvendorNameType.setString(1, vendorDTO.getCompanyRegNo());
				ResultSet rs2 = stGetvendorNameType.executeQuery();
				rs2.next();
				vendorDTO.setVendorName(rs2.getString(1));
				vendorDTO.setVendorType(rs2.getString(2));

			}

			catch (SQLException e) {
				throw new BpsDataBaseException(e.getMessage());
			} catch (Exception e) {
				vendorDTO.setStatus(false);
				return vendorDTO.isStatus();
			}

		}else{
			vendorDTO.setStatus(false);
			return vendorDTO.isStatus();
		}
				return vendorDTO.isStatus();
	}
	
	
	/**
	 * @param venDto
	 * @return updates the data into the database against the selected vendor
	 * @throws BpsDataBaseException
	 */
	public boolean updateData(VendorDTO venDto)
			throws BpsDataBaseException {


		Connection conn = Connector.createConnection();
		if(conn!=null){
			try {
				PreparedStatement getCountryId = conn
						.prepareStatement(DBconstants.COUNTRY_ID);
				getCountryId.setString(1, venDto.getCountry());
				getCountryId.setString(2, venDto.getState());

				ResultSet countryId = getCountryId.executeQuery();
				countryId.next();
				String countryId1 = countryId.getString(1) + "";
				System.out.println(countryId1);

				PreparedStatement stUpdate = conn.prepareStatement(DBconstants.UPDATE_COMPANY_DB);
				stUpdate.setString(1, venDto.getVendorName());
				stUpdate.setString(2, venDto.getVendorType());
				stUpdate.setInt(3, Integer.parseInt(venDto.getCompanyRegNo()));
				
				PreparedStatement stInsert = conn
						.prepareStatement(DBconstants.UPDATE_VENDOR);

				stInsert.setInt(1, Integer.parseInt(venDto.getCompanyRegNo()));

				stInsert.setString(2, venDto.getAddress());

				stInsert.setString(3, venDto.getEmail());

				stInsert.setString(4, venDto.getContactNumber());

				stInsert.setString(5, venDto.getWebsite());

				stInsert.setString(6, venDto.getCertificateIssuedDate());

				stInsert.setString(7, venDto.getCertificateValidityDate());

				stInsert.setInt(8, venDto.getEmployeeCount());

				stInsert.setInt(9, venDto.getCustomerCount());

				stInsert.setInt(10, venDto.getYearOfEstablishment());

				stInsert.setString(11, venDto.getCertificate());

				stInsert.setString(12, countryId1);

				stInsert.setString(13, venDto.getVendorId());
				System.out.println(stInsert);
				System.out.println("beforeDAO ");	
				int count1 = stUpdate.executeUpdate();
				if(count1 > 0){
					int count = stInsert.executeUpdate();
					System.out.println("updateDAO");

					if (count > 0) {
						System.out.println("insidecount");
						venDto.setStatus(true);
						return venDto.isStatus();
					}
				}
				
			} catch (SQLException e) {
				System.out.println("sqlExceptionDAO ");
				throw new BpsDataBaseException(e.getMessage());
			}

			catch (Exception e) {
				System.out.println("inside exceptionDAO ");

				venDto.setStatus(false);
				return venDto.isStatus();
			}

		}else{
			venDto.setStatus(false);
			return venDto.isStatus();
		}
				return venDto.isStatus();
	}
	
	public VendorDTO viewVendor(VendorDTO venDTO){
		
		String vendorID = venDTO.getVendorId();
		System.out.println(vendorID);
		Connection conn = Connector.createConnection();
		if(conn!=null){
			try {
				
				PreparedStatement ps = conn.prepareStatement(DBconstants.SELECT_VENDOR);
				ps.setString(1, vendorID);
				
				System.out.println(ps);
				ResultSet rs = ps.executeQuery();

				venDTO.setStatus(rs.next());
				if (venDTO.isStatus() == true) {

					venDTO.setVendorId(rs.getString(1));
					venDTO.setCompanyRegNo(rs.getString(3));
					venDTO.setAddress(rs.getString(4));
					venDTO.setEmail((rs.getString(5)));
					venDTO.setContactNumber((rs.getString(6)));
					venDTO.setWebsite((rs.getString(7)));
					venDTO.setCertificateIssuedDate((rs.getString(8)));
					venDTO.setCertificateValidityDate((rs.getString(9)));
					venDTO.setCertificate((rs.getString(13)));
					
					
					System.out.println(venDTO.toString());
				} else {
					System.out.println("SQl is not working");
					venDTO.setStatus(false);
					return venDTO;
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return venDTO;
			}
		}else{
			venDTO.setStatus(false);
			return venDTO;
		}
		return venDTO;	
	}
	
	public VendorDTO vendorDeleteDAO(VendorDTO venDTO) throws BpsDataBaseException{
		Connection conn = Connector.createConnection();
		if(conn!=null){
			try{
				
				System.out.println("Vendor Id -->"+venDTO.getVendorId());
				
				PreparedStatement ps = conn.prepareStatement(DBconstants.DELETE_VENDOR);
				ps.setString(1, venDTO.getVendorId());
				boolean bl = ps.execute();
				System.out.println("after sql");
				if(bl == false){
					System.out.println("the sql is working");
					venDTO.setStatus(true);
				}
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			venDTO.setStatus(false);
		}
		
		return venDTO;
	}
	
	public VendorDTO vendorFeedback(VendorDTO venDTO) throws BpsDataBaseException{
		Connection conn = Connector.createConnection();
		if(conn!=null){
			try{
				
				System.out.println("Vendor Id -->"+venDTO.getVendorId());
				
				PreparedStatement ps = conn.prepareStatement(DBconstants.INSERT_FEEDBACK);
				ps.setString(1, venDTO.getVendorId());
				ps.setString(2, venDTO.getFeedback());
				boolean bl = ps.execute();
				System.out.println("after sql");
				if(bl == false){
					System.out.println("the sql is working");
					venDTO.setStatus(true);
				}
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			venDTO.setStatus(false);
		}
		
		return venDTO;
	
	}
	

}
