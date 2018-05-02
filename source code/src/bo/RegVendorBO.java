package bo;

import java.util.Date;

import model.dto.VendorDTO;

/**
 * @author hpatel111
 * 
 * 
 */
public class RegVendorBO extends UserBO{

	/**
	 * @param venDTO
	 * @return it is generating certificate based on employee count and year of
	 *         establishment
	 */
	@SuppressWarnings("deprecation")
	public VendorDTO generateCertificate(VendorDTO venDTO) {
		Date d = new Date();
		int curdate = d.getYear() + 1900;
		int YOE = venDTO.getYearOfEstablishment();
		int YOS = curdate - YOE;

		int empCount = venDTO.getEmployeeCount();
		String certificate = "";
		if (((YOS >= 1) && (YOS < 5)) && ((empCount >= 30) && (empCount < 50))) {
			certificate = "A+";
		} else if (((YOS >= 5) && (YOS < 10))
				&& ((empCount >= 50) && (empCount < 75))) {
			certificate = "B+";
		} else if (((YOS >= 10) && (YOS < 15))
				&& ((empCount >= 75) && (empCount < 100))) {
			certificate = "C+";
		} else if (((YOS >= 15) && (YOS < 25))
				&& ((empCount >= 100) && (empCount < 200))) {
			certificate = "D+";
		} else if (((YOS >= 25) && (YOS <= 50))
				&& ((empCount >= 200) && (empCount < 500))) {
			certificate = "E+";
		} else if (((YOS > 50)) && ((empCount >= 500))) {
			certificate = "F+";
		} else {
			certificate = "NG";
		}

		venDTO.setCertificate(certificate);
		return venDTO;
	}
	/**
	 * 
	 * @param venDTO
	 * @return it is generating the unique vendor id for each vendor
	 */
	public VendorDTO generateVendorID(VendorDTO venDTO) {
		Date d = new Date();
		StringBuffer str = new StringBuffer(d.getTime() + "").reverse();
		String rand = str.substring(0, 5);
		venDTO.setVendorId(rand);
		return venDTO;

	}

}
