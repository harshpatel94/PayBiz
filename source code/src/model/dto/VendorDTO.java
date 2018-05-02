package model.dto;


/**
 * @author hpatel111
 *vendor DTO object that contains 
 */
public class VendorDTO {
		String vendorName;
		String password;
		String companyRegNo;
		String vendorType;
		String address;
		String country;
		String state;
		String email;
		String contactNumber;
		String website;
		String certificateIssuedDate;
		String certificateValidityDate;
		int employeeCount;
		int customerCount;
		int yearOfEstablishment;
		int yearOfService;
		String certificate="";
		String vendorId;
		boolean status;
		String feedback;
		
		
		/**
		 * @return
		 * get the password
		 */
		public String getPassword() {
			return password;
		}
		/**
		 * @param password
		 * get the password
		 */
		public void setPassword(String password) {
			this.password = password;
		}
		/**
		 * @return
		 * to check the status
		 */
		public boolean isStatus() {
			return status;
		}
		/**
		 * @param status
		 * to check status
		 */
		public void setStatus(boolean status) {
			this.status = status;
		}
		/**
		 * default constructor
		 */
		public VendorDTO() {
			super();
		}
		/**
		 * @return
		 * get the year of service of the vendor
		 */
		public int getYearOfService() {
			return yearOfService;
		}
		/**
		 * @param yearOfService
		 * set the year of service of the vendor
		 */
		public void setYearOfService(int yearOfService) {
			this.yearOfService = yearOfService;
		}
		/**
		 * @return
		 * get the certificate
		 */
		public String getCertificate() {
			return certificate;
		}
		/**
		 * @param certificate
		 * set the certificate
		 */
		public void setCertificate(String certificate) {
			this.certificate = certificate;
		}
		/**
		 * @return
		 * get the vendor id
		 */
		public String getVendorId() {
			return vendorId;
		}
		/**
		 * @param vendorId
		 * set the vendor id
		 */
		public void setVendorId(String vendorId) {
			this.vendorId = vendorId;
		}
		/**
		 * @param vendorName
		 * @param password
		 * @param companyRegNo
		 * @param vendorType
		 * @param address
		 * @param country
		 * @param state
		 * @param email
		 * @param contactNumber
		 * @param website
		 * @param certificateIssuedDate
		 * @param certificateValidityDate
		 * @param employeeCount
		 * @param customerCount
		 * @param yearOfEstablishment
		 * 
		 * parameterized constructor
		 */
		public VendorDTO(String password,String vendorName, String companyRegNo,
				String vendorType, String address, String country,
				String state, String email, String contactNumber,
				String website, String certificateIssuedDate,
				String certificateValidityDate, int employeeCount,
				int customerCount, int yearOfEstablishment) {
			super();
			this.password = password;
			this.vendorName = vendorName;
			this.companyRegNo = companyRegNo;
			this.vendorType = vendorType;
			this.address = address;
			this.country = country;
			this.state = state;
			this.email = email;
			this.contactNumber = contactNumber;
			this.website = website;
			this.certificateIssuedDate = certificateIssuedDate;
			this.certificateValidityDate = certificateValidityDate;
			this.employeeCount = employeeCount;
			this.customerCount = customerCount;
			this.yearOfEstablishment = yearOfEstablishment;
		
		}
		
		public VendorDTO(String vendorName, String companyRegNo,
				String vendorType, String address, String country,
				String state, String email, String contactNumber,
				String website, String certificateIssuedDate,
				String certificateValidityDate, int employeeCount,
				int customerCount, int yearOfEstablishment) {
			super();
			this.vendorName = vendorName;
			this.companyRegNo = companyRegNo;
			this.vendorType = vendorType;
			this.address = address;
			this.country = country;
			this.state = state;
			this.email = email;
			this.contactNumber = contactNumber;
			this.website = website;
			this.certificateIssuedDate = certificateIssuedDate;
			this.certificateValidityDate = certificateValidityDate;
			this.employeeCount = employeeCount;
			this.customerCount = customerCount;
			this.yearOfEstablishment = yearOfEstablishment;
		}
	
