package constants;

/**
 * @author hpatel111 it will contain all the database constants
 */
public class DBconstants {

	/**
	 * it will hold the driver
	 */

	public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * Setting Url path for server
	 */

	public static final String DATABASE_PATH = "jdbc:mysql://www.papademas.net/fp?autoReconnect=true&useSSL=false";

	/**
	 * Username of database server
	 */

	public static final String USERNAME = "dbfp";

	/**
	 * Password of the database
	 */
	public static final String PASSWORD = "510";

	/**
	 * Login query 
	 */
	public static final String LOGIN = "select * from login_h where user_id= ? and password= ? and priviledge = ?;";
	
	/**
	 * Finding the last login_id
	 */
	public static final String LOGIN_LAST_ID = "select login_id from fp.login_h order by login_id desc LIMIT 1;";
	

	/**
	 * query to fetch country Id 
	 */
	public static final String COUNTRY_ID = "Select country_id from country_db where country = ?  and state = ? ;";

	/**
	 * query to fetch country Id
	 */
	public static final String COUNTRY_STATE = "Select country , state from country_db where country_id = ? ;";

	/**
	 * query to insert vendor details into database
	 */
	public static final String REGISTER_VENDOR = "Insert into vendor  values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	/**
	 * query to select vendor
	 */
	public static final String SELECT_VENDOR = "select * from vendor where vendor_id= ? ;";

	/**
	 * query to select vendor
	 */
	public static final String DELETE_VENDOR = "delete from fp.vendor where vendor_id= ?;";

	/**
	 * query to select vendor name type
	 */
	public static final String SELECT_VENDOR_NAME_TYPE = "select vendor_name,vendor_type from company_db where company_reg_no=?";

	/**
	 * query to update vendor
	 */
	public static final String UPDATE_COMPANY_DB = "update company_db set vendor_name=?, vendor_type=? where company_reg_no=?";
	
	/**
	 * query to update vendor
	 */
	public static final String UPDATE_VENDOR = "update vendor "
			+ "set company_reg_no=?," + "vendor_address=?,"
			+ "vendor_email_id=?," + "vendor_contact_no=?,"
			+ "vendor_website=?," + "certificate_issue_date=?,"
			+ "certificate_validity_date=?," + "employee_count=?,"
			+ "customer_count=?," + "year_of_establishment=?,"
			+ "certificate=?," + "country_id=? " + "where vendor_id= ?";
	
	/**
	 * query to insert values into company_db
	 */
	public static final String INSERT_COMPANY_DB = "insert into company_db values(?,?,?)";

	/**
	 * query to insert values into company_db
	 */
	public static final String INSERT_AMOUNT_DB = "insert into amount_db values(?,?,?)";
	
	/**
	 * query to register customer
	 */
	public static final String REGISTER_CUSTOMER = "insert into customer_h values(?,?,?,?,?,?,?,?,?,?,?,?,?) ;";

	/**
	 * query to insert into login table
	 */
	public static final String INSERT_LOGIN = "insert into login_h values(?,?,?,?) ;";

	/**
	 * query to feedback
	 */
	public static final String INSERT_FEEDBACK = "insert into fp.feedback values(?,?) ;";
	
	
	/**
	 * query to select customer
	 */
	public static final String SELECT_CUSTOMER = "select * from customer_h where customer_id= ?";

	/**
	 * query to Update customer
	 */
	public final static String UPDATE_CUSTOMER = "update customer_h SET "
			+ "customer_name = ?,"
			+ "customer_address = ?, customer_contact = ?, country_id = ?, customer_email_id = ?,"
			+ "identification_document = ?, document_detail_number = ?, vendor_type = ?,"
			+ "card_no = ?, customer_balance = ?  where customer_id = ? ;";

	/**
	 * query to select customer
	 */
	public static final String DELETE_CUSTOMER = "delete from fp.customer_h where customer_id= ?";

	/**
	 * query to Fetch the customer details for payment
	 */
	public static final String FETCH_CUSTOMER_PAY = "select card_no from customer_h where customer_id= ? and vendor_type= ?;";

	/**
	 * query to register customer
	 */
	public static final String UPDATE_CUSTOMER_PAY = "Update customer_h set customer_balance= ? where card_no= ? ;";

	public static final String INSERT_VENDOR_TYPE = "insert into fp.amount_db values(?,?)";
}
