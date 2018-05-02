package bo;

import java.util.Date;
import java.util.regex.Pattern;

import model.dto.VendorDTO;

/**
 * @author hpatel111
 *Parent Class 
 */
public class UserBO {
	String t;
	
	public UserBO(){
		t="";
	}
	
	VendorDTO venDTO = new VendorDTO();
	public String generateID(int length){
			Date d = new Date();
			String rand = null;
			StringBuffer str = new StringBuffer(d.getTime() + "").reverse();
			if(length == 5){
				rand = str.substring(0, length);
				venDTO.setVendorId(rand);
			}else{
				rand = str.substring(0, length);
			}
		
		return rand;
}
	/**
	 * @param str
	 * @return it checks that the given name should only contain alphabets and
	 *         spaces
	 */
	public int checkname(String str) {

		if (Pattern.matches("[a-zA-Z ]+", str)) {
			return 0;
		} else {
			return 1;
		}
	}
	
	public int checkMail(String str){
		if(Pattern.matches("", str)){
			return 0;
		}else{
			return 1;
		}
	}
}