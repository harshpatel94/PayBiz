package model.dto;


/**
 * @author hpatel111
 *update response DTO for bill payment
 */
public class AfterBillPaymentUpdateResponse {
	
		private boolean status;

		/**
		 * default constructor
		 */
		public AfterBillPaymentUpdateResponse() {
			super();
		}

		/**
		 * @param status
		 * parameterized constructor
		 */
		public AfterBillPaymentUpdateResponse(boolean status) {
			super();
			this.status = status;
		}

		/**
		 * @return
		 * get the status
		 */
		public boolean isStatus() {
			return status;
		}

		/**
		 * @param status
		 * set status
		 */
		public void setStatus(boolean status) {
			this.status = status;
		}
		

	}
