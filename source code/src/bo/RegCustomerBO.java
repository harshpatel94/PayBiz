package bo;

import java.util.Date;

/**
 * @author hpatel111
 * 
 */
public class RegCustomerBO extends UserBO {


	/**
	 * @param identificationDocument
	 * @param docDetNum
	 * @return it is verifying the document format against details provided by
	 *         the customer
	 */
	public int customerDocVerification(String identificationDocument,
			String docDetNum) {
		int n = 0;
		int size = 0;
		String substr;

		if (identificationDocument.equals("Voter Id")) {
			substr = docDetNum.substring(0, 3);
			if (substr.equals("GMV")) {
				return 1;
			}
		} else if (identificationDocument.equals("Driving License")) {
			substr = docDetNum.substring(0, 1);
			size = docDetNum.length();
			if (substr.equals("D")  && size == 12) {
				return 1;
			}
		} else if (identificationDocument.equals("Pass Port")) {
			substr = docDetNum.substring(0, 4);
			if (substr.equals("PASS")) {
				return 1;
			}
		} else if (identificationDocument.equals("Pan Card")) {
			substr = docDetNum.substring(0, 3);
			if (substr.equals("PAN")) {
				return 1;
			}
		} else if (identificationDocument.equals("SSN")){
			 size = docDetNum.length();
			 if(size == 9){
				 return 1;
			 }
		}
		return n;
	}
	/**
	 * @return it is generating the unique customer id for each customer
	 */
	public int customerIdgeneration() {
		Date d = new Date();
		StringBuffer str = new StringBuffer(d.getTime() + "").reverse();
		String rand = str.substring(0, 3);
		return Integer.parseInt(rand);
	}

	
}