		/**
		 * @return the feedback
		 */
		public String getFeedback() {
			return feedback;
		}
		/**
		 * @param feedback the feedback to set
		 */
		public void setFeedback(String feedback) {
			this.feedback = feedback;
		}
		/**
		 * @return
		 * get the name of the vendor
		 */
		public String getVendorName() {
			return vendorName;
		}
		/**
		 * @param vendorName
		 * set the name of the vendor
		 */
		public void setVendorName(String vendorName) {
			this.vendorName = vendorName;
		}
		/**
		 * @return
		 * get company registration number
		 */
		public String getCompanyRegNo() {
			return companyRegNo;
		}
		/**
		 * @param companyRegNo
		 * set company registration number
		 */
		public void setCompanyRegNo(String companyRegNo) {
			this.companyRegNo = companyRegNo;
		}
		/**
		 * @return
		 * get the type of vendor
		 */
		public String getVendorType() {
			return vendorType;
		}
		/**
		 * @param vendorType
		 * set the type of vendor
		 */
		public void setVendorType(String vendorType) {
			this.vendorType = vendorType;
		}
		/**
		 * @return
		 * get the address of the vendor
		 */
		public String getAddress() {
			return address;
		}
		/**
		 * @param address
		 * set the address of the vendor
		 */
		public void setAddress(String address) {
			this.address = address;
		}
		/**
		 * @return
		 * get the country name
		 */
		public String getCountry() {
			return country;
		}
		/**
		 * @param country
		 * set he country name
		 */
		public void setCountry(String country) {
			this.country = country;
		}
		/**
		 * @return
		 * get state name
		 */
		public String getState() {
			return state;
		}
		/**
		 * @param state
		 * set state name
		 */
		public void setState(String state) {
			this.state = state;
		}
		/**
		 * @return
		 * get email id of the vendor
		 */
		public String getEmail() {
			return email;
		}
		/**
		 * @param email
		 * set email id of the vendor
		 */
		public void setEmail(String email) {
			this.email = email;
		}
		/**
		 * @return
		 * get contact number of the vendor
		 */
		public String getContactNumber() {
			return contactNumber;
		}
		/**
		 * @param contactNumber
		 * set contact number of the vendor
		 */
		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}
		/**
		 * @return
		 * get the website of the vendor
		 */
		public String getWebsite() {
			return website;
		}
		/**
		 * @param website
		 * set the website of the vendor
		 */
		public void setWebsite(String website) {
			this.website = website;
		}
		/**
		 * @return
		 * get the issue date of the certificate
		 */
		public String getCertificateIssuedDate() {
			return certificateIssuedDate;
		}
		/**
		 * @param certificateIssuedDate
		 * set certificate  issue date
		 */
		public void setCertificateIssuedDate(String certificateIssuedDate) {
			this.certificateIssuedDate = certificateIssuedDate;
		}
		/**
		 * @return
		 * get certificate validity date
		 */
		public String getCertificateValidityDate() {
			return certificateValidityDate;
		}
		/**
		 * @param certificateValidityDate
		 * set certificate validity date
		 */
		public void setCertificateValidityDate(String certificateValidityDate) {
			this.certificateValidityDate = certificateValidityDate;
		}
		/**
		 * @return
		 * get the number of employees
		 */
		public int getEmployeeCount() {
			return employeeCount;
		}
		/**
		 * @param employeeCount
		 * set the number of employees
		 */
		public void setEmployeeCount(int employeeCount) {
			this.employeeCount = employeeCount;
		}
		/**
		 * @return
		 * get the number of customer
		 */
		public int getCustomerCount() {
			return customerCount;
		}
		/**
		 * @param customerCount
		 * set the number of customer
		 */
		public void setCustomerCount(int customerCount) {
			this.customerCount = customerCount;
		}
		/**
		 * @return
		 * get the year of establishment
		 */
		public int getYearOfEstablishment() {
			return yearOfEstablishment;
		}
		/**
		 * @param yearOfEstablishment
		 * set the year of establishment
		 */
		public void setYearOfEstablishment(int yearOfEstablishment) {
			this.yearOfEstablishment = yearOfEstablishment;
		}
		@Override
		public String toString() {
			return "VendorDTO [vendorName=" + vendorName + ", password=" + password + ", companyRegNo=" + companyRegNo
					+ ", vendorType=" + vendorType + ", address=" + address + ", country=" + country + ", state="
					+ state + ", email=" + email + ", contactNumber=" + contactNumber + ", website=" + website
					+ ", certificateIssuedDate=" + certificateIssuedDate + ", certificateValidityDate="
					+ certificateValidityDate + ", employeeCount=" + employeeCount + ", customerCount=" + customerCount
					+ ", yearOfEstablishment=" + yearOfEstablishment + ", yearOfService=" + yearOfService
					+ ", certificate=" + certificate + ", vendorId=" + vendorId + ", status=" + status + "]";
		}
		
		
		

}
