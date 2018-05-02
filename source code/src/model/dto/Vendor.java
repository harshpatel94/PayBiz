package model.dto;

/**
 * @author hpatel111
 * implementation DTO Vendor for especially TableView componant of javafx for displaying data.
 */
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Vendor {
		private SimpleStringProperty vendor_id;
		private SimpleIntegerProperty company_reg_no;
		private SimpleStringProperty vendor_address;
		private SimpleStringProperty vendor_contact_no;
		private SimpleStringProperty vendor_website;
		private SimpleStringProperty certificate_issue_date;
		private SimpleStringProperty certificate_validity_date;
		private SimpleStringProperty certificate;
		
		
//		public Vendor(String vendorId, Integer comp_reg_no, String address, String contact_number, String website,
//				String certi_issue, String certi_validity, Integer empCount, Integer yoe, String certi) {
//			super();
//			this.vendor_id = new SimpleStringProperty(vendorId);
//			this.company_reg_no = new SimpleIntegerProperty(comp_reg_no);
//			this.vendor_address = new SimpleStringProperty(address);
//			this.vendor_contact_no = new SimpleStringProperty(contact_number);
//			this.vendor_website = new SimpleStringProperty(website);
//			this.certificate_issue_date = new SimpleStringProperty(certi_issue);
//			this.certificate_validity_date = new SimpleStringProperty(certi_validity);
//			this.empCount = new SimpleIntegerProperty(empCount);
//			this.yoe = new SimpleIntegerProperty(yoe);
//			this.certificate = new SimpleStringProperty(certi);
//		}
		
		public Vendor(String vendorId, Integer comp_reg_no, String address, String contact_number, String website,
				String certi_issue, String certi_validity, String certi) {
			super();
			this.vendor_id = new SimpleStringProperty(vendorId);
			this.company_reg_no = new SimpleIntegerProperty(comp_reg_no);
			this.vendor_address = new SimpleStringProperty(address);
			this.vendor_contact_no = new SimpleStringProperty(contact_number);
			this.vendor_website = new SimpleStringProperty(website);
			this.certificate_issue_date = new SimpleStringProperty(certi_issue);
			this.certificate_validity_date = new SimpleStringProperty(certi_validity);
			this.certificate = new SimpleStringProperty(certi);
		}
		
		
		public Vendor(){};

		/**
		 * @return the vendorId
		 */
		public String getVendorId() {
			return vendor_id.get();
		}
		/**
		 * @return the comp_reg_no
		 */
		public Integer getComp_reg_no() {
			return company_reg_no.get();
		}
		/**
		 * @return the address
		 */
		public String getAddress() {
			return vendor_address.get();
		}
		/**
		 * @return the contact_number
		 */
		public String getContact_number() {
			return vendor_contact_no.get();
		}
		/**
		 * @return the website
		 */
		public String getWebsite() {
			return vendor_website.get();
		}
		/**
		 * @return the certi_issue
		 */
		public String getCerti_issue() {
			return certificate_issue_date.get();
		}
		/**
		 * @return the certi_validity
		 */
		public String getCerti_validity() {
			return certificate_validity_date.get();
		}

//		/**
//		 * @return the empCount
//		 */
//		public Integer getEmpCount() {
//			return empCount.get();
//		}
//		/**
//		 * @return the yoe
//		 */
//		public Integer getYoe() {
//			return yoe.get();
//		}
		/**
		 * @return the certi
		 */
		public String getCerti() {
			return certificate.get();
		}


		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "Vendor [vendor_id=" + vendor_id + ", company_reg_no=" + company_reg_no + ", vendor_address="
					+ vendor_address + ", vendor_contact_no=" + vendor_contact_no + ", vendor_website=" + vendor_website
					+ ", certificate_issue_date=" + certificate_issue_date + ", certificate_validity_date="
					+ certificate_validity_date + ", certificate=" + certificate + "]";
		}
		
		
		
		
}
