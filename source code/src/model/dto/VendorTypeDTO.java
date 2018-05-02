package model.dto;

/**
 * @author hpatel111
 *vendorType DTO object that contains 
 */
public class VendorTypeDTO {
		private String vendorType;
		private String vendorAmount;
		private boolean status;
		
		
		public VendorTypeDTO(String vendorType, String vendorAmount) {
			super();
			this.vendorType = vendorType;
			this.vendorAmount = vendorAmount;
		}
		@Override
		public String toString() {
			return "VendorTypeDTO [vendorType=" + vendorType + ", vendorAmount=" + vendorAmount + "]";
		}
		/**
		 * @return
		 * to check the VendorAmount
		 */
		public String getVendorAmount() {
			return vendorAmount;
		}
		/**
		 * @param vendorAmount
		 * to check vendorAmount
		 */
		public void setVendorAmount(String vendorAmount) {
			this.vendorAmount = vendorAmount;
		}
		/**
		 * @return
		 * to check the vendorType
		 */
		public String getVendorType() {
			return vendorType;
		}
		/**
		 * @param vendorType
		 * to check vendorType
		 */
		public void setVendorType(String vendorType) {
			this.vendorType = vendorType;
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
		
		 
}
